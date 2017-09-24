package com._520it.wms.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;

public class StockIncomeBillAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IStockIncomeBillService service;
	
	@Setter
	private IDepotService depotService;

	@Getter 
	private StockIncomeBillQueryObject qo=new StockIncomeBillQueryObject();

	@Getter
	private StockIncomeBill stockIncomeBill=new StockIncomeBill();

	@RequiredPermission("采购入库单列表")
	public String execute(){
		put("depots", depotService.list());
		try {
			put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("采购入库单编辑")
	public String input() {
		put("depots", depotService.list());
		try {
			if (stockIncomeBill.getId() != null) {

                stockIncomeBill = service.get(stockIncomeBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}
	@RequiredPermission("查看入库单")
	public String show() {
	put("depots", depotService.list());
	stockIncomeBill = service.get(stockIncomeBill.getId());
		return "show";
	}

	@RequiredPermission("采购入库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockIncomeBill.getId() == null) {
                service.save(stockIncomeBill);
				addActionMessage("保存成功");
            } else {
                service.update(stockIncomeBill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("采购入库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockIncomeBill.getId() != null) {
                service.delete(stockIncomeBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}
	@RequiredPermission("入库审核")
	public String audit()  throws  Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				service.audit(stockIncomeBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("审核失败");
		}
		return NONE;
	}

}
