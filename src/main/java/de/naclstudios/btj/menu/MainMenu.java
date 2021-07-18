package de.naclstudios.btj.menu;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.io.SystemDependentFiles;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.si.scene.DesignerScene;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.elements.BorderedLabel;
import de.edgelord.saltyengine.ui.elements.Button;
import de.edgelord.saltyengine.ui.elements.Label;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.Positions;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.naclstudios.btj.B4TJGameObjectDeParser;
import de.naclstudios.btj.B4TJGameObjectParser;
import de.naclstudios.btj.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * The game's main menu
 */
public class MainMenu extends Scene {

    public static final Color BACKGROUND_COLOR = new Color(40, 44, 52);

    private Label titleLbl;
    private Button startBtn;
    private Button editorBtn;
    private Button creditsBtn;
    private Button quitBtn;

    private final int buttonWidth = 150;
    private final int buttonHeight = 40;
    private final float btnX = Game.getHost().getHorizontalCentrePosition(buttonWidth);
    private final float btnY0 = Positions.yForDecimal(.35f);
    private final float buttonOffsetY = buttonHeight + 50;
    private final Font btnFont = SaltySystem.defaultFont.deriveFont(15f).deriveFont(Font.BOLD);

    @Override
    public void initialize() {
        Game.getHost().setBackgroundColor(BACKGROUND_COLOR);
        titleLbl = new BorderedLabel("Before the Junk", new Vector2f(0, 100), Game.getGameWidth(), 100);
        titleLbl.setFont(titleLbl.getFont().deriveFont(75f).deriveFont(Font.BOLD));
        titleLbl.setForegroundColor(ColorUtil.GOLD);

        startBtn = new Button("start", btnX, btnY0, buttonWidth, buttonHeight) {
            @Override
            public void onClick(MouseEvent e) {
                Input.getMouseHandlers().clear();
                Input.getKeyboardHandlers().clear();
                final JFileChooser sceneFileChooser = new JFileChooser(SystemDependentFiles.getUserDir());
                sceneFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                sceneFileChooser.setDialogTitle("load a .sj scene file");
                if (sceneFileChooser.showSaveDialog(Game.getHostAsDisplayManager().getDisplay()) == JFileChooser.APPROVE_OPTION) {
                    final File sceneFile = sceneFileChooser.getSelectedFile();
                    try {
                        SceneManager.setCurrentScene(LevelLoader.loadLevel(sceneFile));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        };
        startBtn.setBackgroundColor(ColorUtil.CRIMSON_RED);
        startBtn.setFont(btnFont);

        editorBtn = new Button("level editor", btnX, btnY0 + buttonOffsetY, buttonWidth, buttonHeight) {
            @Override
            public void onClick(MouseEvent e) {
                final JFileChooser sceneFileChooser = new JFileChooser(SystemDependentFiles.getUserDir());
                sceneFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                sceneFileChooser.setDialogTitle("load or create a .sj scene file");
                if (sceneFileChooser.showSaveDialog(Game.getHostAsDisplayManager().getDisplay()) == JFileChooser.APPROVE_OPTION) {
                    try {
                        SceneManager.setCurrentScene(new DesignerScene("assets/scenedesginer.config.sj",
                                sceneFileChooser.getSelectedFile(), new B4TJGameObjectParser(), new B4TJGameObjectDeParser()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        };
        editorBtn.setBackgroundColor(ColorUtil.DEEP_PINK);
        editorBtn.setFont(btnFont);

        creditsBtn = new Button("credits", btnX, btnY0 + buttonOffsetY * 2, buttonWidth, buttonHeight) {
            @Override
            public void onClick(MouseEvent e) {
                try {
                    Desktop.getDesktop().open(new InnerResource().getFileResource("assets/credits.mp4"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        creditsBtn.setBackgroundColor(ColorUtil.ORCHID_PURPLE);
        creditsBtn.setFont(btnFont);

        quitBtn = new Button("quit", btnX, btnY0 + buttonOffsetY * 3, buttonWidth, buttonHeight) {
            @Override
            public void onClick(MouseEvent e) {
                Game.quit();
            }
        };
        quitBtn.setBackgroundColor(ColorUtil.DODGER_BLUE);
        quitBtn.setFont(btnFont);

        getUI().addElement(titleLbl);
        getUI().addElement(startBtn);
        getUI().addElement(editorBtn);
        getUI().addElement(creditsBtn);
        getUI().addElement(quitBtn);
    }
}
