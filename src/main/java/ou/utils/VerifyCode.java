package ou.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * 图片验证码：利用java程序画制验证码图片
 * @author Administrator
 *
 */
public class VerifyCode {
	/** 
	 * 图片的宽
	 */
	private static int width = 120;
	/**
	 * 图片的高
	 */
	private static int height = 40;
	/**
	 * 随机生成的字符“池”
	 */
	private static String codes = "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	/**
	 * 图片字体
	 */
	private static String[] fontNames = { "微软雅黑", "黑体", "华文楷体", "幼圆", "宋体",
			"楷体" };
	/**
	 * 保存验证码文本
	 */
	private String valistr = "";
	
	/**
	 * 绘制验证码图片
	 */
	public void drawImage(OutputStream output){
		//1.创建一个图片缓冲区对象，并设置图片的宽高和类型
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//2.获取画笔（得到绘制环境）
		Graphics2D graphics2d = (Graphics2D) bImage.getGraphics();
		
		//3.开始画图
		//3.1.设置背景颜色
		graphics2d.fillRect(0, 0, width, height);
		
		//3.2.设置画笔颜色
		graphics2d.setColor(Color.red);
		
		//3.3.设置字体大小
		graphics2d.setFont(new Font("微软雅黑", Font.BOLD, 22));
		
		//3.4.画一个字符
		for (int i = 0; i < 4; i++) {
			//3.4.1设置字体颜色随机
			graphics2d.setColor(new Color(getRandom(0, 175), getRandom(0, 175), getRandom(0, 175)));
			
			//3.4.2设置字体随机
			graphics2d.setFont(new Font(fontNames[getRandom(0, fontNames.length)], Font.BOLD, 22));
			
			//3.4.3将图片旋转指定的度数
			//3.4.3.1将随机获取的度数旋转成弧度
			double theta = getRandom(-45, +45) * Math.PI / 180;
			graphics2d.rotate(theta, i * 30 + 7, height - 7);
			
			//3.4.3.2随机获取一个字符
			String code = codes.charAt(getRandom(0, codes.length())) + "";
			graphics2d.drawString(code, i * 30 + 7, height - 7);
			
			//拼接字符
			valistr = valistr + code;
			
			//3.4.4将图片再旋转回来
			graphics2d.rotate(-theta,i * 30 +7,height - 7);
		}
		
		//3.5.画干扰线
		for (int i = 0; i < 6; i++) {
			//3.5.1设置字体颜色随机
			graphics2d.setColor(new Color(getRandom(0, 175), getRandom(0, 175), getRandom(0, 175)));
			
			//3.5.2随机画一条线
			graphics2d.drawLine(getRandom(0, width), getRandom(0, height), getRandom(0, width), getRandom(0, height));
			
			//3.6画一个边框,设置变框的颜色
			graphics2d.setColor(Color.gray);
			graphics2d.drawRect(0, 0, width-1, height-1);
			
			//4.保存图片到指定位置（硬盘/发送给浏览器）
			try {
				ImageIO.write(bImage, "JPEG", output);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			//5.释放资源
			graphics2d.dispose();
		}
	}
	
	/**
	 * 返回验证码文本
	 * @return
	 */
	public String getCode(){
		return valistr;
	}
	/**
	 * 获取一个指定范围的随机数 0~10 3~10
	 * @param start
	 * @param end
	 * @return
	 */
	public int getRandom(int start,int end){
		Random random = new Random();
		return random.nextInt(end - start) + start;
	}
	
	public static void main(String[] args) throws Exception {
		VerifyCode vc = new VerifyCode();
		vc.drawImage(new FileOutputStream("./src/main/webapp/images/vc.jpg"));
		System.out.println("执行完毕");
	}
}
