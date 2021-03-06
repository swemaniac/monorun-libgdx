package se.bazookian.monorun;

import se.bazookian.monorun.screens.EndScreen;
import se.bazookian.monorun.screens.GameplayScreen;
import se.bazookian.monorun.screens.HighScoreScreen;
import se.bazookian.monorun.screens.StartScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Monorun extends Game implements ApplicationListener {
	public static float DISPLAY_DENSITY;
	
	private AssetManager assetManager;
	private ScreenManager screenManager;
	
	public Monorun(float density) {
		DISPLAY_DENSITY = density;
	}
	
	@Override
	public void create() {
		assetManager = new AssetManager();
		screenManager = new ScreenManager(this);
		
		createScreens();
		loadAssets();
		init();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		screenManager.dispose();
		assetManager.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
	
	private void createScreens() {
		screenManager.addScreen(GameState.START, StartScreen.class);
		screenManager.addScreen(GameState.GAMEPLAY, GameplayScreen.class);
		screenManager.addScreen(GameState.END, EndScreen.class);
		screenManager.addScreen(GameState.HIGHSCORE, HighScoreScreen.class);
	}
	
	private void loadAssets() {
		assetManager.load(Resources.UI_SKIN, Skin.class);
		assetManager.finishLoading();
		
		assetManager.load(Resources.SPRITE_ATLAS, TextureAtlas.class);
		assetManager.load(Resources.SPAWN_SOUND, Sound.class);
	}
	
	private void init() {
		screenManager.setScreen(GameState.START);
	}
}