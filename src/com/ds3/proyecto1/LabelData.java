package com.ds3.proyecto1;

import javax.swing.*;

public record LabelData(String text, int x, int y, int w, int h) {
    private static JLabel label = null;

    public JLabel generatingLabel(){
        label = new JLabel(text);
        label.setBounds(x,y,w,h);
        return label;
    }
}
