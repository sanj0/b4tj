package de.naclstudios.btj.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.naclstudios.btj.enemy.Enemy;

public class SmartEnemyHomingMovement<T extends Enemy> extends EnemyMovement<T> {

    GameObject platform;

    private boolean inSenseRadius;

    public SmartEnemyHomingMovement(final T parent, final String name, final float speed, final GameObject platform) {
        super(parent, name, speed, 8000);

        this.platform = platform;
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {
        if ((((getParent().getX() - getParent().getPlayer().getX()))
                * ((getParent().getX() - getParent().getPlayer().getX()))
                + ((getParent().getY() - getParent().getPlayer().getY()))
                * ((getParent().getY() - getParent().getPlayer().getY())))
                <= (getSenseRadius() * getSenseRadius())) { // haha malte ich fix das nich
            inSenseRadius = true;
        } else {
            inSenseRadius = false;
        }

        if (inSenseRadius && getParent().getPlayer().getPlatform() != null) {
            if (java.lang.Math.abs(getParent().getPlayer().getPlatform().getX() - getParent().getPlayer().getPlatform().getWidth() / 2)
                    < getParent().getPlayer().getPlatform().getWidth() / 2) {
                getParent().moveX(getSpeed());
            } else if (java.lang.Math.abs(getParent().getTransform().getMaxX() - getParent().getPlayer().getPlatform().getWidth() / 2)
                    < getParent().getPlayer().getPlatform().getWidth() / 2) {
                getParent().moveX(-getSpeed());
            }
        }
    }
}
