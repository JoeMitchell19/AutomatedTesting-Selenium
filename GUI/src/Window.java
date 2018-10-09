import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

import javax.swing.JFrame;

public class Window {

	public void runMe()
	{
		Frame f = new Frame("Window");
		
		Button B1 = new Button("1");
		Button B2 = new Button("2");
		Button B3 = new Button("3");
		Button B4 = new Button("4");
		
		f.add(B1, BorderLayout.NORTH);
		f.add(B2, BorderLayout.SOUTH);
		f.add(B3, BorderLayout.EAST);
		f.add(B4, BorderLayout.WEST);
		f.setSize(400, 400);
		f.setVisible(true);
		
	}
	
	public void JFrameWindow()
	{
		JFrame fr = new JFrame("Window 2");
		
		Button B1 = new Button("1");
		Button B2 = new Button("2");
		Button B3 = new Button("3");
		Button B4 = new Button("4");
		
		fr.add(B1, BorderLayout.NORTH);
		fr.add(B2, BorderLayout.SOUTH);
		fr.add(B3, BorderLayout.EAST);
		fr.add(B4, BorderLayout.WEST);
		fr.setSize(400, 400);
		fr.setVisible(true);
	}
}