package de.naclstudios.btj;

import de.edgelord.saltyengine.components.Component;
import de.edgelord.saltyengine.components.Components;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

public class FloatingHealthBarComponent extends Component {

    private Dimensions size;
    private Vector2f offset;
    private int maxHealth;
    private int currentHealth;
    private Color color;

    public FloatingHealthBarComponent(ComponentContainer parent, String name, Dimensions size, Vector2f offset, int maxHealth, int currentHealth, Color color) {
        super(parent, name, Components.RENDER_COMPONENT);
        this.size = size;
        this.offset = offset;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.color = color;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        final Vector2f barPos = getParent().getPosition().added(offset);
        saltyGraphics.setColor(color);
        saltyGraphics.outlineRect(barPos, size);
        saltyGraphics.drawRect(barPos.getX(), barPos.getY(),
                size.getWidth() * currentHealth / maxHealth, size.getHeight());
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onCollision(CollisionEvent e) {
        // empty
    }

    public Dimensions getSize() {
        return size;
    }

    public void setSize(Dimensions size) {
        this.size = size;
    }

    public Vector2f getOffset() {
        return offset;
    }

    public void setOffset(Vector2f offset) {
        this.offset = offset;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
