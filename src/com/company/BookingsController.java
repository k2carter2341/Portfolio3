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
            view.setTextAreaComboBoxInfo(dbResults);
        }
        //allows you to enter text which is displayed in the "area"
        void enterText(String input){
            model.add(input);
            view.clearTextFieldAddTeacher();
            String toArea = "";
            for(String t : model.get()) toArea += t + "\n";
            view.setTextAreaComboBoxInfo(toArea);
        }

        //Retrieves information from Course Table
        void getInfoFromCourse(String courseID) {
            if (view.radioButtonInfoCourse.isSelected()) {
                ArrayList<String> result = model.getInfoFromCourse(courseID);
                String textInfo = "Maximum number of students is " + result.get(0);
                view.setTextAreaComboBoxInfo(textInfo);
            }
            else {
                view.clearTextAreaComboBoxInfo();
            }
        }

        //adds a lecturer based on the input in the text box
        void addTeacher(String teacherName) {
            int teacherID = model.addTeacher(teacherName);
            //Success check
            if (teacherID != -1) {
                String teacherIDAndName = teacherID + " - " + teacherName;
                view.comboBoxTeachers.getItems().add(teacherIDAndName);
            }
        }

        void addRoomBooking(String CourseID, String TimeSlotID, String RoomID) {
                if(model.hasRoomBooking(CourseID, TimeSlotID, RoomID)){
                    view.setTextAreaComboBoxInfo("Room booking already exists! Please try again.");
                } else {
                    int roomBookingID = model.addRoomBooking(CourseID, TimeSlotID, RoomID);
                    view.setTextAreaComboBoxInfo("Room is booked! The Room Booking ID is " + roomBookingID + ".");
                }
        }

        void addTeacherBooking(String CourseID, String TimeSlotID, int TeacherID) {
            if(model.hasTeacherBooking(CourseID, TimeSlotID, TeacherID)){
                view.setTextAreaComboBoxInfo("Room Booking already exists! Please try again.");
            } else {
                int teacherBookingID = model.addTeacherBooking(CourseID, TimeSlotID, TeacherID);
                view.setTextAreaComboBoxInfo("Room is booked! The Room Booking ID is " + teacherBookingID + ".");
            }
        }

}
