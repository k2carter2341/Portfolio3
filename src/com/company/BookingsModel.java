package com.company;

import javax.lang.model.element.Name;
import java.util.ArrayList;

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
        //db.cmdSQL("CREATE TABLE if not exists RoomBooking "+ "(rbID, cID, tsID, rID);");
        //db.cmdSQL("create table if not exists TeacherBooking "+ "(tbID, cID, tsID, tID);");


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
        //addTeacher("792749", "Peter");
        //addTeacher("671651", "Clarice");
        //addTeacher("917358", "Ana");
        //addTeacher("571234", "Emily");
        //addTeacher("164796", "John");

        //Room Booking
        //addRoomBooking("rb 137", "History","Monday PM", "room 1");
        //addRoomBooking("rb 146", "PE","Tuesday PM", "room 2");
        //addRoomBooking("rb 177", "English","Monday AM", "room 3");

        //Teacher Booking
        //addTeacherBooking()
    }

    //void addTeacher(String tID, String Name){  db.cmdSQL("insert into Teacher (TeacherID ,Name) values ('"+tID+"', "+Name+");");}
//      ArrayList<String> getTeacher(){return db.querySQL("select Name from Teacher;","Name");}
    void addTeacher(String s){
        db.cmdSQL("insert into Teacher (TeacherID ,Name) values ('"+s+"');");
    }

    ArrayList<String> getTeacher(){
        return db.querySQL("select name from Teacher;","name");
    }

    boolean hasTeacher(String s){
        ArrayList<String> lst= db.querySQL("select name from Teacher where name = '"+s+"';","name");
        System.out.println(lst);
        return lst.size()>0;
        //return getTeacher().contains(s);
    }

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

    //void addRoomBooking(String rbID, String cID, String tsID, String rID){ db.cmdSQL("insert into RoomBooking (rbID, cID, tsID, rID) values ('"+rbID+"',"+cID+", "+tsID+", "+rID+",);");}
    //ArrayList<String> getRoomBooking(){return db.querySQL("select name from RoomBooking;","name");}

    //void addTeacherBooking(String tbID, String cID, String tsID, String tID){ db.cmdSQL("insert into RoomBooking (tbID, cID, tsID, tID) values ('"+tbID+"',"+cID+", "+tsID+", "+tID+",);");}
    //ArrayList<String> getTeacherBooking(){return db.querySQL("select name from RoomBooking;","name");}
}

