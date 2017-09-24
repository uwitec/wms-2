package com._520it.wms.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms.util.DateUtil;

@Setter@Getter
public class StockIncomeBillQueryObject extends QueryObject {

    private Long depotId=-1L;
    private int status=-1;
    private Date beginDate;
    private Date endDate;

    public  Date getEndDate(){
    	return endDate!=null?DateUtil.getEndDate(endDate):null;
    }
}
