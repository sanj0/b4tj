package de.naclstudios.btj;

import de.edgelord.saltyengine.components.Component;
import de.edgelord.saltyengine.components.animation.AnimationRender;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.sprite.SpritesheetAnimation;
import de.edgelord.saltyengine.graphics.sprite.TextureAtlas;
import de.edgelord.saltyengine.graphics.sprite.TextureAtlasParser;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.io.IOException;
import java.util.Map;

/**
 * misnomer: also manages hitbox changes
 */
public class PlayerController extends Component<GameObject> {

    public static final String UP_FREEZE = "freeze-up";
    public static final String DOWN_FREEZE = "freeze-down";
    public static final String LEFT_FREEZE = "freeze-left";
    public static final String RIGHT_FREEZE = "freeze-right";

    public static final String UP_WALK = "walk-up";
    public static final String DOWN_WALK = "walk-down";
    public static final String LEFT_WALK = "walk-left";
    public static final String RIGHT_WALK = "walk-right";

    private AnimationRender animationRender;

    private SpritesheetAnimation walkUp;
    private SpritesheetAnimation walkDown;
    private SpritesheetAnimation walkRight;
    private SpritesheetAnimation walkLeft;

    private SaltyImage upFreeze;
    private SaltyImage downFreeze;
    private SaltyImage rightFreeze;
    private SaltyImage leftFreeze;

    private SaltyImage currentFreezeImage;

    private boolean freeze = true;

    public PlayerController(final GameObject owner, final String id) throws IOException {
        super(owner, id, "de.naclstudios.btj.player.animations");
        animationRender = new AnimationRender(owner, "de.naclstudios.btj.player.animationRender", null, 13);

        final TextureAtlas atlas = TextureAtlasParser.readAtlas(SaltySystem.defaultResource.getFileResource("assets/mainchar.texmap.sj"));
        walkUp = atlas.getAnimation(UP_WALK);
        walkDown = atlas.getAnimation(DOWN_WALK);
        walkLeft = atlas.getAnimation(LEFT_WALK);
        walkRight = atlas.getAnimation(RIGHT_WALK);

        upFreeze = atlas.getImage(UP_FREEZE);
        downFreeze = atlas.getImage(DOWN_FREEZE);
        leftFreeze = atlas.getImage(LEFT_FREEZE);
        rightFreeze = atlas.getImage(RIGHT_FREEZE);

        currentFreezeImage = downFreeze;
    }

    @Override
    public void draw(SaltyGraphics graphics) {
        if (freeze) {
            graphics.drawImage(currentFreezeImage, getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            animationRender.draw(graphics);
        }
    }

    @Override
    public void onFixedTick() {
        animationRender.onFixedTick();
        freeze = true;
        if (Input.inputUp) {
            freeze = false;
            animationRender.setSpritesheetAnimation(walkUp);
            currentFreezeImage = upFreeze;
            setBigHitbox();
        }

        if (Input.inputDown) {
            freeze = false;
            animationRender.setSpritesheetAnimation(walkDown);
            currentFreezeImage = downFreeze;
            setBigHitbox();
        }

        if (Input.inputLeft) {
            freeze = false;
            animationRender.setSpritesheetAnimation(walkLeft);
            currentFreezeImage = leftFreeze;
            setSlimHitbox();
        }

        if (Input.inputRight) {
            freeze = false;
            animationRender.setSpritesheetAnimation(walkRight);
            currentFreezeImage = rightFreeze;
            setSlimHitbox();
        }
    }

    @Override
    public void onCollision(CollisionEvent e) {
    }

    private void setSlimHitbox() {
        getParent().getHitboxAsSimpleHitbox().setOffsetX(14);
        getParent().getHitboxAsSimpleHitbox().setWidth(45);
    }

    private void setBigHitbox() {
        getParent().getHitboxAsSimpleHitbox().setWidth(71);
        getParent().getHitboxAsSimpleHitbox().setOffsetX(0);
    }
}
