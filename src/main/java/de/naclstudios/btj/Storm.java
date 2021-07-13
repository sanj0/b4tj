package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.components.RandomRainEmitter;
import de.edgelord.saltyengine.emitter.modifiers.RandomSpeedParticleModifier;
import de.edgelord.saltyengine.emitter.particles.RectangleParticle;
import de.edgelord.saltyengine.emitter.prc.RandomColorProfileParticleRenderContext;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ColorUtil;

public class Storm extends EmptyGameObject {

    private final EmitterComponent rainEmitter = new RandomRainEmitter(this, "rain-emt", RectangleParticle.class, 4.5f, 50, 7);

    public Storm() {
        super(0, 0, Game.getGameWidth(), 0, "storm");

        rainEmitter.setFixedMinParticleDimensions(new Dimensions(1, 15));
        rainEmitter.setFixedMaxParticleDimensions(new Dimensions(3, 30));
        rainEmitter.setRenderContext(new RandomColorProfileParticleRenderContext(ColorUtil.withAlpha(ColorUtil.BLUE, .6f), ColorUtil.withAlpha(ColorUtil.MIDNIGHT_BLUE, .6f), ColorUtil.withAlpha(ColorUtil.NAVY_BLUE_COLOR, .6f)));
        rainEmitter.addModifier(new RandomSpeedParticleModifier(5, 7, 1, false));
        addComponent(rainEmitter);
        rainEmitter.enable();
    }
}
