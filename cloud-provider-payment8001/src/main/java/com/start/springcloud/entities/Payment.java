package com.start.springcloud.entities;

import lombok.*;

import java.io.Serializable;

/**
 * @author 86130
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private long id;

    private String serial;

}
