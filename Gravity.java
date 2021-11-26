package gravity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gravity extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();	
	root.setPadding(new Insets(10,10,10,10));
        Button btn1,btn2,btn3;
        TextField txt1, txt2,txt3,txt4;
        Label lblSum,lb1,lb2,lb3,lb4;
	lblSum = new Label("");
	root.setBottom(lblSum);
        GridPane center = new GridPane();         
	center.setVgap(5);
	center.setHgap(5);
        Text welcometxt = new Text("Gravity Calculator");
        welcometxt.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        center.add(welcometxt,0,0);
        btn1 = new Button("Read Data");
        btn1.setPrefWidth(170);
        btn2 = new Button("Reset");
        btn2.setPrefWidth(170);
        btn3 = new Button("Compute Distance");
        btn3.setPrefWidth(450);
        center.add(btn1, 0, 1);
	center.add(btn2, 1, 1);
	txt1 = new TextField("");       
	txt1.setPrefWidth(350);
	txt2 = new TextField("");
	txt2.setPrefWidth(350);
        txt3 = new TextField();
	txt3.setPrefWidth(350);
        txt4 = new TextField();
	txt4.setPrefWidth(350);
        lb1=new Label("Acceleration");
        lb1.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        lb2=new Label("Time");
        lb2.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        lb3=new Label("Initial Velocity");
        lb3.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        lb4=new Label("Initial Position");
        lb4.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));

        center.add(lb1, 0, 2);
	center.add(lb2, 0, 4);
        center.add(lb3, 0, 6);
        center.add(lb4, 0, 8);
	center.add(txt1, 0, 3);
	center.add(txt2, 0, 5);
        center.add(txt3, 0, 7);
        center.add(txt4, 0, 9);
        center.add(btn3, 0, 10);
      
         root.setCenter(center);
        btn2.disableProperty().bind(Bindings.isEmpty(txt1.textProperty())
                .and(Bindings.isEmpty(txt2.textProperty()))
                .and(Bindings.isEmpty(txt3.textProperty())));
      
           btn1.setOnAction(new EventHandler<ActionEvent>()
	   {
	   	@Override
	   	public void handle(ActionEvent event)
	   	{
                    
                         try {
                           Scanner scanner = new Scanner(new File("C:\\Users\\Abishek Mahesh\\Downloads\\values.txt"));
                         List<String> lines = new ArrayList<String>();
                         while (scanner.hasNextLine()) {
                           lines.add(scanner.nextLine());
                           }
                           String[] arr = lines.toArray(new String[0]);
                            int index=0;
                            if(txt1.getText().isEmpty()){
                               
                               txt1.setText(arr[index]);
                             }
                            else if(txt2.getText().isEmpty()){
                               
                              txt2.setText(arr[index+1]);
                             }     
                            else if(txt3.getText().isEmpty()){
                              
                              txt3.setText(arr[index+2]);
                              } 
                            else if(txt4.getText().isEmpty()){
                             
                              txt4.setText(arr[index+3]);
                              }
                               

                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(Gravity.class.getName()).log(Level.SEVERE, null, ex);
                       }
                              
	   	}
	   }
	  );
             btn2.setOnAction(new EventHandler<ActionEvent>()
	   {
	   	@Override
	   	public void handle(ActionEvent event)
	   	{
                    txt1.clear();
                    txt2.clear();
                    txt3.clear();
                    txt4.clear();
                }
           });
             btn3.setOnAction(new EventHandler<ActionEvent>()
	   {
	   	@Override
	   	public void handle(ActionEvent event)
	   	{
                    btn1.setDisable(true);
                   double acc = Double.parseDouble(txt1.getText());
                   double time = Double.parseDouble(txt2.getText());
                   double initialVelocity = Double.parseDouble(txt3.getText());
                   double initialPosition = Double.parseDouble(txt4.getText());
                   double finalPosition = 0.0;
                   finalPosition = (acc*(Math.pow(time,2)/2)+ (initialVelocity*time) + initialPosition);
                   lblSum.setText("The object's position after " + time +" seconds is "+finalPosition + " m.");
                }
           });
         Scene scene = new Scene(root, 450, 350);
        primaryStage.setTitle("Gravity Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
