package phpregistration;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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


public class Phpregistration extends Application {
    
     @Override
   public void start(Stage primaryStage) throws Exception
   {
	  BorderPane root = new BorderPane();	
	  root.setPadding(new Insets(10,10,10,10));

        Button btnAdd;
        TextField txtNum1, txtNum2,txtNum3;
        Label lblSum,lb1,lb2,lb3;
	  lblSum = new Label("");
	  root.setBottom(lblSum);
	  GridPane center = new GridPane();         
	  center.setVgap(5);
	  center.setHgap(5);
          Text welcometxt = new Text("Registration");
          welcometxt.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
          center.add(welcometxt,0,0);
	  txtNum1 = new TextField("");       
	  txtNum1.setPrefWidth(350);
	  txtNum2 = new TextField("");
	  txtNum2.setPrefWidth(350);
          txtNum3 = new PasswordField();
	  txtNum3.setPrefWidth(350);
          lb1=new Label("Enter Your Name");
          lb1.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
          lb2=new Label("Enter Your E-mail");
          lb2.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
          lb3=new Label("Enter Your Password");
          lb3.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
	  center.add(lb1, 0, 2);
	  center.add(lb2, 0, 4);
          center.add(lb3, 0, 6);
	  center.add(txtNum1, 0, 3);
	  center.add(txtNum2, 0, 5);
          center.add(txtNum3, 0, 7);
         
	  btnAdd = new Button("Click to Register");
           btnAdd. setStyle("-fx-background-color: #00FF00; ");
	  center.add(btnAdd, 0, 9);
	  root.setCenter(center);

	  
	  btnAdd.setOnAction(new EventHandler<ActionEvent>()
	   {
	   	@Override
	   	public void handle(ActionEvent event)
	   	{
                       try {
                           Scanner scanner = new Scanner(new File("C:\\Users\\Abishek Mahesh\\Downloads\\OTP.DAT"));
                         List<String> lines = new ArrayList<String>();
                         while (scanner.hasNextLine()) {
                           lines.add(scanner.nextLine());
                           }
                           String[] arr = lines.toArray(new String[0]);
                           Random random = new Random();
                           int index = random.nextInt(arr.length);                                                  
                           FileWriter myWriter;
                           try {
                                   myWriter = new FileWriter("C:\\Users\\Abishek Mahesh\\Downloads\\RegistrationOTP.txt");
                                         myWriter.write(arr[index]);
                                          myWriter.close();
                                System.out.println("Please type the OTP received in local system");
                                lblSum.setText("Please type the OTP received in local system");
                               } catch (IOException ex) {
                                   Logger.getLogger(Phpregistration.class.getName()).log(Level.SEVERE, null, ex);
                               }
    
                           showOTPscreen(arr[index]);
                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(Phpregistration.class.getName()).log(Level.SEVERE, null, ex);
                       }
	   	}
	   }
	  );

   	  Scene scene = new Scene(root, 450, 250);
      primaryStage.setTitle("PHP Registration");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
    public void showOTPscreen(String OTP) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();	
	root.setPadding(new Insets(10,10,10,10));
        GridPane center = new GridPane();
         TextField txtNum1;
        Label lblSum,lb1;
        lblSum = new Label("");
	  center.setVgap(10);
	  center.setHgap(10);
          center.setPadding(new Insets(10));       
            Text welcometxt = new Text("Enter OTP Number ");
          welcometxt.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
          center.add(welcometxt,0,0);
	  txtNum1 = new TextField("");
	  txtNum1.setPrefWidth(350);
            lb1=new Label("Enter OTP Number");
          lb1.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
	  center.add(lb1, 0, 2);
	  center.add(txtNum1, 0, 3);
         Button btnAdd = new Button("Submit");
           btnAdd. setStyle("-fx-background-color: #00FF00; ");
           btnAdd.setDisable(true);
	  center.add(btnAdd, 0, 5);
           center.add(lblSum, 0, 7);
          root.setCenter(center);
        txtNum1.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
           if(t1.equals(""))
               btnAdd.setDisable(true);
           else
               btnAdd.setDisable(false);
        }          
    });
          btnAdd.setOnAction(new EventHandler<ActionEvent>()
	   {
	   	@Override
	   	public void handle(ActionEvent event)
	   	{
                    String OTPe = txtNum1.getText();
                    if(OTPe.equals(OTP)){
                        lblSum.setText("Your Registration is success……");
                    }else{
                        lblSum.setText("OTP is incorrect ");
                    }                   
	   	}
	   }
	  );
        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("Enter OTP");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
    
}
