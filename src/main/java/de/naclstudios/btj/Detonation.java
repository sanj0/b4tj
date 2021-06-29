package de.naclstudios.btj;

import de.edgelord.saltyengine.collision.collider.GhostCollider;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.components.RandomRadialEmitter;
import de.edgelord.saltyengine.emitter.particles.RectangleParticle;
import de.edgelord.saltyengine.emitter.prc.RandomColorProfileParticleRenderContext;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;
import java.util.Optional;

public class Detonation extends GameObject {

    public static final String TAG = "detonation";
    public static int duration = 100;
    public static float knockBack = 5000;
    public static float maxImpulse = 500000;

    private final int damage;
    private int ticks = 0;

    private final EmitterComponent emitter = new RandomRadialEmitter(this, "particles", RectangleParticle.class, 2f, 25);

    public Detonation(final Vector2f centre, final float radius, final int damage, final Player origin) {
        super(centre.getX() - radius * .5f, centre.getY() - radius * .5f, radius, radius, TAG);

        this.damage = damage;
    }

    @Override
    public void initialize() {
        setCollider(new GhostCollider());
        addComponent(emitter);
        emitter.setRenderContext(new RandomColorProfileParticleRenderContext(ColorUtil.MAROON_RED_COLOR, ColorUtil.YELLOW, ColorUtil.BLACK));
        emitter.setSpawnPoint(getTransform().getCentre());
        emitter.setLifetime(50);
        emitter.impact();
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        final Optional<B4TJEntity> b4TJEntity = B4TJUtils.optionalB4TJEntity(event.getOtherGameObject());
        if (!b4TJEntity.isPresent()) {
            return;
        }
        final B4TJEntity entity = b4TJEntity.get();
        entity.damage(damage);
        final Vector2f centre = getTransform().getCentre();
        final Vector2f otherCentre = entity.getTransform().getCentre();
        final float d = centre.distance(otherCentre);
        final float r = getWidth();
        final float significantDistance = r - d; /*so that knockback decreases with distance*/
        float x = significantDistance / (otherCentre.getX() - centre.getX()) * knockBack;
        float y = significantDistance / -(otherCentre.getY() - centre.getY()) * knockBack;
        x = Math.min(Math.abs(x), maxImpulse) * Math.signum(x);
        y = Math.min(Math.abs(y), maxImpulse) * Math.signum(y);
        if (x > 0) {
            entity.accelerate(x, Directions.Direction.RIGHT);
        } else {
            entity.accelerate(-x, Directions.Direction.LEFT);
        }
        if (y > 0) {
            entity.accelerate(y, Directions.Direction.UP);
        } else {
            entity.accelerate(-y, Directions.Direction.DOWN);
        }
    }

    @Override
    public void onFixedTick() {
        if (ticks >= duration) {
            removeFromCurrentScene();
        } else {
            ticks++;
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.YELLOW);
        final float scale = (float) ticks / duration;
        final float drawnRadius = getWidth() * scale;
        final float radiusDiff = getWidth() - drawnRadius;
        saltyGraphics.setStroke(new BasicStroke(15 * scale));
        saltyGraphics.outlineOval(getX() + radiusDiff / 2f, getY() + radiusDiff / 2f, drawnRadius, drawnRadius);
    }

    /**
     * Gets {@link #damage}.
     *
     * @return the value of {@link #damage}
     */
    public int getDamage() {
        return damage;
    }
}
