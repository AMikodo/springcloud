package com.start.springcloud.dao;

import com.start.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int add(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

    Payment getPaymentById2(@Param("id") Long id);
}
