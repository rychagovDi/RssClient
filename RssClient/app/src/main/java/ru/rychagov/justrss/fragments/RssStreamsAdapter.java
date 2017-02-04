package ru.rychagov.justrss.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.rychagov.justrss.R;
import ru.rychagov.justrss.data.RssStream;

class RssStreamsAdapter extends RecyclerView.Adapter<RssStreamsAdapter.RssStreamsViewHolder> {

  private static final String TAG = "RssStreamsAdapter";

  private Context context;
  private ArrayList<RssStream> streams;

  RssStreamsAdapter(Context context, ArrayList<RssStream> streams) {
    this.context = context;
    this.streams = streams;
  }

  @Override
  public RssStreamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.nav_row, parent, false);
    return new RssStreamsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RssStreamsViewHolder holder, int position) {
    RssStream stream = streams.get(position);

    SpannableStringBuilder stringBuilder = new SpannableStringBuilder(stream.getTitle());
    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.rgb(153,0,0));
    stringBuilder.setSpan(colorSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    holder.title.setText(stringBuilder);

    holder.listener.setLink(stream.getLink());
    holder.listener.setId(stream.getId());
  }

  @Override
  public int getItemCount() {
    return streams.size();
  }

  class RssStreamsViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    OnItemClickListener listener = new OnItemClickListener();

    RssStreamsViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.nav_row_title);
      itemView.setOnClickListener(listener);
    }
  }

  private class OnItemClickListener implements View.OnClickListener {

    private String link;
    private int id;

    @Override
    public void onClick(View v) {
      // TODO broadcast

      Log.d(TAG, String.format("link: %s, id: %d", link, id));
    }

    void setLink(String link) {
      this.link = link;
    }
    void setId(int id) {
      this.id = id;
    }
  }
}
