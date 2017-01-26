package ru.rychagov.justrss.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RssStreamsAdapter extends RecyclerView.Adapter<RssStreamsAdapter.RssStreamsViewHolder> {

  @Override
  public RssStreamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(RssStreamsViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  class RssStreamsViewHolder extends RecyclerView.ViewHolder {
    public RssStreamsViewHolder(View itemView) {
      super(itemView);
    }
  }
}
