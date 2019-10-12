package com.hjl.service.impl;

import com.hjl.dao.XmlManageDao;
import com.hjl.entity.SecDef;
import com.hjl.service.XmlManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/11 10:02
 * @description：
 * @modified By：
 */
@Service
public class XmlManageServiceImpl implements XmlManageService {
    private static final Logger LOG = LoggerFactory.getLogger(XmlManageServiceImpl.class);
    @Autowired
    private XmlManageDao xmlManageDao;

    @Override
    @Cacheable("test")
    public List<SecDef> queryAll() {
        LOG.info("queryAll......");
        return xmlManageDao.queryAll();
    }
}
