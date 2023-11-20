package tool;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class PizzaOrder extends JFrame implements ActionListener {
	// PizzaOrder Ŭ���� ���� JFrame���� ���� ���
	private int sum, temp1, temp2, temp3; // �׼Ǹ����� ����
	private JButton order_button, cancel_button; // ������Ʈ�� �����̳� ��������
	private JPanel down_panel;
	private JTextField text;

	WelcomePanel welcom_panel = new WelcomePanel(); // ����, Ÿ��, ����, ũ�� �г� ����
	TypePanel TypePanel = new TypePanel();
	ToppingPanel ToppingPanel = new ToppingPanel();
	SizePanel SizePanel = new SizePanel();
	
	int price;
	double multiplier;

	public PizzaOrder() { // ������ ����

		this.setSize(270, 200); // ������ ũ�� ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ����
		this.setTitle("���� �ֹ�"); // Ÿ��Ʋ �ʱ�ȭ

		this.order_button = new JButton("�ֹ�"); // �ֹ� ��ư ����
		this.order_button.addActionListener(this); // �̺�Ʈ ó�� ���
		this.cancel_button = new JButton("���"); // ��� ��ư ����
		this.cancel_button.addActionListener(this); // �̺�Ʈ ó�� ���

		this.text = new JTextField(); // �ؽ�Ʈ �ʵ� ����
		text.setEditable(false); // ���� �Ұ�
		text.setColumns(6); // ���� 6ĭ

		down_panel = new JPanel(); // �г� ����
		down_panel.add(this.order_button); // �ֹ� ��ư ��� ��ư �߰�
		down_panel.add(this.cancel_button);
		down_panel.add(this.text); // �ؽ�Ʈ �ʵ� �߰�

		this.setLayout(new BorderLayout()); // ������ ��ġ������ ����

		this.add(welcom_panel, BorderLayout.NORTH); // ���� �г� ����
		this.add(down_panel, BorderLayout.SOUTH); // ��ư �г� ����
		this.add(SizePanel, BorderLayout.EAST); // ������ �г� ����
		this.add(TypePanel, BorderLayout.WEST); // Ÿ�� �г� ����
		this.add(ToppingPanel, BorderLayout.CENTER); // ���� �г� �߾�

		this.setVisible(true); // ������ ȭ�� ǥ��
	}

	public void actionPerformed(ActionEvent e) { // �׼��̺�Ʈ ó��
		if (e.getSource() == this.order_button) { // �׼Ǽҽ� ���� '�ֹ� ��ư��
			price = 0;
			if (TypePanel.combo.isSelected()) {
				price += 12000;
			}
			else if (TypePanel.potato.isSelected()) {
				price += 15000;
			}
			else if (TypePanel.bulgogi.isSelected()) {
				price += 18000;
			}
			
			if (ToppingPanel.pepper.isSelected()) {
				price += 1000;
			}
			if (ToppingPanel.cheese.isSelected()) {
				price += 1200;
			}
			if (ToppingPanel.peperoni.isSelected()) {
				price += 1400;
			}
			if (ToppingPanel.bacon.isSelected()) {
				price += 1600;
			}
			
			if (SizePanel.small.isSelected()) {
				multiplier = 1.0;
			}
			if (SizePanel.medium.isSelected()) {
				multiplier = 1.5;
			}
			if (SizePanel.large.isSelected()) {
				multiplier = 2.0;
			}
			
			this.text.setText("" + (int)(price * multiplier)); // �ؽ�Ʈ �ʵ� ���
		}
		
		if (e.getSource() == this.cancel_button) { // �׼� �ҽ��� ��� ��ư�� ���
			temp1 = 0;
			temp2 = 0;
			temp3 = 0;
			sum = 0;
			this.text.setText("" + sum); // ���� �ʱ�ȭ �� �ʱⰪ ���
		}
	}

	class WelcomePanel extends JPanel { // ���� �г� Ŭ���� ���� JPanel ���
		private JLabel message; // �޼��� �� ���� ����

		public WelcomePanel() { // ������
			message = new JLabel("�ڹ� ���ڿ� ���Ű��� ȯ���մϴ�.");
			add(message); // ���� ������ ��ü�� �߰�
		}
	}

	class TypePanel extends JPanel { // Ÿ�� �г� ���� JPanel ���
		private JRadioButton combo, potato, bulgogi; // ���� ��ư ����
		private ButtonGroup bg; // ��ư �׷� ����

		public TypePanel() { // ������
			setLayout(new GridLayout(3, 1)); // ��ġ������ ����

			combo = new JRadioButton("�޺�", true); // ���� ��ư ����
			potato = new JRadioButton("��������");
			bulgogi = new JRadioButton("�Ұ��");

			bg = new ButtonGroup(); // ��ư �׷� ������ ����
			bg.add(combo);
			bg.add(potato);
			bg.add(bulgogi);

			setBorder(BorderFactory.createTitledBorder("����")); // ��ư�׷� ����

			add(combo); // ��ü�� ���� ��ư �߰�
			add(potato);
			add(bulgogi);

		}
	}

	class ToppingPanel extends JPanel { // ���� �г� ���� JPanel ���
		private JCheckBox pepper, cheese, peperoni, bacon; // ������ư ��������
		private ButtonGroup bg; // ��ư �׷� ��������

		public ToppingPanel() { // ������
			setLayout(new GridLayout(4, 1)); // ��ġ������ �Ϸ�

			pepper = new JCheckBox("�Ǹ�"); // ���� ��ư ����
			cheese = new JCheckBox("ġ��");
			peperoni = new JCheckBox("����δ�");
			bacon = new JCheckBox("������");

			setBorder(BorderFactory.createTitledBorder("�߰�����"));

			add(pepper); // ��ü�� ���� ��ư �߰�
			add(cheese);
			add(peperoni);
			add(bacon);
		}
	}

	class SizePanel extends JPanel { // ������ �г� ���� JPanel ���
		private JRadioButton small, medium, large; // ���� ��ư ��������
		private ButtonGroup bg; // ��ư �׷� ��������

		public SizePanel() { // ������
			setLayout(new GridLayout(3, 1)); // ��ġ������

			small = new JRadioButton("Small", true); // ���� ��ư ����
			medium = new JRadioButton("Medium");
			large = new JRadioButton("Large");

			bg = new ButtonGroup(); // ��ư �׷� ����
			bg.add(small);
			bg.add(medium);
			bg.add(large);
			// ��ư �׷� ����
			setBorder(BorderFactory.createTitledBorder("ũ��"));

			add(small); // ��ü�� ���� ��ư �߰�
			add(medium);
			add(large);
		}
	}
}
