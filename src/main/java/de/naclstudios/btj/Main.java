package de.naclstudios.btj;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.naclstudios.btj.menu.MainMenu;

public class Main extends Game {

    public static void main(String[] args) {
        init(GameConfig.config(1280, 960, "Before The Junk", 10));
        start(24, new MainMenu());
    }
}
