package com.evgen.EventApp.sandbox;


import com.evgen.EventApp.events.*;
import com.evgen.EventApp.events.Event;
import com.evgen.EventApp.events.types.MouseMotionEvent;
import com.evgen.EventApp.events.types.MousePressedEvent;
import com.evgen.EventApp.events.types.MouseReleasedEvent;

import java.awt.*;

public class Out extends Example {

    private Event event;

    public Out(String name, Color color) {
        super(name, color);
    }

    @Override
    public void onEvent(Event event) {
        super.onEvent(event);
        this.event = event;
    }

    @Override
    public void onRender(Graphics g) {
        g.setColor(this.color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.WHITE);
        switch (event.getType()) {
            case MOUSE_MOVED:
                g.drawString("OUT : Mouse moved "  , rect.x + 10, rect.y + 10);
                g.drawString("X : " + ((MouseMotionEvent) event).getX()  , rect.x + 10, rect.y + 25);
                g.drawString("Y : " + ((MouseMotionEvent) event).getY()  , rect.x + 10, rect.y + 40);
                break;
            case MOUSE_PRESSED:
                g.drawString("OUT : Mouse pressed "  , rect.x + 10, rect.y + 10);
                g.drawString("Button : " + ((MousePressedEvent)event).getKeyCode() , rect.x + 10, rect.y + 25);
                break;
            case MOUSE_RELEASED:
                g.drawString("OUT : Mouse released "  , rect.x + 10, rect.y + 10);
                break;
        }

    }
}
