package com.taotao.fdfs;


import org.csource.fastdfs.ClientGlobal;



import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;


public class TestFastDfs {
	
	@Test 
	public void testUpload() throws Exception {
		//创建一个配置文件fastdfs.conf 配置文件的内容就是指定Trankerserver的地址
		//加载配置文件
		ClientGlobal.init("F:/taotao/taotao-manager-web/src/main/resources/resource/fast_dfs.conf");
		//创建一个TranckerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//通过TranckerClient对象获得TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建一个StorageServer的引用。nulliu可以
		StorageServer storageServer = null;
		//创建一个StorageClient对象。两个参数TrackerServer,StorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//使用StrorageClient对象上传文件
		//参数1。文件的绝对路径 参数2 文件扩展名 参数3 元数据（比如文件名）
		String[] upload_file = storageClient.upload_file("C:/Chrysanthemum.jpg","jpg",null);
		for (String string : upload_file) {
			System.out.println(string);
		}
	}

	@Test
	public void TestFastDfsClient() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("F:/taotao/taotao-manager-web/src/main/resources/resource/fast_dfs.conf");
		String uploadFile = fastDFSClient.uploadFile("C:/Hydrangeas.jpg");
		System.out.println(uploadFile);
	}
}
