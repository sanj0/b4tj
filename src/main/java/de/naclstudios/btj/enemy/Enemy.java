package de.naclstudios.btj.enemy;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;
import de.naclstudios.btj.B4TJEntity;
import de.naclstudios.btj.Player;

public abstract class Enemy extends B4TJEntity {

    public Enemy(float xPos, float yPos, float width, float height, String tag) {
        super(xPos, yPos, width, height, tag);
    }

    public Enemy(final Transform t, final String tag) {
        super(t, tag);
    }

    @Override
    public void onCollision(CollisionEvent event) {
        if (event.getOtherGameObject() instanceof Player) {
            event.getOtherGameObject().accelerate(50000, Directions.Direction.UP);
            removeFromCurrentScene();
        }
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(true);
    }
}
