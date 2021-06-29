package de.naclstudios.btj;

import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

/**
 * Detonates and leaves a {@link Detonation}
 */
public class Dynamite extends GameObject {

    public static final float WIDTH = 70;
    public static final float HEIGHT = 20;
    public static final String SPRITE_PATH = "assets/dynamite.png";
    private static final SaltyImage SPRITE =
            ImageUtils.resize(new InnerResource().getImageResource(SPRITE_PATH), WIDTH, HEIGHT, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    public static int TIMER = 250;
    public static int damage = 5;
    public static int radius = 300;
    public static final String TAG = "dynamite";

    private int lifeTime = 0;

    public Dynamite(final float xPos, final float yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, TAG);
    }

    public void detonate() {
        SceneManager.getCurrentScene().addGameObject(new Detonation(getTransform().getCentre(), radius, damage));
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(true);
        getPhysics().addTagToIgnore(Player.TAG);
    }

    @Override
    public void onCollision(final CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {
        if (lifeTime >= TIMER) {
            detonate();
            removeFromCurrentScene();
        } else {
            lifeTime++;
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.blend(ColorUtil.BLACK, ColorUtil.MAROON_RED_COLOR, (float) lifeTime / TIMER));
        saltyGraphics.drawImage(SPRITE, getPosition());
    }
}
