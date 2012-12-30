package com.hunterdavis.popthemballoons;

import java.util.Random;

import com.crittercism.app.Crittercism;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
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
		ImageButton mediumButton = (ImageButton) findViewById(R.id.mediumButton);
		ImageButton hardButton = (ImageButton) findViewById(R.id.hardButton);

		easyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPopBallons(v.getContext(), 1 + (new Random().nextInt(4)));
			}
		});

		mediumButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPopBallons(v.getContext(), 5 + (new Random().nextInt(3)));
			}
		});

		hardButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPopBallons(v.getContext(), 8 + (new Random().nextInt(8)));
			}
		});
		
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());


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
