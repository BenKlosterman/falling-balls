package com.benandjake.game.managers;


import com.benandjake.game.BallFaller;
import com.benandjake.game.screens.AbstractScreen;
import com.benandjake.game.screens.GameScreen;
import com.benandjake.game.screens.MainScreen;

import java.util.HashMap;

public class GameScreenManager {
    public enum STATE {
        PLAY,
        MAIN_MENU,
        GAME_OVER
    }

    public final BallFaller app;

    private HashMap<STATE, AbstractScreen> gameScreens;

    public GameScreenManager(final BallFaller app){
        this.app = app;

        initGameScreens();
        setScreen(STATE.MAIN_MENU);
    }

    private void initGameScreens(){
        this.gameScreens = new HashMap<STATE, AbstractScreen>();
        this.gameScreens.put(STATE.PLAY, new GameScreen(app));
        this.gameScreens.put(STATE.MAIN_MENU, new MainScreen(app));
    }

    public void setScreen(STATE nextScreen){
        app.setScreen(gameScreens.get(nextScreen));
    }

    public void dispose(){
        for(AbstractScreen screen: gameScreens.values()){
            if(screen != null){
                screen.dispose();
            }
        }
    }
}
