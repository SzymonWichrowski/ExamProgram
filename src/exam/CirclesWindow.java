package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CirclesWindow extends JFrame implements KeyListener {

    private JTextField circleX_tField, circleY_tField;
    private JLabel coordinates_label;
    private JButton btnAdd, btnAnimate;

    private Font buttonFont = new Font("Dialog", Font.ITALIC, 14);
    private Color colorOfBtn = new Color(182, 127, 71);
    public CirclesWindow() {

        setSize(1200, 800);
        setTitle("Circles");
        setLayout(null);
        getContentPane().setBackground(new Color(70, 68, 68));

        AnimPanel canva = new AnimPanel();
        canva.setBounds(50, 50, 1100, 500);
        add(canva);
        SwingUtilities.invokeLater(() -> canva.initialize());

        btnAdd = new JButton("Add");	//dodanie przycisku add
        btnAdd.setBounds(450, 600, 80, 23);
        btnAdd.setBackground(colorOfBtn);
        btnAdd.setFont(buttonFont);
        btnAdd.setEnabled(false);	//blokada przycisku add
        add(btnAdd);
        btnAdd.addActionListener(e -> {
            canva.addFigure();
        });

        btnAnimate = new JButton("Animate/Stop");	//dodanie przycisku animacji
        btnAnimate.setBounds(250, 600, 150, 23);
        btnAnimate.setBackground(colorOfBtn);
        btnAnimate.setFont(buttonFont);
        add(btnAnimate);
        btnAnimate.addActionListener(e -> {
            canva.animate();
            if(!btnAdd.isEnabled()) btnAdd.setEnabled(true);	//odblokowanie przycisku add
            else btnAdd.setEnabled(false);						//zablokowanie przycisku add
        });

        coordinates_label = new JLabel("Coordinates");
        coordinates_label.setFont(buttonFont);
        coordinates_label.setForeground(colorOfBtn);
        coordinates_label.setBounds(90, 600, 100, 30);
        add(coordinates_label);

        circleX_tField = new JTextField();
        circleX_tField.setBounds(50, 650, 50, 25);
        circleX_tField.setFont(buttonFont);
        circleX_tField.setBackground(new Color(176, 136, 95));
        add(circleX_tField);


        circleY_tField = new JTextField();
        circleY_tField.setBounds(150, 650, 50, 25);
        circleY_tField.setFont(buttonFont);
        circleY_tField.setBackground(new Color(176, 136, 95));
        add(circleY_tField);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
