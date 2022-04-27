package com.company;

import java.util.ArrayList;

public class BookingsModel {
    BookingsDB db = new BookingsDB();   //add in external libraries


    BookingsModel() {

        db.cmdSQL("drop table if exists lst1;");
        db.cmdSQL("create table if not exists lst1 "+
                "(fld1 integer primary key autoincrement, fld2 text);");

        db.cmdSQL("drop table if exists Courses;");
        db.cmdSQL("create table if not exists Courses "+
                "(name text, stud integer);");
        addCourses("Software Development","50");
        addCourses("Essential Computing","90");

        db.cmdSQL("drop table if exists Rooms;");
        db.cmdSQL("create table if not exists Rooms "+
                "(name text, stud integer);");
        addRoom("10.2.49","60");
        addRoom("10.1.25","30");

        db.cmdSQL("drop table if exists Timeslot;");
        db.cmdSQL("create table if not exists Timeslot "+
                "(name text);");
//        for(int i=1;i<=10;i++)addTimeslot("Slot "+i);
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
//        for(int i=1;i<=10;i++)addTimeslot("Slot "+i);
        for(String day:days){addTimeslot(day+" AM");addTimeslot(day+" PM");}
        db.cmdSQL("drop table if exists Lecturer;");
        db.cmdSQL("create table if not exists Lecturer "+
                "(name text);");
        addLecturer("Mads Rosendahl");
        addLecturer("Mickey Mouse");
        addLecturer("Spiderman");

        db.cmdSQL("drop table if exists RoomBooking;");
        db.cmdSQL("create table if not exists RoomBooking "+
                "(name text, Course ID, Time ID, Room ID);");

        db.cmdSQL("drop table if exists TeacherBooking;");
        db.cmdSQL("create table if not exists TeacherBooking "+
                "(name text, Course ID, Time ID, Teacher ID);");

        //PRAGMA foreign_keys = ON;

    }

    void addLecturer(String s){  db.cmdSQL("insert into Lecturer (name) values ('"+s+"');");}
    ArrayList<String> getLecturer(){return db.querySQL("select name from Lecturer;","name");}

    boolean hasLecturer(String s){
        ArrayList<String> lst = db.querySQL("select name from Lecturer where name = '"+s+"';","name");
        System.out.println(lst);
        return lst.size()>0;
        //return getLecturer().contains(s);
    }

    void addRoom(String s,String stud){db.cmdSQL("insert into Rooms (name,stud) values ('"+s+"',"+stud+");");}
    ArrayList<String> getRoom(){return db.querySQL("select name from Rooms;","name");}

    void addCourses(String s,String stud){ db.cmdSQL("insert into Courses (name,stud) values ('"+s+"',"+stud+");");}
    ArrayList<String> getCourses(){
        return db.querySQL("select name from Courses;","name");
    }

    String findRoom(String c){
        ArrayList<String> lst= db.querySQL(
                "SELECT Rooms.name from Rooms inner join Courses"
                        +" where Courses.name = '"+c+"' and Rooms.stud > Courses.stud;","name");
        System.out.println(lst);
        if(lst.size()==0)return "";
        else return lst.get(0);
    }

    void addTimeslot(String s){ // remember to sanitize your data!
        db.cmdSQL("insert into Timeslot (name) values ('"+s+"');");
    }
    ArrayList<String> getTimeslot(){
        return db.querySQL("select name from Timeslot;","name");
    }

    void add(String s){ // remember to sanitize your data!
        db.cmdSQL("insert into lst1 (fld2) values ('"+s+"');");
    }
    ArrayList<String> get(){
        return db.querySQL("select fld2 from lst1 order by fld1;","fld2");
    }
}

