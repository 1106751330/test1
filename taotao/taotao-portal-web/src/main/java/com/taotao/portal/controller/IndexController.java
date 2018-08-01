package com.taotao.portal.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;

/**
 * 商品首页系统展示
 */

@Controller
public class IndexController {

	@Value("${AD1_CONTENT}")
	private Long AD1_CONTENT;

	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	
	@Value("${AD1_WIDTHB}")
	private Integer AD1_WIDTHB;
	
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	
	@Value("${AD1_HEIGHTB}")
	private Integer AD1_HEIGHTB;
	
	@Autowired
	private ContentService contentSevice;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//取内容分类id，从属性文件中取
		//根据内容分类id查询内容列表
		List<TbContent> contentList = contentSevice.getContentList(AD1_CONTENT);
		List<Ad1Node> andNodeList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			Ad1Node node = new Ad1Node();
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			node.setSrc(tbContent.getPic());
			node.setSrcB(tbContent.getPic2());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHTB);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTHB);
			andNodeList.add(node);
			System.out.println(node.toString());
		}
		//转成成json数据
		String json = JsonUtils.objectToJson(andNodeList);
		model.addAttribute("ad1",json);
//		System.out.println(json.toString());
		return "index";
	}
	
	
}
