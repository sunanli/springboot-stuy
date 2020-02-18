package com.tangwh.config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Info implements InfoContributor {


    @Override
    public void contribute(org.springframework.boot.actuate.info.Info.Builder builder) {
        Map<String,Object> info = new HashMap<>();
        info.put("email", "2678691035@qq.com");
        builder.withDetail("author",info);

    }
}
