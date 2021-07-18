package de.naclstudios.btj.objects;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.transform.Transform;

public class Platform extends GameObject {
    public static final String TAG = "platform";
    public static final String SPRITE_PATH = "assets/platform0.png";
    public static final SaltyImage SPRITE = new InnerResource().getImageResource(SPRITE_PATH);

    public Platform(final Transform t) {
        super(t, TAG);
    }

    @Override
    public void initialize() {
        // nohting to init
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        // nope
    }

    @Override
    public void onFixedTick() {
        // nah
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(SPRITE, this);
    }
}
