package de.naclstudios.btj.menu;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.light.StaticLightSystem;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.elements.BorderedLabel;
import de.edgelord.saltyengine.ui.elements.Button;
import de.edgelord.saltyengine.ui.elements.Label;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.Positions;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.naclstudios.btj.DestroyableRock;
import de.naclstudios.btj.Obstacle;
import de.naclstudios.btj.Player;
import de.naclstudios.btj.Storm;
import de.naclstudios.btj.enemy.DummyEnemy;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The game's main menu
 */
public class MainMenu extends Scene {

    public static final Color BACKGROUND_COLOR = new Color(40, 44, 52);

    private Label titleLbl;
    private Button startBtn;
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
                SceneManager.setCurrentScene(new Scene() {
                    @Override
                    public void initialize() {
                        setGravity(1200);
                        addGameObject(new Player(500, 500));
                        addGameObject(new Obstacle(0, 900, 1000, 10, Color.BLACK,"floor"));
                        addGameObject(new Obstacle(1200, 1200, 1000, 10, Color.BLACK,"floor1"));
                        addGameObject(new Obstacle(2000, 1000, 1000, 10, Color.BLACK,"floor2"));
                        addGameObject(new DummyEnemy(1200, 0, getGameObjects().get(2)));
                        addGameObject(new DestroyableRock(1500, 1100));
                        addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
                            final SaltyImage img = new InnerResource().getImageResource("assets/sam.jpeg");

                            @Override
                            public void draw(SaltyGraphics saltyGraphics) {
                                saltyGraphics.drawImage(img, Vector2f.zero(), new Dimensions(2000, 1000));
                            }
                        });
                        addGameObject(new Storm());
                        setLightSystem(new StaticLightSystem(ColorUtil.withAlpha(ColorUtil.NAVY_BLUE_COLOR, 0.35f)));
                    }
                });
            }
        };
        startBtn.setBackgroundColor(ColorUtil.DEEP_PINK);
        startBtn.setFont(btnFont);

        creditsBtn = new Button("credits", btnX, btnY0 + buttonOffsetY, buttonWidth, buttonHeight) {
            @Override
            public void onClick(MouseEvent e) {

            }
        };
        creditsBtn.setBackgroundColor(ColorUtil.CRIMSON_RED);
        creditsBtn.setFont(btnFont);

        quitBtn = new Button("quit", btnX, btnY0 + buttonOffsetY * 2, buttonWidth, buttonHeight) {
            @Override
            public void onClick(MouseEvent e) {
                Game.quit();
            }
        };
        quitBtn.setBackgroundColor(ColorUtil.DODGER_BLUE);
        quitBtn.setFont(btnFont);

        getUI().addElement(titleLbl);
        getUI().addElement(startBtn);
        getUI().addElement(creditsBtn);
        getUI().addElement(quitBtn);
    }
}
