package com.company;

public class BookingsController {

        BookingsModel model;
        Main view;
        BookingsController(BookingsModel model, Main view){
            this.model=model; this.view=view;
        }
        void initArea(){
            String toarea="";
            for(String t:model.get())toarea+=t+"\n";
            view.setArea(toarea);
        }
        void enterText(String s){
            model.add(s);
            view.clearField();
            String toarea="";
            for(String t:model.get())toarea+=t+"\n";
            view.setArea(toarea);
        }
        void addLecturer(String s){
            if(model.hasLecturer(s)){
                view.setArea("Cannot insert lecturer (repeat) "+s);
            } else {
                model.addLecturer(s);
                view.lecturer.getItems().add(s);
            }
        }
        void findRoom(String c){
            String room=model.findRoom(c);
            if(room.equals(""))view.setArea("No Room");
            else view.setArea("Room: "+room);
        }

}
