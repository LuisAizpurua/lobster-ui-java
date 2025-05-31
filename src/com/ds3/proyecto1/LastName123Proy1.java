package com.ds3.proyecto1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class LastName123Proy1 extends MessageLobster implements ActionListener{

    private static JFrame frame;
    private static final Presentation presentacion = new Presentation();//  Project presentation
    private static final Student student = new Student("Mute-Dev","https://github.com/LuisAizpurua/project1-DSIII-java.git");   //  Student presentation

    public static void main(final String[] args) {
        new LastName123Proy1( createdFrame() ); // Up the project
    }

    private static JFrame createdFrame(){
        frame = new JFrame("Proyecto 1");    //  creating the JFrame
        frame.setBounds(450, 110, 1000, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    public LastName123Proy1(JFrame frame){
        super(frame);   //  Generating message from construct

        //Generating Presentation
        AtomicInteger count = new AtomicInteger(10);
         List.of(presentacion, student)
                 .stream()
                 .map(IteratorFields::listFields) //    getting field list
                 .flatMap(List::stream)//from list to stream
                 .map(value->{
                     int y = count.accumulateAndGet(30,Integer::sum); // add counter
                     return new LabelData(value,665,y,1000,100);
                 })
                 .map(LabelData::generatingLabel)
                 .forEach(frame::add);

        generatingButton(frame, 100,500); //    Generation the 99 buttons of JFrame

        countAroundJFrame(frame); //    Numbers around the buttons

        //Adding label over the coordinates
        LabelData[] localizacion = {
                new LabelData("NORTE",305, -35, 500, 100),
                new LabelData("SUR",310, 525, 500, 100),
                new LabelData("OESTE",10, 285, 500, 100),
                new LabelData("ESTE",630, 285, 500, 100),
        };
        for(LabelData loc: localizacion ){
            frame.add(loc.generatingLabel());   //  Adding frame
        }

        buttonCoordinates.addActionListener(this); //   adding the action listener to button coordinates

        positionLobster();//  this method places the shoal in a new position
        changeButtonsAroundLobster();
        labelLocation.setText("Se movio al " + Location.motion(buttonLobster) );

        frame.setVisible(true); //  Visible window
    }

    //generating numbers around of frame
    private void countAroundJFrame(JFrame frame) {
        final int basex = 75;//base x
        final int basey = 5;//base y

        BiFunction<Integer,Integer,Integer> formula = (offset ,index) -> offset + 50 * (index % 11); //formula

        IntStream.of(0,1).forEach( item -> {
            for (int i = item; i < 11; i++) {

                int y = (item == 1)? formula.apply(basey,i): basey + 5; //value Y ----- inferior=535 | superior=5
                int x = (item == 0)? formula.apply(basex,i): basex;

                JLabel label = new LabelData(String.valueOf(i+1), x, y, 50, 50).generatingLabel();
                frame.add(label);
            }
        });
    }

    //generating the crop field buttons
    private void generatingButton(JFrame frame, int x, int y){
        JButton boton = null;
        for (int i = 99; i >= 0; i--) {
            boton = new JButton(String.valueOf(i));
            boton.setBounds(x + 50 * (-i / -10), y + 50 * (-i % 10), 50, 50); //adding position in the frame

            listButtonField.add(boton); //list fields of the button
            boton.addActionListener(this); //adding the action listener
            frame.add(boton);//add button
        }
    }
    //crop button action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonAction = (JButton) e.getSource();  // getting the button pressed
        int countAttempt = Integer.parseInt(labelAttempts.getText().split(": ")[1]); // converting the text attempt into an array to get the index (the number of attempts)

        String[] options = {"YES","NOT"};
        if (buttonAction == buttonLobster) {
            int option = JOptionPane.showOptionDialog(null, "Capturaste la langosta con " + countAttempt + " intentos \n\n Quieres seguir jugando?", "Juego Terminado", 0, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (option == 0) {
                frame.setVisible(false);  // hidden frame current
                new LastName123Proy1(createdFrame()); // up new frame

            } else { System.exit(0); }
        }else{
            boolean access = false;
            for (JButton buttonAround : listButtonsAround) {
                if (buttonAction == buttonAround) {access = true; break;}
            }
            if(access){
                positionLobster(); // moving the lobster position
                changeButtonsAroundLobster(); // change buttons around of lobster
                lobsterInCorner(); // if it's in the corner
                labelLocation.setText("Se movio al " + Location.motion(buttonLobster) );
            }
        }
        // button action coordinate to show or hide location
        if(buttonAction == buttonCoordinates){
            boolean isHidden = buttonCoordinates.getText().equals("Localizacion"); // equals with the text 'Localization'
            String text = isHidden? "Ocultar":"Localizacion"; // ternary operator for new text
            buttonCoordinates.setText(text); // adding text to button coordinates
            messageCoordinates.setVisible(isHidden); // hidden or open
        }else{
            labelAttempts.setText("Intentos: " + (countAttempt+1)); //  adding new text try}
        }
    }
}