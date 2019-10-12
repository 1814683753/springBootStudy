package com.hjl.dao;

import com.hjl.entity.SecDef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/11 9:40
 * @description：
 * @modified By：
 */
@Repository
public interface XmlManageDao {
    /**
     * 查询所有
     * @return
     */
    List<SecDef> queryAll();
}
