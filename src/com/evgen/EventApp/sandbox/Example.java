package com.evgen.EventApp.sandbox;


import com.evgen.EventApp.events.Dispatcher;
import com.evgen.EventApp.events.Event;
import com.evgen.EventApp.events.EventHandler;
import com.evgen.EventApp.events.types.MouseMotionEvent;
import com.evgen.EventApp.events.types.MousePressedEvent;
import com.evgen.EventApp.events.types.MouseReleasedEvent;
import com.evgen.EventApp.layers.Layer;

import java.awt.*;
import java.util.Random;

public class Example extends Layer{


    protected String name;
    protected Color color;
    protected Rectangle rect;
    protected boolean dragging = false;
    protected int px, py;

    protected static final Random random = new Random();



    public Example(String name, Color color) {
        this.name = name;
        this.color = color;

        rect = new Rectangle(random.nextInt(100) + 50, random.nextInt(100) + 250, 120 , 240);
    }


    public void onEvent(Event event) {
       // System.out.println(event);

        Dispatcher dispatcher = new Dispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onPressed((MousePressedEvent)e));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onReleased((MouseReleasedEvent)e)) ;
        dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMoved((MouseMotionEvent)e)) ;

    }

    public void onRender(Graphics g) {
        g.setColor(this.color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.WHITE);
        g.drawString(name, rect.x + 15, rect.y + 35);

    }

    private boolean onPressed(MousePressedEvent event) {
        if (rect.contains(new Point(event.getX(), event.getY())))
            dragging = true;
            //System.out.println("Mouse pressed "  + event.getKeyCode());

//        return false;
        return dragging;
    }

    private boolean onReleased(MouseReleasedEvent event) {

           // System.out.println("Mouse released ");
        dragging = false;
        return dragging;
    }

    private boolean onMoved(MouseMotionEvent event) {

          //  System.out.println("Mouse moved "  + event.getX() + " | " + event.getY());
        if (dragging) {
            rect.x += event.getX() - px;
            rect.y += event.getY() - py;
        }
        px = event.getX();
        py = event.getY();
        return dragging;
    }

}
