package com.product.service.impl;


import java.util.List;

import com.common.adapter.Base64Adapter;
import com.product.dao.impl.ProductDaoImpl;
import com.product.dao.intf.ProductDaoIntf;
import com.product.service.intf.ProductServiceIntf;
import com.product.vo.Product;

import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveBoxingDelegate;



public class ProductServiceImpl implements ProductServiceIntf{
	
	private ProductDaoIntf dao;
	private Base64Adapter base64;

	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
		base64 = new Base64Adapter();
	}
//	第二種寫法
//	@Override
//	public Product selectById(Product product) {
//		Integer id = product.getProdId();
//		Product prod = dao.selectById(id);
//		prod.setProdPicBase64(base64.Encoder(prod.getProdPic()));
//		return prod;
//	}
	@Override
	public Product prodEdit(Product prod) {
		
		final String name = prod.getProdName();
		final int price = prod.getPrice();
		final String intro = prod.getIntro();
		
		if("".equals(name)) {
			prod.setMessage("產品名未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getPrice() == null) {
			prod.setMessage("金額未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if("".equals(intro)) {
			prod.setMessage("產品簡介未輸入");
			prod.setSuccessful(false);
			return prod; 
		}
		if (dao.selectByProdName(name) !=null) {
			prod.setMessage("此產品名已建立過");
			prod.setSuccessful(false);
			return prod;
		}
		if (dao.update(prod) == false) {
			prod.setMessage("新增失敗,請聯絡管理員");
			prod.setSuccessful(false);
			return prod;
		}
		prod.setSuccessful(true);
		return prod;
	}
	
	public Product prodAddItem(Product prod){
		
		final String name = prod.getProdName();
		final String intro = prod.getIntro();
		final String picBase64 = prod.getProdPicBase64();
		
		if("".equals(name)) {
			prod.setMessage("產品名未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getPrice() == null) {
			prod.setMessage("金額未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if("".equals(intro)) {
			prod.setMessage("產品簡介未輸入");
			prod.setSuccessful(false);
			return prod; 
		}
		if("".equals(picBase64)) {
			prod.setMessage("圖片未存入");
			prod.setSuccessful(false);
			return prod; 
		} else {
			prod.setProdPic(base64.Decoder(picBase64));
		}
		if (dao.selectByProdName(name) !=null) {
			prod.setMessage("此產品名已建立過");
			prod.setSuccessful(false);
			return prod;
		}
		if (dao.insert(prod) == false) {
			prod.setMessage("新增失敗,請聯絡管理員");
			prod.setSuccessful(false);
			return prod;
		}
		prod.setSuccessful(true);
		return prod;
	}

	@Override
	public Product selectById(Integer prodId) {
		Product prod = dao.selectById(prodId);
		if (prod.getProdPic() != null) {
			prod.setProdPicBase64(base64.Encoder(prod.getProdPic()));
		}
		return prod;
	}

	@Override
	public List<Product> getAll() {
		return dao.selectAll();
	}

}
