package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJFormatKeys;
import de.edgelord.saltyengine.si.SJGameObjectDeParser;
import de.edgelord.sanjo.SJClass;

import static de.edgelord.saltyengine.si.SJGameObjectDeParser.*;

public class B4TJGameObjectDeParser implements SJGameObjectDeParser {
    private int gameObjectNum = 0;
    @Override
    public SJClass deparse(GameObject object) {
        gameObjectNum++;
        final SJClass clazz = new SJClass("object" + gameObjectNum);
        switch (object.getTag()) {
            case B4TJGameObjectParser.ID_PLATFORM:
                final Obstacle platform = (Obstacle) object;
                clazz.addValue(deparseColor(platform.color, true));
                clazz.addValue(deparseTransform(platform.getTransform()));
                return clazz;
            case Player.TAG:
                clazz.addValue(deparseTransform(object.getTransform()));
                return clazz;
            case B4TJGameObjectParser.ID_ROCK:
                clazz.addValue(SJFormatKeys.KEY_POSITION, object.getPosition());
                return clazz;
        }

        return clazz;
    }
}
