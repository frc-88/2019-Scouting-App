package org.robotics.tj2.scout88;

import java.util.ArrayList;

public class Performance {
    private String scouter;
    private int teamNumber;
    private int matchNumber;
    private int numHighPanels;
    private int numHighPanelsSS;
    private int numHighCargo;
    private int numHighCargoSS;
    private int numMedPanels;
    private int numMedPanelsSS;
    private int numMedCargo;
    private int numMedCargoSS;
    private int numLowPanels;
    private int numLowPanelsSS;
    private int numLowCargo;
    private int numLowCargoSS;
    private ArrayList<Integer> cargo;
    //0=not scored, 1=scored in teleop, 2=scored in sandstorm, 3=null panel
    private ArrayList<Integer> panels;
    private int levelOfClimb;
    private int startingLevel;
    private String comments;
    private String startingElement;
    private int crossInSandstorm;
    private double defense; //time in seconds spent on defense

    public Performance() {
        teamNumber = 0;
        matchNumber = 1;
        cargo = fillArrayList();
        panels = fillArrayList();
        levelOfClimb = -1;
        startingLevel = 0;
        comments = "";
        startingElement = "";
        defense = 0.0;
        crossInSandstorm = 0;
        scouter = "";
    }

    @Override
    public String toString(){
        return teamNumber + "_" + matchNumber + ": " + "\n" +
                "scouter: " + scouter + "\n" +
                "cargo: " + cargo + "\n" +
                "panels: " + panels + "\n" +
                "ss: " + crossInSandstorm + "\n" +
                "start level: " + startingLevel + "with" + startingElement + "\n" +
                "def time: " + defense + "\n" +
                "climb level: " + levelOfClimb + "\n" +
                "comments: " + comments + "\n";

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

    public ArrayList<Integer> getCargo() {
        return cargo;
    }

    public void setCargo(ArrayList<Integer> cargo) {
        this.cargo = cargo;
    }

    public ArrayList<Integer> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<Integer> panels) {
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

    public void setStartingElement(String startingElement) {
        this.startingElement = startingElement;
    }

    public String getScouter() {
        return scouter;
    }

    public void setScouter(String scouter) {
        this.scouter = scouter;
    }

    public int getNumHighCargo() {
        return numHighCargo;
    }

    public int getNumHighCargoSS() {
        return numHighCargoSS;
    }

    public int getNumHighPanels() {
        return numHighPanels;
    }

    public int getNumHighPanelsSS() {
        return numHighPanelsSS;
    }

    public int getNumLowCargo() {
        return numLowCargo;
    }

    public int getNumLowCargoSS() {
        return numLowCargoSS;
    }

    public int getNumLowPanels() {
        return numLowPanels;
    }

    public int getNumLowPanelsSS() {
        return numLowPanelsSS;
    }

    public int getNumMedCargo() {
        return numMedCargo;
    }

    public int getNumMedCargoSS() {
        return numMedCargoSS;
    }

    public int getNumMedPanels() {
        return numMedPanels;
    }

    public int getNumMedPanelsSS() {
        return numMedPanelsSS;
    }

    public void setNumHighCargo(int numHighCargo) {
        this.numHighCargo = numHighCargo;
    }

    public void setNumHighPanels(int numHighPanels) {
        this.numHighPanels = numHighPanels;
    }

    public void setNumHighCargoSS(int numHighCargoSS) {
        this.numHighCargoSS = numHighCargoSS;
    }

    public void setNumHighPanelsSS(int numHighPanelsSS) {
        this.numHighPanelsSS = numHighPanelsSS;
    }

    public void setNumLowCargo(int numLowCargo) {
        this.numLowCargo = numLowCargo;
    }

    public void setNumMedPanels(int numMedPanels) {
        this.numMedPanels = numMedPanels;
    }

    public void setNumLowCargoSS(int numLowCargoSS) {
        this.numLowCargoSS = numLowCargoSS;
    }

    public void setNumMedCargo(int numMedCargo) {
        this.numMedCargo = numMedCargo;
    }

    public void setNumLowPanels(int numLowPanels) {
        this.numLowPanels = numLowPanels;
    }

    public void setNumLowPanelsSS(int numLowPanelsSS) {
        this.numLowPanelsSS = numLowPanelsSS;
    }

    public void setNumMedCargoSS(int numMedCargoSS) {
        this.numMedCargoSS = numMedCargoSS;
    }

    public void setNumMedPanelsSS(int numMedPanelsSS) {
        this.numMedPanelsSS = numMedPanelsSS;
    }

    public String getStartingElement() {
        return startingElement;
    }

    public int getCrossInSandstorm(){
        return crossInSandstorm;
    }

    public void setCrossInSandstorm(int v){
        crossInSandstorm = v;
    }

    private ArrayList<Integer> fillArrayList(){
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            al.add(0);
        }
        return al;
    }
}
