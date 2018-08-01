package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCartServiceImpl implements com.taotao.service.ItemCartService {
	
	@Autowired
	private TbItemCatMapper ibItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		//根据父节点id查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = ibItemCatMapper.selectByExample(example);
		//数据转换
		List<EasyUITreeNode> resultList = new  ArrayList();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode data = new EasyUITreeNode();
			data.setId(tbItemCat.getId());
			data.setText(tbItemCat.getName());
			data.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(data);
		}
		return resultList;
	}
}
