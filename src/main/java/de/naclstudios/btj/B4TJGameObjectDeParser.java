package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJFormatKeys;
import de.edgelord.saltyengine.si.SJGameObjectDeParser;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;
import de.naclstudios.btj.enemy.Rat;
import de.naclstudios.btj.objects.Barrier;
import de.naclstudios.btj.objects.Obstacle;
import de.naclstudios.btj.objects.Platform;

public class B4TJGameObjectDeParser implements SJGameObjectDeParser {
    private int gameObjectNum = 0;
    @Override
    public SJClass deparse(GameObject object) {
        gameObjectNum++;
        final SJClass clazz = new SJClass("object" + gameObjectNum);
        final SJClass defaultParsedClazz = SJGameObjectDeParser.defaultDeparsing(object, clazz.getName());
        if (defaultParsedClazz != null) return defaultParsedClazz;
        clazz.addValue(SJFormatKeys.KEY_ID, object.getTag());
        switch (object.getTag()) {
            case B4TJGameObjectParser.ID_OBSTACLE:
                final Obstacle platform = (Obstacle) object;
                clazz.addValue(SJGameObjectDeParser.deparseColor(platform.getColor(), true));
                clazz.addValue(SJGameObjectDeParser.deparseTransform(platform.getTransform()));
                return clazz;
            case Player.TAG:
            case B4TJGameObjectParser.ID_ROCK:
            case Barrier.TAG:
                clazz.addValue(SJGameObjectDeParser.deparseTransform(object.getTransform()));
                return clazz;
            case "rain":
                clazz.addValue(new SJValue(SJFormatKeys.KEY_ID, "rain"));
                return clazz;
            case "enemy":
                clazz.addValue(new SJValue(SJFormatKeys.KEY_ID, "enemy"));
                clazz.addValue(SJGameObjectDeParser.deparseTransform(object.getTransform()));
                return clazz;
            case Rat.TAG:
                clazz.addValue("speed", ((Rat) object).getSpeed());
                clazz.addValue("tpf", ((Rat) object).getTpf());
                clazz.addValue(SJGameObjectDeParser.deparseTransform(object.getTransform()));
                return clazz;
            case Platform.TAG:
                clazz.addValue(SJFormatKeys.KEY_ID, Platform.TAG);
                clazz.addValue(SJGameObjectDeParser.deparseTransform(object.getTransform()));
                return clazz;
        }

        return clazz;
    }
}
