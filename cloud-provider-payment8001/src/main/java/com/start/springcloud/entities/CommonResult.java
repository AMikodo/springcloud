package com.start.springcloud.entities;

import lombok.*;

/**
 * @author 86130
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResult (Integer code, String message) {
        this(code, message, null);
    }
}
