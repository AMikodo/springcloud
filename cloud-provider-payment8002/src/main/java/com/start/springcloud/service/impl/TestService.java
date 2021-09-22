package com.start.springcloud.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    public Map test(){
        Map<String,String>  map=new HashMap<>();
        map.put("t1","我是在Service设置的值");
        return map;
    }
}
