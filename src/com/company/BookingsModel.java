package com.company;

import javax.lang.model.element.Name;
import java.util.ArrayList;

public class BookingsModel {
    BookingsDB db = new BookingsDB();   //add in external libraries

    BookingsModel() {
//        db.cmd("drop table if exists lst1;");
//        db.cmd("create table if not exists lst1 "+
//                "(fld1 integer primary key autoincrement, fld2 text);");

        db.cmd("drop table if exists Rooms;");
        db.cmd("CREATE TABLE Rooms" + "(RoomID string NOT NULL, MaxCapacity int NOT NULL, PRIMARY KEY (RoomID));");
        addRoom("10.2.49","60");
        addRoom("10.1.25","30");

        //incomplete
        db.cmd("drop table if exists Timeslot;");
        db.cmd(" CREATE TABLE if not exists Timeslot "+
                "(TimeSlotID string NOT NULL, PRIMARY KEY (TimeSlotID));");

        addTimeslot("Monday AM");
        addTimeslot("Monday PM");
        addTimeslot("Tuesday AM");
        addTimeslot("Tuesday PM");
        addTimeslot("Wednesday AM");
        addTimeslot("Wednesday PM");
        addTimeslot("Thursday AM");
        addTimeslot("Thursday PM");
        addTimeslot("Friday AM");
        addTimeslot("Friday PM");


        db.cmd("drop table if exists Lecturer;");
        db.cmd("CREATE TABLE Lecturer" +
                "(TeacherID int NOT NULL, Name string NOT NULL, PRIMARY KEY (TeacherID));");
//        addLecturer("792749", "Peter");
//        addLecturer("671651", "Clarice");
//        addLecturer("917358", "Ana");
//        addLecturer("571234", "Emily");
//        addLecturer("164796", "John");




        db.cmd("drop table if exists RoomBooking;");
        db.cmd("create table if not exists RoomBooking "+
                "(rbID, cID, tsID, rID);");
//        addRoomBooking("rb 137", "History","Monday PM", "room 1");
//        addRoomBooking("rb 146", "PE","Tuesday PM", "room 2");
//        addRoomBooking("rb 177", "English","Monday AM", "room 3");


        db.cmd("drop table if exists TeacherBooking;");
        db.cmd("create table if not exists TeacherBooking "+
                "(tbID, cID, tsID, tID);");
        //addTeacherBooking()

        db.cmd("drop table if exists Courses;");
        db.cmd("CREATE TABLE Courses " +
                "(CourseID string NOT NULL, Capacity string NOT NULL, PRIMARY KEY (CourseID)); ");

        addCourses("Software Development","50");
        addCourses("Essential Computing","90");

    }

//    void addLecturer(String tID, String Name){  db.cmd("insert into Lecturer (TeacherID ,Name) values ('"+tID+"', "+Name+");");}
//    ArrayList<String> getLecturer(){return db.query("select Name from Lecturer;","Name");}
    void addLecturer(String s){  db.cmd("insert into Lecturer (TeacherID ,Name) values ('"+s+"');");}
    ArrayList<String> getLecturer(){return db.query("select name from Lecturer;","name");}

    boolean hasLecturer(String s){
        ArrayList<String> lst= db.query("select name from Lecturer where name = '"+s+"';","name");
        System.out.println(lst);
        return lst.size()>0;
        //return getLecturer().contains(s);
    }

    void addRoom(String rID,String maxcap){db.cmd("insert into Rooms (RoomID, MaxCapacity) values ('"+rID+"',"+maxcap+");");}
    ArrayList<String> getRoom(){return db.query("select RoomID from Rooms;","RoomID");}

    void addCourses(String s,String Cap){ db.cmd("insert into Courses (CourseID,Capacity) values ('"+s+"',"+Cap+");");}
    ArrayList<String> getCourses(){
        return db.query("select CourseID from Courses;","CourseID");
    }

    String findRoom(String c){
        ArrayList<String> lst= db.query(
                "select Rooms.name from Rooms inner join Courses"
                        +" where Courses.name = '"+c+"' and Rooms.stud > Courses.stud;","name");
        System.out.println(lst);
        if(lst.size()==0)return "";
        else return lst.get(0);
    }

    void addTimeslot(String tsID){ // remember to sanitize your data!
        db.cmd("insert into Timeslot (TimeSlotID) values ('"+tsID+"');");
    }
    ArrayList<String> getTimeslot(){
        return db.query("select TimeSlotID from Timeslot;","TimeSlotID");
    }

    void add(String s){ // remember to sanitize your data!
        db.cmd("insert into lst1 (fld2) values ('"+s+"');");
    }
    ArrayList<String> get(){
        return db.query("select fld2 from lst1 order by fld1;","fld2");
    }

    //void addRoomBooking(String rbID, String cID, String tsID, String rID){ db.cmd("insert into RoomBooking (rbID, cID, tsID, rID) values ('"+rbID+"',"+cID+", "+tsID+", "+rID+",);");}
    //ArrayList<String> getRoomBooking(){return db.query("select name from RoomBooking;","name");}

    //void addTeacherBooking(String tbID, String cID, String tsID, String tID){ db.cmd("insert into RoomBooking (tbID, cID, tsID, tID) values ('"+tbID+"',"+cID+", "+tsID+", "+tID+",);");}
    //ArrayList<String> getTeacherBooking(){return db.query("select name from RoomBooking;","name");}
}

