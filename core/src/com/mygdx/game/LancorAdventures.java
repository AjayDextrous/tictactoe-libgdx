package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LancorAdventures extends ApplicationAdapter {
	SpriteBatch batch;
	Texture board, cross, dot, caret;
	boolean currentPlayer = true;
	int caret_position = 5;
	int[] board_state = {-1,
			-1,-1,-1,
			-1,-1,-1,
			-1,-1,-1};
	
	@Override
	public void create () {
		System.out.println("### create");
		batch = new SpriteBatch();
		board = new Texture("tictactoe_board.png");
		cross = new Texture("tictactoe_cross.png");
		dot = new Texture("tictactoe_dot.png");
		caret = new Texture("tictactoe_pointer.png");

		Gdx.input.setInputProcessor(new InputAdapter(){

			@Override
			public boolean keyDown(int keycode) {
				switch (keycode){
					case Input.Keys.DOWN:
						System.out.println("### down");
						if(caret_position > 3){
							caret_position -= 3;
						}
						break;
					case Input.Keys.UP:
						System.out.println("### up");
						if(caret_position <= 6){
							caret_position += 3;
						}
						break;
					case Input.Keys.LEFT:
						System.out.println("### left");
						if((caret_position) % 3 != 1){
							caret_position -= 1;
						}
						break;
					case Input.Keys.RIGHT:
						System.out.println("### right");
						if((caret_position) % 3 != 0){
							caret_position += 1;
						}
						break;

					case Input.Keys.ENTER:
						if(board_state[caret_position] == -1){
							board_state[caret_position] = currentPlayer ?1:0;
							exitIfWin();
							currentPlayer = !currentPlayer;
						}
						break;

				}
				return super.keyDown(keycode);
			}

		});
	}

	private void exitIfWin() {
		int x_loc, y_loc,currentPlayerId;
		x_loc = (caret_position - 1) % 3;
		y_loc = (caret_position - 1) / 3;
		currentPlayerId = currentPlayer?1:0;
		if(board_state[1] == currentPlayerId){
			if(board_state[2] == currentPlayerId){
				if(board_state[3] == currentPlayerId){
					Gdx.app.exit();
				}
			}
			if(board_state[4] == currentPlayerId){
				if(board_state[9] == currentPlayerId){
					Gdx.app.exit();
				}
			}
			if (board_state[5] == currentPlayerId){
				if(board_state[9] == currentPlayerId){
					Gdx.app.exit();
				}
			}
		}
		if(board_state[5] == currentPlayerId){
			if(board_state[2] == currentPlayerId){
				if(board_state[8] == currentPlayerId){
					Gdx.app.exit();
				}
			}
			if(board_state[4] == currentPlayerId){
				if(board_state[6] == currentPlayerId){
					Gdx.app.exit();
				}
			}
			if(board_state[3] == currentPlayerId){
				if(board_state[7] == currentPlayerId){
					Gdx.app.exit();
				}
			}
		}
		if(board_state[9] == currentPlayerId){
			if(board_state[8] == currentPlayerId){
				if(board_state[7] == currentPlayerId){
					Gdx.app.exit();
				}
			}
			if(board_state[6] == currentPlayerId){
				if(board_state[3] == currentPlayerId){
					Gdx.app.exit();
				}
			}
		}

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(board, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (int i = 1; i <10; i++) {
			if(board_state[i] < 0){
				continue;
			}
			plotItemAtPosition((board_state[i] == 0)?dot:cross,i);
		}
		plotItemAtPosition(caret,caret_position);
		batch.end();
	}

	private void plotItemAtPosition(Texture texture, int position){
		int sq_size_w = Gdx.graphics.getWidth() / 3;
		int sq_size_h = Gdx.graphics.getHeight() / 3;

		int x_loc, y_loc;
		x_loc = (position - 1) % 3;
		y_loc = (position - 1) / 3;

//		System.out.println("### x_loc: "+x_loc);
//		System.out.println("### y_loc: "+y_loc);

//		System.out.println("### sq_x: "+sq_size_w);
//		System.out.println("### sq_y: "+sq_size_h);

		x_loc = x_loc * sq_size_w;
		y_loc = y_loc * sq_size_h;

		batch.draw(texture,x_loc,y_loc,sq_size_w,sq_size_h);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);


	}

	@Override
	public void dispose () {
		batch.dispose();
		board.dispose();
	}
}
