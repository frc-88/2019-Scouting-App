package org.robotics.tj2.scout88.etc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class MatchPreviewCalculations {
    private String teamName;
    private int teamNum;
    private ArrayList<Performance> blue1Performances;
    private ArrayList<Performance> blue2Performances;
    private ArrayList<Performance> blue3Performances;
    private ArrayList<Performance> red1Performances;
    private ArrayList<Performance> red2Performances;
    private ArrayList<Performance> red3Performances;
    private double winChance;
    private double endRPChance;
    private double rocketRP;
    private final double ALPHA = 0.15;
    private ArrayList<Double> allyInt = new ArrayList<>();
    private ArrayList<Double> oppInt = new ArrayList<>();
    private ArrayList<ArrayList<Performance>> competition = new ArrayList<ArrayList<Performance>>();
    private int calcRP;



    public MatchPreviewCalculations() {
        teamName = "";
        teamNum = 0;
    }
    public MatchPreviewCalculations(String name, int tNum) {
        teamName = name;
        teamNum = tNum;
    }

    public MatchPreviewCalculations(ArrayList<Performance>[] alpa){
        blue1Performances = alpa[0];
        blue2Performances = alpa[1];
        blue3Performances = alpa[2];
        red1Performances = alpa[3];
        red2Performances = alpa[4];
        red3Performances = alpa[5];
    }

    //win chance calculations

    public double mean(ArrayList<Performance> team) {
        double avg = 0;
        double total = 0;
        for(Performance x: team) {
            total += x.getTotalPoints();
        }
        avg = total/ (team.size());
        return avg;
    }

    public double sd(ArrayList<Performance> team) {
        double sdP = 0;
        double[] t = new double[team.size()];
        for (int i = 0; i < t.length; i++) {
            t[i] = (team.get(i).getTotalPoints());
        }
        StandardDeviation s = new StandardDeviation(false);
        sdP = s.evaluate(t);
        return sdP;
    }

    public double winChance() {
        double allyM = 0;
        double oppM = 0;
        double allySD = 0;
        double oppSD = 0;
        double allyN = 0;
        double oppN = 0;
        double allyG = 0;
        double oppG = 0;
        double tVal = 0;


        allyM = mean(blue1Performances) + mean(blue2Performances) + mean(blue3Performances);
        oppM = mean(red1Performances) + mean(red2Performances) + mean(red3Performances);
        allySD = Math.sqrt(Math.pow(sd(blue1Performances),2) + Math.pow(sd(blue2Performances), 2) + Math.pow(sd(blue3Performances), 2));
        oppSD =  Math.sqrt(Math.pow(sd(red1Performances),2) + Math.pow(sd(red2Performances), 2) + Math.pow(sd(red3Performances), 2));
        allyN = ((double)(blue1Performances.size() + blue2Performances.size() + blue3Performances.size()))/3;
        oppN = ((double)(red1Performances.size() + red2Performances.size() + red3Performances.size()))/3;
        allyG = (Math.pow(allySD, 2)/(allyN));
        oppG = (Math.pow(oppSD, 2)/(oppN));
        tVal = ((allyM - oppM)/(Math.sqrt(allyG + oppG)));

        double vA = allyN - 1;
        double vO = oppN - 1;

        //intermediary steps
        double gs = allyG + oppG;
        double gsSq= Math.pow(gs, 2);
        double gSqA = Math.pow(allyG, 2);
        double gSqO = Math.pow(oppG, 2);

        double degF = gsSq/ (  (gSqA /vA) +  (gSqO/vO));
        TDistribution tDist = new TDistribution(null, degF);
        winChance = 1 - tDist.cumulativeProbability(tVal);

        return(winChance);
    }

    //end game ranking point calculations

    //mean of endgame points
    public double meanE(ArrayList<Performance> team) {
        double avg = 0;
        double total = 0;
        for(Performance x: team) {
            total += x.getEndPoints();
        }
        avg = total/ (team.size());
        return avg;
    }

    public double sdE(ArrayList<Performance> team) {
        double sdP = 0;
        double[] t = new double[team.size()];
        for (int i = 0; i < t.length; i++) {
            t[i] = (team.get(i).getEndPoints());
        }
        StandardDeviation s = new StandardDeviation(false);
        sdP = s.evaluate(t);
        return sdP;
    }


    public double endChance() {
        double allyM = 0;
        double allySD = 0;
        double allyN = 0;
        double tVal = 0;

        allyM = meanE(blue1Performances) + meanE(blue2Performances) + meanE(blue3Performances);
        allySD = Math.sqrt(Math.pow(sdE(blue1Performances),2) + Math.pow(sdE(blue2Performances), 2) + Math.pow(sdE(blue3Performances), 2));
        allyN = ((double)(blue1Performances.size() + blue2Performances.size() + blue3Performances.size()))/3;
        tVal = (allyM- 15)/(allySD/(Math.sqrt(allyN)));
        double degF = allyN -1;
        TDistribution tDist = new TDistribution(null, degF);
        endRPChance = 1 - tDist.cumulativeProbability(tVal);

        return(endRPChance);
    }


    //calculating rocket ranking point chance
    public double meanR(ArrayList<Performance> team, String lvl) {
        double avg = 0;
        double total = 0;

        if (lvl.equalsIgnoreCase("low")) {
            for(Performance x: team) {
                total += 3*(x.getLow_Cargo_Rocket() + x.getSandstorm_Low_Cargo_Rocket()) +
                        2 * (x.getLow_Panels_Rocket() + x.getSandstorm_Low_Panels_Rocket());
            }
            avg = total/ (team.size());
        }
        else if (lvl.equalsIgnoreCase("mid")) {
            for(Performance x: team) {
                total += 3*(x.getMiddle_Cargo() + x.getSandstorm_Middle_Cargo()) +
                        2 * (x.getMiddle_Panels() + x.getSandstorm_Middle_Panels());
            }
            avg = total/ team.size();
        }
        else if (lvl.equalsIgnoreCase("high")) {
            for(Performance x: team) {
                total += 3*(x.getHigh_Cargo() + x.getSandstorm_High_Cargo()) +
                        2 * (x.getHigh_Panels() + x.getSandstorm_High_Panels());
            }
            avg = total/ team.size();
        }

        return (avg);
    }

    public double sdR(ArrayList<Performance> team, String lvl) {
        double sdP = 0;
        StandardDeviation s = new StandardDeviation(false);
        if (lvl.equalsIgnoreCase("low")) {
            double[] t = new double[team.size()];
            for (int i = 0; i < t.length; i++) {
                t[i] = 3*(team.get(i).getLow_Cargo_Rocket() + team.get(i).getSandstorm_Low_Cargo_Rocket()) +
                        2 * (team.get(i).getLow_Panels_Rocket() + team.get(i).getSandstorm_Low_Panels_Rocket());
            }
            sdP = s.evaluate(t);
        }
        else if (lvl.equalsIgnoreCase("mid")) {
            double[] t = new double[team.size()];
            for (int i = 0; i < t.length; i++) {
                t[i] = 3*(team.get(i).getMiddle_Cargo() + team.get(i).getSandstorm_Middle_Cargo()) +
                        2 * (team.get(i).getMiddle_Panels() + team.get(i).getSandstorm_Middle_Panels());
            }
            sdP = s.evaluate(t);
        }
        else if (lvl.equalsIgnoreCase("high")) {
            double[] t = new double[team.size()];
            for (int i = 0; i < t.length; i++) {
                t[i] = 3*(team.get(i).getHigh_Cargo() + team.get(i).getHigh_Cargo()) +
                        2 * (team.get(i).getHigh_Panels() + team.get(i).getSandstorm_High_Panels());
            }
            sdP = s.evaluate(t);
        }
        return(sdP);
    }

    public double rocketChance() {
        double allyN =0;
        double tH = 0;
        double tM = 0;
        double tL = 0;
        double pH;
        double pM;
        double pL;


        double mH = meanR(blue1Performances, "high") + meanR(blue2Performances, "high") + meanR(blue3Performances, "high");
        double mM = meanR(blue1Performances, "mid") + meanR(blue2Performances,"mid") + meanR(blue3Performances, "mid");
        double mL = meanR(blue1Performances, "low") + meanR(blue2Performances,"low") + meanR(blue3Performances, "low");
        double sH = Math.sqrt(Math.pow(meanR(blue1Performances, "high"),2) + Math.pow(meanR(blue2Performances, "high"),2) +
                Math.pow(meanR(blue3Performances, "high"),2));
        double sM = Math.sqrt(Math.pow(meanR(blue1Performances, "mid"),2) + Math.pow(meanR(blue2Performances, "mid"),2) +
                Math.pow(meanR(blue3Performances, "mid"),2));
        double sL = Math.sqrt(Math.pow(meanR(blue1Performances, "low"),2) + Math.pow(meanR(blue2Performances, "low"),2) +
                Math.pow(meanR(blue3Performances, "low"),2));
        allyN = ((double)(blue1Performances.size() + blue2Performances.size() + blue3Performances.size()))/3;

        tH = (mH- 10)/(sH/(Math.sqrt(allyN)));
        tL = (mL- 10)/(sL/(Math.sqrt(allyN)));
        tM = (mM- 10)/(sM/(Math.sqrt(allyN)));
        double degF = allyN -1;

        TDistribution tDist = new TDistribution(null, degF);
        pH = 1 - tDist.cumulativeProbability(tH);
        pM = 1 - tDist.cumulativeProbability(tM);
        pL = 1 - tDist.cumulativeProbability(tL);

        if (pH <= ALPHA && pM < ALPHA && pL < ALPHA) {
            rocketRP = 1;
        }
        else {
            rocketRP = 0;
        }


        return(rocketRP);
    }

    //calculated predicted ranking points
    public int predictedRP() {
        int pRP = 0;
        double w = winChance();
        double e = endChance();
        double r = rocketChance();


        if (w < ALPHA) {
            pRP += 2;
        }
        else if (w == ALPHA) {
            pRP +=1;
        }

        if (e < ALPHA) {
            pRP += 1;
        }
        pRP += r;

        return(pRP);
    }

    //team score range
    //allies
    public ArrayList<Double> pointIntA() {
        double m = mean(blue1Performances) + mean(blue2Performances) + mean(blue3Performances);
        double s = Math.sqrt(Math.pow(sd(blue1Performances), 2) + Math.pow(sd(blue2Performances), 2) + Math.pow(sd(blue3Performances), 2));
        int size = (int)((blue1Performances.size() + blue2Performances.size() + blue3Performances.size())/3);
        double degf = size - 1;
        TDistribution t = new TDistribution(degf);
        double tvalue =(t.inverseCumulativeProbability(ALPHA/2.0));
        double tval = (Math.abs(tvalue));
        double me = tval * (s/(Math.sqrt(size)));
        double t1Max = m + me;
        double t1Min = m - me;
        allyInt.add(t1Min);
        allyInt.add(t1Max);
        return(allyInt);
    }

    //opponents
    public ArrayList<Double> pointIntO() {
        double m = mean(red1Performances) + mean(red2Performances) + mean(red3Performances);
        double s = Math.sqrt(Math.pow(sd(red1Performances), 2) + Math.pow(sd(red2Performances), 2) + Math.pow(sd(red3Performances), 2));
        double size = (int)((red1Performances.size() + red2Performances.size() + red3Performances.size())/3);
        double degf = size - 1;
        TDistribution t = new TDistribution(degf);
        double tvalue =(t.inverseCumulativeProbability(ALPHA/2.0));
        double tval = (Math.abs(tvalue));
        double me = tval * (s/(Math.sqrt(size)));
        double t1Max = m + me;
        double t1Min = m - me;
        oppInt.add(t1Min);
        oppInt.add(t1Max);
        return(oppInt);
    }



    //total predicted ranking point

    public int pTotalRP(ArrayList<Performance> t) {
        calcRP = 0;
        for (int i = 0; i < t.size(); i++) {
            calcRP = calcRP + t.get(i).getEarnedRP() + predictedRP();
        }
        return(calcRP);
    }


    public void compSet(ArrayList<Performance> t) {
        competition.add(t);
    }

    //sort teams into expected seeds, starting at index 0 = 1st seed
    public int[] pSeeding(int numTeams){
        int[] seeding = new int[numTeams];
        ArrayList<ArrayList<Performance>> t = new ArrayList<ArrayList<Performance>>();
        for(int i=0; i< numTeams; i++) {
            t.add(competition.get(i));
        }
        Collections.sort(t, new SortbyRP());
        for(int i =0; i < t.size(); i++) {
            seeding[i] = t.get(i).get(i).getTeam_Number();
        }
        return(seeding);
    }
}

class SortbyRP extends MatchPreviewCalculations implements Comparator<ArrayList<Performance>>
{

    public int compare(ArrayList<Performance> a, ArrayList<Performance> b) {


        return pTotalRP(a) - pTotalRP(b);
    }

    public boolean equals(Object o){
        return false;
    }

}
