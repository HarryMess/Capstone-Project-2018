package view;

import controller.TextFieldListener;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField
{
	private Color unselected = new Color(150, 150, 150);
	public CustomTextField(String hintText)
	{
		super(hintText, 12);
		setForeground(unselected);
		addFocusListener(new TextFieldListener(hintText));
	}
}
