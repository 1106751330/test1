package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 内容管理的服务
 *
 */
public interface ContentService {
	TaotaoResult insertContent(TbContent content);
	List<TbContent> getContentList(long id);
}
