package com.qa.calculatorGUI;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class App extends Frame implements WindowListener
{
	static int size = 400;
	
	public App()
	{
		Panel display = new Panel();
		Panel buttons = new Panel();
		
		Button b1,b2,b3,b4,b5,b6, n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
		TextField t3;
		
		n0 = new Button("0");
		n1 = new Button("1");
		n2 = new Button("2");
		n3 = new Button("3");
		n4 = new Button("4");
		n5 = new Button("5");
		n6 = new Button("6");
		n7 = new Button("7");
		n8 = new Button("8");
		n9 = new Button("9");
		
		b1 = new Button("+");
		b1.setBackground(Color.RED);
		b2 = new Button("-");
		b2.setBackground(Color.RED);
		b3 = new Button("x");
		b3.setBackground(Color.RED);
		b4 = new Button("/");
		b4.setBackground(Color.RED);
		b5 = new Button("C");
		b5.setBackground(Color.ORANGE);
		b6 = new Button("=");
		b6.setBackground(Color.GREEN);
		
		t3 = new TextField("", 50);
		t3.setEditable(false);
		
		display.add(t3);
		
		Operations E = new Operations(t3);
		FlowLayout Fl = new FlowLayout();
		GridLayout G1 = new GridLayout(4, 4);
		
		display.setLayout(Fl);
		buttons.setLayout(G1);
		
		b1.addActionListener(E);
		b2.addActionListener(E);
		b3.addActionListener(E);
		b4.addActionListener(E);
		b5.addActionListener(E);
		b6.addActionListener(E);
		n0.addActionListener(E);
		n1.addActionListener(E);
		n2.addActionListener(E);
		n3.addActionListener(E);
		n4.addActionListener(E);
		n5.addActionListener(E);
		n6.addActionListener(E);
		n7.addActionListener(E);
		n8.addActionListener(E);
		n9.addActionListener(E);
				
		buttons.add(n0);
		buttons.add(n1);
		buttons.add(n2);
		buttons.add(b1);
		buttons.add(n3);
		buttons.add(n4);
		buttons.add(n5);
		buttons.add(b2);
		buttons.add(n6);
		buttons.add(n7);
		buttons.add(n8);
		buttons.add(b3);
		buttons.add(n9);
		buttons.add(b6);
		buttons.add(b5);
		buttons.add(b4);
		
		add(display, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	
		setSize(size, size);
		addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		this.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{	
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		
	}
}
