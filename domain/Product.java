package com._520it.wms.domain;

import generator.ObjectProp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("商品管理")
public class Product extends BaseDomain {

	@ObjectProp("商品名称")
	private String name;
	@ObjectProp("商品编码")
	private String sn;
	@ObjectProp("成本价格")
	private BigDecimal costPrice;
	@ObjectProp("销售价格")
	private BigDecimal salePrice;
	@ObjectProp("货品图片路径")
	private String imagePath;
	@ObjectProp("备注")
	private String intro;

	@ObjectProp("品牌")
	private Brand brand;

	public String getJsonString() {
		Map<String, Object> json = new HashMap();
		json.put("id", id);
		json.put("name", name);
		json.put("costPrice", costPrice);
		json.put("brandName", brand.getName());
		return JSON.toJSONString(json);
	}

	public String getSmallImagePath() {
		// 截取点前面的字符
		String fileName = imagePath.substring(0, imagePath.lastIndexOf("."))
				+ "_small";
		// 截取拓展名
		String ext = imagePath.substring(imagePath.lastIndexOf("."),
				imagePath.length());
		return fileName + ext;
	}
}
