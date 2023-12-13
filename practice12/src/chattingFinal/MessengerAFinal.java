package chattingFinal;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessengerAFinal {
	final static int ServerPort = 5001;
	protected JTextField textField;
	protected JTextField nicknameField;
	protected JPanel inputPanel;
	protected JTextArea textArea;
	DataInputStream is;
	DataOutputStream os;

	public MessengerAFinal() throws IOException {
		MyFrame f = new MyFrame();
		InetAddress ip = InetAddress.getByName("localhost");
		Socket s = new Socket(ip, ServerPort);
		is = new DataInputStream(s.getInputStream());
		os = new DataOutputStream(s.getOutputStream());

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						String msg = is.readUTF();
						textArea.append(new String(msg) + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread2.start();
	}

	// 내부 클래스 정의
	class MyFrame extends JFrame implements ActionListener {
		public MyFrame() {
			super("MessengerA");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			textField = new JTextField(30);
			textField.addActionListener(this);
			nicknameField = new JTextField(25);
			nicknameField.addActionListener(this);
			inputPanel = new JPanel(new BorderLayout());
			inputPanel.add(textField, "North");
			inputPanel.add(nicknameField, "South");

			textArea = new JTextArea(10, 30);
			textArea.setEditable(false);

			add(inputPanel, BorderLayout.PAGE_END);
			add(textArea, BorderLayout.CENTER);
			pack();
			setVisible(true);
		}

		public void actionPerformed(ActionEvent evt) {
			String s = textField.getText();
			String s2 = nicknameField.getText();
			try {
				os.writeUTF("(" + s2 + ") " + s);
			} catch (IOException e) {
				e.printStackTrace();
			}
			textField.selectAll();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}

	public static void main(String[] args) throws IOException {
		MessengerAFinal m = new MessengerAFinal();
	}
}