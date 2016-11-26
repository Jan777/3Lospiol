package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;

public class PantallaBatalla extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PantallaBatalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(5, 5, 422, 243);
		contentPane.add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 422, 243);
		layeredPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(12, 196, 398, 34);
		layeredPane.add(panel_1);
		
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setSize(100, 50);
		
		comboBox.setToolTipText("select");
		panel_1.add(comboBox);
	}
}
