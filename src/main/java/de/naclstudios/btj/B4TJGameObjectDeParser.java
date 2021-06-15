package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJFormatKeys;
import de.edgelord.saltyengine.si.SJGameObjectDeParser;
import de.edgelord.sanjo.SJClass;

public class B4TJGameObjectDeParser implements SJGameObjectDeParser {
    private int gameObjectNum = 0;
    @Override
    public SJClass deparse(GameObject object) {
        gameObjectNum++;
        final SJClass clazz = new SJClass("object" + gameObjectNum);
        switch (object.getTag()) {
            case B4TJGameObjectParser.ID_PLATFORM:
                final Obstacle platform = (Obstacle) object;
                clazz.addValue(SJFormatKeys.KEY_COLOR, platform.color);
                clazz.addValue(SJFormatKeys.KEY_TRANSFORM, object.getTransform());
                return clazz;
            case Player.TAG:
                clazz.addValue(SJFormatKeys.KEY_TRANSFORM, object.getTransform());
                return clazz;
        }

        return clazz;
    }
}
