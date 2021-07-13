package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.naclstudios.btj.menu.MainMenu;

import java.io.IOException;

public class Main extends Game {

    public static void main(String[] args) throws IOException {
        init(GameConfig.config(1280, 960, "Before The Junk", 10));
        //start(24, new MainMenu());
        start(120, LevelLoader.loadLevel("assets/test-level.sj"));
    }
}
