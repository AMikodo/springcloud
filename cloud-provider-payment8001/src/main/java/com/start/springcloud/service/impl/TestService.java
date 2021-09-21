package com.start.springcloud.service.impl;

import com.start.springcloud.util.MyAnn;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    @MyAnn
    public Map test(){
        Map<String,String>  map=new HashMap<>();
        map.put("t1","我是在Service设置的值");
        return map;
    }
}
