package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.mapper.OrderBillItemMapper;
import com._520it.wms.mapper.OrderBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.ActionContextUtil;

public class OrderBillServiceImpl implements IOrderBillService {
	@Setter
	private OrderBillMapper mapper;

	@Setter
	private OrderBillItemMapper itemMapper;

	public void delete(Long id) {
		itemMapper.deleteByBillId(id);
		mapper.delete(id);
	}

	public void save(OrderBill bill) {
		// 设置输入人
		bill.setInputUser(ActionContextUtil.getCurrentUser());
		// 设置输入的时间
		bill.setInputTime(new Date());
		// 设置审核状态
		bill.setStatus(OrderBill.STATUS_NOMAL);
		// 设置单价和数量
		BigDecimal totalNumber = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (OrderBillItem item : bill.getItems()) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber())
					.setScale(2, RoundingMode.HALF_UP);
			item.setAmount(amount);
			totalNumber = totalNumber.add(item.getNumber());
			totalAmount = totalAmount.add(item.getAmount());
		}
		bill.setTotalAmount(totalAmount);
		bill.setTotalNumber(totalNumber);
		mapper.save(bill);
		for (OrderBillItem item : bill.getItems()) {
			item.setBillId(bill.getId());
			itemMapper.save(item);
		}
	}

	public OrderBill get(Long id) {
		return mapper.get(id);
	}

	public List<OrderBill> list() {
		return mapper.list();
	}

	public void update(OrderBill bill) {
		OrderBill old = mapper.get(bill.getId());
		if (old.getStatus() == OrderBill.STATUS_NOMAL) {
			itemMapper.deleteByBillId(bill.getId());
			BigDecimal totalNumber = BigDecimal.ZERO;
			BigDecimal totalAmount = BigDecimal.ZERO;
			for (OrderBillItem item : bill.getItems()) {
				BigDecimal amout = item.getCostPrice()
						.multiply(item.getNumber())
						.setScale(2, RoundingMode.HALF_UP);
				item.setAmount(amout);
				totalNumber = totalNumber.add(item.getNumber());
				totalAmount = totalAmount.add(amout);
				item.setBillId(bill.getId());
				itemMapper.save(item);
			}
			bill.setTotalAmount(totalAmount);
			bill.setTotalNumber(totalNumber);
			mapper.update(bill);
		}
	}

	@Override
	public PageResult pageQuery(OrderBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<OrderBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),
				qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}

	@Override
	public void audit(Long billId) {
		// 获取订单对象
		OrderBill bill = mapper.get(billId);
		if (bill.getStatus() == OrderBill.STATUS_NOMAL) {
			// 审核人
			bill.setAuditor(ActionContextUtil.getCurrentUser());
			// 审核时间
			bill.setAuditTime(new Date());
			// 设置已审核
			bill.setStatus(OrderBill.STATUS_AUDIT);
			// 保存数据
			mapper.updateStatus(bill);
		}
	}
}
