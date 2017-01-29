package ru.rychagov.justrss.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.rychagov.justrss.R;
import ru.rychagov.justrss.fragments.RssStreamsFragment;

public class MainActivity extends AppCompatActivity {

  private Context context;
  private Toolbar toolbar;
  private RssStreamsFragment streamsFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);

    context = getApplicationContext();

    setToolbar("Test");

    streamsFragment = (RssStreamsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);
    streamsFragment.setUp(R.id.fragment_nav_drawer,(DrawerLayout)findViewById(R.id.a_drawer), toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.m_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

// --- UI methods --- //

  private void setToolbar(String title) {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }
}
