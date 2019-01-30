package org.robotics.tj2.scout88;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBInterface {

    private static SQLiteDatabase db;

    public static boolean initDB(String pathName){

        db = SQLiteDatabase.openOrCreateDatabase(pathName , null);


        db.execSQL("CREATE TABLE IF NOT EXISTS competitions" +
                "(competitionId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "competitionName TEXT," +
                "week INTEGER);");

        db.execSQL("CREATE TABLE IF NOT EXISTS teams" +
                "(teamNumber INTEGER PRIMARY KEY," +
                "name TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS matches" +
                "(matchId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "matchNumber INTEGER," +
                "competitionId INTEGER," +
                "FOREIGN KEY(competitionId) REFERENCES competitions(competitionId));");

        db.execSQL("CREATE TABLE IF NOT EXISTS performances" +
                "(performanceId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cargoRocketA1 INTEGER," +
                "cargoRocketA2 INTEGER," +
                "cargoRocketA3 INTEGER," +
                "cargoRocketA4 INTEGER," +
                "cargoRocketA5 INTEGER," +
                "cargoRocketA6 INTEGER," +
                "cargoRocketB1 INTEGER," +
                "cargoRocketB2 INTEGER," +
                "cargoRocketB3 INTEGER," +
                "cargoRocketB4 INTEGER," +
                "cargoRocketB5 INTEGER," +
                "cargoRocketB6 INTEGER," +
                "cargoMid1 INTEGER," +
                "cargoMid2 INTEGER," +
                "cargoMid3 INTEGER," +
                "cargoMid4 INTEGER," +
                "cargoMid5 INTEGER," +
                "cargoMid6 INTEGER," +
                "cargoMid7 INTEGER," +
                "cargoMid8 INTEGER," +
                "panelRocketA1 INTEGER," +
                "panelRocketA2 INTEGER," +
                "panelRocketA3 INTEGER," +
                "panelRocketA4 INTEGER," +
                "panelRocketA5 INTEGER," +
                "panelRocketA6 INTEGER," +
                "panelRocketB1 INTEGER," +
                "panelRocketB2 INTEGER," +
                "panelRocketB3 INTEGER," +
                "panelRocketB4 INTEGER," +
                "panelRocketB5 INTEGER," +
                "panelRocketB6 INTEGER," +
                "panelMid1 INTEGER," +
                "panelMid2 INTEGER," +
                "panelMid3 INTEGER," +
                "panelMid4 INTEGER," +
                "panelMid5 INTEGER," +
                "panelMid6 INTEGER," +
                "panelMid7 INTEGER," +
                "panelMid8 INTEGER," +
                "climbLevel INTEGER," +
                "startingPosition INTEGER," +
                "autoPoints INTEGER," +
                "matchId INTEGER," +
                "competitionId INTEGER," +
                "FOREIGN KEY(matchId) REFERENCES matches (matchId)," +
                "FOREIGN KEY(competitionId) REFERENCES competitions(competitionId));");

        db.execSQL("INSERT INTO competitions (competitionName, week)" +
                "VALUES ('Granite State' , 1);");
        Cursor cursor = db.rawQuery("SELECT * FROM competitions" , new String[] {});
        Log.v("scoutDB" , cursor.getColumnNames()[0]);
        return true;
    }
}
