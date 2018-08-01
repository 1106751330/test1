package com.taotao.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;
 
/**
 * 图片上传解析
 * */

@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile) {
		//接收上传的文件
		try {
			byte[] content = uploadFile.getBytes(); 
			//获取文件扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			//把文件上传到服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/fast_dfs.conf");
			//上传成功返回的图片url
			String url = fastDFSClient.uploadFile(content,ext);
			//创建返回结果对象 
			Map result = new HashMap<>();
			result.put("error",0);
			result.put("url", IMAGE_SERVER_URL+url);
			//返回结果
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error",0);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
	}
}