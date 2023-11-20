package tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {

	private JPanel panel, tPanel;
	private JTextField tField, rField;
	private JButton[] buttons;
	private String[] labels = { 
			"", "", "", "CE", "%",
			"7", "8", "9", "/", "sqrt",
			"4", "5", "6", "x", "x^2",
			"1", "2", "3", "-", "1/x",
			"0", "+/-", ".", "+", "=",
	};

	private Map<String, Integer> map = new HashMap<String, Integer>();
	private String[] ops = {
			"%", "/", "sqrt", "x", "x^2", "-", "1/x", "+/-", "+", "="	
	};
	private int[] priorities = {
			3, 3, 4, 3, 4, 2, 4, 4, 2, 1	
	};

	Stack<Double> numStack = new Stack<>();
	Stack<String> opStack = new Stack<>();
	
	StringBuffer num = new StringBuffer("");
	
	double res;

	public void processOp(int priority) {
		String operator;
		double d1, d2;
		int prev_prio;
		
		while (!opStack.isEmpty()) {
	           operator = opStack.pop();
	           prev_prio = map.get(operator);

	           if (prev_prio < priority) {
	               opStack.push(operator);
	               break;
	           }
	           d2 = numStack.pop();
	           d1 = numStack.pop();
	           double total = 0;

	           switch (operator) {
	               case "%":
	                   total = d1 % d2;
	                   break;
	               case "/":
	                   total = d1 / d2;
	                   break;
	               case "sqrt":
	                   total = Math.sqrt(d1);
	                   break;
	               case "x":
	                   total = d1 * d2;
	                   break;
	               case "x^2":
	                   total = Math.pow(d1, 2);
	                   break;
	               case "-":
	                   total = d1 - d2;
	                   break;
	               case "1/x":
	                   total = 1 / d2;
	                   break;
	               case "+/-":
	                   total = -d2;
	                   break;
	               case "+":
	                   total = d1 + d2;
	                   break;
	           }

	           numStack.push(total);
	       }
	   }
		
		// (실습7-2) 구현 위치
		

	public void processKey(String key) {
		tField.setText(tField.getText()+key);
		switch(key) {
			case "CE":
				numStack.clear();
				opStack.clear();				
				num.setLength(0);
				tField.setText("");
				rField.setText("");
				break;
			case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".":
				num.append(key);				
				break;
			case "+", "-", "x", "/":
				numStack.push(Double.parseDouble(num.toString()));
				processOp(map.get(key));
				num.setLength(0);
				opStack.push(key);			
				break;
			case "=":
				numStack.push(Double.parseDouble(num.toString()));
				processOp(map.get(key));
				if (numStack.size() != 1 || opStack.size() != 0)
					rField.setText("Error!!!");
				else
					rField.setText(Double.toString(numStack.peek()));
				break;
			default:
				break;
		}
	}

	public void actionPerformed(ActionEvent e) {
    	JButton but = (JButton) e.getSource();
        String key = (String) but.getText();
        processKey(key);
    }

	public Calculator() {
		for(int i=0; i<ops.length; i++)
			map.put(ops[i], priorities[i]);
		
		tField = new JTextField(35);
		rField = new JTextField(35);
		tPanel = new JPanel();
		panel = new JPanel();
		tField.setText("");
		tField.setEnabled(false);
		rField.setText("");
		rField.setEnabled(false);

		tPanel.setLayout(new GridLayout(2, 1, 3, 3));
		panel.setLayout(new GridLayout(0, 5, 3, 3));
		buttons = new JButton[25];
		int index = 0;
		for (int rows = 0; rows < 5; rows++) {
			for (int cols = 0; cols < 5; cols++) {
				buttons[index] = new JButton(labels[index]);
				if( cols >= 3 )
					buttons[index].setForeground(Color.red);
				else 
					buttons[index].setForeground(Color.blue);
				buttons[index].setBackground(Color.yellow);
				buttons[index].addActionListener(this);
				panel.add(buttons[index]);
				index++;
			}
		}
		
		tPanel.add(tField, BorderLayout.NORTH);
		tPanel.add(rField, BorderLayout.CENTER);
		add(tPanel, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
}