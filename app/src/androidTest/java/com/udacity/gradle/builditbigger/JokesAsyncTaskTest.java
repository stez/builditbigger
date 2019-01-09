package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class JokesAsyncTaskTest {

    @Test
    public void testJokesAsyncTask() throws InterruptedException {
        final Object syncObj = new Object();
        new JokesAsyncTask(new JokesAsyncTaskCallback() {
            @Override
            public void loadingJoke() {

            }

            @Override
            public void showJoke(String joke) {
                Assert.assertNotNull(joke);
                Assert.assertFalse(joke.isEmpty());
                synchronized (syncObj){
                    syncObj.notify();
                }
            }
        }).execute();
        synchronized (syncObj){
            syncObj.wait();
        }
    }
}