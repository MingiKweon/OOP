package chatting;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//�ҽ��� �Է��ϰ� Ctrl+Shift+O�� ������ �ʿ��� ������ �����Ѵ�. 
public class MessengerA {
	final static int ServerPort = 5000;
	protected JTextField textField;
	protected JTextArea textArea;
	DataInputStream is;
	DataOutputStream os;

	public MessengerA() throws IOException {
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
						// ���� ��Ŷ�� �ؽ�Ʈ ������ ǥ���Ѵ�.
						textArea.append("RECIEVED: " + new String(msg) + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread2.start();
	}

	// ���� Ŭ���� ����
	class MyFrame extends JFrame implements ActionListener {
		public MyFrame() {
			super("MessengerA");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			textField = new JTextField(30);
			textField.addActionListener(this);

			textArea = new JTextArea(10, 30);
			textArea.setEditable(false);

			add(textField, BorderLayout.PAGE_END);
			add(textArea, BorderLayout.CENTER);
			pack();
			setVisible(true);
		}

		public void actionPerformed(ActionEvent evt) {
			String s = textField.getText();
			try {
				os.writeUTF(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
			textArea.append("SENT: " + s + "\n");
			textField.selectAll();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}

	public static void main(String[] args) throws IOException {
		MessengerA m = new MessengerA();
	}
}