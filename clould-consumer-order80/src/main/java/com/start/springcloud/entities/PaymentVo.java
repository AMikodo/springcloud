package com.start.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVo implements Serializable {
    private Long id;

    private String serial;

    public PaymentVo(String serial) {
        this.serial = serial;
    }
}
