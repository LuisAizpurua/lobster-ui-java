package com.ds3.proyecto1;

import javax.swing.*;
import java.awt.*;

public enum Location {
    SUROESTE("suroeste"),NOROESTE("noroeste"),
    NORESTE("noreste"), SURESTE("sureste"),
    NORTE("norte"), SUR("sur"),
    OESTE("oeste"),ESTE("este"),
    CENTRO("centro");

    private String pointCardinal;
    Location(String puntoCardinal){
        this.pointCardinal = puntoCardinal;
    }
    public static Location motion(JButton button) {
        Point point = button.getLocation();
        int x = point.x;
        int y = point.y;

        if (x <= 550 && y <= 200) return Location.NOROESTE;
        if (x <= 550 && y >= 350) return Location.SUROESTE;
        if (x >= 800 && y <= 200) return Location.NORESTE;
        if (x >= 800 && y >= 350) return Location.SURESTE;

        if (x >= 600 && x <= 750) {
            if (y <= 200) return Location.NORTE;
            if (y >= 350) return Location.SUR;
        }

        if (y >= 250 && y <= 300) {
            if (x <= 650) return Location.OESTE;
            return Location.ESTE;
        }
        return Location.CENTRO;
    }

    public String getPointCardinal() {
        return pointCardinal;
    }
}
