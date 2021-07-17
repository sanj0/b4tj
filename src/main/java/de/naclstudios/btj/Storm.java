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
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.GeneralUtil;

public class Storm extends EmptyGameObject {

    private final EmitterComponent rainEmitter = new RandomRainEmitter(this, "rain-emt",
            RectangleParticle.class, 0, 40, 8);

    public Storm() {
        super(0, 0, Game.getGameWidth() * 2f, 0, "storm");

        rainEmitter.setFixedMinParticleDimensions(new Dimensions(1, 35));
        rainEmitter.setFixedMaxParticleDimensions(new Dimensions(3, 50));
        rainEmitter.setRenderContext(new RandomColorProfileParticleRenderContext(ColorUtil.withAlpha(ColorUtil.BLUE, .6f),
                ColorUtil.withAlpha(ColorUtil.MIDNIGHT_BLUE, .6f), ColorUtil.withAlpha(ColorUtil.NAVY_BLUE_COLOR, .6f)));
        rainEmitter.addModifier(new RandomSpeedParticleModifier(10, 20, 1, false));
        rainEmitter.addModifier(particle -> particle.moveX(GeneralUtil.randomInt(-5, 5)));
        rainEmitter.setLifetime(200);
        addComponent(rainEmitter);
        rainEmitter.enable();
    }

    @Override
    public void onFixedTick() {
        setPosition(Game.getCamera().getPosition().subtracted(new Vector2f(Game.getGameWidth() / 2f, 0)));
    }
}
