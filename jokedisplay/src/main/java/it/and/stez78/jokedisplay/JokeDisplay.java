package it.and.stez78.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplay extends AppCompatActivity {

    public static final String JOKE_INTENT_EXTRA_KEY = "jokeIntentExtra";

    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        jokeTextView = findViewById(R.id.joke_text);
        if (getIntent().hasExtra(JOKE_INTENT_EXTRA_KEY)){
            jokeTextView.setText(getIntent().getStringExtra(JOKE_INTENT_EXTRA_KEY));
        }

    }
}
