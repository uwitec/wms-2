package com._520it.wms.domain;


import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class StockIncomeBill extends BaseDomain {

    public static final int STATUS_NOMAL=0;//待审核
    public static final int STATUS_AUDIT=1;//已审核

    @ObjectProp("入库单编号编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("入库数量")
    private BigDecimal totalNumber;
    @ObjectProp("入库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("录入人")
    private Employee inputUser;
    @ObjectProp("入库时间")
    private Date inputTime;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("审核状态")
    private int status=STATUS_NOMAL;
    @ObjectProp("仓库")
    private Depot depot;
    @ObjectProp("供应商")
    private Supplier supplier;

    private List<StockIncomeBillItem> items = new ArrayList<>();

}
