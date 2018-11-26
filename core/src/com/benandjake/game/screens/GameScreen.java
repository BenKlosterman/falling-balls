package com.benandjake.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.benandjake.game.Ball;
import com.benandjake.game.BallFaller;
import com.benandjake.game.Player;


import java.util.ArrayList;
import java.util.Random;

import static com.benandjake.game.Constants.BASE_BALL_VELOCITY;
import static com.benandjake.game.Constants.PLAYER_SIZE;
import static com.benandjake.game.Constants.PLAYER_VELOCITY;

public class GameScreen extends AbstractScreen{


    public OrthographicCamera camera;
    public Player player1;
    public ArrayList<Ball> fallingBalls1;
    public Player player2;
    public ArrayList<Ball> fallingBalls2 = new ArrayList<Ball>();
    public Random rand = new Random();
    public BitmapFont font;

    public GameScreen(final BallFaller app){
        super(app);

        app.player1Lost = false;
        app.player2Lost = false;

        fallingBalls1 = new ArrayList<Ball>();
        fallingBalls2 = new ArrayList<Ball>();


        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        player1 = new Player("white", (PLAYER_SIZE/2), (PLAYER_SIZE/2));
        player2 = new Player("black", (app.w/2)+1+(PLAYER_SIZE/2), (PLAYER_SIZE/2));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, app.w, app.h);


        for(int i=0;i<3;i++){
            int x = randInt(50, (app.w/2)-50, rand);
            int velocity = randInt(BASE_BALL_VELOCITY, BASE_BALL_VELOCITY+15, rand);
            fallingBalls1.add(new Ball(x, app.h, velocity));
            fallingBalls2.add(new Ball(x+(app.w/2), app.h, velocity));
        }

    }

    @Override
    public void show() {

    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta){
        super.render(delta);


        camera.update();
        app.shapeRenderer.setProjectionMatrix(camera.combined);

        //batch.begin();
        //batch.draw(img, 0, 0);
        app.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //CREATE GAME SIDES?
        app.shapeRenderer.setColor(1, 1, 1, 1);
        app.shapeRenderer.rect(app.w/2, 0, app.w, app.h);



        //DRAW OUR BOYS
        //player1
        app.shapeRenderer.setColor(player1.getColors()[0], player1.getColors()[1],player1.getColors()[2], 1);
        app.shapeRenderer.rect(player1.getX()-(PLAYER_SIZE/2), player1.getY()-(PLAYER_SIZE/2), 100, 100);
        //DRAW BALLS
        for(Ball ball: fallingBalls1){
            if(ball.getInfo().y <= 0){
                ball.setY(app.h);
                ball.setX(randInt(50, (app.w/2)-50, rand));
                ball.setVelocity(randInt(BASE_BALL_VELOCITY, BASE_BALL_VELOCITY+15, rand));
            }
            app.shapeRenderer.circle(ball.getInfo().x, ball.getInfo().y, ball.getInfo().radius);
            if(Intersector.overlaps(ball.getInfo(), new Rectangle(player1.getX()-(PLAYER_SIZE/2), player1.getY()-(PLAYER_SIZE/2), 100, 100))){
                app.player1Lost = true;
            }
            if(!app.player1Lost){
                ball.setY((int)ball.getInfo().y - ball.getVelocity());
            }

        }


        //player2
        app.shapeRenderer.setColor(player2.getColors()[0], player2.getColors()[1],player2.getColors()[2], 1);
        app.shapeRenderer.rect(player2.getX()+1-(PLAYER_SIZE/2), player2.getY()-(PLAYER_SIZE/2), 100, 100);
        //DRAW BALLS
        for(Ball ball: fallingBalls2){
            if(ball.getInfo().y <= 0){
                ball.setY(app.h);
                ball.setX(randInt((app.w/2)+50, app.w-50, rand));
                ball.setVelocity(randInt(BASE_BALL_VELOCITY, BASE_BALL_VELOCITY+15, rand));
            }
            if(Intersector.overlaps(ball.getInfo(), new Rectangle(player2.getX()-(PLAYER_SIZE/2), player2.getY()-(PLAYER_SIZE/2), 100, 100))){
                app.player2Lost = true;
            }
            app.shapeRenderer.circle(ball.getInfo().x, ball.getInfo().y, ball.getInfo().radius);
            ball.setY((int)ball.getInfo().y - ball.getVelocity());
        }


        app.shapeRenderer.end();


        //HANDLE TOUCHES
        for (int i = 0; i < 2; i++) { // 20 is max number of touch points
            if (Gdx.input.isTouched(i)) {

                int screenX = Gdx.input.getX(i);
                int screenY = Gdx.input.getY(i);
                screenY = app.h - screenY;

                Vector2 touchVector = new Vector2(screenX, screenY);
                Vector2 playerVector = null;

                //MOVING PLAYER1
                if(screenX < app.w/2){

                    playerVector = new Vector2(player1.getX(), player1.getY());
                    Vector2 movementVector = touchVector.sub(playerVector);
                    if (movementVector.len() > PLAYER_VELOCITY){
                        movementVector = movementVector.nor();
                        player1.setX(player1.getX() + (int)(movementVector.x*PLAYER_VELOCITY));
                        player1.setY(player1.getY() + (int)(movementVector.y*PLAYER_VELOCITY));
                    }


                }else{//PLAYER2
                    playerVector = new Vector2(player2.getX(), player2.getY());
                    Vector2 movementVector = touchVector.sub(playerVector);
                    if (movementVector.len() > PLAYER_VELOCITY){
                        movementVector = movementVector.nor();
                        player2.setX(player2.getX() + (int)(movementVector.x*PLAYER_VELOCITY));
                        player2.setY(player2.getY() + (int)(movementVector.y*PLAYER_VELOCITY));
                    }

                }
            }
        }

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

    public static int randInt(int min, int max, Random rand) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
