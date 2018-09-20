package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class LinkListener extends MouseAdapter
{
	private JFrame oldFrame;
	private String newFrameStr;
	private FrameManager fm;
	public LinkListener(FrameManager fm, JFrame oldFrame, String newFrameStr)
	{
		this.oldFrame = oldFrame;
		this.newFrameStr = newFrameStr;
		this.fm = fm;
	}

	public void mouseClicked(MouseEvent e)
	{
		//Get new frame from Frame Manager
		JFrame newFrame = fm.getFrame(newFrameStr);

		//Make sure new frame is same size/place as old one.
		Dimension currentSize = oldFrame.getSize();
		Point currentPoint = oldFrame.getLocation();
		newFrame.setSize(currentSize);
		newFrame.setLocation(currentPoint);

		//Switch visible frame
		newFrame.setVisible(true);
		oldFrame.setVisible(false);
	}
}
