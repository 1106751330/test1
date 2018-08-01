package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
/**
 * 内容管理service
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		//添加pojo属性
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		//把数据插入到内容表中
		tbContentMapper.insert(content);
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentList(long id) {
		//根据分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		return tbContentMapper.selectByExample(example);
	}

}
