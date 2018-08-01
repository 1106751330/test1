package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCatList(Long parentId);
	TaotaoResult insertContentCat(long parendId,String name);
}
