package com.start.springcloud.controller;

import com.start.springcloud.entities.CommonResult;
import com.start.springcloud.entities.PaymentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("consumer/payment/create")
    public CommonResult<PaymentVo> query(@RequestBody PaymentVo paymentVo) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", paymentVo, CommonResult.class);
    }

    @PostMapping("consumer/payment/{id}")
    public CommonResult<PaymentVo> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }
}
