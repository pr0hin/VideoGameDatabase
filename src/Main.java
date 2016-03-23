import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sql.rowset.JdbcRowSet;
import java.io.IOException;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane baseLayout;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VideoGameApp");

        createBaseLayout();
    }

    /**
     * Base Frame.
     */
    public void createBaseLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/base.fxml"));
            baseLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(baseLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // This line needs to be at the end when we have the SQL connection working
        //launch(args);

        String[] login = getOracleLogin();
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Setting up connection...");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1522:ug", login[0], login[1]);
            Statement stm = con.createStatement();
            ResultSet set = stm.executeQuery("SELECT * FROM gameupc");
            while (set.next()) {
                System.out.println(set.getString(2));
        }
            System.out.println("Connected!");
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


