package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

public interface ItemCartService {
	List<EasyUITreeNode> getItemCatList(Long parentId);
}
