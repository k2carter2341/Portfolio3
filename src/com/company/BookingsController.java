package com.company;

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
            view.setArea(dbResults);
        }
        //allows you to enter text which is displayed in the "area"
        void enterText(String input){
            model.add(input);
            view.clearField();
            String toArea = "";
            for(String t : model.get()) toArea += t + "\n";
            view.setArea(toArea);
        }
        //adds a lecturer based on the input in the text box
        void addLecturer(String input){
            if(model.hasLecturer(input)){
                view.setArea("Lecturer name already exists! " + input + " Cannot be added to the system.");
            } else {
                model.addLecturer(input);
                view.lecturer.getItems().add(input);
            }
        }
        //not exactly sure what this method does - is it for searching for a particular room? or for booking a room?
        void findRoom(String input){
            String room = model.findRoom(input);
            if(room.equals("")) view.setArea("No Room");
            else view.setArea("Room: " + room);
        }

}
