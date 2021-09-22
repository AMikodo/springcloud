package com.start.springcloud.controller;

import com.start.springcloud.entities.CommonResult;
import com.start.springcloud.entities.Payment;
import com.start.springcloud.service.PaymentService;
import com.start.springcloud.service.impl.TestService;
import jodd.time.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    private TestService testService;


    @Value("${server.port}")
    private String port;

    @PostMapping(path = "/payment/create")
    public CommonResult add(@RequestBody Payment payment) {
        int result = paymentService.add(payment);
        log.info("result is " + result);
        if (result > 0) {
            return new CommonResult(200, "insert success" + port);
        } else {
            return new CommonResult(400, "insert failed" + port);
        }
    }

    @GetMapping(path = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("result is " + payment);
        if (null != payment) {
            return new CommonResult(200, "query success" + port, payment);
        } else {
            return new CommonResult(400, "query failed" + port, payment);
        }


    }
    @GetMapping("payment/test")
    public CommonResult get() {
        Map<String, String> map = testService.test();
        log.info("result is " + map);
        return new CommonResult(200, "success", map);
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        JSONObject jsonObject = new JSONObject();
//
//
//
//
//
//
//        // fastjson string 转对象
//        List<Payment> list = new ArrayList<>();
//        list.add(new Payment(12, "12"));
//        list.add(new Payment(23, "23"));
//        list.add(new Payment(34, "34"));
//        list.contains(new Payment(23, "23"));
//        String jsonArray = JSONObject.toJSONString(list);
//
////        JSONObject jsonObject = new JSONObject();
//        String json = "{\n" +
//                "  \"id\": \"1111\",\n" +
//                "  \"serial\": \"234\"\n" +
//                "}";
//        Payment payment = JSON.parseObject(json, Payment.class);
//        System.out.println(payment.getId());
//        Payment payment1 = JSONObject.parseObject(json, Payment.class);
//        System.out.println(payment1.getId());
//        // java 对象 转 String
//        System.out.println(JSON.toJSONString(payment));
//        System.out.println(JSONObject.toJSONString(payment));
//
//        JSONArray jsonArray1 = JSONObject.parseArray(jsonArray);
//        jsonArray1.forEach(value -> {
//            System.out.println(value.toString());
//        });
//        JsonObject jsonObject1 = new JsonObject();
//        JSONObject netDataInfo = new JSONObject();
//
//        //Jackson  string 转对象
//        ObjectMapper objectMapper = new ObjectMapper();
//        Payment payment2 = objectMapper.readValue(json, Payment.class);
//        System.out.println(payment2.getId());
//        System.out.println(objectMapper.writeValueAsString(payment2));
//        List<Payment> list1 = Arrays.asList(objectMapper.readValue(jsonArray, Payment[].class));
//        list1.forEach(value -> {
//            System.out.println(value.toString());
//        });
//        list1 = objectMapper.readValue(jsonArray, new TypeReference<List<Payment>>() {
//        });
//        list1.forEach(value -> {
//            System.out.println(value.toString());
//        });
//        objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
//        }).forEach((key, vaule) -> {
//            System.out.println("begin ");
//            System.out.println(key);
//            System.out.println(vaule);
//            System.out.println("end");
//        });
//
//        String id = "{\n" +
//                "  \"id\": 1111,\n" +
//                "  \"serial\": \"234\",\n" +
//                "  \"size\": \"333\"\n" +
//                "}";
//        // 针对多余 字段进行过滤不报错
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        System.out.println(objectMapper.readValue(id, Payment.class));
//        Payment payment3 = new Payment();
//        payment3.setId(12);
//        System.out.println("=========");
//        System.out.println(objectMapper.writeValueAsString(payment3));
//
//        //Gson  String 转对象
//        Gson gson = new GsonBuilder().create();
//        System.out.println(gson.fromJson(json, Payment.class).getId());
//        System.out.println(gson.toJson(payment));
//        Type type = new TypeToken<ArrayList<Payment>>(){}.getType();
//        gson.fromJson(jsonArray, type);
//    }

    public static void main(String[] args) {
        String lockKey = "paymentKey";
        long waitTimeout = 30;
        long leaseTime = 100;
        // 1.构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress("redis://123.60.34.168:6379").setPassword("fs341225").setDatabase(0);
        // 2.构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
        // 3.获取锁对象实例（无法保证是按线程的顺序获取到）
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            /**
             * 4.尝试获取锁
             * waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
             * leaseTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
             */
            boolean res = rLock.tryLock(waitTimeout, leaseTime, TimeUnit.SECONDS);
            if (res) {
                //成功获得锁，在这里处理业务
                System.out.println("get lock success ");
            }
            // 可重入锁, 测试是否可重入
            rLock.tryLock(waitTimeout, leaseTime, TimeUnit.SECONDS);
            if (res) {
                System.out.println("可重入锁获取第二次");
            }
        } catch (Exception e) {
            throw new RuntimeException("aquire lock fail");
        } finally {
            //无论如何, 最后都要解锁
            rLock.unlock();
            rLock.unlock();
            redissonClient.shutdown();
        }
    }
}
