package tool;
import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.*;
import java.awt.event.*;

public class MyPaintApp extends JFrame {
	Vector<Point> list = new Vector<Point>();

    class MyPanel extends JPanel {
        
    	public MyPanel() {
    		addMouseMotionListener(new MouseMotionAdapter() {
    		    
    			public void mouseDragged(MouseEvent e) { 
    		    list.add(e.getPoint());
    		    repaint();
		    }
		   });
    		   
		   addMouseListener(new MouseAdapter() {
		    
			  public void mousePressed(MouseEvent e) {
		     list.add(null);
		     list.add(e.getPoint());
		    }
		   });

		  }
	
	    public void paintComponent(Graphics g) {
	        
	    	super.paintComponent(g);
	        
	    	for (int i = 1; i < list.size(); i++) {
	            if (list.get(i - 1) == null)
	             continue;
	            
	            else if (list.get(i) == null)
	             continue;
	            
	            else
	             g.drawLine((int) list.get(i - 1).getX(), (int) list.get(i - 1).getY(), (int) list.get(i).getX(), (int) list.get(i).getY());
	
	
	           }
	
	          }
	
	         }
            

    public MyPaintApp() {
        setSize(600, 450);
        setLocation(400, 0);
        setTitle("My Paint");
        add(new MyPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}