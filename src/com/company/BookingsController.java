package com.company;

import java.util.ArrayList;

public class BookingsController {


        BookingsModel model;
        Main view;
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
        //adds a lecturer based on the input in the text box
        void addTeacher(String teacherName) {
            /*
            if(model.hasTeacher(input)){
                view.setArea("Teacher name already exists! " + input + " Cannot be added to the system.");
            } else {
             */
                int teacherID = model.addTeacher(teacherName);
                //Success check
                if (teacherID != -1) {
                    String teacherIDAndName = teacherID + " - " + teacherName;
                    view.comboBoxTeachers.getItems().add(teacherIDAndName);
                }
            //}
        }

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

        //not exactly sure what this method does - is it for searching for a particular room? or for booking a room?
    /*
        void findRoom(String input){
            String room = model.findRoom(input);
            if(room.equals("")) view.setTextAreaComboBoxInfo("No Room");
            else view.setTextAreaComboBoxInfo("Room: " + room);
        }
    */
}
