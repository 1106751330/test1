package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCartService;

/**
 * 商品分类管理
 * @author quan
 *
 */
@Controller
public class ItemCartController {
	
	@Autowired
	private ItemCartService itemCartService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		return itemCartService.getItemCatList(parentId);
	}
}