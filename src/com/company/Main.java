package com.company;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import java.util.Scanner;

public class Main extends Application {

    static String reset;

    private BookingsModel model = new BookingsModel();
    private BookingsController controller = new BookingsController(model,this);

//Dropdown boxes that have information/IDs from Tables
    ComboBox<String> comboBoxCourses = new ComboBox<>();
    Label labelSelectCourse = new Label("Select course");
    RadioButton radioButtonInfoCourse = new RadioButton("Get info from course");

    ComboBox<String> comboBoxRooms = new ComboBox<>();
    Label labelSelectRoom = new Label("Select room");

    ComboBox<String> comboBoxTimeSlots = new ComboBox<>();
    Label labelSelectTimeSlot = new Label("Select time slot");

    ComboBox<String> comboBoxTeachers = new ComboBox<>();
    Label labelSelectTeacher = new Label("Select teacher");

    //The user can add another teacher
    Button buttonAddTeacher = new Button("Add teacher");
    TextField textFieldAddTeacher = new TextField();    //The name of the Teacher that will be added

    //RadioButton radioButtonInfoTeacher = new RadioButton("Get info from teacher");
    //RadioButton radioButtonInfoRoom = new RadioButton("Get info from room");
    //RadioButton radioButtonInfoTimeSlot = new RadioButton("Get info from time slot");

    Label labelInfo = new Label("Info");
    TextArea textAreaInfo = new TextArea();

    Button buttonBookRoom = new Button("Book room");
    Button buttonBookTeacher = new Button("Book teacher");

    ToggleGroup togleGroupRadioButtons;

    void setTextAreaInfo(String s){textAreaInfo.setText(s);}
    void cleartextAreaInfo(){textAreaInfo.setText("");}
    void clearTextFieldAddTeacher(){textFieldAddTeacher.setText("");}

    @Override
    public void start(Stage stage) {

        //New Ui

        //Pre configurations

        controller.initArea();
        textFieldAddTeacher.setOnAction(e->controller.enterText(textFieldAddTeacher.getText()));

        radioButtonInfoCourse.setToggleGroup(togleGroupRadioButtons);
        radioButtonInfoCourse.setOnAction(e->controller.getInfoFromCourse(comboBoxCourses.getValue()));
        radioButtonInfoCourse.setDisable(true);
        //radioButtonInfoTeacher.setToggleGroup(togleGroupRadioButtons);
        //radioButtonInfoRoom.setToggleGroup(togleGroupRadioButtons);

        textAreaInfo.setEditable(false);

        comboBoxTeachers.getItems().addAll(model.getTeacherToDisplay());
        comboBoxCourses.getItems().addAll(model.getCourse());
        comboBoxRooms.getItems().addAll(model.getRoom());
        comboBoxTimeSlots.getItems().addAll(model.getTimeSlot());
        buttonAddTeacher.setOnAction(e->controller.addTeacher(textFieldAddTeacher.getText()));

        comboBoxCourses.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                controller.getInfoFromCourse(comboBoxCourses.getValue());
                radioButtonInfoCourse.setDisable(false);
            }
        });

        //buttonBookRoom.setOnAction(e->radioButtonInfoCourse.setSelected(false));
        buttonBookRoom.setOnAction(e->controller.addRoomBooking(comboBoxCourses.getValue(), comboBoxTimeSlots.getValue(), comboBoxRooms.getValue()));

        //buttonBookTeacher.setOnAction(e->radioButtonInfoCourse.setSelected(false));
        buttonBookTeacher.setOnAction(e->controller.addTeacherBooking(comboBoxCourses.getValue(), comboBoxTimeSlots.getValue(), comboBoxTeachers.getValue()));

        //Adding UI elements to VBox
        VBox root = new VBox(labelSelectCourse, comboBoxCourses, radioButtonInfoCourse,
                labelSelectRoom, comboBoxRooms,
                labelSelectTimeSlot, comboBoxTimeSlots,
                labelSelectTeacher, comboBoxTeachers,
                textFieldAddTeacher, buttonAddTeacher,
                labelInfo,textAreaInfo,
                buttonBookRoom, buttonBookTeacher);


        //Creating scene and stage and lunching them
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Booking System");
        stage.setScene(scene);
        stage.show();

    }
    //launch: create obj from class, create stage obj, call start
    public static void main(String[] args) {
        System.out.print("Would you like to reset the database? ('y' = Yes | 'n' = No): ");
        Scanner scanner = new Scanner(System.in);
        reset = scanner.nextLine();
        launch(args);
    }
}