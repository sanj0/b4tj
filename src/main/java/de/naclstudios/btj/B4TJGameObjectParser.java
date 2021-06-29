package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJGameObjectParser;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.util.Map;

import static de.edgelord.saltyengine.si.SJFormatKeys.*;

public class B4TJGameObjectParser implements SJGameObjectParser {

    public static final String ID_PLATFORM = "platform";
    public static final String ID_PLAYER = "player";

    public static final Vector2f DEFAULT_PLATFORM_POSITION = Vector2f.zero();
    public static final Dimensions DEFAULT_PLATFORM_SIZE = new Dimensions(100, 15);

    @Override
    public GameObject parseGameObject(Map<String, Object> attributes) {
        final String id = attributes.get(KEY_ID).toString();
        switch (id) {
            case ID_PLATFORM:
                return new Obstacle(SJGameObjectParser.parseTransform(attributes, DEFAULT_PLATFORM_POSITION, DEFAULT_PLATFORM_SIZE),
                        (Color) attributes.get(KEY_COLOR),
                        ID_PLATFORM);
            case ID_PLAYER:
                return new Player((Vector2f) attributes.get(KEY_POSITION));

            default:
                return null;
        }
    }
}
