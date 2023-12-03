package progettosoftwareengineering.localifttt.controller.guicontroller;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

public class LocalIFTTT extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\HomeView.fxml"));
        stage.setScene(scene);
        stage.getIcons().add(new Image("/progettosoftwareengineering/localifttt/logo.png"));
        stage.setTitle("Local IFTTT");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File(fxml).toURI().toURL());
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}