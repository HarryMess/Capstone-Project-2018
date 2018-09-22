package controller;

import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordFieldListener implements FocusListener
{
	private Color unselected = new Color(150, 150, 150);
	private Color selected = new Color(50, 50, 50);
	private String hintText;
	private boolean userTyped = false;

	public PasswordFieldListener(String hintText)
	{
		this.hintText = hintText;
	}

	public void focusGained(FocusEvent e)
	{
		JPasswordField textField = (JPasswordField) e.getSource();

		textField.setEchoChar('\u25CF');

		if(!userTyped)
		{
			textField.setText("");
			textField.setForeground(selected);
		}
	}

	public void focusLost(FocusEvent e)
	{
		JPasswordField textField = (JPasswordField) e.getSource();

		if(textField.getPassword().length != 0) userTyped = true;
		else userTyped = false;

		if(!userTyped)
		{
			textField.setEchoChar((char)0); //Shows real text instead of hiding
			textField.setText(hintText);
			textField.setForeground(unselected);
		}
	}
}
