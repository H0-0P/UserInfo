package com.ctgu.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ctgu.utils.VerifyCode;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
@RequestMapping("/user")
public class VerifyCodeController {
	
	@RequestMapping("/getVerifycode.do")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
		response.setContentType("image/jpeg");
		Map<String, BufferedImage> map = VerifyCode.getMap();
		String code = VerifyCode.getCode(map);
		BufferedImage image = VerifyCode.getImage(map);
		//将code绑定到session
		request.getSession().setAttribute("code", code);
		//image可以压缩当做一个输出流
		OutputStream os = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(image);
	}
	
	@RequestMapping("/checkCode.do")
	public @ResponseBody ModelAndView CheckCode(HttpServletRequest request) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		String code1 = request.getParameter("code");
		//获得服务器生成的code值
		String code2 = (String) request.getSession().getAttribute("code");
		if(code1.equals(code2)){
			map.put("msg", "success");
		} else{
			map.put("msg", "error");
		}
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
}
