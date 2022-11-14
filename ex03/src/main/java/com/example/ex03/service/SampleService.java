package com.example.ex03.service;

import com.example.ex03.aspect.LogStatus;
import com.example.ex03.aspect.Plus10;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SampleService {

    @Plus10
    @LogStatus
    public Integer doAdd(String str1, String str2) throws Exception{ //PointCut
        log.info("핵심 로직");
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}
