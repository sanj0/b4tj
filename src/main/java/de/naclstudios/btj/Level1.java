package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.light.StaticLightSystem;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.naclstudios.btj.enemy.DummyEnemy;

import java.awt.*;

public class Level1 extends Scene {

    @Override
    public void initialize() {
        Main.au.loop("rain");
        setGravity(1200);
        addGameObject(new Player(30, 900));
        addGameObject(new Obstacle(0, 1037, 2000, 10, Color.BLACK,"floor3"));
        addGameObject(new DummyEnemy(1200, 0, getGameObjects().get(1)));
        addGameObject(new DestroyableRock(1630, 950));
        addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
            final SaltyImage img = ImageUtils.resize(new InnerResource().getImageResource("assets/level0.png"), 3508 / 2f, 2480 / 2f, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            @Override
            public void draw(SaltyGraphics saltyGraphics) {
                saltyGraphics.drawImage(img, Vector2f.zero());
            }
        });
        addGameObject(new Storm());
        setLightSystem(new StaticLightSystem(ColorUtil.withAlpha(ColorUtil.MIDNIGHT_BLUE, 0.35f)));
        Game.getCamera().setPosition(new Vector2f(0, 800));
    }
}
