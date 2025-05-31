package com.ds3.proyecto1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MethodsPosition {
    protected List<JButton> listButtonField, listButtonsAround;
    protected JButton buttonLobster;

    public MethodsPosition(){
        this.listButtonField = new ArrayList<>(); //list button field initial
        this.listButtonsAround = new ArrayList<>(); //list buttons around of the lobster initial
    }

    public void changeButtonsAroundLobster() {
        try {
            listButtonsAround.clear();  //removing all values from the list

            JButton buttonAbove = searchButtonAround(buttonLobster,0,-50); // searching button above of lobster
            JButton buttonBelow = searchButtonAround(buttonLobster,0,50);// searching button below of lobster

            JButton buttonSideRight = searchButtonAround(buttonBelow,50,-100);  //  Button side right(above)
            JButton buttonSideLeft = searchButtonAround(buttonAbove, -50,100); //  Button side left(below)

            // right click to get the other ones down
            searchButtonAround(buttonSideRight,0,50);   //adding right middle button
            searchButtonAround(buttonSideRight,0,100); // adding right below button

            //  Right click to see the others above.
            searchButtonAround(buttonSideLeft,0,-50);  //  adding left middle button
            searchButtonAround(buttonSideLeft,0,-100);  //   adding left above button

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private JButton searchButtonAround(JButton button, int sumX, int sumY){
        Point point = button.getLocation();
        return listButtonField.stream()
                .filter(loc -> loc.getLocation().x == point.x + (sumX))
                .filter(loc -> loc.getLocation().y == (point.y + (sumY)))
                .peek(this::addButtonList).findFirst().orElse(null);
    }

    private void addButtonList(JButton button){
//        button.setBackground(Color.GREEN);
        listButtonsAround.add(button);
    }

    // method for the corners of x and y
    private void lobsterBetweenXandY(int pointx, int pointy){
        int y = (pointy == 500)? -50: 50;
        int x = (pointx == 550)? -50 : 50;
        if(pointy == 500 || pointy == 50 ){
            searchButtonAround(buttonLobster,x,0);
            searchButtonAround(buttonLobster,x,y);
            searchButtonAround(buttonLobster,0,y);
        }else{
            searchButtonAround(buttonLobster,0,-50);
            searchButtonAround(buttonLobster,x,-50);
            searchButtonAround(buttonLobster,x,0);
            searchButtonAround(buttonLobster,x,50);
            searchButtonAround(buttonLobster,0,50);
        }
//        buttonLobster.setBackground(Color.red);
    }

    //  main method that verifies if the button is on the sides or corners
    public void lobsterInCorner() {
        Point point = buttonLobster.getLocation();
        switch (point.x) {
            case 100, 550 -> lobsterBetweenXandY(point.x,point.y);
            default -> {
                if (point.y == 50 || point.y == 500) {
                    int y = point.y == 50? 50: -50;
                    searchButtonAround(buttonLobster,-50,0);
                    searchButtonAround(buttonLobster,-50,y);
                    searchButtonAround(buttonLobster,0,y);
                    searchButtonAround(buttonLobster,50,y);
                    searchButtonAround(buttonLobster,50,0);
//                    buttonLobster.setBackground(Color.red);
                }
            }
        }
    }
}