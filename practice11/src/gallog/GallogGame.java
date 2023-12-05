package gallog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GraphicObject {
	BufferedImage img = null;
	int x = 0, y = 0;

	public GraphicObject(String name) {
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	public void update() {
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, 100, 100, null);
	}

	public void keyPressed(KeyEvent event) {
	}
}

class Missile extends GraphicObject implements Runnable {
	boolean launched = false;

	public Missile(String name) {
		super(name);
		y = -200;
	}
	
	public void run() {
      while (launched && y > -100) {
          y -= 2;
          try {
              Thread.sleep(10);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }

  public void launch(int x, int y) {
      this.x = x;
      this.y = y;
      launched = true;
      Thread missileThread = new Thread(this);
      missileThread.start();
  }
}

class Enemy extends GraphicObject {
	int dx = -5;
	int life = 5;

	public Enemy(String name) {
		super(name);
		x = 500;
		y = 30;
	}

	public void update() {
		x += dx;
		if (x < 0)
			dx = +3;
		if (x > 400)
			dx = -3;
	}
}

class SpaceShip extends GraphicObject {
	public SpaceShip(String name) {
		super(name);
		x = 150;
		y = 380;
	}

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			y -= 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			y += 10;
		}
	}
}

class MyPanel extends JPanel implements KeyListener {
	private boolean gameover = false;
    Enemy enemy;
	SpaceShip spaceship;
	Missile missile;
	List<Missile> missiles;

	public MyPanel() {
		super();
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable(true);

		enemy = new Enemy("enemy.png");
		spaceship = new SpaceShip("spaceship.png");
		missiles = new ArrayList<>();
		
		class MyThread extends Thread {
			public void run() {
				while (true) {
					enemy.update();
					spaceship.update();
					for (Missile missile : missiles) {
					  missile.update();
					}
					Collisions();
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		}
		
		Thread t = new MyThread();
		t.start();
	}

    private void Collisions() {
      Iterator<Missile> missileIterator = missiles.iterator();
      while (missileIterator.hasNext()) {
          
        Missile missile = missileIterator.next();
          if (enemy.x < missile.x && missile.x < enemy.x + 30 && enemy.y < missile.y && missile.y < enemy.y + 30) {
              enemy.life--;
              missileIterator.remove();
              if (enemy.life == 0) {
                   gameover = true;
              }
          }
      }
  }
	
	public void paint(Graphics g) {
		super.paint(g);
		 if (!gameover) {
	          enemy.draw(g);       
	          g.setFont(new Font("Arial", Font.BOLD, 15));
	          String Life = "Life: " + enemy.life;
	          g.drawString(Life, getWidth() - 60, 20);
	        }
		spaceship.draw(g);
		for (Missile missile : missiles) {
          missile.draw(g);
      }
      
      if (gameover) {
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String gameOverMessage = "Game Over!!!";
        g.drawString(gameOverMessage, (getWidth() - g.getFontMetrics().stringWidth(gameOverMessage)) / 2, getHeight() / 3);
        repaint();
      }
	}

	public void keyPressed(KeyEvent event) {
		spaceship.keyPressed(event);
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
          // Fire a missile
          Missile missile = new Missile("missile.png");
          missile.launch(spaceship.x + 12, spaceship.y);
          missiles.add(missile);
      }
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}

public class GallogGame extends JFrame {
	public GallogGame() {
		setTitle("My Game");
		add(new MyPanel());
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}