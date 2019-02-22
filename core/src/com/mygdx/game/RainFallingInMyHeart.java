package com.mygdx.game;


import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.screen.MainMenuScreen;

public class RainFallingInMyHeart extends Game {
	public SpriteBatch batch;
	
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		
		this.setScreen(new MainMenuScreen(this));
	}
	
	
	@Override
	public void render () {
		
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
