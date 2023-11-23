package tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Mole {
  int x, y;
  int speedX, speedY;
  Image img;

  Mole(int x, int y, int speedX, int speedY) {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
    this.speedY = speedY;

    ImageIcon icon = new ImageIcon("mole.png");
    img = icon.getImage();
  }

  public void draw(Graphics g) {
    g.drawImage(img, x, y, null);
  }

  public void move() {
    x += speedX;
    y += speedY;

    if (x < 0 || x > 600 - 40) {
      speedX = -speedX;
    }

    if (y < 0 || y > 400 - 40) {
      speedY = -speedY;
    }
  }

}


public class MoleGame extends JFrame implements ActionListener {
  ArrayList<Mole> moles = new ArrayList<Mole>();
  boolean gameover = false;


  class MyPanel extends JPanel {
    public MyPanel() {
      setBackground(Color.white);
      addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          checkClick(e.getX(), e.getY());
        }
      });
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (Mole m : moles) {
        m.draw(g);
      }
    }
  }

  public MoleGame() {
    MyPanel panel = new MyPanel();
    panel.setPreferredSize(new Dimension(600, 400));
    add(panel);
    pack();
    setTitle("Mole Catch Game");

    for (int i = 0; i < 10; i++)
      moles.add(new Mole(getRand(600 - 40, 0), getRand(400 - 40, 0), getRand(5, 1), getRand(5, 1)));


    Timer timer = new Timer(10, this);
    timer.start();
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  int getRand(int range, int offset) {
    return (int) (Math.random() * range + offset);
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    for (Mole m : moles) {
      m.move();
    }

    if (moles.isEmpty()) {
      // "Game Over!!" 문구를 출력
      gameover = true;
      repaint();
      // 타이머를 멈춤
      ((Timer) evt.getSource()).stop();
    }

    repaint();
  }

  private void checkClick(int x, int y) {
    for (Mole m : moles) {
      if (x >= m.x && x <= m.x + 70 && y >= m.y && y <= m.y + 70) {
        moles.remove(m);
        break;
      }
    }
  }

  public void paint(Graphics g) {
    super.paint(g);
    if (gameover) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("Arial", Font.BOLD, 40));
      g.drawString("Game Over!!!", 180, 250);
    }
  }


}
