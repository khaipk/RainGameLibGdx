package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.RainFallingInMyHeart;

public class GameOverScreen implements Screen{
	
	final RainFallingInMyHeart game;
	
	OrthographicCamera camera;
	
	int score, highscore;
	
	public GameOverScreen(RainFallingInMyHeart game, int score) {
		this.game = game;
		
		this.score = score;
		
		Preferences pref = Gdx.app.getPreferences("rain");
		this.highscore = pref.getInteger("highscore", 0);
		
		if(score > highscore) {
			pref.putInteger("highscore", score);
			pref.flush();
		}
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		
		game.font.draw(game.batch, "GAME OVER \n Score: "+ score, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		game.font.draw(game.batch, "Highscore: "+highscore, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-40);
		
		game.batch.end();
		
		//go to menu screen
		if(Gdx.input.isKeyPressed(Keys.SPACE))
			game.setScreen(new MainMenuScreen(game));
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
		// TODO Auto-generated method stub
		
	}

}
