package com.hjl.service.impl;

import com.hjl.service.AopTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author ：hjl
 * @date ：2019/11/1 13:32
 * @description：
 * @modified By：
 */
@Service
public class AopTestServiceImpl implements AopTestService {
    private static final Logger LOG = LoggerFactory.getLogger(AopTestServiceImpl.class);

    @Override
    public void test() {
        LOG.info("===aop test method ====");
    }
}
