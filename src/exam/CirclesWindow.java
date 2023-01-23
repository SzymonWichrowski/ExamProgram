package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CirclesWindow extends JFrame {

    private JTextField circleX_tField, circleY_tField;
    private JLabel coordinates_label, x_label, y_label;
    private JButton btnAdd, btnAnimate, btnColor;

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
        btnAdd.setBounds(550, 600, 100, 100);
        btnAdd.setBackground(colorOfBtn);
        btnAdd.setFont(buttonFont);
        btnAdd.setEnabled(false);	//blokada przycisku add
        add(btnAdd);
        btnAdd.addActionListener(e -> {
            canva.addFigure();
        });

        btnAnimate = new JButton("Animate/Stop");	//dodanie przycisku animacji
        btnAnimate.setBounds(350, 600, 150, 100);
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
        coordinates_label.setBounds(85, 600, 150, 30);
        add(coordinates_label);

        x_label = new JLabel("x");
        x_label.setFont(buttonFont);
        x_label.setForeground(colorOfBtn);
        x_label.setBounds(70, 620, 50, 25);
        add(x_label);

        y_label = new JLabel("y");
        y_label.setFont(buttonFont);
        y_label.setForeground(colorOfBtn);
        y_label.setBounds(170, 620, 50 , 25);
        add(y_label);

        circleX_tField = new JTextField();
        circleX_tField.setBounds(50, 650, 50, 25);
        circleX_tField.setFont(buttonFont);
        circleX_tField.setBackground(new Color(176, 136, 95));
        add(circleX_tField);
        circleX_tField.addActionListener(e -> {
            if(!circleX_tField.getText().isEmpty()) {
                Figure.setCoordinateX(Double.parseDouble(circleX_tField.getText()));
                Random random = new Random();
                circleX_tField.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            }
        });

        circleY_tField = new JTextField();
        circleY_tField.setBounds(150, 650, 50, 25);
        circleY_tField.setFont(buttonFont);
        circleY_tField.setBackground(new Color(176, 136, 95));
        add(circleY_tField);
        circleY_tField.addActionListener(e -> {
            if(!circleY_tField.getText().isEmpty()) {
                Figure.setCoordinateY(Double.parseDouble(circleY_tField.getText()));
                Random random = new Random();
                circleY_tField.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            }
        });

        btnColor = new JButton("Color");
        btnColor.setBackground(colorOfBtn);
        btnColor.setFont(buttonFont);
        btnColor.setBounds(750, 600, 100, 100);
        add(btnColor);
        btnColor.addActionListener(e -> {
            Figure.setClrToRemember(JColorChooser.showDialog(null, "Choose color!", Color.BLACK));
        });
    }
}
