package view;

import controller.PasswordFieldListener;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class CustomPasswordField extends JPasswordField
{
	private Color unselected = new Color(150, 150, 150);
	public CustomPasswordField(String hintText)
	{
		super(hintText, 12);
		setForeground(unselected);
		addFocusListener(new PasswordFieldListener(hintText));
	}


}
