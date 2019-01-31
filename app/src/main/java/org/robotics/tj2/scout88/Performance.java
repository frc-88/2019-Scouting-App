package org.robotics.tj2.scout88;

import java.util.ArrayList;

public class Performance {
    private int teamNumber;
    private int matchNumber;
    //0=not scored, 1=scored in teleop, 2=scored in sandstorm
    private ArrayList<String> cargo;
    //0=not scored, 1=scored in teleop, 2=scored in sandstorm, 3=null panel
    private ArrayList<String> panels;
    private int levelOfClimb;
    private int startingLevel;
    private String comments;
    private double defense; //time in seconds spent on defense

    public Performance(){
        //default needed for Firebase
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public ArrayList<String> getCargo() {
        return cargo;
    }

    public void setCargo(ArrayList<String> cargo) {
        this.cargo = cargo;
    }

    public ArrayList<String> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<String> panels) {
        this.panels = panels;
    }

    public int getLevelOfClimb() {
        return levelOfClimb;
    }

    public void setLevelOfClimb(int levelOfClimb) {
        this.levelOfClimb = levelOfClimb;
    }

    public int getStartingLevel() {
        return startingLevel;
    }

    public void setStartingLevel(int startingLevel) {
        this.startingLevel = startingLevel;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
