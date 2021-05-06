package de.naclstudios.btj;

import de.edgelord.saltyengine.components.Component;
import de.edgelord.saltyengine.components.animation.AnimationRender;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.sprite.Spritesheet;
import de.edgelord.saltyengine.graphics.sprite.SpritesheetAnimation;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.utils.Directions;

/**
 * misnomer: also manages hitbox changes
 * fixme: implement texture atlas in salty engine and use here
 */
public class PlayerController extends Component<GameObject> {
    private ImageFactory imageFactory = new ImageFactory(new InnerResource());

    private AnimationRender animationRender;

    private Spritesheet spritesheetAnimations = new Spritesheet(imageFactory.getPreferredImageResource("assets/mainchar.png"), 73, 94);

    private SpritesheetAnimation walkUp;
    private SpritesheetAnimation walkDown;
    private SpritesheetAnimation walkRight;
    private SpritesheetAnimation walkLeft;

    private SaltyImage upFreeze = spritesheetAnimations.getFrame(0, 0).getImage();
    private SaltyImage downFreeze = spritesheetAnimations.getFrame(1, 0).getImage();
    private SaltyImage rightFreeze = spritesheetAnimations.getFrame(2, 0).getImage();
    private SaltyImage leftFreeze = spritesheetAnimations.getFrame(4, 0).getImage();

    private SaltyImage currentFreezeImage;

    private Coordinates[] walkUpSpriteCoordinates = {new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(0, 0), new Coordinates(0, 2)};
    private Coordinates[] walkDownSpriteCoordinates = {new Coordinates(1, 0), new Coordinates(1, 1), new Coordinates(1, 0), new Coordinates(1, 2)};
    private Coordinates[] walkRightSpriteCoordinates = {new Coordinates(2, 0), new Coordinates(2, 1), new Coordinates(2, 0), new Coordinates(2, 2)};
    private Coordinates[] walkLeftSpriteCoordinates = {new Coordinates(5, 0), new Coordinates(5, 1), new Coordinates(5, 0), new Coordinates(5, 2)};

    private Directions.Direction currentDirection;
    private boolean freeze = true;

    public PlayerController(final GameObject owner, final String id) {
        super(owner, id, "de.naclstudios.btj.player.animations");
        animationRender = new AnimationRender(owner, "de.naclstudios.btj.player.animationRender", null, 13);
        walkUp = spritesheetAnimations.getAnimation(walkUpSpriteCoordinates);
        walkDown = spritesheetAnimations.getAnimation(walkDownSpriteCoordinates);
        walkLeft = spritesheetAnimations.getAnimation(walkLeftSpriteCoordinates);
        walkRight = spritesheetAnimations.getAnimation(walkRightSpriteCoordinates);

        currentDirection = Directions.Direction.DOWN;
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

            currentDirection = Directions.Direction.UP;
            freeze = false;
        }

        if (Input.inputDown) {

            currentDirection = Directions.Direction.DOWN;
            freeze = false;
        }

        if (Input.inputLeft) {

            currentDirection = Directions.Direction.LEFT;
            freeze = false;
        }

        if (Input.inputRight) {

            currentDirection = Directions.Direction.RIGHT;
            freeze = false;
        }


        if (currentDirection != null) {
            switch (currentDirection) {
                case RIGHT:
                    animationRender.setSpritesheetAnimation(walkRight);
                    currentFreezeImage = rightFreeze;
                    setSlimHitbox();
                    break;
                case LEFT:
                    animationRender.setSpritesheetAnimation(walkLeft);
                    currentFreezeImage = leftFreeze;
                    setSlimHitbox();
                    break;
                case UP:
                    animationRender.setSpritesheetAnimation(walkUp);
                    currentFreezeImage = upFreeze;
                    setBigHitbox();
                    break;
                case DOWN:
                    animationRender.setSpritesheetAnimation(walkDown);
                    currentFreezeImage = downFreeze;
                    setBigHitbox();
                    break;
            }
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
