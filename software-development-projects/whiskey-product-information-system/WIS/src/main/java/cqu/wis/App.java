package cqu.wis;

import cqu.wis.data.UserData;
import cqu.wis.data.WhiskeyData;
import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.SceneCoordinator.SceneKey;
import cqu.wis.roles.UserDataManager;
import cqu.wis.roles.UserDataValidator;
import cqu.wis.roles.WhiskeyDataManager;
import cqu.wis.roles.WhiskeyDataValidator;
import cqu.wis.view.LoginController;
import cqu.wis.view.PasswordController;
import cqu.wis.view.QueryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

/**
 * Main JavaFX App class
 *
 * Whiskey Information System (WIS) is a product system that can help customers
 * searching for fine spirits in the whiskey database of CQU liquor distributor.
 * Registered customers can login to the system using the credentials stored in
 * the users database. Due to security concerns, customers are required to
 * change their old passwords. The WIS authentication in turn will encrypt new
 * passwords and store their hashes in the database.
 *
 * The App class: a. Initalises scenes, creates primary stage and connects with
 * SceneCoordinator that handles transition between them. b. Initialises
 * database access and validation objects
 *
 * This is the final phase. All JUnit5 tests and manual GUI tests passed.
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Entry point for the app. Sets primary stage, scenes, data and validation
     * objects
     *
     * @param stage The primary stage
     * @throws IOException when loading scene fails
     */
    @Override
    public void start(Stage stage) throws IOException {
        //Create scene controller object 
        SceneCoordinator sc = new SceneCoordinator(stage);

        /* Configure each scene and add it to the coordinator.
         Transitions between scenes are achieved by the controller for a scene
         requesting the coordinator to make a particular scene the root node
         of the scene graph.*/
        try {
            // create data related objects
            WhiskeyData wd = new WhiskeyData();
            wd.connect();//Connection to whiskey DB

            WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
            WhiskeyDataValidator wdv = new WhiskeyDataValidator();

            UserData ud = new UserData();
            ud.connect();//Connection to users DB

            UserDataManager udm = new UserDataManager(ud);
            UserDataValidator udv = new UserDataValidator();            

            //Create query scene
            Scene qs = makeScene(SceneKey.QUERY);
            QueryController qc = (QueryController) qs.getUserData();
            qc.inject(sc, wdm, wdv);
            sc.addScene(SceneKey.QUERY, qs);

            //Create Login scene
            Scene ls = makeScene(SceneKey.LOGIN);
            LoginController lc = (LoginController) ls.getUserData();
            lc.inject(sc, udm, udv);
            sc.addScene(SceneKey.LOGIN, ls);

            //Create Change Password scene
            Scene cs = makeScene(SceneKey.PASSWORD);
            PasswordController pc = (PasswordController) cs.getUserData();
            pc.inject(sc, udm, udv);
            sc.addScene(SceneKey.PASSWORD, cs);

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database connection error");
            alert.setHeaderText("The application could not connect to the required database.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

            System.exit(1);
        }
        sc.start();
    }

    /**
     * Creates scenes using SceneKey associated with the scene.
     *
     * @param key the enum key specified in SceneCoordinator class
     * @return the constructed scene object
     * @throws Exception if error occurs in scene creation
     */
    private static Scene makeScene(SceneKey key) throws Exception {
        // construct path name for fxml file
        String fxml = "/cqu/wis/view/" + key.name().toLowerCase() + ".fxml";

        // create scene object and add a reference to its controller object
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        scene.setUserData(loader.getController());
        return scene;
    }

    /**
     * Main method that launches the app
     *
     * @param args optional command line arguments, not used
     */
    public static void main(String[] args) {
        launch();
    }

}
