package com.company;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.Random;

public class BookingsModel {
    BookingsDB db = new BookingsDB();   //add in external libraries

    //GET course capacity

    BookingsModel() {

        if (Main.reset.equals("y")) {

            //---Tables checks---
            db.cmdSQL("DROP TABLE if exists Room;");
            db.cmdSQL("DROP TABLE if exists TimeSlot;");
            db.cmdSQL("DROP TABLE if exists Teacher;");
            db.cmdSQL("DROP TABLE if exists Course;");
            db.cmdSQL("DROP TABLE if exists RoomBooking;");
            db.cmdSQL("DROP TABLE if exists TeacherBooking;");


            //---Tables creations---
            db.cmdSQL("CREATE TABLE IF NOT EXISTS Room" + "(RoomID string NOT NULL, MaxCapacity int NOT NULL, PRIMARY KEY (RoomID));");
            db.cmdSQL("CREATE TABLE IF NOT EXISTS TimeSlot " + "(TimeSlotID string NOT NULL, PRIMARY KEY (TimeSlotID));");
            db.cmdSQL("CREATE TABLE IF NOT EXISTS Teacher" + "(TeacherID int NOT NULL, Name string NOT NULL, PRIMARY KEY (TeacherID));");
            db.cmdSQL("CREATE TABLE IF NOT EXISTS Course " + "(CourseID string NOT NULL, Capacity string NOT NULL, PRIMARY KEY (CourseID)); ");
            db.cmdSQL("CREATE TABLE IF NOT EXISTS RoomBooking " + "(RoomBookingID int NOT NULL, CourseID string NOT NULL, TimeSlotID string NOT NULL, RoomID string NOT NULL, PRIMARY KEY (RoomBookingID), FOREIGN KEY (CourseID) REFERENCES Course (CourseID), FOREIGN KEY (TimeSlotID) REFERENCES TimeSlot (TimeSlotID), FOREIGN KEY (RoomID) REFERENCES Room (RoomID) );");
            db.cmdSQL("Create Table IF NOT EXISTS TeacherBooking "+ "(TeacherBookingID int NOT NULL, CourseID string NOT NULL, TimeSlotID string NOT NULL, TeacherID int NOT NULL, PRIMARY KEY (TeacherBookingID), FOREIGN KEY (CourseID) REFERENCES Course (CourseID), FOREIGN KEY (TimeSlotID) REFERENCES TimeSlot (TimeSlotID), FOREIGN KEY (TeacherID) REFERENCES Teacher (TeacherID) );");


            //---Tables Insertions---

            //Course
            addCourse("Software Development", 50);
            addCourse("Essential Computing", 90);
            addCourse("Computer Science Project", 120);
            addCourse("BigData Knowledge", 40);
            addCourse("UI Design", 55);

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
            addRoom("Room1", 60);
            addRoom("Room2", 30);
            addRoom("Room3", 50);
            addRoom("Room4", 40);
            addRoom("Room5", 70);

            //Teacher
            addDefaultTeacher(49123, "Peter");
            addDefaultTeacher(84202, "Clarice");
            addDefaultTeacher(28492, "Ana");
            addDefaultTeacher(91291, "Emily");
            addDefaultTeacher(59395, "John");

            //Room Booking
            addRoomBooking("Software Development","Monday PM", "Room1");
            addRoomBooking("Essential Computing","Tuesday PM", "Room2");
            addRoomBooking("BigData Knowledge","Thursday AM", "Room3");

            //Teacher Booking
            addTeacherBooking("Computer Science Project", "Wednesday PM", 84202);
            addTeacherBooking("Ui Design", "Monday AM", 59395);
        }
    }


    //---Teacher functions---

    void addDefaultTeacher(int TeacherID ,String TeacherName) {
        db.cmdSQL("INSERT INTO Teacher (TeacherID, Name) VALUES ("+TeacherID+",'"+TeacherName+"');");
    }

    int addTeacher(String TeacherName){
        if (TeacherName != "") {
            Random rand = new Random();
            int TeacherID = rand.nextInt(10000,99999);
            db.cmdSQL("INSERT INTO Teacher (TeacherID, Name) VALUES ("+TeacherID+",'"+TeacherName+"');");
            return TeacherID;
        }
        else {
            return -1;
        }
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

    // IN DOUBT IF WE NEED TO USE
    /*
    boolean hasTeacher(String s){
        ArrayList<String> lst= db.querySQL("select name from Teacher where name = '"+s+"';","name");
        System.out.println(lst);
        return lst.size()>0;
        //return getTeacher().contains(s);
    }
     */


    //---Room functions---

    void addRoom(String RoomID, int MaxCapacity){
        db.cmdSQL("INSERT INTO Room (RoomID, MaxCapacity) VALUES ('"+RoomID+"',"+MaxCapacity+");");
    }

    ArrayList<String> getRoom(){
        return db.querySQL("SELECT RoomID FROM Room;","RoomID");
    }

    //NOT BEING USED
    /*
    String findRoom(String c){
        ArrayList<String> lst= db.querySQL(
                "SELECT Room.Name FROM Room INNER JOIN Course"
                        +" WHERE Course.Name = '"+c+"' and Room.Stud > Course.Stud;","name");
        System.out.println(lst);
        if(lst.size()==0)return "";
        else return lst.get(0);
    }
     */


    //---Course functions---

    void addCourse(String CourseID, int MaxCapacity) {
        db.cmdSQL("INSERT INTO Course (CourseID,Capacity) VALUES ('"+CourseID+"',"+MaxCapacity+");");
    }

    ArrayList<String> getCourse() {
        return db.querySQL("SELECT CourseID FROM Course;","CourseID");
    }

    ArrayList<String> getInfoFromCourse(String CourseID) {
        return db.querySQL("SELECT Capacity FROM Course WHERE Course.CourseID = '"+CourseID+"';", "Capacity");
    }


    //---Time Slot functions---

    void addTimeSlot(String TimeSlotID) {
        db.cmdSQL("INSERT INTO TimeSlot (TimeSlotID) VALUES ('"+TimeSlotID+"');");
    }

    ArrayList<String> getTimeSlot(){
        return db.querySQL("SELECT TimeSlotID FROM TimeSlot;","TimeSlotID");
    }


    //---Room Booking functions---

    int addRoomBooking(String CourseID, String TimeSlotID, String RoomID){

        //ArrayList<String> RoomsBooked;
        //db.querySQL("SELECT TimeSlotID FROM TimeSlot;","TimeSlotID");

        Random rand = new Random();
        int RoomBookingID = rand.nextInt(100);
        db.cmdSQL("INSERT INTO RoomBooking (RoomBookingID, CourseID, TimeSlotID, RoomID) VALUES ("+RoomBookingID+",'"+CourseID+"','"+TimeSlotID+"','"+RoomID+"');");
        return RoomBookingID;
    }

    //ArrayList<String> getRoomBooking(){
    //  return db.querySQL("select name from RoomBooking;","name");
    //}



    //---Teacher Booking functions---

    void addTeacherBooking(String CourseID, String TimeSlotID, int TeacherID){
        Random rand = new Random();
        int TeacherBookingID = rand.nextInt(100);
        db.cmdSQL("INSERT INTO TeacherBooking (TeacherBookingID, CourseID, TimeSlotID, TeacherID) VALUES ("+TeacherBookingID+",'"+CourseID+"','"+TimeSlotID+"',"+TeacherID+");");
    }

    //ArrayList<String> getTeacherBooking(){
    //  return db.querySQL("select name from RoomBooking;","name");
    //}



    //---Common functions----
    //Don't know exactly what are they supposed to (?)

    void add(String s){
        db.cmdSQL("INSERT INTO lst1 (fld2) VALUES ('"+s+"');");
    }
    ArrayList<String> get(){
        return db.querySQL("SELECT fld2 FROM lst1 ORDER BY fld1;","fld2");
    }
}

