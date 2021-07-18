package de.naclstudios.btj.enemy;

import de.edgelord.saltyengine.components.animation.AnimationRender;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.sprite.SpritesheetAnimation;
import de.edgelord.saltyengine.graphics.sprite.TextureAtlas;
import de.edgelord.saltyengine.graphics.sprite.TextureAtlasParser;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;
import de.naclstudios.btj.B4TJEntity;

import java.io.IOException;

public class Rat extends Enemy {
    public static final String TAG = "rat";
    public static final String TEXMAP_PATH = "assets/rat.texmap.sj";
    public static TextureAtlas TEX_ATLAS;
    private static final SpritesheetAnimation WALK_LEFT_ANIM = TEX_ATLAS.getAnimation("walk-left");
    private static final SpritesheetAnimation WALK_RIGHT_ANIM = TEX_ATLAS.getAnimation("walk-right");

    static {
        try {
            TEX_ATLAS = TextureAtlasParser.readAtlas(new InnerResource().getFileResource(TEXMAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final AnimationRender animationRender;
    private final int tpf;
    private GameObject ground = null;
    private boolean walkingLeft = true;
    private float speed;

    public Rat(final Transform t, final float speed, final int tpf) {
        super(t, TAG);
        this.speed = speed;
        this.tpf = tpf;

        animationRender = new AnimationRender(this, "anim-ren", WALK_LEFT_ANIM, tpf);

        addComponent(animationRender);
        animationRender.enable();
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        super.onCollision(event);
        if (ground == null && !(event.getOtherGameObject() instanceof B4TJEntity)) {
            if (event.getCollisionDirection() == Directions.Direction.DOWN) {
                ground = event.getOtherGameObject();
            }
        }
        if (event.getCollisionDirection() == Directions.Direction.RIGHT && !walkingLeft) {
            walkingLeft = true;
            animationRender.setSpritesheetAnimation(WALK_LEFT_ANIM);
        } else if (event.getCollisionDirection() == Directions.Direction.LEFT && walkingLeft) {
            walkingLeft = false;
            animationRender.setSpritesheetAnimation(WALK_RIGHT_ANIM);
        }
    }

    @Override
    public void onFixedTick() {
        if (ground != null) {
            if (walkingLeft) {
                if (getX() <= ground.getX()) {
                    walkingLeft = false;
                    animationRender.setSpritesheetAnimation(WALK_RIGHT_ANIM);
                } else {
                    moveX(-speed);
                }
            } else {
                if (getTransform().getMaxX() >= ground.getTransform().getMaxX()) {
                    walkingLeft = true;
                    animationRender.setSpritesheetAnimation(WALK_LEFT_ANIM);
                } else {
                    moveX(speed);
                }
            }
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

    }

    /**
     * Gets {@link #speed}.
     *
     * @return the value of {@link #speed}
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Gets {@link #tpf}.
     *
     * @return the value of {@link #tpf}
     */
    public int getTpf() {
        return tpf;
    }
}
