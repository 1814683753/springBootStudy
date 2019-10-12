package com.hjl.entity;

import lombok.Data;

/**
 * @author ：hjl
 * @date ：2019/10/11 9:49
 * @description：
 * @modified By：
 */
@Data
public class SecDef {
    private Integer id;
    private String desc;
    private String securityTypeId;
    private String termToMaturityString;
    private String centraQuoteBondIndic;
    private String instrmtId;
    private String instrmtSym;
    private String ptyNoPartyIds;
    private String ptyId;
    private String ptyR;
}
