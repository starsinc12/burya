package managers;

import GameStates.*;
import logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GameStateManager {

    private boolean paused;
    private PauseState pauseState;

    private GameState[] gameStates;
    private int currentState;
    private int previousState;

    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;

    public static final int NUM_STATES = 4;
    public static final int INVENTORY = 0;
    public static final int TALENTS = 1;
    public static final int WORLD = 2;
    public static final int PLAY = 3;

    public GameStateManager() {
        paused = false;
        pauseState = new PauseState(this);
        gameStates = new GameState[NUM_STATES];
        setState(PLAY);
    }

    private void setState(int i) {
        previousState = currentState;
        unloadState(previousState);
        currentState = i;
        if(i == INVENTORY) {
            gameStates[i] = new InventoryState(this);
            gameStates[i].init();
        }
        else if(i == TALENTS) {
            gameStates[i] = new TalentsState(this);
            gameStates[i].init();
        }
        else if(i == WORLD) {
            gameStates[i] = new WorldState(this);
            gameStates[i].init();
        }
        else if(i == PLAY) {
            gameStates[i] = new PlayState(this);
            gameStates[i].init();
        }
    }

    public void unloadState(int i) {
        gameStates[i] = null;
    }

    public void setPaused(boolean b) {
        paused = b;
    }

    public void update() {
        if(paused) {
            pauseState.update();
        }
        else if(gameStates[currentState] != null) {
            gameStates[currentState].update();
            mouseX = GameLogic.mouseX;
            mouseY = GameLogic.mouseY;
        }
    }

    public void draw(Graphics2D g) {
        if(paused) {
            pauseState.draw(g);
        }
        else if(gameStates[currentState] != null) {
            gameStates[currentState].draw(g);
        }
    }

    public Cursor getCursor() {
        return gameStates[currentState].getCursor();
    }
}
