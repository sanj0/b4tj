package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;

public class Level1Background extends EmptyGameObject {

    private static SaltyImage img = new InnerResource().getImageResource("assets/first level.png");
    public Level1Background() {
        super(0, 0, 0, 0, "background");
        img = ImageUtils.resize(img, img.getWidth() * .75f, img.getHeight() * .75f, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }

    @Override
    public void onFixedTick() {
        final float camSpeed = 3;
        if (Input.isInputRight()) {
            Game.getCamera().moveX(camSpeed);
        } else if (Input.isInputLeft()) {
            Game.getCamera().moveX(-camSpeed);
        }

        if (Input.isInputUp()) {
            Game.getCamera().moveY(-camSpeed);
        } else if (Input.isInputDown()) {
            Game.getCamera().moveY(camSpeed);
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(img, 0, 0);
    }
}
