package org.robotics.tj2.scout88;

public class Performance {
    public int team_number = -1;
    public int match_number = -1;
    //0=not scored, 1=scored in teleop, 2=scored in sandstorm
    public String[] cargo = new String[20];
    //0=not scored, 1=scored in teleop, 2=scored in sandstorm, 3=null panel
    public String[] panels = new String[20];
    public int level_of_climb = 0;
    public int starting_level = 0;
    public String comments = "";
    public double defense = 0; //time in seconds spent on defense
}
