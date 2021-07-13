package de.naclstudios.btj;

import de.edgelord.saltyengine.audio.AudioPlayer;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.resource.InnerResource;

public class SoundManager {
    private static final AudioPlayer au = new AudioPlayer(new AudioFactory(new InnerResource()));

    static {
        au.loadNewAudio("rain", "rain.wav");
    }
}
