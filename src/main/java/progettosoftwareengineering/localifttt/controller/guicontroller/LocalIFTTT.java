package progettosoftwareengineering.localifttt.controller.guicontroller;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import progettosoftwareengineering.localifttt.controller.actioncontroller.ChainActionControllersCreator;
import progettosoftwareengineering.localifttt.model.ModelFacade;

public class LocalIFTTT extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\HomeView.fxml"));
        stage.setScene(scene);
        stage.getIcons().add(new Image("/progettosoftwareengineering/localifttt/logo.png"));
        stage.setTitle("Local IFTTT");
        stage.show();
        ModelFacade.firstOpening(ChainActionControllersCreator.chain());
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