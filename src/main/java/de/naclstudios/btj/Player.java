package de.naclstudios.btj;

import de.edgelord.saltyengine.components.CameraFollowComponent;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.geom.AffineTransform;
import java.io.IOException;

/**
 * The player!
 */
public class Player extends B4TJEntity {

    // --- constants --- \\
    public static final String TAG = "player";
    public static final float WIDTH = 73;
    public static final float HEIGHT = 94;
    /**
     * Player is accelerated by this value each tick that a) space is being held
     * b) they are jumping c) the negative of the current y velocity is greater
     * than {@link #JUMP_BOOST_THRESHOLD} in order to boost the effect of being
     * able to control jump height by holding space
     */
    private static final float JUMP_BOOST = 1000;
    private static final float JUMP_BOOST_THRESHOLD = 0.1f;

    // --- components --- \\
    private final CameraFollowComponent camFollow = new CameraFollowComponent(this, "cam-follow");

    // --- fields --- \\
    private boolean hasJumped = false;
    private boolean isJumping = false;
    private float velocity = 2500f;
    private float jumpVelocity = 60000f;
    private float lastX = getX();
    private float lastY = getY();
    private int maxFuel = 300;
    private int currentFuel = maxFuel;
    private int fuelReload = 1;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, TAG);

        camFollow.setSpeed(3f);

        try {
            addComponent(new PlayerController(this, "playerController"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addComponent(camFollow);
    }

    public Player(Vector2f pos) {
        this(pos.getX(), pos.getY());
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
        final float currXVelocity = getX() - lastX;
        final float currYVelocity = getY() - lastY;

        final Directions input = Input.getInput();
        input.removeDirection(Directions.Direction.UP);
        input.removeDirection(Directions.Direction.DOWN);
        accelerateTo(velocity, input);
        if (Input.getKeyboardInput().isSpace() && isGrounded()) {
            if (!hasJumped) {
                accelerate(jumpVelocity, Directions.Direction.UP);
                hasJumped = true;
                isJumping = true;
            }
        } else if (!isGrounded()) {
            if (Input.getKeyboardInput().isSpace() && isJumping && (-currYVelocity > JUMP_BOOST_THRESHOLD)) {
                accelerate(JUMP_BOOST, Directions.Direction.UP);
            } else if (!Input.getKeyboardInput().isSpace()) {
                isJumping = false;
            } else if (!isJumping && currentFuel > 0) {
                accelerate(1750, Directions.Direction.UP);
                currentFuel--;
            }
        } else {
            hasJumped = false;
            currentFuel = Math.min(currentFuel + fuelReload, maxFuel);
        }
        lastX = getX();
        lastY = getY();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setTransform(new AffineTransform());
        saltyGraphics.setColor(ColorUtil.PURPLE_COLOR);
        saltyGraphics.outlineRect(25, 25, 200, 30);
        saltyGraphics.drawRect(25, 25, 200f * currentFuel / maxFuel, 30);
    }

    // --- getters for get-only fields --- \\

    public boolean isJumping() {
        return isJumping;
    }

    // --- getters and setters --- \\

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getJumpVelocity() {
        return jumpVelocity;
    }

    public void setJumpVelocity(float jumpVelocity) {
        this.jumpVelocity = jumpVelocity;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }

    public int getFuelReload() {
        return fuelReload;
    }

    public void setFuelReload(int fuelReload) {
        this.fuelReload = fuelReload;
    }
}
