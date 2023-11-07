package tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMI extends JFrame {
	private JPanel panel0, panel1, panel2, panel3, panel4, panel5;
	private JLabel label1, label2, label3, label4;
	private JTextField field1, field2;
	private JButton button;
	String state = "";
	/**	
	 * Create the frame.
	 */
	public BMI() {
		setSize(350, 500);
		setTitle("BMI");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel1 = new JPanel();
		label1 = new JLabel("���� BMI ������ �󸶳� �ɱ�?");
		panel1.add(label1);
		
		panel2 = new JPanel();
		label2 = new JLabel("���� ü��(kg): ");
		panel2.add(label2);
		field1 = new JTextField(15);
		panel2.add(field1);
		
		panel3 = new JPanel();
		label3 = new JLabel("���� Ű(cm): ");
		panel3.add(label3);
		field2 = new JTextField(15);
		panel3.add(field2);
		
		button = new JButton("BMI Ȯ���ϱ�");
		button.addActionListener(new MyListener());
		panel4 = new JPanel();
		panel4.add(button);
		
		panel5 = new JPanel();
		label4 = new JLabel("����� BMI: " + state);
		panel5.add(label4);
		
		panel0 = new JPanel();
		panel0.add(panel1);
		panel0.add(panel2);
		panel0.add(panel3);
		panel0.add(panel4);
		panel0.add(panel5);
		add(panel0);
		
		
		setVisible(true);
	}
	
	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double weight = Double.parseDouble(field1.getText());
			double height = Double.parseDouble(field2.getText());
			double BMI = weight / Math.pow((height / 100.0), 2);

			if (BMI >= 0 && BMI < 18.5) {
				state = "��ü��";
			} else if (BMI >= 18.5 && BMI < 23.0) {
				state = "����";
			} else if (BMI >= 23.0 && BMI < 25.0) {
				state = "��ü��";
			} else {
				state = "��";
			}
			
			label4.setText("����� BMI: " + state);
		}
	}

}

