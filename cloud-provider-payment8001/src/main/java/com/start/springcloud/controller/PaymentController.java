package com.start.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.start.springcloud.entities.CommonResult;
import com.start.springcloud.entities.Payment;
import com.start.springcloud.service.PaymentService;
import com.start.springcloud.service.impl.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    private TestService testService;

    @PostMapping(path = "/payment/create")
    public CommonResult add(@RequestBody Payment payment) {
        int result = paymentService.add(payment);
        log.info("result is " + result);
        return new CommonResult(result, "success");
    }

    @GetMapping(path = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("result is " + payment);
        return new CommonResult(200, "success", payment);
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
}
