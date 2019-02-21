package org.robotics.tj2.scout88.etc;

import java.util.ArrayList;

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
    private final double ALPHA = 0.05;




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
                total += 3*(x.getNumLowCargo() + x.getNumLowCargoSS()) +
                        2 * (x.getNumLowPanels() + x.getNumLowPanelsSS());
            }
            avg = total/ (team.size());
        }
        else if (lvl.equalsIgnoreCase("mid")) {
            for(Performance x: team) {
                total += 3*(x.getNumMedCargo() + x.getNumMedCargoSS()) +
                        2 * (x.getNumMedPanels() + x.getNumMedPanelsSS());
            }
            avg = total/ team.size();
        }
        else if (lvl.equalsIgnoreCase("high")) {
            for(Performance x: team) {
                total += 3*(x.getNumHighCargo() + x.getNumHighCargoSS()) +
                        2 * (x.getNumHighPanels() + x.getNumHighPanelsSS());
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
                t[i] = 3*(team.get(i).getNumLowCargo() + team.get(i).getNumLowCargoSS()) +
                        2 * (team.get(i).getNumLowPanels() + team.get(i).getNumLowPanelsSS());
            }
            sdP = s.evaluate(t);
        }
        else if (lvl.equalsIgnoreCase("mid")) {
            double[] t = new double[team.size()];
            for (int i = 0; i < t.length; i++) {
                t[i] = 3*(team.get(i).getNumMedCargo() + team.get(i).getNumMedCargoSS()) +
                        2 * (team.get(i).getNumMedPanels() + team.get(i).getNumMedPanelsSS());
            }
            sdP = s.evaluate(t);
        }
        else if (lvl.equalsIgnoreCase("high")) {
            double[] t = new double[team.size()];
            for (int i = 0; i < t.length; i++) {
                t[i] = 3*(team.get(i).getNumHighCargo() + team.get(i).getNumHighCargo()) +
                        2 * (team.get(i).getNumHighPanels() + team.get(i).getNumHighPanelsSS());
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
    public double predictedRP() {
        double pRP = 0;
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

}
