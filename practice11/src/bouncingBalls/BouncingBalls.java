package bouncingBalls;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

//공을 Ball 클래스로 모델링한다. 
class Ball {
//공의 속성은 위치, 크기, 속도이다. 
	private int x;
	private int y;
	private int size;
	private int xSpeed;
	private int ySpeed;
	private Color c;

//공을 화면에 그려주는 메소드이다. 
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x, y, size, size);
	}

	public Ball(int x, int y, int size, int xSpeed, int ySpeed, Color c) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.c = c;
	}

	public void update() {
		x += xSpeed;
		y += ySpeed;
		if ((x + size) > BouncingBalls.BOARD_WIDTH || x < 0) {
			xSpeed = -xSpeed;
		}
		if ((y + size) > BouncingBalls.BOARD_HEIGHT || y < 0) {
			ySpeed = -ySpeed;
		}
	}
}

class BouncingPanel extends JPanel implements MouseListener {
//	private Color[] colors = { Color.RED };
  private final java.util.List<Ball> balls = new java.util.ArrayList<>();
    
	public BouncingPanel() {
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		Runnable task = () -> {
			while (true) {
			  for (Ball b : balls) {
                b.update();
            }
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException ignore) {
				}
			}
		};
		new Thread(task).start();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	   Random rand = new Random();
      int size = rand.nextInt(50) + 15;
      Color c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
      int speed = rand.nextInt(10) + 1;
      Ball ball = new Ball(e.getX(), e.getY(), size, speed, speed, c);
      balls.add(ball);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        for (Ball ball : balls) {
          ball.draw(g);
        }
	}
}


public class BouncingBalls extends JFrame {
	public static final int BOARD_WIDTH = 600;
	public static final int BOARD_HEIGHT = 300;

	public BouncingBalls() {
		BouncingPanel panel = new BouncingPanel();
		panel.setPreferredSize(new Dimension(BouncingBalls.BOARD_WIDTH, BouncingBalls.BOARD_HEIGHT));
		add(panel);
		pack();
		setTitle("Bouncing Balls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}