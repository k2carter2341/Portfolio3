package com.company;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {



    //Old code

    private BookingsModel model = new BookingsModel();
    private BookingsController controller = new BookingsController(model,this);
    private TextField field = new TextField();
    private TextArea area = new TextArea();
    ComboBox<String> teacher = new ComboBox<>();
    ComboBox<String> courses = new ComboBox<>();
    ComboBox<String> rooms = new ComboBox<>();
    ComboBox<String> timeslot = new ComboBox<>();
    Button button = new Button("Add lecturer");
    Button button2 = new Button("Find room");
    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}



    //New code
    /*
    ComboBox<String> comboBoxCourses = new ComboBox<>();
    Label labelSelectCourse = new Label("Select course");
    RadioButton radioButtonInfoCourse = new RadioButton("Get info from course");

    ComboBox<String> comboBoxRooms = new ComboBox<>();
    Label labelSelectRoom = new Label("Select room");

    ComboBox<String> comboBoxTimeSlots = new ComboBox<>();
    Label labelSelectTimeSlot = new Label("Select time slot");

    ComboBox<String> comboBoxTeachers = new ComboBox<>();
    Label labelSelectTeacher = new Label("Select teacher");

    Button buttonAddTeacher = new Button("Add teacher");
    TextField textFieldAddTeacher = new TextField();

    //RadioButton radioButtonInfoTeacher = new RadioButton("Get info from teacher");
    //RadioButton radioButtonInfoRoom = new RadioButton("Get info from room");
    //RadioButton radioButtonInfoTimeSlot = new RadioButton("Get info from time slot");

    TextArea textAreaComboBoxInfo = new TextArea();
    Button buttonBookRoom = new Button("Book room");
    Button buttonBookTeacher = new Button("Book teacher");

    ToggleGroup togleGroupRadioButtons;
     */



    @Override
    public void start(Stage stage) {

        //New Ui
        /*
        //Pre configurations
        radioButtonInfoCourse.setToggleGroup(togleGroupRadioButtons);
        //radioButtonInfoTeacher.setToggleGroup(togleGroupRadioButtons);
        //radioButtonInfoRoom.setToggleGroup(togleGroupRadioButtons);

        textAreaComboBoxInfo.setEditable(false);


        //Adding UI elements to VBox
        VBox root = new VBox(labelSelectCourse, comboBoxCourses, radioButtonInfoCourse,
                labelSelectRoom, comboBoxRooms,
                labelSelectTimeSlot, comboBoxTimeSlots,
                labelSelectTeacher, comboBoxTeachers,
                textFieldAddTeacher, buttonAddTeacher, textAreaComboBoxInfo,
                buttonBookRoom, buttonBookTeacher);


        //Creating scene and stage and lunching them
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Booking System");
        stage.setScene(scene);
        stage.show();
         */

        //Old UI
        controller.initArea();
        field.setOnAction(e->controller.enterText(field.getText()));
        VBox root = new VBox(courses,teacher,rooms,timeslot,field,button,button2,area);
        teacher.getItems().addAll(model.getTeacher());
        courses.getItems().addAll(model.getCourse());
        rooms.getItems().addAll(model.getRoom());
        timeslot.getItems().addAll(model.getTimeSlot());
        button.setOnAction(e->controller.addTeacher(field.getText()));
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