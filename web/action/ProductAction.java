package com._520it.wms.web.action;

import java.io.File;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.FileUploadUtil;

import freemarker.template.utility.StringUtil;

public class ProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductService service;
	@Setter
	private IBrandService brandService;

	@Getter
	private ProductQueryObject qo = new ProductQueryObject();

	@Getter
	private Product product = new Product();

	@Setter
	private File pic;
	@Setter
	private String picFileName;

	@RequiredPermission("商品管理列表")
	public String execute() {
		try {
			List<Brand> brand = brandService.list();
			put("brands", brand);
			put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("商品管理编辑")
	public String input() {
		try {
			List<Brand> brand = brandService.list();
			put("brands", brand);
			if (product.getId() != null) {
				product = service.get(product.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("商品管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (pic != null && StringUtils.hasLength(product.getImagePath())) {
				FileUploadUtil.deleteFile(product.getImagePath());
			}
			if (pic != null) {
				String imgPath = FileUploadUtil.uploadFile(pic, picFileName);
				product.setImagePath(imgPath);
			}
			if (product.getId() == null) {

				service.save(product);
				addActionMessage("保存成功");
			} else {
				service.update(product);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("商品管理删除")
	public String delete() throws Exception {
		try {
			if (product.getId() != null) {
				if (product.getImagePath() != null) {
					FileUploadUtil.deleteFile(product.getImagePath());
				}
				service.delete(product.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String selectProductList() {
		List<Brand> brand = brandService.list();
		put("brands", brand);
		put("result", service.pageQuery(qo));
		return "selectProductList";
	}
}
