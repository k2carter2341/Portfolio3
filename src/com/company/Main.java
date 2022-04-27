package com.company;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private BookingsModel model = new BookingsModel();
    private BookingsController controller = new BookingsController(model,this);
    private TextField field = new TextField();
    private TextArea area = new TextArea();
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
        controller.initArea();
        field.setOnAction(e->controller.enterText(field.getText()));
        VBox root = new VBox(courses,lecturer,rooms,timeslot,field,button,button2,area);
        lecturer.getItems().addAll(model.getLecturer());
        courses.getItems().addAll(model.getCourses());
        rooms.getItems().addAll(model.getRoom());
        timeslot.getItems().addAll(model.getTimeslot());
        button.setOnAction(e->controller.addLecturer(field.getText()));
        button2.setOnAction(e->controller.findRoom(courses.getValue()));
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }
    //launch: create obj from class, create stage obj, call start
    public static void main(String[] args) {
        launch(args);
    }
}