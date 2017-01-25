package ru.rychagov.justrss.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.rychagov.justrss.R;

public class MainActivity extends AppCompatActivity {

  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);

    context = getApplicationContext();
  }


  // --- UI methods --- //

  private void setToolbar(String title) {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(title);
    toolbar.setTitleTextColor(getResources().getColor(R.color.white_color));
    setSupportActionBar(toolbar);
  }
}
