package it.and.stez78.jokeprovider;

import java.util.Random;

public class JokeProvider {

    private String[] jokes = new String[] {
            "If we shouldn't eat at night, why do they put a light in the fridge?",
            "I have clean conscience. I haven't used it once until now.",
            "Don't you hate it when someone answers their own questions? I do.",
            "A lot of people cry when they cut onions. The trick is not to form an emotional bond.",
            "How does a snowman get to work? By icicle"
    };

    public String getJoke(){
        Random ran = new Random();
        return jokes[ran.nextInt(jokes.length)];
    }
}
