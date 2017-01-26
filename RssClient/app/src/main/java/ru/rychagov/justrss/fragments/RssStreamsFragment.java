package ru.rychagov.justrss.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.rychagov.justrss.R;
import ru.rychagov.justrss.utils.RssStreamHelper;

public class RssStreamsFragment extends Fragment{

  private static final String TAG = "RssStreamsFragment";

  private RecyclerView recyclerView;
  private ActionBarDrawerToggle drawerToggle;
  private DrawerLayout drawerLayout;
  private RssStreamsAdapter adapter;
  private View containerView;

  public RssStreamsFragment() {

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View layout = inflater.inflate(R.layout.f_navigation_drawer, container, false);
    recyclerView = (RecyclerView) layout.findViewById(R.id.rss_streams_list);

    adapter = new RssStreamsAdapter(getContext(), RssStreamHelper.getStreams(getContext()));
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    return super.onCreateView(inflater, container, savedInstanceState);
  }

  public void setUp(int fragmentID, DrawerLayout drawerLayout, final Toolbar toolbar) {

    containerView = getActivity().findViewById(fragmentID);
    this.drawerLayout = drawerLayout;
    drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        getActivity().invalidateOptionsMenu();
      }

      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        getActivity().invalidateOptionsMenu();
      }

      @Override
      public void onDrawerSlide(View drawerView, float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        toolbar.setAlpha(1 - slideOffset/2);
      }
    };

    drawerLayout.setDrawerListener(drawerToggle);
    drawerLayout.post(new Runnable() {
      @Override
      public void run() {
        drawerToggle.syncState();
      }
    });
  }
}
