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
		//�ļ��ϴ������ط�����
		//ʵ��������
		DiskFileItemFactory disk = new DiskFileItemFactory();
		//������������upload
		ServletFileUpload upload = new ServletFileUpload(disk);
		//��������ͷ����request
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		//ѭ��������ȡ�ļ��ϴ�FileItem
		for(int i = 0; i < list.size(); i++){
			FileItem item = list.get(i);
			//�õ��ļ�����
			String name = item.getName();
			//����һ������Ŀ¼
			String path = request.getServletContext().getRealPath("upLoad");
			String path1 = path.substring(path.lastIndexOf("\\")+1);
			//upload\pic_id\�����ļ���
			path1 = path + File.separator + "pic_" + id;
			//����Ŀ¼
			File file = new File(path1);
			file.mkdirs();
			file = new File(path1, name);
			
			String pname = "pic_" + id + "/" + name;
			p.setPicName(pname);
			p.setUserId(id);
			try {
				item.write(file);
				//����ͼƬ�����ݿ�
				pic_dao.save(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//�ϴ���
		List<Picture> pList = pic_dao.findByUserId(id);
		request.getSession().setAttribute("pList", pList);
		return "redirect:/user/userDetail.do?id="+id;
	}
}
