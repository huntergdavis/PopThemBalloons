package com.hunterdavis.popthemballoons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hunterdavis.easyaudiomanager.EasyAudioManager;
import com.hunterdavis.gameutils.title.TitleScreen;

public class PopXColorBalloons extends Activity {

	/** The Constant numberToMatch. */
	public static final String numberToMatch = "numberToMatch";

	/** The audio manager. */
	EasyAudioManager audioManager;

	/** The pop many baloon panel. */
	balloonPanel popManyBaloonPanel;

	/** The times resumed. */
	private int timesResumed = 0;

	/** The num baloons to match. */
	private int numBaloonsToMatch = 3;

	/**
	 * Start pop x color baloons screen.
	 * 
	 * @param context
	 *            the context
	 * @param numberBaloonsToMatch
	 *            the number baloons to match
	 */
	public static final void startPopXColorBaloonsScreen(Context context,
			int numberBaloonsToMatch) {
		// create the new title screen intent
		Intent baloonsIntent = new Intent(context, PopXColorBalloons.class);
		baloonsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		baloonsIntent.putExtra(numberToMatch, numberBaloonsToMatch);

		// start title screen.
		context.startActivity(baloonsIntent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent baloonIntent = getIntent();
		Bundle extras = baloonIntent.getExtras();
		int baloonsToMatch = extras.getInt(numberToMatch, -1);
		if (baloonsToMatch > 0) {
			numBaloonsToMatch = baloonsToMatch;
		}

		// Set window fullscreen and remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_pop_them_balloons);
		// at this point the layout should be inflated, so
		popManyBaloonPanel = (balloonPanel) findViewById(R.id.SurfaceView01);
		popManyBaloonPanel.setNumBaloonsToWin(numBaloonsToMatch);

		// create the audioManager
		int[] soundBites = new int[1];
		soundBites[0] = R.raw.balloonpop;
		audioManager = new EasyAudioManager(this, soundBites);
		audioManager.setSongAndOnComplete(this,
				R.raw.popxcolorballoonsgametheme, new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.seekTo(0);
						mp.start();
					}

				});
		audioManager.playSong();

		popManyBaloonPanel.setAudioManager(audioManager);
		if (popManyBaloonPanel.surfaceCreated == true) {
			popManyBaloonPanel.createThread(popManyBaloonPanel.getHolder());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		if (popManyBaloonPanel != null) {
			popManyBaloonPanel.terminateThread();
		}
		if ((audioManager != null) && (audioManager.songPlaying)) {
			audioManager.pauseSong();
		}
		System.gc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		timesResumed++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (audioManager != null) {
			if (audioManager.songPlaying) {
				audioManager.pauseSong();
			}
			audioManager = null;
		}
	}

}
