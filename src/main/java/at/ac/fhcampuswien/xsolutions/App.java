package at.ac.fhcampuswien.xsolutions;
import javafx.application.Application;
import javafx.css.Stylesheet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import static at.ac.fhcampuswien.xsolutions.Users.usersList;


public class App extends Application {
    public static Tables[] arrayTables;
    public static Bills[] arrayBills;
    //Converting UsersList Objects to JSON
    public static void userToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            java.nio.file.Path path = Paths.get("src/main/java/usersList.json");
            objectMapper.writeValue(path.toFile(), usersList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Creating JavaFX UI
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        stage.setFullScreen(true); // Set to Full Screen Mode
        stage.show();
    }

    public static Tables[] generateTables(int amount){
        arrayTables = new Tables[amount];
        for(int i = 0;i < amount; i++){
            arrayTables[i]  = new Tables();
        }
        return arrayTables;
    }

    //Testing
    public static void main(String[] args) {
        Users userDmytro = new Users("Dmytro", true, 1);
        Users userTolga = new Users("Tolga", true, 1);
        Users userTosha = new Users("Tosha", true, 1);
        Users userTheresa = new Users("Theresa", true, 1);
        Users userEtrit = new Users("Etrit", true, 1);

        generateTables(35);
        launch();
    }
}