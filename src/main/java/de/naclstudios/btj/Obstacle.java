package de.naclstudios.btj;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.ColorUtil;

public class Obstacle extends GameObject {

    public Obstacle(float xPos, float yPos, float width, float height, String tag) {
        super(xPos, yPos, width, height, tag);
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
        saltyGraphics.setColor(ColorUtil.FOREST_GREEN);
        saltyGraphics.drawRect(this);
    }
}
