package org.robotics.tj2.scout88.etc;

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
    private int mvp;
    private int strongDefense;
    private int broken;
    private int beans;
    private int noShow;

    private int totalEandSS;
    private int totalPoints;
    private int earnedRP;
    private int totalCargo;
    private int totalPanel;

    //private double defense; //time in seconds spent on defense


    public Performance() {
        teamNumber = 0;
        matchNumber = 1;
        cargo = fillArrayList();
        panels = fillArrayList();
        levelOfClimb = 0;
        startingLevel = 0;
        comments = "";
        startingElement = "none";
        //defense = 0.0;
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
                //"def time: " + defense + "\n" +
                "climb level: " + levelOfClimb + "\n" +
                "comments: " + comments + "\n";

    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() == this.getClass()){
            Performance p = (Performance) o;
            return (this.getMatchNumber() == p.getMatchNumber() && p.getTeamNumber() == this.getTeamNumber());
        }
        return false;
    }

    public void setNoShow(int noShow) {
        this.noShow = noShow;
    }

    public int getNoShow() {
        return noShow;
    }

    public int getEarnedRP() {
        return earnedRP;
    }

    public int getTotalCargo() {
        return totalCargo;
    }

    public int getTotalEandSS() {
        return totalEandSS;
    }

    public int getTotalPanel() {
        return totalPanel;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setEarnedRP(int earnedRP) {
        this.earnedRP = earnedRP;
    }

    public void setTotalCargo(int totalCargo) {
        this.totalCargo = totalCargo;
    }

    public void setTotalEandSS(int totalEandSS) {
        this.totalEandSS = totalEandSS;
    }

    public void setTotalPanel(int totalPanel) {
        this.totalPanel = totalPanel;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getBeans() {
        return beans;
    }

    public int getMvp() {
        return mvp;
    }

    public int getBroken() {
        return broken;
    }

    public int getStrongDefense() {
        return strongDefense;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public void setStrongDefense(int strongDefense) {
        this.strongDefense = strongDefense;
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

//    public double getDefense() {
//        return defense;
//    }
//
//    public void setDefense(double defense) {
//        this.defense = defense;
//    }

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
        if (numHighCargo + numHighCargoSS > 4 || numHighCargo < 0){
            return;
        }
        this.numHighCargo = numHighCargo;
    }

    public void setNumHighPanels(int numHighPanels) {
        if (numHighPanels + numHighPanelsSS > 4 || numHighPanels < 0){
            return;
        }
        this.numHighPanels = numHighPanels;
    }

    public void setNumHighCargoSS(int numHighCargoSS) {
        if (numHighCargo + numHighCargoSS > 4 || numHighCargoSS < 0){
            return;
        }
        this.numHighCargoSS = numHighCargoSS;
    }

    public void setNumHighPanelsSS(int numHighPanelsSS) {
        if (numHighPanels + numHighPanelsSS > 4 || numHighPanelsSS < 0){
            return;
        }
        this.numHighPanelsSS = numHighPanelsSS;
    }

    public void setNumLowCargo(int numLowCargo) {
        if (numLowCargo + numLowCargoSS > 4 || numLowCargo < 0){
            return;
        }
        this.numLowCargo = numLowCargo;
    }

    public void setNumMedPanels(int numMedPanels) {
        if (numMedPanels + numMedPanelsSS > 4 || numMedPanels < 0){
            return;
        }
        this.numMedPanels = numMedPanels;
    }

    public void setNumLowCargoSS(int numLowCargoSS) {
        if (numLowCargo + numLowCargoSS > 4 || numLowCargoSS < 0){
            return;
        }
        this.numLowCargoSS = numLowCargoSS;
    }

    public void setNumMedCargo(int numMedCargo) {
        if (numMedCargo + numMedCargoSS > 4 || numMedCargo < 0){
            return;
        }
        this.numMedCargo = numMedCargo;
    }

    public void setNumLowPanels(int numLowPanels) {
        if (numLowPanels + numLowPanelsSS > 4 || numLowPanels < 0){
            return;
        }
        this.numLowPanels = numLowPanels;
    }

    public void setNumLowPanelsSS(int numLowPanelsSS) {
        if (numLowPanels + numLowPanelsSS > 4 || numLowPanelsSS < 0){
            return;
        }
        this.numLowPanelsSS = numLowPanelsSS;
    }

    public void setNumMedCargoSS(int numMedCargoSS) {
        if (numMedCargo + numMedCargoSS > 4 || numMedCargoSS < 0){
            return;
        }
        this.numMedCargoSS = numMedCargoSS;
    }

    public void setNumMedPanelsSS(int numMedPanelsSS) {
        if (numMedPanels + numMedPanelsSS > 4 || numMedPanelsSS < 0){
            return;
        }
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

    public void setIndividualCargoBayCargo(int scored , int index){
        cargo.set(index , scored);
    }

    public void setIndividualCargoBayPanel(int scored , int index){
        panels.set(index , scored);
    }

    public int getEndPoints() {
        switch(levelOfClimb) {
            case 1:
                return(3);
            case 2:
                return(6);
            case 3:
                return(12);
            default:
                return(0);
        }
    }

    public void totals(){

        //panels and cargo
        totalCargo = numHighCargo + numLowCargo + numMedCargo + numHighCargoSS + numLowCargoSS + numHighCargoSS;
        totalPanel = numHighPanels + numLowPanels + numMedPanels + numHighPanelsSS + numLowPanelsSS + numMedPanelsSS;

        for(int i = 0; i < cargo.size(); i++) {
            if (cargo.get(i)== 1|| cargo.get(i) == 2) {
                totalCargo++;
            }
        }
        for(int i = 0; i < panels.size(); i++) {
            if (panels.get(i)== 1|| panels.get(i) == 2) {
                totalPanel++;
            }
        }
        //endgame and sandstorm
        switch(crossInSandstorm) {
            case 1:
                totalEandSS = 3;
                break;
            case 2:
                totalEandSS = 6;
                break;
            default:
                totalEandSS = 0;
                break;
        }
        switch(levelOfClimb) {
            case 1:
                totalEandSS+=3;
                break;
            case 2:
                totalEandSS+=6;
                break;
            case 3:
                totalEandSS+=12;
                break;
            default:
                break;
        }
        totalPoints = totalEandSS + (2* totalPanel) + (3* totalCargo);
    }

}
