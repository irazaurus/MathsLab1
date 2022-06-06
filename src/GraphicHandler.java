import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GraphicHandler extends JFrame {
    private final int number;
        private final int position;

     public GraphicHandler( int number, int position){
        super("simpleApp");
        setSize(1800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.number = number;
        this.position = position;
    }

        private static double f (double x){
        return (Math.pow(x, 2));
    }

        @Override
        public void paint (Graphics g){
        int l = 1;
        double m = 0;
        double d = (double) l / (number - 1); //длина отрезка

            // координаты точек
            double[] a = new double[number]; // координаты точек отрезков без масштаба
            for (int i = 0; i < number; i++) {
                a[i] = 1 + m;
                m += d;
            }
            double[] b = new double[number]; // координаты точек отрезков без масштаба
            for (int i = 0; i < number; i++) {
                b[i] = f(a[i]);
            }

            // координаты оснащения
            double[] x = new double[number - 1];
            double[] y = new double[number - 1];
            x[0] = 400;
            switch (position) {
                case -1: // леове оснащение
                    for (int i = 0; i < number - 1; i++) {
                        x[i] = x[0] + (a[i] - 1) * 1000;
                    }
                    break;
                case 0: // среднее оснащение
                    x[0] = 400 + (a[0] + a[1] - 2) * 500;
                    for (int i = 1; i < number - 1; i++) {
                        x[i] = x[i - 1] + d * 1000;
                    }
                    break;
                case 1: // правое оснащение
                    x[0] = 400 + d * 1000;
                    for (int i = 1; i < number - 1; i++) {
                        x[i] = x[i - 1] + d * 1000;
                    }
                    break;
            }

            for (int i = 0; i < number - 1; i++) {
                y[i] = f(x[i]);
            }
            double[] s = new double[number - 1]; // площади на каждом отрезке
            for (int i = 0; i < number - 1; i++) {
                s[i] = y[i] * d;
            }

            Graphics2D gr2d = (Graphics2D) g;
            gr2d.setBackground(Color.white);
            gr2d.setPaint(Color.BLACK);
            gr2d.setStroke(new BasicStroke(2));
            gr2d.drawLine(10, 900, 1790, 900);
            gr2d.drawLine(10, 10, 10, 900);
            for (int i = 0; i < number; i++) {
                gr2d.draw(new Ellipse2D.Double((a[i] - 1) * 1000 + 400, 895, 4, 4));
            }

            for (int i = 0; i < number - 1; i++) {
                gr2d.setPaint(Color.BLACK);
                gr2d.draw(new Ellipse2D.Double(x[i], 895, 4, 4));
                gr2d.setPaint(Color.black);
                gr2d.draw(new Line2D.Double(x[i], 895, x[i], 800 - f(x[i] / 1000 + 0.6) * 1000));
            }
            gr2d.setPaint(Color.PINK);

            for (double i = 400; i <= 1400; i += 0.1) {
                gr2d.draw(new Ellipse2D.Double(i, 800 - f((i / 1000) + 0.6) * 1000, 1, 1));

            }
            Color c = new Color(100, 100, 100, 64);
            gr2d.setPaint(c);
            gr2d.setBackground(c);

            for (int i = 0; i < number - 1; i++) {
                gr2d.fill(new Rectangle2D.Double(400 + (a[i] - 1) * 1000, 800 - f(x[i] / 1000 + 0.6) * 1000,
                        d * 1000, 900 - (800 - f(x[i] / 1000 + 0.6) * 1000)));
            }
     }

}
