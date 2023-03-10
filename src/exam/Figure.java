package exam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Figure implements Runnable, ActionListener, MouseMotionListener {

    // wspolny bufor
    protected Graphics2D buffer;
    protected Area area;
    // do wykreslania
    protected Shape shape;
    // przeksztalcenie obiektu
    protected AffineTransform aft;

    // przesuniecie
    private int dx, dy;
    // rozciaganie
    private double sf;
    // kat obrotu
    private double an;
    private static AtomicInteger delay = new AtomicInteger();	//klasa dzięki której int może być automatycznie updatowany
    private int width;
    private int height;
    private Color clr;
    private static Color clrToRemember;
    private static boolean paused = false;	//pauza(nieaktywna)
    protected static final Random rand = new Random();
    private static double coordinateX = 200, coordinateY = 200;

    public Figure(Graphics2D buf, int del, int w, int h) {
        delay.set(del);
        buffer = buf;
        width = w;
        height = h;

        dx = 1 + rand.nextInt(5);
        dy = 1 + rand.nextInt(5);
        sf = 1 + 0.05 * rand.nextDouble();
        an = 0.1 * rand.nextDouble();

        if(clrToRemember == null ) {
            clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
        else {
            clr = clrToRemember;
        }
    }

    @Override
    public void run() {
        // przesuniecie na srodek
        aft.translate(coordinateX, coordinateY);
        area.transform(aft);
        shape = area;
        while (true) {
            // przygotowanie nastepnego kadru
            shape = nextFrame();
            try {
                Thread.sleep(delay.get());
            } catch (InterruptedException e) {
            }
        }
    }

    protected Shape nextFrame() {
        // zapamietanie na zmiennej tymczasowej
        // aby nie przeszkadzalo w wykreslaniu
        area = new Area(area);
        aft = new AffineTransform();
        Rectangle bounds = area.getBounds();
        int cx = bounds.x + bounds.width / 2;
        int cy = bounds.y + bounds.height / 2;

        if(!paused) {	//poruszanie tylko wtedy kiedy pauza jest nieaktywna (paused == false)

                // odbicie
                if (cx + bounds.width/2 <= bounds.width) dx = Math.abs(dx);

                if (cx - bounds.width/2 >= width - bounds.width && dx > 0) dx = -dx;

                if (cy + bounds.height/2 <= bounds.height) dy = Math.abs(dy);

                if (cy - bounds.height/2 >= height - bounds.height && dy > 0) dy = -dy;

                // zwiekszenie lub zmniejszenie
                if (bounds.height > height / 3 || bounds.height < 10)
                    sf = 1 / sf;

                // konstrukcja przeksztalcenia
                aft.translate(cx, cy);
                aft.scale(sf, sf);				//skalowanie po zwiekszeniu/zmniejszeniu
                aft.rotate(an);					//rotacja na podstawie kąta an
                aft.translate(-cx, -cy);
                aft.translate(dx, dy);
                // przeksztalcenie obiektu
                area.transform(aft);

        }
        return area;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // wypelnienie obiektu
        buffer.setColor(clr.brighter());
        buffer.fill(shape);
        // wykreslenie ramki
        buffer.setColor(clr.darker());
        buffer.draw(shape);
    }

    public static void setPaused(boolean paused) {
        Figure.paused = paused;
    }
    public static AtomicInteger getDelay() {
        return delay;
    }

    public static void setClrToRemember(Color clr) {
        Figure.clrToRemember = clr;
    }

    public void setAn(double an) {
        this.an = an;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public double getAn() {
        return an;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static void setCoordinateX(double x) {
        Figure.coordinateX = x;
    }

    public static void setCoordinateY(double y) {
        Figure.coordinateY = y;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
