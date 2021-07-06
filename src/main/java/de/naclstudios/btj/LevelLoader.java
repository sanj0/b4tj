package de.naclstudios.btj;

import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.si.SJSceneParser;

import java.io.IOException;

public class LevelLoader {

    private static final InnerResource RES = new InnerResource();

    public static Scene loadLevel(final String path) throws IOException {
        final SJSceneParser parser = new SJSceneParser(RES.getFileResource(path));
        return parser.parseScene(new B4TJGameObjectParser());
    }
}
