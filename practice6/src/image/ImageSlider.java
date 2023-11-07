package image;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageSlider extends JFrame {
	private JPanel panel, panel2;
	private JLabel label;
	private JButton button1, button2, button3, button4;
	private String[] imageFiles = {"dog.png", "bird.png", "cat.png", "pig.png", "rabbit.png"};
	private int index = 0;
	
	
	public ImageSlider() {
		setTitle("이미지 슬라이더");
		setSize(400, 250);

		panel = new JPanel();
		panel2 = new JPanel();
		label = new JLabel("");
		
		ImageIcon icon1 = new ImageIcon("dog.png");
		
		label.setIcon(icon1);
		panel2.add(label);
		button1 = new JButton("<<");
		button1.addActionListener(new MyActor());
		button2 = new JButton("<");
		button2.addActionListener(new MyActor());
		button3 = new JButton(">");
		button3.addActionListener(new MyActor());
		button4 = new JButton(">>");
		button4.addActionListener(new MyActor());
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		add(panel2, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class MyActor implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				label.setIcon(new ImageIcon(imageFiles[0]));
			} else if (e.getSource() == button2) {
				index = (index - 1 + imageFiles.length) % imageFiles.length;
				label.setIcon(new ImageIcon(imageFiles[index]));
			} else if (e.getSource() == button3) {
				index = (index + 1 + imageFiles.length) % imageFiles.length;
				label.setIcon(new ImageIcon(imageFiles[index]));
			} else if (e.getSource() == button4) {
				label.setIcon(new ImageIcon(imageFiles[4]));
			}
		}
	}
}

