package com.mengke.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import jp.sourceforge.qrcode.QRCodeDecoder;

import com.swetake.util.Qrcode;

/**
 * QRCode实现生成二维码加logo与解码
 * @author slz 
 * 2013-8-29 上午08:57:23
 */
public class LogoCode {
	
	/** 
     * 生成二维码(QRCode)图片 
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param logoPath  二维码图片中间的logo路径
     */  
    public static int createQRCode(String content, String imgPath,String logoPath) {  
        try {
        	logoPath = ReaderFile.doCompress(logoPath, 50, 50, 1f, "001", false);
        	
        	Thread.sleep(1000);
            Qrcode code = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            code.setQrcodeErrorCorrect('L');
            // N代表数字,A代表字符a-Z,B代表其他字符
            code.setQrcodeEncodeMode('B');
            // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            code.setQrcodeVersion(0);
            
            byte[] d = content.getBytes("GBK");
            System.out.println(d.length);
            
            boolean[][] b = code.calQrcode(d);
            
            int widths = b.length * 4 + 6;
  		    int heights = b.length * 4 + 6;
            
            BufferedImage bufImg = new BufferedImage(widths, heights, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
  
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, widths, heights);
  
            // 设定图像颜色 > BLACK  RED
            gs.setColor(Color.BLACK);
            //gs.setColor(new Color(100,49,168));
  
            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 3;
            // 输出内容 > 二维码
            if (d.length > 0 && d.length < 512) {
                boolean[][] codeOut = code.calQrcode(d);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 4 + pixoff, i * 4 + pixoff, 4, 4);
                        }
                    }
                }
            } else {
            	System.out.println("二维码字符串过长");
                return -1;
            }
            
            if(logoPath!=""){//添加logo
            	File f = new File(logoPath);
            	if(f.exists()){
		            Image img = ImageIO.read(f);//实例化一个Image对象。
		            gs.drawImage(img, (widths-50)/2, (heights-50)/2, null);
            	}
            }
            
            gs.dispose();
            bufImg.flush();
            
            // 生成二维码QRCode图片
            File imgFile = new File(imgPath);
            if(!imgFile.exists()){
            	imgFile.createNewFile();
	        }
            ImageIO.write(bufImg, "jpg", imgFile);
        } catch (Exception e){
            return -100;
        }
        return 0;
    }
    
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param logoPath  二维码图片中间的logo路径
     */  
    public static int createQRCode(String content, String logoPath,HttpServletResponse response) {  
        try {
        	logoPath = ReaderFile.doCompress(logoPath, 24, 24, 1f, "001", false);
        	
        	Thread.sleep(1000);
            Qrcode code = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            code.setQrcodeErrorCorrect('L');
            // N代表数字,A代表字符a-Z,B代表其他字符
            code.setQrcodeEncodeMode('B');
            // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            code.setQrcodeVersion(0);
            
            byte[] d = content.getBytes("GBK");
            System.out.println(d.length);
            
            boolean[][] b = code.calQrcode(d);
            
            int widths = b.length * 4 + 6;
  		    int heights = b.length * 4 + 6;
            
            BufferedImage bufImg = new BufferedImage(widths, heights, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
  
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, widths, heights);
  
            // 设定图像颜色 > BLACK  RED
            gs.setColor(Color.BLACK);
            //gs.setColor(new Color(100,49,248));
  
            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 3;
            // 输出内容 > 二维码
            if (d.length > 0 && d.length < 512) {
                boolean[][] codeOut = code.calQrcode(d);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 4 + pixoff, i * 4 + pixoff, 4, 4);
                        }
                    }
                }
            } else {
            	System.out.println("二维码字符串过长");
                return -1;
            }
            
            if(logoPath!=""){//添加logo
            	File f = new File(logoPath);
            	if(f.exists()){
		            Image img = ImageIO.read(f);//实例化一个Image对象。
		            gs.drawImage(img, (widths-24)/2, (heights-24)/2, null);
            	}
            }
            
            gs.dispose();
            bufImg.flush();
            
            ImageIO.write(bufImg, "jpg", response.getOutputStream());
        } catch (Exception e){
            return -100;
        }
        return 0;
    }
    
    /**
 	 * 解码
 	 * @param fileName
 	 * @return
 	 */
    public static String decoderImg(String fileName) {
    	QRCodeDecoder decoder = new QRCodeDecoder();
 		File imageFile = new File(fileName);
 		BufferedImage image = null;
 		String str = "";
 		try {
 			image = ImageIO.read(imageFile);
 			str = new String(decoder.decode(new J2SEImage(image)), "GBK");
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return str;
 	}

    public static void main(String[] args) {
    	String imgPath = "D:/qrode.jpg";
    	String logoPath = "D:/logo.jpg";
    	String content = "MECARD:N:肖健;" + 
    		"ADR:湖南长沙;" + 
    		"ORG:梦柯网络科技有限公司;" + 
    		"DIV:研发部;" + 
    		"TIL:工程师;" + 
    		"TEL:17775890074;" + 
    		"EMAIL:xiaojian12369@outlook.com;" + 
    		"URL:weibo.com/sunbreezemoon;" + 
    		"NOTE:QQ :1131716442;" + 
    		";";
    	 
    	int i = LogoCode.createQRCode(content, imgPath, logoPath);
    	if(i==0){
    		System.out.println("生成成功！");
    	}else if(i==-1){
    		System.out.println("二维码字符串过长");
    	}else{
    		System.out.println("生成失败");
    	}
	}
}
