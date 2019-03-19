package org.robotics.tj2.scout88.etc;

import java.util.ArrayList;

public class Performance {
    private String Scouter;
    private int Team_Number;
    private int Match_Number;
    private int High_Panels;
    private int Sandstorm_High_Panels;
    private int High_Cargo;
    private int Sandstorm_High_Cargo;
    private int Middle_Panels;
    private int Sandstorm_Middle_Panels;
    private int Middle_Cargo;
    private int Sandstorm_Middle_Cargo;
    private int Low_Panels_Rocket;
    private int Sandstorm_Low_Panels_Rocket;
    private int Low_Cargo_Rocket;
    private int Sandstorm_Low_Cargo_Rocket;
    private ArrayList<Integer> cargo;
    //0=not scored, 1=scored in teleop, 2=scored in sandstorm, 3=null panel
    private ArrayList<Integer> panels;
    private int Climb_Level;
    private int Starting_Position;
    private String Comments;
    private String Starting_Game_Piece;
    private int Sandstorm_Cross;
    private int MVP;
    private int Strong_Defense;
    private int Broken_Or_DCed;
    private int Oof;
    private int No_Show;

    private int totalEandSS;
    private int totalPoints;
    private int earnedRP;
    private int totalCargo;
    private int totalPanel;

    //private double defense; //time in seconds spent on defense


    public Performance() {
        Team_Number = 0;
        Match_Number = 1;
        cargo = fillArrayList();
        panels = fillArrayList();
        Climb_Level = 0;
        Starting_Position = 0;
        Comments = "";
        Starting_Game_Piece = "none";
        //defense = 0.0;
        Sandstorm_Cross = 0;
        Scouter = "";
    }

    @Override
    public String toString(){
        return Team_Number + "_" + Match_Number + ": " + "\n" +
                "Scouter: " + Scouter + "\n" +
                "cargo: " + cargo + "\n" +
                "panels: " + panels + "\n" +
                "ss: " + Sandstorm_Cross + "\n" +
                "start level: " + Starting_Position + "with" + Starting_Game_Piece + "\n" +
                //"def time: " + defense + "\n" +
                "climb level: " + Climb_Level + "\n" +
                "Comments: " + Comments + "\n";

    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() == this.getClass()){
            Performance p = (Performance) o;
            return (this.getMatch_Number() == p.getMatch_Number() && p.getTeam_Number() == this.getTeam_Number());
        }
        return false;
    }

    public void setNo_Show(int no_Show) {
        this.No_Show = no_Show;
    }

    public int getNo_Show() {
        return No_Show;
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

    public int getOof() {
        return Oof;
    }

    public int getMVP() {
        return MVP;
    }

    public int getBroken_Or_DCed() {
        return Broken_Or_DCed;
    }

    public int getStrong_Defense() {
        return Strong_Defense;
    }

    public void setOof(int oof) {
        this.Oof = oof;
    }

    public void setBroken_Or_DCed(int broken_Or_DCed) {
        this.Broken_Or_DCed = broken_Or_DCed;
    }

    public void setMVP(int MVP) {
        this.MVP = MVP;
    }

    public void setStrong_Defense(int strong_Defense) {
        this.Strong_Defense = strong_Defense;
    }

    public int getTeam_Number() {
        return Team_Number;
    }

    public void setTeam_Number(int team_Number) {
        this.Team_Number = team_Number;
    }

    public int getMatch_Number() {
        return Match_Number;
    }

    public void setMatch_Number(int match_Number) {
        this.Match_Number = match_Number;
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

    public int getClimb_Level() {
        return Climb_Level;
    }

    public void setClimb_Level(int climb_Level) {
        this.Climb_Level = climb_Level;
    }

    public int getStarting_Position() {
        return Starting_Position;
    }

    public void setStarting_Position(int starting_Position) {
        this.Starting_Position = starting_Position;
    }

//    public double getDefense() {
//        return defense;
//    }
//
//    public void setDefense(double defense) {
//        this.defense = defense;
//    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        this.Comments = comments;
    }

    public void setStarting_Game_Piece(String starting_Game_Piece) {
        this.Starting_Game_Piece = starting_Game_Piece;
    }

    public String getScouter() {
        return Scouter;
    }

    public void setScouter(String scouter) {
        this.Scouter = scouter;
    }

    public int getHigh_Cargo() {
        return High_Cargo;
    }

    public int getSandstorm_High_Cargo() {
        return Sandstorm_High_Cargo;
    }

    public int getHigh_Panels() {
        return High_Panels;
    }

    public int getSandstorm_High_Panels() {
        return Sandstorm_High_Panels;
    }

    public int getLow_Cargo_Rocket() {
        return Low_Cargo_Rocket;
    }

    public int getSandstorm_Low_Cargo_Rocket() {
        return Sandstorm_Low_Cargo_Rocket;
    }

    public int getLow_Panels_Rocket() {
        return Low_Panels_Rocket;
    }

    public int getSandstorm_Low_Panels_Rocket() {
        return Sandstorm_Low_Panels_Rocket;
    }

    public int getMiddle_Cargo() {
        return Middle_Cargo;
    }

    public int getSandstorm_Middle_Cargo() {
        return Sandstorm_Middle_Cargo;
    }

    public int getMiddle_Panels() {
        return Middle_Panels;
    }

    public int getSandstorm_Middle_Panels() {
        return Sandstorm_Middle_Panels;
    }

    public void setHigh_Cargo(int high_Cargo) {
        if (high_Cargo + Sandstorm_High_Cargo > 4 || high_Cargo < 0){
            return;
        }
        this.High_Cargo = high_Cargo;
    }

    public void setHigh_Panels(int high_Panels) {
        if (high_Panels + Sandstorm_High_Panels > 4 || high_Panels < 0){
            return;
        }
        this.High_Panels = high_Panels;
    }

    public void setSandstorm_High_Cargo(int sandstorm_High_Cargo) {
        if (High_Cargo + sandstorm_High_Cargo > 4 || sandstorm_High_Cargo < 0){
            return;
        }
        this.Sandstorm_High_Cargo = sandstorm_High_Cargo;
    }

    public void setSandstorm_High_Panels(int sandstorm_High_Panels) {
        if (High_Panels + sandstorm_High_Panels > 4 || sandstorm_High_Panels < 0){
            return;
        }
        this.Sandstorm_High_Panels = sandstorm_High_Panels;
    }

    public void setLow_Cargo_Rocket(int low_Cargo_Rocket) {
        if (low_Cargo_Rocket + Sandstorm_Low_Cargo_Rocket > 4 || low_Cargo_Rocket < 0){
            return;
        }
        this.Low_Cargo_Rocket = low_Cargo_Rocket;
    }

    public void setMiddle_Panels(int middle_Panels) {
        if (middle_Panels + Sandstorm_Middle_Panels > 4 || middle_Panels < 0){
            return;
        }
        this.Middle_Panels = middle_Panels;
    }

    public void setSandstorm_Low_Cargo_Rocket(int sandstorm_Low_Cargo_Rocket) {
        if (Low_Cargo_Rocket + sandstorm_Low_Cargo_Rocket > 4 || sandstorm_Low_Cargo_Rocket < 0){
            return;
        }
        this.Sandstorm_Low_Cargo_Rocket = sandstorm_Low_Cargo_Rocket;
    }

    public void setMiddle_Cargo(int middle_Cargo) {
        if (middle_Cargo + Sandstorm_Middle_Cargo > 4 || middle_Cargo < 0){
            return;
        }
        this.Middle_Cargo = middle_Cargo;
    }

    public void setLow_Panels_Rocket(int low_Panels_Rocket) {
        if (low_Panels_Rocket + Sandstorm_Low_Panels_Rocket > 4 || low_Panels_Rocket < 0){
            return;
        }
        this.Low_Panels_Rocket = low_Panels_Rocket;
    }

    public void setSandstorm_Low_Panels_Rocket(int sandstorm_Low_Panels_Rocket) {
        if (Low_Panels_Rocket + sandstorm_Low_Panels_Rocket > 4 || sandstorm_Low_Panels_Rocket < 0){
            return;
        }
        this.Sandstorm_Low_Panels_Rocket = sandstorm_Low_Panels_Rocket;
    }

    public void setSandstorm_Middle_Cargo(int sandstorm_Middle_Cargo) {
        if (Middle_Cargo + sandstorm_Middle_Cargo > 4 || sandstorm_Middle_Cargo < 0){
            return;
        }
        this.Sandstorm_Middle_Cargo = sandstorm_Middle_Cargo;
    }

    public void setSandstorm_Middle_Panels(int sandstorm_Middle_Panels) {
        if (Middle_Panels + sandstorm_Middle_Panels > 4 || sandstorm_Middle_Panels < 0){
            return;
        }
        this.Sandstorm_Middle_Panels = sandstorm_Middle_Panels;
    }

    public String getStarting_Game_Piece() {
        return Starting_Game_Piece;
    }

    public int getSandstorm_Cross(){
        return Sandstorm_Cross;
    }

    public void setSandstorm_Cross(int v){
        Sandstorm_Cross = v;
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
        switch(Climb_Level) {
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
        totalCargo = High_Cargo + Low_Cargo_Rocket + Middle_Cargo + Sandstorm_High_Cargo + Sandstorm_Low_Cargo_Rocket + Sandstorm_High_Cargo;
        totalPanel = High_Panels + Low_Panels_Rocket + Middle_Panels + Sandstorm_High_Panels + Sandstorm_Low_Panels_Rocket + Sandstorm_Middle_Panels;

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
        switch(Sandstorm_Cross) {
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
        switch(Climb_Level) {
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
