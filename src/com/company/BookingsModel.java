package com.company;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.Random;

public class BookingsModel {
    BookingsDB db = new BookingsDB();   //add in external libraries

    //GET course capacity

    BookingsModel() {

        //---Tables checks---
        db.cmdSQL("DROP TABLE if exists Room;");
        db.cmdSQL("DROP TABLE if exists TimeSlot;");
        db.cmdSQL("DROP TABLE if exists Teacher;");
        db.cmdSQL("DROP TABLE if exists Course;");
        //db.cmdSQL("DROP TABLE if exists RoomBooking;");
        //db.cmdSQL("DROP TABLE if exists TeacherBooking;");


        //---Tables creations---
        db.cmdSQL("CREATE TABLE if not exists Room" + "(RoomID string NOT NULL, MaxCapacity int NOT NULL, PRIMARY KEY (RoomID));");
        db.cmdSQL(" CREATE TABLE if not exists TimeSlot "+ "(TimeSlotID string NOT NULL, PRIMARY KEY (TimeSlotID));");
        db.cmdSQL("CREATE TABLE if not exists Teacher" + "(TeacherID int NOT NULL, Name string NOT NULL, PRIMARY KEY (TeacherID));");
        db.cmdSQL("CREATE TABLE if not exists Course " + "(CourseID string NOT NULL, Capacity string NOT NULL, PRIMARY KEY (CourseID)); ");
        db.cmdSQL("CREATE TABLE if not exists RoomBooking " + "(RoomBookingID int NOT NULL, CourseID string NOT NULL, TimeSlotID string NOT NULL, RoomID string NOT NULL, PRIMARY KEY (RoomBookingID), FOREIGN KEY (CourseID) REFERENCES Course (CourseID), FOREIGN KEY (TimeSlotID) REFERENCES TimeSlot (TimeSlotID), FOREIGN KEY (RoomID) REFERENCES Room (RoomID) );");
        //db.cmdSQL("create table if not exists TeacherBooking "+ "(tbID, cID, tsID, tID);");

        //db.cmdSQL("INSERT INTO RoomBooking (RoomBookingID, CourseID, TimeSlotID, RoomID) values ("+RoomBookingID+",'"+CourseID+"','"+TimeSlotID+"','"+RoomID+"');");

        //---Tables Insertions---

        //Course
        addCourse("Software Development","50");
        addCourse("Essential Computing","90");

        //TimeSlot
        addTimeSlot("Monday AM");
        addTimeSlot("Monday PM");
        addTimeSlot("Tuesday AM");
        addTimeSlot("Tuesday PM");
        addTimeSlot("Wednesday AM");
        addTimeSlot("Wednesday PM");
        addTimeSlot("Thursday AM");
        addTimeSlot("Thursday PM");
        addTimeSlot("Friday AM");
        addTimeSlot("Friday PM");

        //Room
        addRoom("Room1","60");
        addRoom("Room2","30");

        //Teacher
        addTeacher("Peter");
        addTeacher("Clarice");
        addTeacher("Ana");
        addTeacher("Emily");
        addTeacher("John");

        //Room Booking
        //addRoomBooking("Software Development","Monday PM", "Room1");
        //addRoomBooking("rb 146", "PE","Tuesday PM", "room 2");
        //addRoomBooking("rb 177", "English","Monday AM", "room 3");

        //Teacher Booking
        //addTeacherBooking()
    }

    //void addTeacher(String tID, String Name){  db.cmdSQL("insert into Teacher (TeacherID ,Name) values ('"+tID+"', "+Name+");");}
//      ArrayList<String> getTeacher(){return db.querySQL("select Name from Teacher;","Name");}

    void addTeacher(String teacherName){
        Random rand = new Random();
        int id = rand.nextInt(10000,99999);
        db.cmdSQL("INSERT INTO Teacher (TeacherID, Name) VALUES ("+id+",'"+teacherName+"');");
    }

    ArrayList<String> getTeacher(){
        return db.querySQL("SELECT TeacherID, Name FROM Teacher;","TeacherID");
    }

    ArrayList<String> getTeacherToDisplay(){
        ArrayList<String> teachersIDs;
        ArrayList<String> teachersNames;
        ArrayList<String> teachersIDsAndNames = new ArrayList<>();

        teachersIDs = db.querySQL("SELECT TeacherID, Name FROM Teacher;","TeacherID");
        teachersNames = db.querySQL("SELECT TeacherID, Name FROM Teacher;","Name");

        for (int i = 0; i < teachersIDs.size(); i++) {
            teachersIDsAndNames.add(teachersIDs.get(i) + " - " + teachersNames.get(i));
        }
        return teachersIDsAndNames;
    }

    /*
    boolean hasTeacher(String s){
        ArrayList<String> lst= db.querySQL("select name from Teacher where name = '"+s+"';","name");
        System.out.println(lst);
        return lst.size()>0;
        //return getTeacher().contains(s);
    }
     */

    void addRoom(String rID,String maxcap){db.cmdSQL("insert into Room (RoomID, MaxCapacity) values ('"+rID+"',"+maxcap+");");}
    ArrayList<String> getRoom(){return db.querySQL("select RoomID from Room;","RoomID");}

    void addCourse(String s,String Cap){ db.cmdSQL("insert into Course (CourseID,Capacity) values ('"+s+"',"+Cap+");");}
    ArrayList<String> getCourse(){
        return db.querySQL("select CourseID from Course;","CourseID");
    }

    String findRoom(String c){
        ArrayList<String> lst= db.querySQL(
                "select Room.name from Room inner join Course"
                        +" where Course.name = '"+c+"' and Room.stud > Course.stud;","name");
        System.out.println(lst);
        if(lst.size()==0)return "";
        else return lst.get(0);
    }

    void addTimeSlot(String tsID){ // remember to sanitize your data!
        db.cmdSQL("insert into TimeSlot (TimeSlotID) values ('"+tsID+"');");
    }
    ArrayList<String> getTimeSlot(){
        return db.querySQL("select TimeSlotID from TimeSlot;","TimeSlotID");
    }

    void add(String s){ // remember to sanitize your data!
        db.cmdSQL("insert into lst1 (fld2) values ('"+s+"');");
    }
    ArrayList<String> get(){
        return db.querySQL("select fld2 from lst1 order by fld1;","fld2");
    }

    void addRoomBooking(String CourseID, String TimeSlotID, String RoomID){
        Random rand = new Random();
        int RoomBookingID = rand.nextInt(100);
        db.cmdSQL("INSERT INTO RoomBooking (RoomBookingID, CourseID, TimeSlotID, RoomID) values ("+RoomBookingID+",'"+CourseID+"','"+TimeSlotID+"','"+RoomID+"');");
    }
    //ArrayList<String> getRoomBooking(){return db.querySQL("select name from RoomBooking;","name");}

    //void addTeacherBooking(String tbID, String cID, String tsID, String tID){ db.cmdSQL("insert into RoomBooking (tbID, cID, tsID, tID) values ('"+tbID+"',"+cID+", "+tsID+", "+tID+",);");}
    //ArrayList<String> getTeacherBooking(){return db.querySQL("select name from RoomBooking;","name");}
}

