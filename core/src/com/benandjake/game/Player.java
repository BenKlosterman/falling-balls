package com.benandjake.game;

import com.badlogic.gdx.math.Vector3;

import static com.benandjake.game.Constants.PLAYER_SIZE;

public class Player {

    private int offset;
    private int x;
    private int y;
    private int[] colors;
    private int width;
    private int height;


    public Player(String color, int x, int y){
        this.width = PLAYER_SIZE;
        this.height = PLAYER_SIZE;
        this.x = x;
        this.y = y;
        if(color == "white"){
            colors = new int[]{1,1,1};
        }else{
            colors = new int[]{0,0,0};
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }


}
