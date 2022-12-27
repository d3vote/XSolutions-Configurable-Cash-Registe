package at.ac.fhcampuswien.xsolutions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

import static at.ac.fhcampuswien.xsolutions.Product.JSONtoProductList;
import static at.ac.fhcampuswien.xsolutions.Product.productsList;
import static at.ac.fhcampuswien.xsolutions.User.JSONtoUsersList;

public class App extends Application {
    public static Tables[] arrayTables;
    private static final String CONFIG_FILE = "src/main/resources/config.txt";
    private static int tableCount;

    //Creating JavaFX UI
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Configurable Cash Register");
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true); // Set to Full Screen Mode
        stage.show();
    }

    public static void generateTables(int amount){
        arrayTables = new Tables[amount];
        for(int i = 0;i < amount; i++){
            arrayTables[i]  = new Tables();
        }
    }

    private static void readConfig() { //Read Config File with Table Counter
        try (Scanner scanner = new Scanner(new File(CONFIG_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("tableCount = ")) {
                    tableCount = Integer.parseInt(line.substring("tableCount = ".length()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTableCount(int newTableCount) { //Update Config File of Table Counter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write("tableCount = " + newTableCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        JSONtoUsersList(); //Convert the JSON File back to Objects in a List
        JSONtoProductList();
        readConfig();
        generateTables(tableCount);
        productsList.sort(new Comparator<Product>() { //Sort Products List
            @Override
            public int compare(Product p1, Product p2) {
                return p2.getProductTitle().compareTo(p1.getProductTitle());
            }
        });
        launch();
    }
}