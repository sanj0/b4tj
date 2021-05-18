package de.naclstudios.btj;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.utils.ColorUtil;

public class Obstacle extends EmptyGameObject {

    public Obstacle(float xPos, float yPos, float width, float height, String tag) {
        super(xPos, yPos, width, height, tag);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.FOREST_GREEN);
        saltyGraphics.drawRect(this);
    }
}
