package com.company;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {
    private BookingsModel model=new BookingsModel();
    private BookingsController con=new BookingsController(model,this);
    private TextField field=new TextField();
    private TextArea area=new TextArea();
    ComboBox<String> lecturer = new ComboBox<>();
    ComboBox<String> courses = new ComboBox<>();
    ComboBox<String> rooms = new ComboBox<>();
    ComboBox<String> timeslot = new ComboBox<>();
    Button button = new Button("Add lecturer");
    Button button2 = new Button("Find room");
    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}
    @Override
    public void start(Stage stage) {
        con.initArea();
        field.setOnAction(e->con.enterText(field.getText()));
        VBox root = new VBox(courses,lecturer,rooms,timeslot,field,button,button2,area);
        lecturer.getItems().addAll(model.getLecturer());
        courses.getItems().addAll(model.getCourses());
        rooms.getItems().addAll(model.getRoom());
        timeslot.getItems().addAll(model.getTimeslot());
        button.setOnAction(e->con.addLecturer(field.getText()));
        button2.setOnAction(e->con.findRoom(courses.getValue()));
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}