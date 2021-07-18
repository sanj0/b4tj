package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.si.SJGameObjectParser;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.naclstudios.btj.enemy.DummyEnemy;
import de.naclstudios.btj.enemy.Rat;
import de.naclstudios.btj.objects.Barrier;
import de.naclstudios.btj.objects.DestroyableRock;
import de.naclstudios.btj.objects.Obstacle;
import de.naclstudios.btj.objects.Platform;

import java.awt.*;
import java.util.Map;

import static de.edgelord.saltyengine.si.SJFormatKeys.KEY_COLOR;
import static de.edgelord.saltyengine.si.SJFormatKeys.KEY_ID;

public class B4TJGameObjectParser implements SJGameObjectParser {

    public static final String ID_OBSTACLE = "obstacle";
    public static final String ID_ROCK = DestroyableRock.TAG;
    public static final String ID_PLAYER = Player.TAG;

    public static final Vector2f DEFAULT_PLATFORM_POSITION = Vector2f.zero();
    public static final Dimensions DEFAULT_PLATFORM_SIZE = new Dimensions(100, 15);
    public static final Dimensions DEFAULT_ROCK_SIZE = new Dimensions(DestroyableRock.WIDTH, DestroyableRock.HEIGHT);

    @Override
    public GameObject parseGameObject(Map<String, Object> attributes) {
        final GameObject defaultParsed = SJGameObjectParser.defaultParsing(attributes);
        if (defaultParsed != null) return defaultParsed;
        final String id = attributes.get(KEY_ID).toString();
        switch (id) {
            case ID_OBSTACLE:
                return new Obstacle(SJGameObjectParser.parseTransform(attributes, DEFAULT_PLATFORM_POSITION, DEFAULT_PLATFORM_SIZE),
                        (Color) attributes.get(KEY_COLOR),
                        ID_OBSTACLE);
            case ID_PLAYER:
                final Transform pt = SJGameObjectParser.parseTransform(attributes, Vector2f.zero(), new Dimensions(Player.WIDTH, Player.HEIGHT));
                return new Player(pt.getPosition());
            case ID_ROCK:
                final Transform t = SJGameObjectParser.parseTransform(attributes, Vector2f.zero(), DEFAULT_ROCK_SIZE);
                return new DestroyableRock(t.getX(), t.getY());
            case "rain":
                return new Storm();
            case "enemy":
                final Transform tr = SJGameObjectParser.parseTransform(attributes, Vector2f.zero(), Dimensions.zero());
                return new DummyEnemy(tr.getX(), tr.getY());
            case Rat.TAG:
                return new Rat(SJGameObjectParser.parseTransform(attributes, Vector2f.zero(), Dimensions.zero()),
                        Float.parseFloat(attributes.get("speed").toString()), Integer.parseInt(attributes.get("tpf").toString()));
            case Platform.TAG:
                final Transform pformt = SJGameObjectParser.parseTransform(attributes, Vector2f.zero(), Dimensions.zero());
                return new Platform(pformt);
            case Barrier.TAG:
                return new Barrier(SJGameObjectParser.parseTransform(attributes, Vector2f.zero(), Dimensions.zero()));
            default:
                return null;
        }
    }
}
