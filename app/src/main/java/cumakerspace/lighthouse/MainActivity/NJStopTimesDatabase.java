package cumakerspace.lighthouse.MainActivity;

/**
 * Created by student on 8/3/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class NJStopTimesDatabase extends SQLiteOpenHelper {
    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "trip_id";
    public static final String COLUMN_COMMENT_TIME = "arrival_time";
    public static final String COLUMN_COMMENT_ID = "stop_id";


    private static final String DATABASE_NAME = "njstoptimes.db";
    private static final int DATABASE_VERSION = 1;
    ContentValues cv;
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "(" + COLUMN_ID + " INTEGER, "
            + COLUMN_COMMENT_TIME + " INTEGER, "
            + COLUMN_COMMENT_ID + " INTEGER);";
    SQLiteDatabase DB;
    public NJStopTimesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbup, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        dbup.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(dbup);
    }

    public void insertTripIDs(String[] trip_id ){
        try {

            DB = this.getWritableDatabase();

            cv = new ContentValues(); //Declare once
            for (int i = 0; i < trip_id.length; i++) {
                cv.put(COLUMN_ID, trip_id[i]);
                Log.v("SQLLoopErrorNotNull", trip_id[i]);

            }
            DB.insertOrThrow(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error: insert trip id", ex.toString());


        }
    }
    public void insertTripId(String trip_id) {
        try {


            DB = this.getWritableDatabase();
            if (DB == null){
                Log.e("Database State", "null");
            }
            cv = new ContentValues(); //Declare once
            if (cv == null){
                Log.e("ContentValues State", "null");
            }

            cv.put(COLUMN_ID, trip_id);
            Log.v("SQLLoopErrorNotNull", trip_id);


            DB.insertOrThrow(TABLE_COMMENTS, "null", cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error: insert trip id", ex.toString());


        }

        Log.v("DisplayifWorked", trip_id);
    }
    public void insertArrivalTimes(String[] arrival_time ){
        try {

            DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once
            for (int i = 0; i < arrival_time.length; i++) {
                cv.put(COLUMN_COMMENT_TIME, arrival_time[i]);
                Log.v("SQLLoopErrorNotNull", arrival_time[i]);

            }
            DB.insertOrThrow(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error:insertarrivaltime", ex.toString());


        }
    }
    public void insertArrivalTime(String arrival_time) {
        try {

            DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once

            cv.put(COLUMN_COMMENT_TIME, arrival_time);
            Log.v("SQLLoopErrorNotNull", arrival_time);


            DB.insertOrThrow(TABLE_COMMENTS, "null", cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error:insertarrivaltime", ex.toString());


        }

        Log.v("DisplayifWorked", arrival_time);
    }


    public void insertStopIds(String[] stop_id ){
        try {

            DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once
            for (int i = 0; i < stop_id.length; i++) {
                cv.put(COLUMN_COMMENT_ID, stop_id[i]);
                Log.v("SQLLoopErrorNotNull", stop_id[i]);

            }
            DB.insertOrThrow(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error:insert stopid", ex.toString());


        }
    }
    public void insertStopId(String stop_id) {
        try {

            DB = this.getWritableDatabase();
            cv = new ContentValues(); //Declare once

            cv.put(COLUMN_COMMENT_ID, stop_id);
            Log.v("SQLLoopErrorNotNull", stop_id);


            DB.insertOrThrow(TABLE_COMMENTS, null, cv); //Insert each time for loop count
            DB.close(); // Now close the DB Object
        } catch (Exception ex) {
            Log.e("Error:insert stopid", ex.toString());


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
    public ArrayList<String> getAllTripIds(){
        ArrayList<String> trip_ids = new ArrayList<String>();
        String selectquery = "SELECT * FROM " + TABLE_COMMENTS;
        DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
            trip_ids.add(cursor.getString(0));
            }
            while (cursor.moveToNext());

            cursor.close();
            DB.close();


        }
        return trip_ids;
    }
    public ArrayList<String> getAllArrivalTimes(){
        ArrayList<String> arrival_times = new ArrayList<String>();
        String selectquery = "SELECT * FROM " + TABLE_COMMENTS;
        DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
                arrival_times.add(cursor.getString(0));
            }
            while (cursor.moveToNext());

            cursor.close();
            DB.close();


        }
        return arrival_times;
    }
    public ArrayList<String> getAllStopIds(){
        ArrayList<String> stop_ids = new ArrayList<String>();
        String selectquery = "SELECT * FROM " + TABLE_COMMENTS;
        DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
                stop_ids.add(cursor.getString(0));
            }
            while (cursor.moveToNext());

            cursor.close();
            DB.close();


        }
        return stop_ids;
    }

    public ArrayList<Trip> getSchedules(String stop_id_1, String stop_id_2){
        ArrayList<Trip> schedules = new ArrayList<>();
        String selectquery = "SELECT * FROM " + TABLE_COMMENTS ;

        DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(selectquery, null);
        if (cursor != null){
            Log.v("cursor not null", "not null");
        }


        int stop_id_1_count=0;
        if (cursor.moveToFirst()){
            //do{
                cursor.moveToNext();
                for (int i = 0; i < 50;){
                    if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) != null){
                        Log.v("cursor getString", " not null");
                        Log.v("cursor getString", cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)));
                        Log.v("int i", Integer.toString(i));
                        if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)).equals(stop_id_1)){
                            stop_id_1_count++;
                            cursor.moveToNext();
                            i++;
                        }
                        else {
                            cursor.moveToNext();
                            i++;
                        }



                    }
                    else if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) == null){
                        Log.v("cursor getString", "null");
                        cursor.moveToNext();
                    }
                }
            }
        // while (cursor.moveToNext());
        //}

        int stop_id_2_count=0;
        if (cursor.moveToFirst()){
            cursor.moveToNext();
            for (int i = 0; i < 50;){
                if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) != null){
                    Log.v("cursor getString2", " not null");
                    Log.v("cursor getString2", cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)));
                    Log.v("int i", Integer.toString(i));
                    if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)).equals(stop_id_2)){
                        stop_id_2_count++;
                        cursor.moveToNext();
                        i++;
                    }
                    else {
                        cursor.moveToNext();
                        i++;
                    }



                }
                else if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) == null){
                    cursor.moveToNext();
                }
            }

        }

        String [][] first_id = new String [stop_id_1_count][3];
        if (cursor.moveToFirst()) {
            do {
                for (int i = 0; i < stop_id_1_count; i++) {
                    if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) != null) {
                        if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)).equals(stop_id_1)) {
                            first_id[i][2] = cursor.getString(2);
                            first_id[i][1] = cursor.getString(1);
                            first_id[i][0] = cursor.getString(0);
                        } else if (!cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)).equals(stop_id_1)) {

                        } else if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) == null) {

                        }
                    }
                }

            }while (cursor.moveToNext()) ;
        }
        String [][] second_id = new String [stop_id_2_count][3];
        if (cursor.moveToFirst()) {
            do {
                for (int j = 0; j < stop_id_2_count; j++) {
                    if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) != null) {
                        if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)).equals(stop_id_2)) {
                            second_id[j][2] = cursor.getString(2);
                            second_id[j][1] = cursor.getString(1);
                            second_id[j][0] = cursor.getString(0);
                        } else if (!cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)).equals(stop_id_1)) {
                        } else if (cursor.getString(cursor.getColumnIndex(NJStopTimesDatabase.COLUMN_COMMENT_ID)) == null) {
                        }
                    }
                }
            }while (cursor.moveToNext());
        }

        Log.v("elements", " " + stop_id_1_count + " " + stop_id_2_count);


        //ArrayList<Trip> trips = new ArrayList<>();
        for (int i = 0; i <first_id.length; i++){
            for (int j = 0; j< first_id[0].length; j++) {
                Log.v("first_id_elements", " " + first_id[i][j]);
            }
        }

        for (int i = 0; i <second_id.length; i++){
            for (int j = 0; j< second_id[0].length; j++) {
                Log.v("second_id_elements", " " + second_id[i][j]);
            }
        }

        for (int i=0; i < stop_id_1_count; i++){

            for (int j=0; j < stop_id_2_count; j++) {

                if((first_id[i][0]).equals(second_id[j][0])) {
                    schedules.add(new Trip(first_id[i][0],first_id[i][1],second_id[i][1]));
                    Log.v("added trip id now", "added");
                    //schedules.add(trips);
                }
            }
        }



        /* for loop traverse through the cursor, find number of hits for the first stop_id = i

        String[][] first_id = new String[i][3];

        same thing for stop_id_2;

        String[r][c] = blah;
         */
        cursor.close();
        DB.close();
        return schedules;



    }

}



