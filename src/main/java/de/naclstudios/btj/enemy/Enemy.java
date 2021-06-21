package de.naclstudios.btj.enemy;

import de.naclstudios.btj.B4TJEntity;

public abstract class Enemy extends B4TJEntity {

    B4TJEntity player;

    public Enemy(float xPos, float yPos, float width, float height, String tag, B4TJEntity player) {
        super(xPos, yPos, width, height, tag);

        this.player = player;
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
