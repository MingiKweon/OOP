package drawing;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Point2 {
    int x, y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class DrawingBoard extends JFrame {
    int x, y;
    Vector<Vector<Point2>> lines = new Vector<>();
    Vector<Point2> currentLine = new Vector<>();
    Socket drawingSocket;
    DataOutputStream drawingOs;

    class MyPanel extends JPanel {
        public MyPanel() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    currentLine = new Vector<>();
                    currentLine.add(new Point2(e.getX(), e.getY()));
                }

                public void mouseReleased(MouseEvent e) {
                    currentLine.add(new Point2(e.getX(), e.getY()));
                    lines.add(new Vector<>(currentLine));

                    try {
                        sendDrawingInfo(currentLine);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    currentLine.add(new Point2(e.getX(), e.getY()));
                    
                    Vector<Point2> copyOfCurrentLine = new Vector<>(currentLine);
                    lines.add(copyOfCurrentLine);
                    
                    repaint();
                }
            });
            setFocusable(true);
            requestFocus();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));

            for (Vector<Point2> line : lines) {
                for (int i = 1; i < line.size(); i++) {
                    Point2 p1 = line.elementAt(i - 1);
                    Point2 p2 = line.elementAt(i);

                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    public DrawingBoard() {
        try {
            drawingSocket = new Socket("localhost", 5001);
            drawingOs = new DataOutputStream(drawingSocket.getOutputStream());

            new Thread(this::receiveDrawingInfo).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(600, 450);
        setTitle("My Paint");
        add(new MyPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void sendDrawingInfo(Vector<Point2> line) throws IOException {
        StringBuilder message = new StringBuilder("drawing");
        for (Point2 point : line) {
            message.append(" ").append(point.x).append(" ").append(point.y);
        }
        drawingOs.writeUTF(message.toString());
    }

    public void receiveDrawingInfo() {
        try {
            DataInputStream drawingIs = new DataInputStream(drawingSocket.getInputStream());
            while (true) {
                String message = drawingIs.readUTF();
                if (message.startsWith("drawing")) {
                    Vector<Point2> receivedLine = new Vector<>();
                    String[] parts = message.split(" ");
                    for (int i = 1; i < parts.length; i += 2) {
                        int receivedX = Integer.parseInt(parts[i]);
                        int receivedY = Integer.parseInt(parts[i + 1]);
                        receivedLine.add(new Point2(receivedX, receivedY));
                    }
                    lines.add(new Vector<>(receivedLine));
                    MyPanel panel = (MyPanel) getContentPane().getComponent(0);
                    panel.paintComponent(panel.getGraphics());

                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DrawingBoard p = new DrawingBoard();
    }
}