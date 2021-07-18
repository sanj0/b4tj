package de.naclstudios.btj.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.naclstudios.btj.enemy.Enemy;

public class SmartEnemyHomingMovement<T extends Enemy> extends EnemyMovement<T> {

    GameObject platform;

    private boolean inSenseRadius;

    public SmartEnemyHomingMovement(final T parent, final String name, final float speed, final GameObject platform) {
        super(parent, name, speed, 800);
        this.platform = platform;
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {
        GameObject playerPlatform = getParent().getPlayer().getPlatform();
        GameObject enemyPlatform = getParent().getPlatform();
        if (playerPlatform == null || enemyPlatform == null) {
            return;
        }

        float enemyPlatformX = enemyPlatform.getX();
        float enemyPlatformMaxX = enemyPlatform.getTransform().getMaxX();
        float enemyPlatformY = enemyPlatform.getY();
        float playerPlatformX = playerPlatform.getX();
        float playerPlatformMaxX = playerPlatform.getTransform().getMaxX();
        float playerPlatformY = playerPlatform.getY();


        if ((((getParent().getX() - getParent().getPlayer().getX()))
                * ((getParent().getX() - getParent().getPlayer().getX()))
                + ((getParent().getY() - getParent().getPlayer().getY()))
                * ((getParent().getY() - getParent().getPlayer().getY())))
                <= (getSenseRadius() * getSenseRadius())) { // haha malte ich fix das nicht
            inSenseRadius = true;
        } else {
            inSenseRadius = false;
        }

        if (inSenseRadius && getParent().getPlatform() != null) {
            if (enemyPlatform == playerPlatform || enemyPlatformY < playerPlatformY)
                if (getParent().getX() <= getParent().getPlayer().getX()) {
                    if (Math.abs(enemyPlatformMaxX - getParent().getX() - getParent().getWidth()) > 5) {
                        getParent().moveX(getSpeed());
                    } else if (playerPlatformX < getParent().getX() && playerPlatformY > enemyPlatformY) {
                        getParent().moveX(getSpeed());
                    }
                } else {
                    if (Math.abs(enemyPlatformX - getParent().getX()) > 5) {
                        getParent().moveX(-getSpeed());
                    } else if (playerPlatformMaxX > getParent().getX() && playerPlatformY > enemyPlatformY) {
                        getParent().moveX(-getSpeed());
                    }
                }
            else {
                getParent().moveY(-7f);
                if (getParent().getY() < getParent().getPlayer().getY() + 10) {
                    if (getParent().getX() < getParent().getPlayer().getX()) {
                        getParent().moveX(4);
                    } else {
                        getParent().moveX(-4f);
                    }
                    getParent().moveY(5);
                }
                if (playerPlatformX < getParent().getX() && getParent().getX() < playerPlatformMaxX) {
                    getParent().setPlatform(playerPlatform);
                }
            }
        }
    }
}
