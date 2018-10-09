package com.qa.calculatorGUI;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Operations implements ActionListener
{
	TextField t3;
	static String display = "";
	static int index = 0;
	static String function = "";

	public Operations(TextField c)
	{
		t3 = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Button btn;
		int result;
		
		btn = (Button) e.getSource();
		
		if(!btn.getLabel().equals("C"))
		{
			display += btn.getLabel();
		}
		if(btn.getLabel().equals("C")) {
			display = "";
		}
		if (btn.getLabel().equals("+")||btn.getLabel().equals("-")||btn.getLabel().equals("x")||btn.getLabel().equals("/"))
		{
			function = btn.getLabel();
			index = display.length();
		}
		if(btn.getLabel().equals("=")) {
			result = calculation(display, index);
			t3.setText(display+result);
		}
		else
		{
			t3.setText(display);
		}
	}

	private int calculation(String data, int index)
	{
		int a,b,c = 0;
		System.out.println(data);
		a = Integer.valueOf(data.substring(0, index-1));
		b = Integer.valueOf(data.substring(index, data.length()-1));
		
		switch (function)
		{
		case "+":
			c = a + b;
			break;
		case "-":
			c = a - b;
			break;
		case "x":
			c = a * b;
			break;
		case "/":
			c = a / b;
			break;
		}
		return c;
	}
}
