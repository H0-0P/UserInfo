package com.ctgu.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * 验证码工具类
 */
public class VerifyCode {
	//封装和生成图片的方法
	public static Map<String, BufferedImage> getMap(){
		//定义一个空图片
		BufferedImage image = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		//获得一个画笔对象
		Graphics g = image.getGraphics();
		// 设定背景色  
	    g.setColor(getRandColor(200, 250));  
		//填充颜色
		g.fillRect(0, 0, 99, 39);
		//设置和画笔对象颜色，绘制边框
		g.setColor(Color.black);
		g.drawRect(0, 0, 100, 40);
		//构建一个StringBuilder保存图片内容数据
		StringBuilder st = new StringBuilder();
		//随机生成四个颜色
		Random r = new Random();
		for(int i = 0; i < 4; i++){
			//颜色随机RGB
			Color c = new Color(r.nextInt(200), r.nextInt(255), r.nextInt(255));
			//数字随机
			int code = r.nextInt(10);
			//重新上色
			g.setColor(c);
			//设置字体
			Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
			g.setFont(f);
			//绘制到图片上面
			g.drawString(code+"", 20*i+10, 30);
			//保存生成的图片
			st.append(code);
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(st.toString(), image);
		return map;
	}
	
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();  
		if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b); 
	}

	//封装一个迭代出图片的方法
	public static BufferedImage getImage(Map<String, BufferedImage> map){
		Set<Entry<String, BufferedImage>> set = map.entrySet();
		Iterator<Entry<String, BufferedImage>> it = set.iterator();
		Entry<String, BufferedImage> entry = it.next();
		return entry.getValue();
	}
	
	//封装一个迭代出数字的方法
	public static String getCode(Map<String, BufferedImage> map){
		Set<Entry<String, BufferedImage>> set = map.entrySet();
		Iterator<Entry<String, BufferedImage>> it = set.iterator();
		Entry<String, BufferedImage> entry = it.next();
		return entry.getKey();
	}
}
