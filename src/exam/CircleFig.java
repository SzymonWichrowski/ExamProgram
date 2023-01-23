package exam;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class CircleFig extends Figure{

    public CircleFig(Graphics2D buffer, int delay, int width, int height) {

        super(buffer, delay, width, height);

        Random random = new Random();
        double r = random.nextDouble(50);   // losowanie długości promienia
        shape = new Ellipse2D.Double(0, 0, r, r);
        area = new Area(shape);
        aft = new AffineTransform();
    }
}
