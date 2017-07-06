package com.evgen.EventApp;


import com.evgen.EventApp.core.Window;
import com.evgen.EventApp.sandbox.Example;
import com.evgen.EventApp.sandbox.Out;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Window win = new Window("Hi", 960, 640);


        win.addLayer(new Example("Layer-1", Color.BLUE));
        win.addLayer(new Example("Layer-2", Color.RED));
        win.addLayer(new Out("Layer-3", Color.BLACK));
    }

}
