package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends model.User {


    public Doctor(String name, String email){
        super(name, email);
    }

    @Override
    public void showDataUser() {
        System.out.println("Hospital: Cruz roja");
        System.out.println("Departamento de oncologia");

    }

    ArrayList<AvailableAppoinment> availableAppoinments = new ArrayList<>();
    public  void addAvailableAppoinment(String date, String time){
        try {
            availableAppoinments.add(new AvailableAppoinment(date, time));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<AvailableAppoinment> getAvailableAppoinments(){
        return availableAppoinments;
    }

    public static class AvailableAppoinment{
        private int id;
        private Date date;
        private  String time;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        AvailableAppoinment(String date, String time) throws ParseException {
            try{
                this.date = format.parse(date);
            }catch (ParseException e){
                e.printStackTrace();
            }
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate(String  DATE){
            return date;
        }

        public String getDate() {
            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
