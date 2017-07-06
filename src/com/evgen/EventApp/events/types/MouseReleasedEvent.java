package com.evgen.EventApp.events.types;


import com.evgen.EventApp.events.Event;

public class MouseReleasedEvent extends MouseButtonEvent {

    public MouseReleasedEvent(int keyCode, int x, int y) {
        super(Type.MOUSE_RELEASED, keyCode, x, y);
    }
}
