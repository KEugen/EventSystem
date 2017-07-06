package com.evgen.EventApp.events.types;


import com.evgen.EventApp.events.Event;

public class MousePressedEvent extends MouseButtonEvent {

    public MousePressedEvent(int keyCode, int x, int y) {
        super(Type.MOUSE_PRESSED, keyCode, x, y);
    }
}
