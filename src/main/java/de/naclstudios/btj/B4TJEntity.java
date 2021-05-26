package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * health and other general b4tj-specific
 * entity stuff
 */
public abstract class B4TJEntity extends GameObject {

    public static final int DEFAULT_MAX_HEALTH = 100;
    public static final int DEFAULT_CURRENT_HEALTH = DEFAULT_MAX_HEALTH;

    private int maxHealth = DEFAULT_MAX_HEALTH;
    private int currentHealth = DEFAULT_CURRENT_HEALTH;

    public B4TJEntity(final float xPos, final float yPos, final float width, final float height, final String tag) {
        super(xPos, yPos, width, height, tag);
    }

    public B4TJEntity(final Transform transform, final String tag) {
        super(transform, tag);
    }

    public B4TJEntity(final Vector2f position, final Dimensions dimensions, final String tag) {
        super(position, dimensions, tag);
    }

    public B4TJEntity(final Vector2f position, final float width, final float height, final String tag) {
        super(position, width, height, tag);
    }

    public B4TJEntity(final float xPos, final float yPos, final Dimensions dimensions, final String tag) {
        super(xPos, yPos, dimensions, tag);
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
}
