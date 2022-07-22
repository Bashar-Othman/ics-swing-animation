package com.demo;

/**
 *
 * @author BasharOthman
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.time.Duration;
import java.time.Instant;
import javax.swing.*;

public class RotateEllipse extends JPanel {

    private static final double ELLIPSE_W = 400;
    private static final double ELLIPSE_H = 200;
    private static final int PREF_W = 600;
    private static final int PREF_H = 600;
    private static final Stroke STROKE = DrawingUtil.generateStroke(10);//new BasicStroke(5f);
    private static final Color ELLIPSE_COLOR = Color.red;
    private static final Color BACKGROUND = Color.WHITE;
    private static final double ELLIPSE_X = PREF_W / 2 - ELLIPSE_W / 2;
    private static final double ELLIPSE_Y = PREF_H / 2 - ELLIPSE_H / 2;
    private static final int TIMER_DELAY = 10;
    private static final double DELTA_THETA = Math.toRadians(3);

    private Ellipse2D ellipse2D;
    private AffineTransform transform = new AffineTransform();
    private double theta = 0;

    public RotateEllipse() {
        ellipse2D = new Ellipse2D.Double(ELLIPSE_X, ELLIPSE_Y, ELLIPSE_W,
                ELLIPSE_H);
        setBackground(BACKGROUND);
        Timer mainT = new Timer(TIMER_DELAY, new TimerListener());

        mainT.setRepeats(true);
        mainT.setInitialDelay(TIMER_DELAY);
        mainT.start();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(STROKE);
        g2.setColor(ELLIPSE_COLOR);
        g2.setTransform(transform);
        g2.draw(ellipse2D);
    }

    private class TimerListener implements ActionListener {

        Instant start = Instant.now();
        Duration limit = Duration.ofSeconds(30);

        @Override
        public void actionPerformed(ActionEvent e) {
            theta += DELTA_THETA;
            transform = AffineTransform.getRotateInstance(theta,
                    ELLIPSE_X + ELLIPSE_W / 2, ELLIPSE_Y + ELLIPSE_H / 2);

            Instant end = Instant.now();
            Duration durCount = Duration.between(start, end);
            if (durCount.getSeconds() <= 30) {
                repaint();

            }

        }
    }

    private static void start() {
        RotateEllipse paintEg = new RotateEllipse();

        JFrame frame = new JFrame("ICS Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(paintEg);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Runnable runThread = new Runnable() {
            @Override
            public void run() {
                start();
            }
        };
        SwingUtilities.invokeLater(runThread);

    }
}
