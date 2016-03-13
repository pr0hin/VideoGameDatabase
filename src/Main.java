import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Main extends Application{
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        String[] login = getOracleLogin();
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug", login[0], login[1]);
            } catch (SQLException sqle) {
                System.out.println(sqle);
                System.exit(-2);
            }



    }

    public static String[] getOracleLogin() {
        String fileName = "./login.txt";
        String[] loginInfo = new String[2];

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            for(int i = 0; i < loginInfo.length; i++){
                String line = br.readLine();
                if(line != null){
                    loginInfo[i] = line;
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        return loginInfo;

    }

}


