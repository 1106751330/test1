package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;


/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	/*
	 * 分业查询 
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		
		//取总记录数
		PageInfo<TbItem> pageinfo = new PageInfo<>(list);
		result.setTotal(pageinfo.getTotal());
		return result;
	}
	
	/*
	 * 添加商品的方法
	 * */
	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		//生成商品id
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		Date data = new Date();
		item.setCreated(data);
		item.setUpdated(data);
		itemMapper.insert(item);
		//商品描述
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(data);
		tbItemDesc.setUpdated(data);
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}
}