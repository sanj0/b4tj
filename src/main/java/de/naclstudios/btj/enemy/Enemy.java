package de.naclstudios.btj.enemy;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.utils.Directions;
import de.naclstudios.btj.B4TJEntity;
import de.naclstudios.btj.Player;

public abstract class Enemy extends B4TJEntity {

    B4TJEntity player;

    public Enemy(float xPos, float yPos, float width, float height, String tag, B4TJEntity player) {
        super(xPos, yPos, width, height, tag);

        this.player = player;
    }

    @Override
    public final void onCollision(CollisionEvent event) {
        if (event.getOtherGameObject() instanceof Player) {
            event.getOtherGameObject().accelerate(50000, Directions.Direction.UP);
            removeFromCurrentScene();
        }
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(true);
    }

    public B4TJEntity getPlayer() {
        return player;
    }

    public void setPlayer(B4TJEntity player) {
        this.player = player;
    }
}
