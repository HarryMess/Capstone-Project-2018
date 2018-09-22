package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldListener implements FocusListener
{
	private Color unselected = new Color(150, 150, 150);
	private Color selected = new Color(50, 50, 50);
	private String hintText;
	private boolean userTyped = false;

	public TextFieldListener(String hintText)
	{
		this.hintText = hintText;
	}

	public void focusGained(FocusEvent e)
	{
		JTextField textField = (JTextField) e.getSource();

		if(!userTyped)
		{
			textField.setText("");
			textField.setForeground(selected);
		}
	}

	public void focusLost(FocusEvent e)
	{
		JTextField textField = (JTextField) e.getSource();

		if(textField.getText().length() != 0) userTyped = true;
		else userTyped = false;

		if(!userTyped)
		{
			textField.setText(hintText);
			textField.setForeground(unselected);
		}
	}

}