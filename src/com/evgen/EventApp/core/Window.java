package com.evgen.EventApp.core;


import com.evgen.EventApp.events.*;
import com.evgen.EventApp.events.Event;
import com.evgen.EventApp.events.types.MouseMotionEvent;
import com.evgen.EventApp.events.types.MousePressedEvent;
import com.evgen.EventApp.events.types.MouseReleasedEvent;
import com.evgen.EventApp.layers.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.List;

public class Window extends Canvas {

    private BufferStrategy bs;
    private Graphics g;
    private JFrame frame;

    private List<Layer> layers = new ArrayList<>();

    public Window(String name, int width, int height) {
        setPreferredSize(new Dimension(width, height));


        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
                System.out.println("Mouse preessed: " + event.getKeyCode());
            }

            public void mouseReleased(MouseEvent e) {
                MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
                System.out.println("Mouse released ");
            }

        });

        addMouseMotionListener(new MouseMotionListener() {


            public void mouseMoved(MouseEvent e) {
                MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), false);
                onEvent(event);
                System.out.println("Mouse moved " +  event.getX() + " | " + event.getY());
            }

            public void mouseDragged(MouseEvent e) {
                MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), true);
                onEvent(event);
            }
        });


        init(name);
        //onRender(g);
        render();
    }

    private void init(String name) {
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private void render() {
        if (bs == null)
            createBufferStrategy(3);
        bs = getBufferStrategy();


        g = bs.getDrawGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, getWidth(), getHeight());

        onRender(g);
        g.dispose();

        bs.show();


//        try {
//            Thread.sleep(3);
//        } catch (InterruptedException e) {
//
//        }

        EventQueue.invokeLater(() -> render());


    }

    private void onRender(Graphics g) {
        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).onRender(g);
        }
    }

    private void onEvent(Event event) {
        for (int i = layers.size() - 1; i >= 0 ; i--) {
            layers.get(i).onEvent(event);
        }
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

}
