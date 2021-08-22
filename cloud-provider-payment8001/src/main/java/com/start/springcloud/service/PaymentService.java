package com.start.springcloud.service;

import com.start.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int add(Payment payment);

    Payment getPaymentById(Long id);
}
