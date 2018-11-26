package com.benandjake.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.benandjake.game.BallFaller;
import com.benandjake.game.managers.GameScreenManager;

public class MainScreen extends AbstractScreen {


    OrthographicCamera camera;
    BitmapFont font;


    public MainScreen(final BallFaller app){
        super(app);

        font = new BitmapFont();

        camera = new OrthographicCamera();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, app.w, app.h);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta){
        super.render(delta);

        camera.update();
        app.batch.setProjectionMatrix(camera.combined);

        app.batch.begin();
        font.draw(app.batch, "Welcome to Drop!!! ", 100, 150);
        font.draw(app.batch, "Tap anywhere to begin!", 100, 100);
        app.batch.end();

        if (Gdx.input.isTouched()) {
            app.setScreen(new GameScreen(app));
            dispose();
        }


    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
