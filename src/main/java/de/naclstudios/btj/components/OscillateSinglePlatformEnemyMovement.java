package de.naclstudios.btj.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.naclstudios.btj.enemy.Enemy;

public class OscillateSinglePlatformEnemyMovement<T extends Enemy> extends EnemyMovement<T> {

    private GameObject platform;
    private boolean walkingLeft = true;

    public OscillateSinglePlatformEnemyMovement(final T parent, final String name, final float speed, final GameObject platform) {
        super(parent, name, speed, 0);

        this.platform = platform;
    }

    @Override
    public void onFixedTick() {
        if (getParent().isGrounded()) {
            if (walkingLeft) {
                if (getParent().getX() <= platform.getX()) {
                    walkingLeft = false;
                } else {
                    getParent().moveX(-getSpeed());
                }
            } else {
                if (getParent().getTransform().getMaxX() >= platform.getTransform().getMaxX()) {
                    walkingLeft = true;
                } else {
                    getParent().moveX(getSpeed());
                }
            }
        }
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    /**
     * Gets {@link #platform}.
     *
     * @return the value of {@link #platform}
     */
    public GameObject getPlatform() {
        return platform;
    }

    /**
     * Sets {@link #platform}.
     *
     * @param platform the new value of {@link #platform}
     */
    public void setPlatform(final GameObject platform) {
        this.platform = platform;
    }

    /**
     * Gets {@link #walkingLeft}.
     *
     * @return the value of {@link #walkingLeft}
     */
    public boolean isWalkingLeft() {
        return walkingLeft;
    }

    /**
     * Sets {@link #walkingLeft}.
     *
     * @param walkingLeft the new value of {@link #walkingLeft}
     */
    public void setWalkingLeft(final boolean walkingLeft) {
        this.walkingLeft = walkingLeft;
    }
}
