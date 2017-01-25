package ru.rychagov.rssclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ru.rychagov.rssclient.R;

public class SplashActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_splash);

    if (savedInstanceState == null) {

      // TODO Download new RSS from internet

      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          startActivity(new Intent(SplashActivity.this, MainActivity.class));
          finish();
        }
      }, 2000);
    }

  }
}
