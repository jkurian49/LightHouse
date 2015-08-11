package cumakerspace.lighthouse.MainActivity;

/**
 * Created by student on 8/10/15.
 */
public class Stops {
    private String stop_name;
    private String stop_id;

    public Stops(){

    }
    public Stops(String stop_name, String stop_id) {

        this.stop_name = stop_name;
        this.stop_id = stop_id;
    }


    public String getStop_name(){
        return stop_name;
    }

    public String getStop_id(){
        return this.stop_id;
    }

    public String toString(){
        return stop_id + " "+stop_name;
    }


}
/*public class Trip {

    String tripid;
    String departuretime;
    String arrivaltime;

    public Trip() {

    };

    public Trip(String tripid, String departuretime, String arrivaltime){
        this.tripid = tripid;
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
    }
    @Override
    public String toString(){
        return (this.departuretime + "-" + this.arrivaltime);
    }

}
*/