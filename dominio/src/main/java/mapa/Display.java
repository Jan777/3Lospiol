package mapa;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private String title;
	public static int height;
	public static int width;
	public static JFrame frame;
	public static Canvas canvas;

	public Display(String title, int width, int height) {
		Display.height = height;
		Display.width = width;
		this.title = title;
		createDisplay();
	}

	public void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setBackground(Color.black);
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}
}
