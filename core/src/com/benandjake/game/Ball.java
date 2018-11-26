package com.benandjake.game;

import com.badlogic.gdx.math.Circle;

public class Ball {
    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Circle getInfo() {
        return info;
    }

    public void setInfo(Circle info) {
        this.info = info;
    }

    public void setY(int y){
        this.info.y = y;
    }

    public void setX(int x){
        this.info.x = x;
    }

    int velocity;
    Circle info;

    public Ball(int x, int y, int velocity){
        this.info = new Circle(x, y, 50);
        this.velocity = velocity;
    }
}
