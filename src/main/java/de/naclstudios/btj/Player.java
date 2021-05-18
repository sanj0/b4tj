package de.naclstudios.btj;

import de.edgelord.saltyengine.components.CameraFollowComponent;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.utils.Directions;

import java.io.IOException;
import java.util.List;

/**
 * The player!
 */
public class Player extends GameObject {

    public static final String TAG = "player";
    public static final float WIDTH = 72;
    public static final float HEIGHT = 91;


    private final CameraFollowComponent camFollow = new CameraFollowComponent(this, "cam-follow");

    private boolean airborne;
    private boolean hasJumped = false;
    private boolean isJumping = false;
    private float velocity = 2500f;
    private float jumpVelocity = 50000;
    private float lastX;
    private float lastY;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, TAG);

        camFollow.setSpeed(2.5f);

        try {
            addComponent(new PlayerController(this, "playerController"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addComponent(camFollow);
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(true);

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {
        final Directions input = Input.getInput();
        input.removeDirection(Directions.Direction.UP);
        input.removeDirection(Directions.Direction.DOWN);
        accelerateTo(velocity, input);
        if (Input.getKeyboardInput().isSpace() && !airborne) {
            if (!hasJumped) {
                accelerate(jumpVelocity, Directions.Direction.UP);
                hasJumped = true;
                isJumping = true;
            }
        } else if (airborne) {
            if (Input.getKeyboardInput().isSpace() && isJumping && (lastY - getY() > 0.5)) {
                accelerate(1000, Directions.Direction.UP);
            } else if (!Input.getKeyboardInput().isSpace()) {
                isJumping = false;
            }
        } else {
            hasJumped = false;
        }
        lastX = getX();
        lastY = getY();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onCollisionDetectionFinish(List<CollisionEvent> collisions) {
        airborne = collisions.isEmpty();
    }
}
