package com.start.springcloud.controller;

import com.start.springcloud.entities.CommonResult;
import com.start.springcloud.entities.Payment;
import com.start.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(path = "/payment")
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
}
