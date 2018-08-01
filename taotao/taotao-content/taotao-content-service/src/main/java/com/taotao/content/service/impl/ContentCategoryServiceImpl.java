package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理服务
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> lists = contentCategoryMapper.selectByExample(example);
		//返回结果List
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory category : lists) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	
	/*
	 * 添加内容分类
	 * */
	@Override
	public TaotaoResult insertContentCat(long parendId, String name) {
		//创建一个内容分类对象
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parendId);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		//1正常 2删除
		contentCategory.setStatus(1);
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		//插入节点
		contentCategoryMapper.insert(contentCategory);
		//判断父节点是否为叶子节点
		TbContentCategory parendNode = contentCategoryMapper.selectByPrimaryKey(parendId);
		if(!parendNode.getIsParent()) {
			parendNode.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parendNode);
		}
		return TaotaoResult.ok(contentCategory);
	}
}	