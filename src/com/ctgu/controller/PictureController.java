package com.ctgu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctgu.dao.PictureDao;
import com.ctgu.pojo.Picture;

@Controller
@RequestMapping("/user")
public class PictureController {
	
	@Resource
	private PictureDao pic_dao;
	@Resource
	Picture p;
	
	@RequestMapping("/msgUpload.do")
	public String MsgUpload(@RequestParam("id")Integer id, HttpServletRequest request){
		List<FileItem> list = new ArrayList<FileItem>();
		//文件上传到本地服务器
		//实例化参数
		DiskFileItemFactory disk = new DiskFileItemFactory();
		//创建解析对象upload
		ServletFileUpload upload = new ServletFileUpload(disk);
		//解析请求头对象request
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		//循环迭代读取文件上传FileItem
		for(int i = 0; i < list.size(); i++){
			FileItem item = list.get(i);
			//得到文件名称
			String name = item.getName();
			//创建一个本地目录
			String path = request.getServletContext().getRealPath("upLoad");
			String path1 = path.substring(path.lastIndexOf("\\")+1);
			//upload\pic_id\传入文件名
			path1 = path + File.separator + "pic_" + id;
			//创建目录
			File file = new File(path1);
			file.mkdirs();
			file = new File(path1, name);
			
			String pname = "pic_" + id + "/" + name;
			p.setPicName(pname);
			p.setUserId(id);
			try {
				item.write(file);
				//保存图片到数据库
				pic_dao.save(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//上传完
		List<Picture> pList = pic_dao.findByUserId(id);
		request.getSession().setAttribute("pList", pList);
		return "redirect:/user/userDetail.do?id="+id;
	}
}
