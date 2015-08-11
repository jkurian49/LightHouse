package cumakerspace.lighthouse.MainActivity;

/**
 * Created by student on 8/6/15.
 */
public class Trip {

    String tripid;
    String departuretime;
    String arrivaltime;



    public Trip(String tripid, String departuretime, String arrivaltime){
        this.tripid = tripid;
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
    }
    @Override
    public String toString(){
        return departuretime + "-" + arrivaltime;
    }

}
