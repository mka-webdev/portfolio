/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * SceneCoordinator class
 * 
 * Coordinates the scenes switching in this app. 
 * Registers scenes using unique SceneKey enum.
 * 
 * @author Mark Ashkenazi
 * @version Phase6final  
 */
public class SceneCoordinator {
    /**
     * Registers scenes using unique SceneKey enum:
     * LOGIN - login scene, opened on app launch
     * PASSWORD - change password scene
     * QUERY - query scene
     */
    public static enum SceneKey { 

        /**
         *
         */
        QUERY, 

        /**
         *
         */
        LOGIN, 

        /**
         *
         */
        PASSWORD};
    
    //Main stage
    private Stage stage;  
    
    //Mapping of scene keys (SceneKey) to corresponding scenes (Scene)  
    private HashMap<SceneKey,Scene> scenes = new HashMap<>();
    
    /**
     * Construct SceneCoordinator object using specified stage
     * @param s the primary stage
     */
    public SceneCoordinator(Stage s) {
        this.stage = s;
    }
    
    /**
     * Registers scene with the specified SceneKey
     * 
     * @param key unique key associated with the scene
     * @param value matching value 
     */
    public void addScene(SceneKey key, Scene value) {
        scenes.put(key, value);      
    }
    
    /**
     * Displaying the first scene, the Login scene
     */
    public void start() {
        setScene(SceneKey.LOGIN);
    }

    /**
     * Sets the current scene to the scene associated with the SceneKey
     * Sets the title for all scenes.
     * 
     * @param key the SceneKey
     */
    public void setScene(SceneKey key) { 
        Scene s = scenes.get(key);
        stage.setScene(s);
        stage.setTitle("Whiskey Information System");
        stage.show();
    }
}
