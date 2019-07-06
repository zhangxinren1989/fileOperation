
package gui.component.custom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import gui.bean.Message;
import gui.constant.CommonConstant;

public class MessageShowLabel extends JLabel {
	private int centerX = 350;
	private int centerY = 250;
	private List<Message> msgs = new ArrayList<>();
	private List<Message> showMsgs = new ArrayList<>();
	private int bottomY = 500;
	
	public MessageShowLabel()
	{
	    getAllMessages();
	    // 设置背景必有这一句：设置为不透明
	    setOpaque(true);
	    setBackground(Color.GRAY);
	    setForeground(Color.GREEN);
	    setFont(new Font("微软雅黑", Font.PLAIN, 15));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Message message: showMsgs)
		{
			g.drawString(message.getMsg(), centerX - g.getFontMetrics(new Font("微软雅黑", Font.PLAIN, 15)).stringWidth(message.getMsg()) / 2, message.getY());
		}
		
		changeMessages();
	}
	
	private void getAllMessages()
	{
		String[] aboutLines = CommonConstant.ABOUT.split("\\r\\n" , -1);
		Message message = null;
		String s = null;
		
		for(int i = 0; i < aboutLines.length; i++)
		{
			s = aboutLines[i];
			message = new Message(0, bottomY, s);
			msgs.add(message);
		}
		
		message = msgs.get(0);
		showMsgs.add(message);
		msgs.remove(0);
		msgs.add(message);
	}
	
	private void changeMessages()
	{
		boolean deleteMes = false;
		Message message = null;
		Iterator<Message> iterator = showMsgs.iterator();
		while(iterator.hasNext())
		{
			message = iterator.next();
			message.setY(message.getY() - 15);
			if(message.getY() < 0)
			{
				deleteMes = true;
				iterator.remove();
			}
		}
		
		if(deleteMes)
		{
			message = msgs.get(0);
			message.setY(bottomY);
			showMsgs.add(message);
			msgs.remove(0);
			msgs.add(message);
		}
		
		message = showMsgs.get(showMsgs.size() - 1);
		if(message.getY() <= bottomY - 15)
		{
			message = msgs.get(0);
			showMsgs.add(message);
			msgs.remove(0);
			msgs.add(message);
			
		}
	}
}

