package ru.rychagov.rssclient.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import ru.rychagov.rssclient.R;

public class MainActivity extends Activity {

  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);

    context = getApplicationContext();
  }

}
