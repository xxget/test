package com.lding.wiqs.general.web.validate;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStream;

/**
 * 验证码公共类
 * @author xxg
 *
 */
public abstract class AbstractValidateCode extends Randoms {
	protected Font font = new Font("Verdana",Font.ITALIC|Font.BOLD,28);
	protected int len = 8;
	protected int width = 150;
	protected int height = 40;
	private String text = null;
	
	/**
	 * 生成随机字符数组
	 * @return 字符数组
	 */
	protected char[] alphas() {
		char[] cs = new char[len];
		for (int i = 0; i < len; i++) {
			cs[i] = alpha();
		}
		text = new String(cs);
		return cs;
	}
	
	/**
	 * 生成随机颜色
	 */
	protected Color color(int fc,int bc) {
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r=num(fc,bc);
		int g=num(fc,bc);
		int b=num(fc,bc);
		return new Color(r,g,b);
	}
	
	/**
	 * 抽象输出方法
	 * @param os
	 */
	public abstract void out(OutputStream os);
	
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
