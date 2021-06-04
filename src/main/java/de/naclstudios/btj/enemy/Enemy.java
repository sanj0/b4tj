package de.naclstudios.btj.enemy;

import de.naclstudios.btj.B4TJEntity;

public abstract class Enemy extends B4TJEntity {

    public Enemy(float xPos, float yPos, float width, float height, String tag) {
        super(xPos, yPos, width, height, tag);
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(true);
    }
}
