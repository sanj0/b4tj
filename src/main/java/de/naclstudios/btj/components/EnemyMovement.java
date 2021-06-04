package de.naclstudios.btj.components;

import de.edgelord.saltyengine.components.Component;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.naclstudios.btj.B4TJEntity;

public abstract class EnemyMovement< T extends B4TJEntity> extends Component< T > {

    public static final String TAG = "de.naclstudios.enemymovement";

    private float speed;
    private float senseRadius;

    public EnemyMovement(final T parent, final String name, final float speed, final float senseRadius) {
        super(parent, name, TAG);

        this.speed = speed;
        this.senseRadius = senseRadius;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        // nothing to render!
    }

    /**
     * Gets {@link #speed}.
     *
     * @return the value of {@link #speed}
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets {@link #speed}.
     *
     * @param speed the new value of {@link #speed}
     */
    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    /**
     * Gets {@link #senseRadius}.
     *
     * @return the value of {@link #senseRadius}
     */
    public float getSenseRadius() {
        return senseRadius;
    }

    /**
     * Sets {@link #senseRadius}.
     *
     * @param senseRadius the new value of {@link #senseRadius}
     */
    public void setSenseRadius(final float senseRadius) {
        this.senseRadius = senseRadius;
    }
}
