package de.naclstudios.btj.objects;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;

import java.awt.*;

public class Obstacle extends GameObject {

    private Color color;

    public Obstacle(float xPos, float yPos, float width, float height, Color color, String tag) {
        super(xPos, yPos, width, height, tag);
        this.color = color;
    }

    public Obstacle(Transform transform, Color color, String tag) {
        super(transform, tag);
        this.color = color;
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(false);
    }

    @Override
    public void onCollision(final CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(color);
        saltyGraphics.drawRect(this);
    }

    /**
     * Gets {@link #color}.
     *
     * @return the value of {@link #color}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets {@link #color}.
     *
     * @param color the new value of {@link #color}
     */
    public void setColor(final Color color) {
        this.color = color;
    }
}
