package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.emitter.ParticleModifier;
import de.edgelord.saltyengine.emitter.components.RandomRainEmitter;
import de.edgelord.saltyengine.emitter.modifiers.RandomSpeedParticleModifier;
import de.edgelord.saltyengine.emitter.particles.RectangleParticle;
import de.edgelord.saltyengine.emitter.prc.RandomColorProfileParticleRenderContext;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.GeneralUtil;

public class Storm extends EmptyGameObject {

    private final EmitterComponent rainEmitter = new RandomRainEmitter(this, "rain-emt",
            RectangleParticle.class, 4.5f, 35, 10);

    public Storm() {
        super(0, 0, Game.getGameWidth(), 0, "storm");
        Main.au.loop("rain");

        rainEmitter.setFixedMinParticleDimensions(new Dimensions(1, 15));
        rainEmitter.setFixedMaxParticleDimensions(new Dimensions(3, 30));
        rainEmitter.setRenderContext(new RandomColorProfileParticleRenderContext(ColorUtil.withAlpha(ColorUtil.BLUE, .6f),
                ColorUtil.withAlpha(ColorUtil.MIDNIGHT_BLUE, .6f), ColorUtil.withAlpha(ColorUtil.NAVY_BLUE_COLOR, .6f)));
        rainEmitter.addModifier(new RandomSpeedParticleModifier(4, 10, 1, false));
        rainEmitter.addModifier(new ParticleModifier() {
            private float currentWind = 2;
            private int ticks = 0;
            private int windChangeDelay = 20;

            @Override
            public void modifyParticle(final Particle particle) {
                particle.moveX(GeneralUtil.randomInt(-3, 3));
            }
        });
        rainEmitter.setLifetime(200);
        addComponent(rainEmitter);
        rainEmitter.enable();
    }
}
