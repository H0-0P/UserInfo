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
 * ��֤�빤����
 */
public class VerifyCode {
	//��װ������ͼƬ�ķ���
	public static Map<String, BufferedImage> getMap(){
		//����һ����ͼƬ
		BufferedImage image = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		//���һ�����ʶ���
		Graphics g = image.getGraphics();
		// �趨����ɫ  
	    g.setColor(getRandColor(200, 250));  
		//�����ɫ
		g.fillRect(0, 0, 99, 39);
		//���úͻ��ʶ�����ɫ�����Ʊ߿�
		g.setColor(Color.black);
		g.drawRect(0, 0, 100, 40);
		//����һ��StringBuilder����ͼƬ��������
		StringBuilder st = new StringBuilder();
		//��������ĸ���ɫ
		Random r = new Random();
		for(int i = 0; i < 4; i++){
			//��ɫ���RGB
			Color c = new Color(r.nextInt(200), r.nextInt(255), r.nextInt(255));
			//�������
			int code = r.nextInt(10);
			//������ɫ
			g.setColor(c);
			//��������
			Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
			g.setFont(f);
			//���Ƶ�ͼƬ����
			g.drawString(code+"", 20*i+10, 30);
			//�������ɵ�ͼƬ
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

	//��װһ��������ͼƬ�ķ���
	public static BufferedImage getImage(Map<String, BufferedImage> map){
		Set<Entry<String, BufferedImage>> set = map.entrySet();
		Iterator<Entry<String, BufferedImage>> it = set.iterator();
		Entry<String, BufferedImage> entry = it.next();
		return entry.getValue();
	}
	
	//��װһ�����������ֵķ���
	public static String getCode(Map<String, BufferedImage> map){
		Set<Entry<String, BufferedImage>> set = map.entrySet();
		Iterator<Entry<String, BufferedImage>> it = set.iterator();
		Entry<String, BufferedImage> entry = it.next();
		return entry.getKey();
	}
}
