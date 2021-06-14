package de.naclstudios.btj;

import de.edgelord.saltyengine.components.CollisionOverlapFixer;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.util.List;

/**
 * health and other general b4tj-specific
 * entity stuff
 */
public abstract class B4TJEntity extends GameObject {

    public static final int DEFAULT_MAX_HEALTH = 100;
    public static final int DEFAULT_CURRENT_HEALTH = DEFAULT_MAX_HEALTH;

    private boolean grounded = false;
    private int maxHealth = DEFAULT_MAX_HEALTH;
    private int currentHealth = DEFAULT_CURRENT_HEALTH;

    private CollisionOverlapFixer collisionOverlapFixer = new CollisionOverlapFixer(this, "collision-overlap-fixer");

    public B4TJEntity(final float xPos, final float yPos, final float width, final float height, final String tag) {
        super(xPos, yPos, width, height, tag);
        b4tjInit();
    }

    public B4TJEntity(final Transform transform, final String tag) {
        super(transform, tag);
        b4tjInit();
    }

    public B4TJEntity(final Vector2f position, final Dimensions dimensions, final String tag) {
        super(position, dimensions, tag);
        b4tjInit();
    }

    public B4TJEntity(final Vector2f position, final float width, final float height, final String tag) {
        super(position, width, height, tag);
        b4tjInit();
    }

    public B4TJEntity(final float xPos, final float yPos, final Dimensions dimensions, final String tag) {
        super(xPos, yPos, dimensions, tag);
        b4tjInit();
    }

    private void b4tjInit() {
        addComponent(collisionOverlapFixer);
    }

    @Override
    public void onCollisionDetectionFinish(final List<CollisionEvent> collisions) {
        grounded = false;
        final int nCollisions = collisions.size();
        for (int i = 0; i < nCollisions; i++) {
            if (collisions.get(i).getCollisionDirection() == Directions.Direction.DOWN) {
                grounded = true;
            }
        }
    }

    /**
     * Convenience method for decreasing
     * {@link #currentHealth}.
     * <br>INFO: no range / sign check
     *
     * @param damageAmount the damage that should be subtracted from
     *                     {@link #currentHealth}
     */
    public void damage(final int damageAmount) {
        currentHealth -= damageAmount;
    }

    /**
     * Convenience method for increasing
     * {@link #currentHealth}.
     * <br>INFO: no range / sign check
     *
     * @param healAmount the damage that should be added to
     *                     {@link #currentHealth}
     */
    public void heal(final int healAmount) {
        currentHealth += healAmount;
    }

    /**
     * Gets {@link #maxHealth}.
     *
     * @return the value of {@link #maxHealth}
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets {@link #maxHealth}.
     *
     * @param maxHealth the new value of {@link #maxHealth}
     */
    public void setMaxHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Gets {@link #currentHealth}.
     *
     * @return the value of {@link #currentHealth}
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets {@link #currentHealth}.
     *
     * @param currentHealth the new value of {@link #currentHealth}
     */
    public void setCurrentHealth(final int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Gets {@link #grounded}.
     *
     * @return the value of {@link #grounded}
     */
    public boolean isGrounded() {
        return grounded;
    }
}
