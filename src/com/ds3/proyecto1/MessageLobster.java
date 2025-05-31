package com.ds3.proyecto1;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MessageLobster extends MethodsPosition{

    protected JTextField messageCoordinates;
    protected JLabel labelAttempts, labelLocation;
    protected JButton buttonCoordinates;

    protected MessageLobster(JFrame frame){
        super();
        messageCoordinates = new JTextField();
        messageCoordinates.setBounds(635, 410, 130, 25);
        messageCoordinates.setEnabled(false);
        messageCoordinates.setVisible(false);

        buttonCoordinates = new JButton("Localizacion");
        buttonCoordinates.setBounds(770, 410, 130, 25);

        labelAttempts = new LabelData("Intentos: 0",635, 440,130,25).generatingLabel(); // instantiating label attempts
        labelLocation = new LabelData("se movio",635,480,250,25).generatingLabel(); // instantiating label location

        List<JComponent> components = Arrays.asList(labelLocation,labelAttempts, messageCoordinates, buttonCoordinates);
        components.forEach(frame::add);  // adding components of the class MessageLobster

    }

    //  this method places the shoal in a new position
    protected void positionLobster() {
        int random = (int) (Math.random() * 99 + 0); // a random value from 0 to 99
       Point point = listButtonField.stream()
                .filter(button -> Integer.parseInt(button.getText()) == random)
                .peek(button -> {
                    buttonLobster = button; //  give value to the lobster button
//                    button.setBackground(Color.red);  // changing the color of button lobster
                })
                .map(JButton::getLocation)  // adding location of button lobster
               .findFirst().orElse(null);
        this.messageCoordinates.setText( String.format("X=%s | Y=%s", point.x, point.y)); //adding message points of the lobster)
    }
}