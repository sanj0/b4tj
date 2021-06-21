package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.naclstudios.btj.enemy.DummyEnemy;

import java.awt.*;

public class Main extends Game {

    public static void main(String[] args) {
        init(GameConfig.config(1280, 960, "Before The Junk", 10));
        start(24, new Scene() {
            @Override
            public void initialize() {
                setGravity(1200);
                Player player = new Player(500, 500);
                addGameObject(player);
                addGameObject(new Obstacle(0, 900, 1400, 10, Color.BLACK, "floor"));
                addGameObject(new Obstacle(1200, 1200, 1000, 10, Color.BLACK, "floor1"));
                addGameObject(new Obstacle(2000, 1000, 1000, 10, Color.BLACK, "floor2"));
                addGameObject(new DummyEnemy(1200, 0, getGameObjects().get(2), player));
                addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
                    final SaltyImage img = new InnerResource().getImageResource("assets/sam.jpeg");

                    @Override
                    public void draw(SaltyGraphics saltyGraphics) {
                        saltyGraphics.drawImage(img, Vector2f.zero(), new Dimensions(2000, 1000));
                    }
                });
            }
        });
    }
}
