import controllers.AbstractTabController;
import controllers.BaseController;
import controllers.CustomerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane baseLayout;

    public void start(Stage primaryStage) {

        String[] login = getOracleLogin();

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Setting up connection...");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1522:ug", login[0], login[1]);
            System.out.println("Connected!");

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("VideoGameApp");

            createBaseLayout(conn);
        } catch (SQLException sqle) {
            System.out.println(sqle);
            System.exit(-2);
        }
    }

    /**
     * Base Frame.
     */
    public void createBaseLayout(Connection conn) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/base.fxml"));
            baseLayout = loader.load();
            BaseController baseController = loader.getController();

            // Get the controller for each tab and pass the conn object to it
            List<AbstractTabController> controllers = baseController.getTabControllers();
            for (AbstractTabController tab : controllers) {
                tab.setConn(conn);
            }

            // Show the scene containing the root layout.
            Scene scene = new Scene(baseLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
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


