package com.mygdx.game.screen;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.RainFallingInMyHeart;

public class GameScreen implements Screen {

	final RainFallingInMyHeart game;
	
	private Texture bucket;
	private Texture water;
	private Sound sound;
	private Music rain;
	
	private Rectangle bucketRect;
	
	private OrthographicCamera camera;
	
	private ArrayList<Rectangle> rains;
	
	private float rainingTime;
	private int score;
	
	private float speed = 200;
	private int speedScore;


	public GameScreen( RainFallingInMyHeart game) {
		this.game = game;
		
		bucket = new Texture("2.png");
		water = new Texture("1.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("sound.wav"));
		rain = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rain.setLooping(true);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		bucketRect = new Rectangle();
		bucketRect.x = Gdx.graphics.getWidth()/2 - bucket.getWidth()/2;
		bucketRect.y = 20;
		bucketRect.width = bucket.getWidth();
		bucketRect.height = bucket.getHeight();
		
		rains = new ArrayList<Rectangle>();
		spawnRainDrop();
	}
	
	public void spawnRainDrop() {
		Rectangle rain = new Rectangle();
		rain.x = MathUtils.random(0, Gdx.graphics.getWidth()- water.getWidth());
		rain.y = Gdx.graphics.getHeight() - water.getHeight();
		rain.width = water.getWidth();
		rain.height = water.getHeight();
		rains.add(rain);
		rainingTime = TimeUtils.nanoTime();
	}

	@Override
	public void show() {
		rain.play();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		
		game.batch.draw(bucket, bucketRect.x, bucketRect.y);
		
		for(Rectangle rain: rains) {
		game.batch.draw(water, rain.x, rain.y);
		}
		
		game.font.draw(game.batch, "Score : "+score, 0, Gdx.graphics.getHeight());
		
		game.batch.end();
		
		// move bucket 
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			bucketRect.x -= 500 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			bucketRect.x += 500 * Gdx.graphics.getDeltaTime();
		}
		
		// add rain
		if(TimeUtils.nanoTime() - rainingTime > 1000000000) {
			spawnRainDrop();
		}
		// move rain and remove 1
		/*ArrayList<Rectangle> removeRain = new ArrayList<Rectangle>();
		for(Rectangle rain: rains) {
			rain.y -= 200* Gdx.graphics.getDeltaTime();
			if(rain.y < 0) {
				removeRain.add(rain);
			}
			if(rain.overlaps(bucketRect)) {
				removeRain.add(rain);
				sound.play();
				score ++;
			}
		}
		rains.removeAll(removeRain);*/
		
		// move rain and remove 2
		Iterator<Rectangle> iter = rains.iterator();
		while(iter.hasNext()) {
			Rectangle rain = iter.next();
			rain.y -= speed * Gdx.graphics.getDeltaTime();
			if(rain.y +rain.height <0) {
				iter.remove();
			}
			if(rain.overlaps(bucketRect)) {
				iter.remove();
				sound.play();
				score++;
			}
		}
		
		//update speed
		if(score - speedScore >9) {
			speed *= 1.2;
			speedScore = score;
		}
		
		// game over screen
		if(Gdx.input.isKeyPressed(Keys.Q)) {
			dispose();
			game.setScreen(new GameOverScreen(game, score));
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		bucket.dispose();
		water.dispose();
		sound.dispose();
		rain.dispose();

	}



}
