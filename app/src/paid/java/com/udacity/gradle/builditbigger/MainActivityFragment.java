package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import it.and.stez78.jokedisplay.JokeDisplay;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokesAsyncTaskCallback {

    private Button tellJokeButton;
    private ProgressBar loadingJokeProgbar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        loadingJokeProgbar = root.findViewById(R.id.tell_joke_progbar);
        tellJokeButton = root.findViewById(R.id.tell_joke_button);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
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
        Intent jokeDisplay = new Intent(getContext(),JokeDisplay.class);
        jokeDisplay.putExtra(JokeDisplay.JOKE_INTENT_EXTRA_KEY,joke);
        startActivity(jokeDisplay);
    }
}
