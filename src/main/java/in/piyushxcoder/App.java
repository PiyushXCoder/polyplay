package in.piyushxcoder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Hello world!
 */
public class App extends JFrame implements MouseMotionListener, MouseListener {
    boolean dragging = false;
    boolean resizing = false;
    int initX = 0, initY = 0;

    public App() {
        setTitle("My Window Bhai log!");
        setSize(400, 300);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn = new JButton("Hello World!");
        btn.addActionListener(e -> {
            App a = new App();
            a.setLocation(getX() + 50, getY() + 50);
        });
        add(btn);
        setResizable(false);
        setVisible(true);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        if (dragging)
            setLocation(x - initX, y - initY);

        if (resizing) {
            int newW = x - getX();
            int newH = y - getY();
            if (newW < 200 || newH < 150)
                return;
            setSize(newW, newH);
            setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        Point loc = getLocation();
        int relX = x - loc.x;
        int relY = y - loc.y;

        if (relX > getWidth() - 30 && relY > 0 && relX < getWidth() && relY < 30) {
            this.dispose();
        }

        if (relX > getWidth() - 20 && relY > getHeight() - 20 && relX < getWidth() && relY < getHeight()) {
            resizing = true;
            initX = relX;
            initY = relY;
            repaint();
            return;
        }

        if (relX > 0 && relY > 0 && relX < getWidth() && relY < 30) {
            dragging = true;
            initX = relX;
            initY = relY;
            repaint();
            return;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        resizing = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, getWidth(), 30);

        g2d.setColor(Color.WHITE);
        g2d.drawString(getTitle(), 10, 20);

        g2d.setColor(Color.RED);
        g2d.fillOval(getWidth() - 30, 0, 30, 30);
        g2d.setColor(Color.WHITE);
        g2d.drawLine(getWidth() - 20, 10, getWidth() - 10, 20);
        g2d.drawLine(getWidth() - 10, 10, getWidth() - 20, 20);

        // Draw Simlely in center with circle
        if (dragging)
            g2d.setColor(new Color(255, 200, 0));
        else if (resizing)
            g2d.setColor(new Color(0, 150, 0));
        else
            g2d.setColor(Color.YELLOW);
        int posX = getWidth() / 2 - 50;
        int posY = getHeight() / 2 - 50;
        g2d.fillOval(posX, posY, 100, 100);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(posX + 30, posY + 30, 10, 10);
        g2d.fillOval(posX + 60, posY + 30, 10, 10);
        g2d.drawArc(posX + 25, posY + 45, 50, 30, 0, -180);

        // 2 lines at botton right corner
        g2d.setColor(Color.GRAY);
        g2d.drawLine(getWidth() - 20, getHeight() - 10, getWidth() - 10, getHeight() - 10);
        g2d.drawLine(getWidth() - 10, getHeight() - 20, getWidth() - 10, getHeight() - 10);

        Component c[] = this.getComponents();
        c[0].setLocation(10, 40);
        c[0].setSize(150, 35);
        c[0].paintAll(g);

        g.dispose();
    }

    public static void main(String[] args) {
        new App();
    }
}
