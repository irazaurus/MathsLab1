import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GraphicHandler extends JFrame {
    private final int number;
        private final int position;

     public GraphicHandler( int number, int position){
        super("f(x) = x^2, [-3, 0]");
        setSize(1200, 1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.number = number;
        this.position = position;
    }

        private static double f(double x) {
            return Math.pow(x, 2);
    }

        @Override
        public void paint (Graphics g){
        int l = 3;
        double m = 0;
        double d = (double) l / (number - 1); //длина отрезков

            // координаты точек x
            double[] a = new double[number];
            for (int i = 0; i < number; i++) {
                a[i] = 1 + m;
                m += d;
            }
            // координаты точек y
            double[] b = new double[number];
            for (int i = 0; i < number; i++) {
                b[i] = f(a[i]);
            }

            // координаты оснащения
            double[] x = new double[number - 1];
            double[] y = new double[number - 1];
            switch (position) {
                case -1 -> { // левое оснащение
                    x[0] = -3;
                    for (int i = 0; i < number - 1; i++) {
                        x[i] = x[0] + (a[i] - 1);
                    }
                }
                case 0 -> { // среднее оснащение
                    x[0] = -3 + (a[0] + a[1] -2)/2;
                    for (int i = 1; i < number - 1; i++) {
                        x[i] = x[i - 1] + d;
                    }
                }
                case 1 -> { // правое оснащение
                    x[0] = -3 + d;
                    for (int i = 1; i < number - 1; i++) {
                        x[i] = x[i - 1] + d;
                    }
                }
            }

            for (int i = 0; i < number - 1; i++) {
                y[i] = f(x[i]);
            }


            //площадь интегральной суммы
            double sum = 0;
            for (int i = 0; i < number - 1; i++) {
                sum += y[i] * d;
            }

            //координатная плоскость
            Graphics2D gr2d = (Graphics2D) g;
            gr2d.setBackground(Color.white);
            gr2d.setPaint(Color.BLACK);
            gr2d.setStroke(new BasicStroke(1));
            gr2d.drawLine(10, 900, 1100, 900);
            gr2d.drawLine(1000, 1000, 1000, 0);
            gr2d.drawString("-3", 395, 915);
            gr2d.drawString("0", 1005, 915);

            //точечки
            for (int i = 0; i < number; i++) {
                gr2d.draw(new Ellipse2D.Double((a[i] - 1) * 200 + 400, 897, 3, 3));
            }

            //линии, соединяющие точки
            for (int i = number - 1; i > 0; i--) {
                gr2d.setPaint(Color.BLACK);
                gr2d.draw(new Ellipse2D.Double(-(a[i] - 1) * 200 + 1000, 900 - f((a[i] - 1)* 3)*8.85, 3, 3));
                gr2d.setPaint(Color.black);
                gr2d.draw(new Line2D.Double(-(a[i] - 1) * 200 + 1000, 897, -(a[i] - 1) * 200 + 1000, 900 - f((a[i] - 1)* 3)*8.85));
            }

            //линия функции
            gr2d.setPaint(Color.PINK);
            for (double i = 600; i >= 0; i -= 0.1) {
                gr2d.draw(new Ellipse2D.Double(-i+1000, 900 - f((i/600) * 3) * 80, 1, 1));
            }

            //площадь интегральной суммы
            //Color c = new Color(100, 100, 100, 64);
            Color c = new Color(239, 102, 113, 60);
            gr2d.setPaint(c);
            gr2d.setBackground(c);
            for (int i = number - 2; i >= 0; i--) {
                gr2d.fill(new Rectangle2D.Double(400 + (a[i] - 1) * 200, 900 - f(x[i]) * 80,
                        d * 200, f(x[i]) * 80));
            }

            gr2d.setPaint(Color.BLACK);
            gr2d.setFont(new Font("MyFont", Font.PLAIN, 16));
            gr2d.drawString("Площадь интегральной суммы = " + sum, 100, 160);
            gr2d.drawString("Ошибка вычислений = " + String.format("%.5f", Math.abs(9 - sum)), 100, 140);
     }

}
