package de.naclstudios.btj.enemy;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.naclstudios.btj.B4TJEntity;
import de.naclstudios.btj.components.SmartEnemyHomingMovement;

public class DummyEnemy extends Enemy {

    public DummyEnemy(final float xPos, final float yPos, final GameObject platform, B4TJEntity player) {
        super(xPos, yPos, 50, 50, "dummy-enemy", player);

        addComponent(new SmartEnemyHomingMovement<Enemy>(this, "mm", 1f, platform));
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.PURPLE_COLOR);
        saltyGraphics.drawRect(this);
    }
}
