package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import it.and.stez78.jokedisplay.JokeDisplay;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokesAsyncTaskCallback {

    private InterstitialAd interstitialAd;
    private AdRequest adRequest;
    private String currentJoke;

    private AdView adView;
    private Button tellJokeButton;
    private ProgressBar loadingJokeProgbar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        adView = root.findViewById(R.id.adView);
        tellJokeButton = root.findViewById(R.id.tell_joke_button);
        loadingJokeProgbar = root.findViewById(R.id.tell_joke_progbar);

        adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        tellJokeButton.setEnabled(false);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                tellJokeButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {
                tellJokeButton.setEnabled(false);
                interstitialAd.loadAd(adRequest);
                Intent jokeDisplay = new Intent(getContext(),JokeDisplay.class);
                jokeDisplay.putExtra(JokeDisplay.JOKE_INTENT_EXTRA_KEY,currentJoke);
                startActivity(jokeDisplay);
            }
        });
        return root;
    }

    public void tellJoke() {
        new JokesAsyncTask(this).execute();
    }

    @Override
    public void loadingJoke() {
        tellJokeButton.setEnabled(false);
        loadingJokeProgbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showJoke(String joke) {
        tellJokeButton.setEnabled(true);
        loadingJokeProgbar.setVisibility(View.GONE);
        currentJoke = joke;
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}
