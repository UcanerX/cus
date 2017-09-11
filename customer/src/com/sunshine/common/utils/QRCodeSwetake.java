package com.sunshine.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/**
 * 创建二维码
 * 
 * @author 银杏
 *
 */
public class QRCodeSwetake {

	// QR码是正方形的，所以最好宽度和高度值相同
	// 生成二维码的宽度
	private static final int WIDTH = 500;

	// 生成二维码的高度
	private static final int HEIGHT = 500;

	/**
	 * 创建二维码
	 * 
	 * @param sms_info
	 *            信息内容 如：www.baidu.com
	 * @param filePath
	 *            输出路径
	 * @throws Exception
	 */
	public static void createrImage(String sms_info, String filePath) throws Exception {
		try {
			Qrcode testQrcode = new Qrcode();
			testQrcode.setQrcodeErrorCorrect('M');// 设置错误容错率 L(7%) M(15%) Q(25%)
													// H(30%)
			testQrcode.setQrcodeEncodeMode('B');// 设置编码模式 N数字 A英文 其他为Binary
												// (设置为A的时候生成的二维码异常)
			testQrcode.setQrcodeVersion(7);// 设置版本 (1~40)
			String testString = sms_info;
			byte[] d = testString.getBytes("UTF-8");
			System.out.println("信息编码后长度：" + d.length);

			// 限制最大字节数为120。容错率、编码模式、版本 这几个参数决定了这个二维码能存储多少字节。
			if (d.length > 0 && d.length < 120) {
				boolean[][] s = testQrcode.calQrcode(d);
				BufferedImage bi = new BufferedImage(s.length, s[0].length, BufferedImage.TYPE_BYTE_BINARY);
				Graphics2D g = bi.createGraphics();
				g.setBackground(Color.WHITE);
				g.clearRect(0, 0, s.length, s[0].length);
				g.setColor(Color.BLACK);
				int mulriple = 1; // 1像素
				for (int i = 0; i < s.length; i++) {
					for (int j = 0; j < s.length; j++) {
						if (s[j][i]) {
							g.fillRect(j * mulriple, i * mulriple, mulriple, mulriple);
						}
					}
				}
				g.dispose();// 释放此图形的上下文以及它使用的所有系统资源。调用 dispose 之后，就不能再使用
							// Graphics 对象。
				bi.flush();
				File f = new File(filePath);
				if (!f.exists()) {
					if(!f.getParentFile().exists()){
					    f.getParentFile().mkdirs();//创建文件夹
					}
					f.createNewFile();
				}

				// 图像缩放
				bi = resize(bi, WIDTH, HEIGHT);

				// 创建图片
				ImageIO.write(bi, "png", f);
			} else {
				System.out.println(
						"信息编码后长度过长，请升高版本值(比如 testQrcode.setQrcodeVersion(10))，或降低容错率（比如：testQrcode.setQrcodeErrorCorrect(L)）");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 图像缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 解析二维码
	 * 
	 * @param imgPath
	 *            二维码路径
	 * @return 二维码内容
	 */
	public static String decoderImage(String imgPath) {
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "UTF-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	public static void main(String args[]) throws Exception {

		String sms = "http://www.baidu.com";
		String fileName = "1";
		String filePath = "X:\\Users\\weifb\\Desktop\\photo\\" + fileName + ".png";

		createrImage(sms, filePath);

		String decodeMsg = decoderImage(filePath);
		System.out.println("二维码内容为：" + decodeMsg);
	}

}
