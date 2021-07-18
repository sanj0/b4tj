package de.naclstudios.btj;

import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.io.SystemDependentFiles;
import de.edgelord.saltyengine.si.scene.DesignerScene;

import java.io.IOException;

public class LevelDesigner extends Main {

    public static void main(String[] args) {
        Main.main(args);
        try {
            SceneManager.setCurrentScene(new DesignerScene("assets/scenedesginer.config.sj",
                    SystemDependentFiles.getUserFile("scene.sj"),
                    new B4TJGameObjectParser(), new B4TJGameObjectDeParser()));
            SceneManager.getCurrentScene().setGravity(1200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
