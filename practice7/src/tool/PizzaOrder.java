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
	// PizzaOrder 클래스 정의 JFrame으로 부터 상속
	private int sum, temp1, temp2, temp3; // 액션리스너 구현
	private JButton order_button, cancel_button; // 컴포넌트와 컨테이너 참조변수
	private JPanel down_panel;
	private JTextField text;

	WelcomePanel welcom_panel = new WelcomePanel(); // 문구, 타입, 토핑, 크기 패널 생성
	TypePanel TypePanel = new TypePanel();
	ToppingPanel ToppingPanel = new ToppingPanel();
	SizePanel SizePanel = new SizePanel();
	
	int price;
	double multiplier;

	public PizzaOrder() { // 생성자 정의

		this.setSize(270, 200); // 프레임 크기 정의
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 설정
		this.setTitle("피자 주문"); // 타이틀 초기화

		this.order_button = new JButton("주문"); // 주문 버튼 생성
		this.order_button.addActionListener(this); // 이벤트 처리 등록
		this.cancel_button = new JButton("취소"); // 취소 버튼 생성
		this.cancel_button.addActionListener(this); // 이벤트 처리 등록

		this.text = new JTextField(); // 텍스트 필드 생성
		text.setEditable(false); // 수정 불가
		text.setColumns(6); // 길이 6칸

		down_panel = new JPanel(); // 패널 생성
		down_panel.add(this.order_button); // 주문 버튼 취소 버튼 추가
		down_panel.add(this.cancel_button);
		down_panel.add(this.text); // 텍스트 필드 추가

		this.setLayout(new BorderLayout()); // 프레임 배치관리자 설정

		this.add(welcom_panel, BorderLayout.NORTH); // 웰컴 패널 북쪽
		this.add(down_panel, BorderLayout.SOUTH); // 버튼 패널 남쪽
		this.add(SizePanel, BorderLayout.EAST); // 사이즈 패널 동쪽
		this.add(TypePanel, BorderLayout.WEST); // 타입 패널 서쪽
		this.add(ToppingPanel, BorderLayout.CENTER); // 토핑 패널 중앙

		this.setVisible(true); // 프레임 화면 표시
	}

	public void actionPerformed(ActionEvent e) { // 액션이벤트 처리
		if (e.getSource() == this.order_button) { // 액션소스 검출 '주문 버튼시
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
			
			this.text.setText("" + (int)(price * multiplier)); // 텍스트 필드 출력
		}
		
		if (e.getSource() == this.cancel_button) { // 액션 소스가 취소 버튼일 경우
			temp1 = 0;
			temp2 = 0;
			temp3 = 0;
			sum = 0;
			this.text.setText("" + sum); // 전부 초기화 후 초기값 출력
		}
	}

	class WelcomePanel extends JPanel { // 웰컴 패널 클래스 정의 JPanel 상속
		private JLabel message; // 메세지 라벨 참조 변수

		public WelcomePanel() { // 생성자
			message = new JLabel("자바 피자에 오신것을 환영합니다.");
			add(message); // 라벨을 생성후 객체에 추가
		}
	}

	class TypePanel extends JPanel { // 타입 패널 정의 JPanel 상속
		private JRadioButton combo, potato, bulgogi; // 라디오 버튼 정의
		private ButtonGroup bg; // 버튼 그룹 정의

		public TypePanel() { // 생성자
			setLayout(new GridLayout(3, 1)); // 배치관리자 정의

			combo = new JRadioButton("콤보", true); // 라디오 버튼 생성
			potato = new JRadioButton("포테이토");
			bulgogi = new JRadioButton("불고기");

			bg = new ButtonGroup(); // 버튼 그룹 정의후 묶기
			bg.add(combo);
			bg.add(potato);
			bg.add(bulgogi);

			setBorder(BorderFactory.createTitledBorder("종류")); // 버튼그룹 정리

			add(combo); // 객체에 라디오 버튼 추가
			add(potato);
			add(bulgogi);

		}
	}

	class ToppingPanel extends JPanel { // 토핑 패널 정의 JPanel 상속
		private JCheckBox pepper, cheese, peperoni, bacon; // 라디오버튼 참조변수
		private ButtonGroup bg; // 버튼 그룹 참조변수

		public ToppingPanel() { // 생성자
			setLayout(new GridLayout(4, 1)); // 배치관리자 일렬

			pepper = new JCheckBox("피망"); // 라디오 버튼 생성
			cheese = new JCheckBox("치즈");
			peperoni = new JCheckBox("페페로니");
			bacon = new JCheckBox("베이컨");

			setBorder(BorderFactory.createTitledBorder("추가토핑"));

			add(pepper); // 객체에 라디오 버튼 추가
			add(cheese);
			add(peperoni);
			add(bacon);
		}
	}

	class SizePanel extends JPanel { // 사이즈 패널 정의 JPanel 상속
		private JRadioButton small, medium, large; // 라디오 버튼 참조변수
		private ButtonGroup bg; // 버튼 그룹 참조변수

		public SizePanel() { // 생성자
			setLayout(new GridLayout(3, 1)); // 배치관리자

			small = new JRadioButton("Small", true); // 라디오 버튼 생성
			medium = new JRadioButton("Medium");
			large = new JRadioButton("Large");

			bg = new ButtonGroup(); // 버튼 그룹 생성
			bg.add(small);
			bg.add(medium);
			bg.add(large);
			// 버튼 그룹 정리
			setBorder(BorderFactory.createTitledBorder("크기"));

			add(small); // 객체에 라디오 버튼 추가
			add(medium);
			add(large);
		}
	}
}
