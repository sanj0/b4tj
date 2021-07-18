package de.naclstudios.btj.objects;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.components.RandomRadialEmitter;
import de.edgelord.saltyengine.emitter.modifiers.SpeedOperatorParticleModifier;
import de.edgelord.saltyengine.emitter.particles.RectangleParticle;
import de.edgelord.saltyengine.emitter.prc.RandomColorProfileParticleRenderContext;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;

public class DestroyableRock extends GameObject {

    public static final String TAG = "rock";
    public static final float WIDTH = 902f / 10;
    public static final float HEIGHT = 674f / 10;
    public static final String SPRITE_PATH = "assets/rock.png";
    public static final SaltyImage SPRITE =
            ImageUtils.resize(new InnerResource().getImageResource(SPRITE_PATH), WIDTH, HEIGHT, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    private final EmitterComponent explosion =
            new RandomRadialEmitter(this, "expl", RectangleParticle.class, 3f, 25f, 15);

    public DestroyableRock(float xPos, float yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, TAG);

        explosion.setSpawnPoint(getTransform().getCentre());
        explosion.disable();
        explosion.setRenderContext(new RandomColorProfileParticleRenderContext(ColorUtil.SADDLE_BROWN, ColorUtil.TREE_BROWN, ColorUtil.BLACK));
        explosion.addModifier(new SpeedOperatorParticleModifier(0.99f, SpeedOperatorParticleModifier.Operation.MULTIPLY));
        addComponent(explosion);
    }

    public void destroy() {
        explosion.enable();
        Game.executeLater(this::removeFromCurrentScene, 120);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {
        if (event.getOtherGameObject() instanceof Detonation) {
            destroy();
        }
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(SPRITE, this);
    }
}
