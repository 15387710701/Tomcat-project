package tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateImage {
	public static int WIDTH=100;
	public static int HEIGHT=50;
	public static BufferedImage create(String code){
		BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setFont(new Font("ºÚÌå",Font.BOLD,30));
		for(int i=0;i<code.length();i++)
		{
			char c = code.charAt(i);
			g.setColor(getColor());
			g.drawString(c+"",15*i+5,35);
		}
		g.dispose();
		return image;
	}
	public static Color getColor(){
		int r,g,b;
		Random random = new Random();
		r = random.nextInt(255);
		g = random.nextInt(255);
		b = random.nextInt(255);
		Color color = new Color(r,g,b);
		return color;
		
	}

}
