package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJFormatKeys;
import de.edgelord.saltyengine.si.SJGameObjectDeParser;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;

public class B4TJGameObjectDeParser implements SJGameObjectDeParser {
    private int gameObjectNum = 0;
    @Override
    public SJClass deparse(GameObject object) {
        gameObjectNum++;
        final SJClass clazz = new SJClass("object" + gameObjectNum);
        switch (object.getTag()) {
            case B4TJGameObjectParser.ID_PLATFORM:
                final Obstacle platform = (Obstacle) object;
                clazz.addValue(new SJValue(SJFormatKeys.KEY_ID, B4TJGameObjectParser.ID_PLATFORM));
                //clazz.addValue(deparseColor(platform.color, true));
                //clazz.addValue(deparseTransform(platform.getTransform()));
                return clazz;
            case Player.TAG:
                clazz.addValue(new SJValue(SJFormatKeys.KEY_ID, B4TJGameObjectParser.ID_PLAYER));
                //clazz.addValue(deparseTransform(object.getTransform()));
                return clazz;
            case B4TJGameObjectParser.ID_ROCK:
                clazz.addValue(new SJValue(SJFormatKeys.KEY_ID, B4TJGameObjectParser.ID_ROCK));
                //clazz.addValue(deparseTransform(object.getTransform()));
                return clazz;
        }

        return clazz;
    }
}
