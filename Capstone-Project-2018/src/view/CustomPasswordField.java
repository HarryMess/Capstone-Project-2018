package view;

import controller.PasswordFieldListener;
import controller.TextFieldListener;

import javax.swing.*;
import java.awt.*;

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
