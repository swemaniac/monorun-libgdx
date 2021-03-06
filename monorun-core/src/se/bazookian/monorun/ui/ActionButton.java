package se.bazookian.monorun.ui;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class ActionButton extends TextButton {
	private static final Color OUT_COLOR = new Color(0.34f, 0.77f, 0.86f, 1); 
	private static final Color OVER_COLOR = new Color(0.21f, 0.27f, 0.29f, 1); 

	private boolean isOver;
	private UIAction action;
	
	public ActionButton(String text, Skin skin) {
		super(text, skin);
		
		align(Align.center);
		pad(12, 22, 12, 22);
		getColor().set(OUT_COLOR);
		
		addListener(new InputListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				isOver = true;
				
				if (!isDisabled()) {
					addAction(color(OVER_COLOR, 0.2f));
				}
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				isOver = false;
				
				if (!isDisabled()) {
					addAction(color(OUT_COLOR, 0.2f));
				}
			}
		});
	}
	
	public ActionButton(String text, Skin skin, UIAction clickAction) {
		this(text, skin);
		
		if (clickAction == null) {
			throw new IllegalArgumentException("Click action must not be null");
		}
		
		this.action = clickAction;
		
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return !isDisabled();
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (isOver) {
					action.execute();
				}
			}
		});
	}
}