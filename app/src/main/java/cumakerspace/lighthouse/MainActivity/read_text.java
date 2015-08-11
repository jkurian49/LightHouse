package cumakerspace.lighthouse.MainActivity;

import android.app.Activity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cumakerspace.lighthouse.R;


public class read_text extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_text);

        NJStationsDatabase njstations = new NJStationsDatabase(this.getBaseContext());

        InputStream is = this.getResources().openRawResource(R.raw.njstops);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        int i = 0;
        String stop_id = null;
        String stop_name = null;
        try {
            while ((line = br.readLine()) != null) {


                String[] ar = line.split(",");

                stop_id = ar[0];
                stop_name = ar[2];
                njstations.insertStationId(stop_id);
                njstations.insertRecord(stop_name);
                i++;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        NJStopTimesDatabase njstoptimes = new NJStopTimesDatabase(this.getBaseContext());
        InputStream is2 = this.getResources().openRawResource(R.raw.stop_times);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
        String trip_id = null;
        String arrival_time = null;
        String stop_id2 = null;
        try {
            while ((line = br2.readLine()) != null) {


                String[] ar2 = line.split(",");


                trip_id = ar2[0];
                arrival_time = ar2[1];
                stop_id2 = ar2[3];
                njstoptimes.insertTripId(trip_id);
                njstoptimes.insertArrivalTime(arrival_time);
                njstoptimes.insertStopId(stop_id2);
                i++;


            }
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}



