package com.company;

import java.util.ArrayList;

public class BookingsController {

    BookingsModel model;
    Main view;
    //
    BookingsController(BookingsModel model, Main view){
        this.model = model; this.view = view;
    }
    //seems this displays rows of info from the db in the "area"
    void initArea() {
        String dbResults = "";
        for(String result : model.get()) dbResults += result + "\n";
        view.setTextAreaInfo(dbResults);
    }
    //allows you to enter text which is displayed in the "area"
    void enterText(String input){
        model.add(input);
        view.clearTextFieldAddTeacher();
        String toArea = "";
        for(String t : model.get()) toArea += t + "\n";
        view.setTextAreaInfo(toArea);
    }

    //Retrieves information from Course Table
    void getInfoFromCourse(String courseID) {
        if (view.radioButtonInfoCourse.isSelected()) {
            ArrayList<String> result = model.getInfoFromCourse(courseID);
            String textInfo = "Maximum number of students is " + result.get(0);
            view.setTextAreaInfo(textInfo);
        }
        else {
            view.cleartextAreaInfo();
        }
    }

    //adds a lecturer based on the input in the text box
    void addTeacher(String teacherName) {
        int teacherID = model.addTeacher(teacherName);
        //Success check
        if (teacherID != -1) {
            String teacherIDAndName = teacherID + " - " + teacherName;
            view.comboBoxTeachers.getItems().add(teacherIDAndName);
            view.setTextAreaInfo("Teacher " +teacherName+ " added (TeacherID = "+teacherID+")");
        }
        else {
            view.setTextAreaInfo("Insert a valid teacher name.");
        }
    }

    void addRoomBooking(String CourseID, String TimeSlotID, String RoomID) {
        if (CourseID == null || TimeSlotID == null || RoomID == null) {
            view.setTextAreaInfo("Please select a course, time slot and a room.");
        }
        else{
            if (model.hasRoomBooking(TimeSlotID, RoomID)){
                view.setTextAreaInfo("Room booking already exists! Please try again.");
            } else {
                int RoomBookingID = model.addRoomBooking(CourseID, TimeSlotID, RoomID);
                view.setTextAreaInfo("Room is booked! The Room Booking ID is " + RoomBookingID + ".");
            }
        }
    }

    void addTeacherBooking(String CourseID, String TimeSlotID, String TeacherIDAndName) {
        if (CourseID == null || TimeSlotID == null || TeacherIDAndName == null) {
            view.setTextAreaInfo("Please select a course, time slot and a teacher.");
        }
        else {
            int TeacherID = Integer.parseInt(TeacherIDAndName.substring(0,5));
            if (model.hasTeacherBooking(TimeSlotID, TeacherID)) {
                view.setTextAreaInfo("Teacher booking already exists! Please try again.");
            } else {
                int TeacherBookingID = model.addTeacherBooking(CourseID, TimeSlotID, TeacherID);
                view.setTextAreaInfo("Teacher is booked! The Teacher booking ID is " + TeacherBookingID + ".");
            }
        }
    }
}
