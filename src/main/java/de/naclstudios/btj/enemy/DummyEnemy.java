package de.naclstudios.btj.enemy;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.naclstudios.btj.B4TJUtils;
import de.naclstudios.btj.components.OscillateSinglePlatformEnemyMovement;

public class DummyEnemy extends Enemy {

    private boolean foundGround = false;

    public DummyEnemy(final float xPos, final float yPos) {
        super(xPos, yPos, 50, 50, "dummy-enemy");
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        super.onCollision(event);
        if (!foundGround && !B4TJUtils.optionalB4TJEntity(event.getOtherGameObject()).isPresent()) {
            addComponent(new OscillateSinglePlatformEnemyMovement<Enemy>(this, "mm", 1f, event.getOtherGameObject()));
            foundGround = true;
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.PURPLE_COLOR);
        saltyGraphics.drawRect(this);
    }
}
