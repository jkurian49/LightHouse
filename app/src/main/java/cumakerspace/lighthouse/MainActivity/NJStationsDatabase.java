package cumakerspace.lighthouse.MainActivity;

/**
 * Created by student on 7/28/15.
 */



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

public class NJStationsDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase DB;
    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "stop_id";
    public static final String COLUMN_COMMENT = "stop_name";

    private static final String DATABASE_NAME = "njstations.db";
    private static final int DATABASE_VERSION = 1;

    ContentValues cv;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "(" + COLUMN_ID
            + " INTEGER, " + COLUMN_COMMENT
            + " text);";

    public NJStationsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

    public void insertRecords(String[] station_name) {
        try {

             DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once
            for (int i = 0; i < station_name.length; i++) {
                cv.put(COLUMN_COMMENT, station_name[i]);
                Log.v("SQLLoopErrorNotNull", station_name[i]);

            }
            DB.insertOrThrow(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error: insert stop name", ex.toString());


        }
    }
    public void insertRecord(String station_name) {
        try {

            DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once

                cv.put(COLUMN_COMMENT, station_name);
                Log.v("SQLLoopErrorNotNull", station_name);


            DB.insert(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error: insert stop name", ex.toString());


        }

        Log.v("DisplayifWorked", station_name);
    }
    public void insertStationId(String stop_id) {
        try {

            DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once

            cv.put(COLUMN_ID, stop_id);
            Log.v("SQLLoopErrorNotNull", stop_id);


            DB.insert(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error: insert stop id", ex.toString());


        }

        Log.v("DisplayifWorked", stop_id);
    }
    public ArrayList<String> getAllStations(){
        ArrayList<String> stations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COMMENTS;

        DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                stations.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        DB.close();

        String s = "";

        for (int i = 0; i < stations.size(); i++){
            s = s + stations.get(i);

        }

        Log.v("<stations> added", s);



        // returning lables

        return stations;
    }

    public ArrayList<Stops> getAllStationsAndIds(){
        ArrayList<Stops> station_and_ids = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_COMMENTS;

        DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                station_and_ids.add(new Stops (cursor.getString(cursor.getColumnIndex(NJStationsDatabase.COLUMN_COMMENT)), cursor.getString(cursor.getColumnIndex(NJStationsDatabase.COLUMN_ID))));
                Log.v("stations check", " " + cursor.getString(cursor.getColumnIndex(NJStationsDatabase.COLUMN_COMMENT)) + cursor.getString(cursor.getColumnIndex(NJStationsDatabase.COLUMN_ID)));
            } while (cursor.moveToNext());
        }
        return station_and_ids;
          /*for (int i=0; i < stop_id_1_count; i++){

        for (int j=0; j < stop_id_2_count; j++) {

            if((first_id[i][0]).equals(second_id[j][0])) {
                schedules.add(new Trip(first_id[i][0],first_id[i][1],second_id[i][1]));
                */
    }


}








