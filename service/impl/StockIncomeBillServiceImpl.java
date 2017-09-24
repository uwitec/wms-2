package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.mapper.StockIncomeBillItemMapper;
import com._520it.wms.mapper.StockIncomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.ActionContextUtil;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Setter
	private StockIncomeBillMapper mapper;

	@Setter
	private StockIncomeBillItemMapper stockItemMapper;

	public void delete(Long id) {
		stockItemMapper.deleteByStockbillId(id);
		mapper.delete(id);
	}

	public void save(StockIncomeBill bill) {
		bill.setInputUser(ActionContextUtil.getCurrentUser());
		bill.setInputTime(new Date());
		bill.setStatus(StockIncomeBill.STATUS_NOMAL);
		BigDecimal totalAmout = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;

		List<StockIncomeBillItem> items = bill.getItems();
		for (StockIncomeBillItem item : items) {
			BigDecimal amout = item.getCostPrice().multiply(item.getNumber())
					.setScale(2, RoundingMode.HALF_UP);
			item.setAmount(amout);
			totalNumber = totalNumber.add(item.getNumber());
			totalAmout = totalAmout.add(item.getAmount());
		}
		bill.setTotalAmount(totalAmout);
		bill.setTotalNumber(totalNumber);
		mapper.save(bill);
		for (StockIncomeBillItem item : items) {
			item.setBillId(bill.getId());
			stockItemMapper.save(item);
		}
	}

	public StockIncomeBill get(Long id) {
		return mapper.get(id);
	}

	public List<StockIncomeBill> list() {
		return mapper.list();
	}

	public void update(StockIncomeBill bill) {
		StockIncomeBill old = mapper.get(bill.getId());
		if (old.getStatus() == StockIncomeBill.STATUS_NOMAL) {
			stockItemMapper.deleteByStockbillId(bill.getId());
			BigDecimal totalNumber = BigDecimal.ZERO;
			BigDecimal totalAmount = BigDecimal.ZERO;
			for (StockIncomeBillItem item : bill.getItems()) {
				BigDecimal amount = item.getCostPrice()
						.multiply(item.getNumber())
						.setScale(2, RoundingMode.HALF_UP);
				item.setAmount(amount);
				totalNumber = totalNumber.add(item.getNumber());
				totalAmount = totalAmount.add(amount);
				item.setBillId(bill.getId());
				stockItemMapper.save(item);
			}
			bill.setTotalNumber(totalNumber);
			bill.setTotalAmount(totalAmount);
			mapper.update(bill);
		}
	}

	@Override
	public PageResult pageQuery(StockIncomeBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<StockIncomeBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),
				qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}

	@Override
	public void audit(Long StockBillId) {
		// 获取仓库录入对象
		StockIncomeBill incomeBill = mapper.get(StockBillId);
		if (incomeBill.getStatus() == StockIncomeBill.STATUS_NOMAL) {
			incomeBill.setAuditor(ActionContextUtil.getCurrentUser());
			incomeBill.setAuditTime(new Date());
			incomeBill.setStatus(StockIncomeBill.STATUS_AUDIT);
			mapper.updateStatus(incomeBill);
		}

	}
}
