package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimPanel extends JPanel implements ActionListener {


    // bufor
    Image image;
    // wykreslacz ekranowy
    Graphics2D device;
    // wykreslacz bufora
    Graphics2D buffer;
    private int delay = 70, width, height;
    private Timer timer;

    public AnimPanel() {
        super();
        setBackground(Color.WHITE);
        timer = new Timer(delay, this);
    }

    public void initialize() {
        width = getWidth();
        height = getHeight();

        image = createImage(width, height);		//tworzy off-screen image potrzebny do podwójnego buforowania (bufor)
        buffer = (Graphics2D) image.getGraphics();		//przypisujemy kontekst graficzny bufora do zmiennej buffer
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();	//przypisujemy kontekst graficzny naszego okna do zmiennej device
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    void addFigure() {
        Figure fig = new CircleFig(buffer, delay, getWidth(), getHeight());
        timer.addActionListener(fig);
        new Thread(fig).start();
    }

    void animate() {
        if (timer.isRunning()) {
            Figure.setPaused(true);		//pauza
            timer.stop();
        } else {
            timer.start();
            Figure.setPaused(false);	//koniec pauzy
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == timer) {
            device.drawImage(image, 0, 0, null);
            buffer.clearRect(0, 0, getWidth(), getHeight());
        }
    }
}
