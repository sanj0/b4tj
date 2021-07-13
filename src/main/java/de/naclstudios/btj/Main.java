package de.naclstudios.btj;

import de.edgelord.saltyengine.audio.AudioPlayer;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.naclstudios.btj.menu.MainMenu;

public class Main extends Game {

    public static final AudioPlayer au = new AudioPlayer(new AudioFactory(new InnerResource()));

    public static void main(String[] args) {
        loadAudio();
        init(GameConfig.config(1280, 960, "Before The Junk", 10));
        start(24, new MainMenu());
        //start(120, LevelLoader.loadLevel("assets/test-level.sj"));
    }

    private static void loadAudio() {
        au.loadNewAudio("rain", "assets/rain.wav");
    }
}
