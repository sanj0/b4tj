package de.naclstudios.btj;

import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.si.SJSceneParser;

import java.io.File;
import java.io.IOException;

public class LevelLoader {

    public static Scene loadLevel(final File f) throws IOException {
        final SJSceneParser parser = new SJSceneParser(f);
        return parser.parseScene(new B4TJGameObjectParser());
    }
}
