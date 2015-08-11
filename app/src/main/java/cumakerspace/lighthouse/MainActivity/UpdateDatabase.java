package cumakerspace.lighthouse.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Handler;

import cumakerspace.lighthouse.R;


public class UpdateDatabase extends Activity {

    Context context = this.getBaseContext();
    Context activity = this;
    private static int progress;
    private ProgressBar bar;
    private ProgressBar bar2;
    private int progressStatus = 0;
    private int progressStatus2 = 0;
    private NJStationsDatabase njstations;
    private NJStopTimesDatabase njstoptimes;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);
        progress = 0;
        bar = (ProgressBar) findViewById(R.id.updateDBStationsTripIdsProgBar);
        bar.setMax(200);


        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 200) {
                    progressStatus = doSomeWork();
                    handler.post(new Runnable() {
                        public void run() {
                            bar.setProgress(progressStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    public void run() {
                        // ---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
                        bar.setVisibility(View.GONE);
                    }
                });

            }


            private int doSomeWork() {
                try {
                    njstations = new NJStationsDatabase(context);
                    Log.v("NJ Stations", njstations.toString());
                    InputStream is = activity.getResources().openRawResource(R.raw.njstops);

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    int i = 0;
                    String sn;

                    while ((line = br.readLine()) != null) {


                        String[] ar = line.split(",");


                        sn = ar[2];
                        njstations.insertRecord(sn);
                        i++;


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ++progress;

            }

        }).start();


        new Thread(new Runnable() {
            public void run() {

                try {

                    njstoptimes = new NJStopTimesDatabase(context);
                    InputStream is2 = activity.getResources().openRawResource(R.raw.stop_times);
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                    String line;
                    int i = 0;
                    String trip_id;
                    String arrival_time;
                    String stop_id;

                    while ((line = br2.readLine()) != null) {

                        String[] ar2 = line.split(",");

                        trip_id = ar2[0];
                        arrival_time = ar2[1];
                        stop_id = ar2[3];
                        njstoptimes.insertTripId(trip_id);
                        njstoptimes.insertArrivalTime(arrival_time);
                        njstoptimes.insertStopId(stop_id);
                        i++;


                    }
                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
        }).start();


    }
}


