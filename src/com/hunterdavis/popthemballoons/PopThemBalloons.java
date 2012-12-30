package com.hunterdavis.popthemballoons;

import java.util.Random;

import com.crittercism.app.Crittercism;
import com.hunterdavis.gameutils.title.TitleScreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PopThemBalloons extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crittercism.init(getApplicationContext(), "50df8f8d89ea7426af000025");
		setContentView(R.layout.game_select_screen);

		ImageButton easyButton = (ImageButton) findViewById(R.id.easyButton);
		ImageButton mediumButton = (ImageButton) findViewById(R.id.easyButton);
		ImageButton hardButton = (ImageButton) findViewById(R.id.easyButton);

		easyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPopBallons(v.getContext(), 3 + (new Random().nextInt(2)));
			}
		});

		mediumButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPopBallons(v.getContext(), 5 + (new Random().nextInt(5)));
			}
		});

		hardButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPopBallons(v.getContext(), 15 + (new Random().nextInt(15)));
			}
		});

		// create a title screen and throw it up
		TitleScreen.startTitleScreen(getApplicationContext(),
				R.raw.popxcolorballoonstitletheme,
				R.drawable.popxcolorbaloonstitle, true/* touchToExit */,
				false /* exitOnWavComplete */, 3000/* timeout */, false /*
																	 * landscape
																	 * mode
																	 */);
	}

	public void startPopBallons(Context context, int numBalloons) {
		PopXColorBalloons.startPopXColorBaloonsScreen(context, numBalloons);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
}
