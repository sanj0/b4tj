package de.naclstudios.btj;

import de.edgelord.saltyengine.components.CameraFollowComponent;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;

/**
 * The player!
 */
public class Player extends GameObject {

    public static final String TAG = "player";
    public static final float WIDTH = 72;
    public static final float HEIGHT = 91;

    private final CameraFollowComponent camFollow = new CameraFollowComponent(this, "cam-follow");

    private float velocity = 2500f;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, TAG);

        camFollow.setSpeed(2.5f);

        addComponent(new PlayerController(this, "playerController"));
        addComponent(camFollow);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {
        accelerateTo(velocity, Input.getInput());
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }
}
