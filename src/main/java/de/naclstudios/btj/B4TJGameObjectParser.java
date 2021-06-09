package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJGameObjectParser;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.util.Map;

import static de.edgelord.saltyengine.si.SJFormatKeys.*;

public class B4TJGameObjectParser implements SJGameObjectParser {

    public static final String ID_PLATFORM = "platform";
    public static final String ID_PLAYER = "player";

    @Override
    public GameObject parseGameObject(Map<String, Object> attributes) {
        final String id = attributes.get(KEY_ID).toString();
        switch (id) {
            case ID_PLATFORM:
                return new Obstacle((Transform) attributes.get(KEY_TRANSFORM),
                        (Color) attributes.get(KEY_COLOR),
                        ID_PLATFORM);

            case ID_PLAYER:
                return new Player((Vector2f) attributes.get(KEY_POSITION));

            default:
                return null;
        }
    }
}
