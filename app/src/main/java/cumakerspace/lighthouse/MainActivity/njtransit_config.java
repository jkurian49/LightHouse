package cumakerspace.lighthouse.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cumakerspace.lighthouse.R;


public class njtransit_config extends Activity {

    Spinner fromspinner;
    Spinner tospinner;
    String stop_id_1;
    String stop_id_2;
    private ListView lv;
    ArrayList<Stops> stations_and_ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_njtransit_config);
        //NJStopTimesDatabase DB = new NJStopTimesDatabase(getApplicationContext());
        fromspinner = (Spinner) findViewById(R.id.fromspinner);
        tospinner = (Spinner) findViewById(R.id.tospinner);
        loadSpinnerData();
        /*ArrayList<String> allTripIds = DB.getAllTripIds();
        ArrayList<String> allArrivalTimes = DB.getAllArrivalTimes();
        ArrayList<String> allStopIDs = DB.getAllStopIds();
        Log.v("Size of Trip", " " + Integer.toString(allTripIds.size()));
        for (int i = 0; i <allTripIds.size(); i++){
            Log.v("allTripIds", " " + allTripIds.get(i));
        }
        for (int i = 0; i <allArrivalTimes.size(); i++){
            Log.v("allArrivalTimes", " " + allArrivalTimes.get(i));
        }
        for (int i = 0; i <allStopIDs.size(); i++){
            Log.v("allStopIDs", " " + allStopIDs.get(i));
        }
        */


    }
    private void loadSpinnerData() {

        NJStationsDatabase db = new NJStationsDatabase(getApplicationContext());

        stations_and_ids = db.getAllStationsAndIds();
        //make method to get stations with stopids into an arraylist of Stops
        //Log.v("Stations in Spinner?", stations_and_ids.get(0));
        String[] stations = new String[stations_and_ids.size()];
        for (int i = 0; i < stations_and_ids.size(); i++){
            stations[i] = stations_and_ids.get(i).getStop_name();

        }
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(njtransit_config.this, android.R.layout.simple_spinner_item, stations);
        //instead of stations, make for loop to get all stations names from stops
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromspinner.setAdapter(dataAdapter);
        fromspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stop_id_1 = stations_and_ids.get(position).getStop_id();

                Log.v("stop_id_1_value", stop_id_1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tospinner.setAdapter(dataAdapter);
        tospinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stop_id_2 = stations_and_ids.get(position).getStop_id();
                Log.v("stop_id_2_value", stop_id_2);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        final Button showSchedulesButton = (Button) findViewById(R.id.showschedules_button);
        showSchedulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NJStopTimesDatabase DB = new NJStopTimesDatabase(getApplicationContext());

                if ((stop_id_1 != null) && (stop_id_2 != null)) {


                    if (!(stop_id_1.equals(stop_id_2))) {

                        Log.v("i did it", "i did it");

                        ArrayList<Trip> trips = DB.getSchedules(stop_id_1, stop_id_2);

                        if (trips != null) {

                            int i = trips.size();
                            String listview_rows[] = new String[i];
                            for (int j = 0; j < i; j++) {
                                if (trips.get(j) != null) {
                                    listview_rows[j] = trips.get(j).toString();
                                }
                                else if (trips.get(j) == null){
                                    listview_rows[j] = "null";
                                }
                            }

                            ArrayAdapter<String> adapter;
                            lv = (ListView) findViewById(R.id.schedules_listView);
                            adapter = new ArrayAdapter<String>(njtransit_config.this,
                                    android.R.layout.simple_list_item_1, listview_rows);

                            lv.setAdapter(adapter);
                        } else
                            Toast.makeText(getApplicationContext(), "No search results found.", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lirr_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}






