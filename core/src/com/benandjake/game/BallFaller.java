package com.benandjake.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.benandjake.game.managers.GameScreenManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static com.benandjake.game.Constants.BASE_BALL_VELOCITY;
import static com.benandjake.game.Constants.PLAYER_SIZE;
import static com.benandjake.game.Constants.PLAYER_VELOCITY;

public class BallFaller extends Game {
	public SpriteBatch batch;

	public ShapeRenderer shapeRenderer;

	public GameScreenManager gsm;

	public boolean player1Lost;
	public boolean player2Lost;

	public int w = 0;
	public int h = 0;

	@Override
	public void create () {

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        gsm = new GameScreenManager(this);


	}

	@Override
	public void render () {

        super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
        shapeRenderer.dispose();
	}

    public static int randInt(int min, int max, Random rand) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
