package com.example.recyclerview.dsp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.model.entity.TabletDeviceEntity;

import java.util.List;

/**
 * @file TabletListAdapter.java
 * @brief Original IPC control facility definition
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
public class TabletListAdapter extends RecyclerView.Adapter<TabletListAdapter.TabletViewHolder> {

  class TabletViewHolder extends RecyclerView.ViewHolder {
    private final TextView tabletItemView;
    private TabletViewHolder( View itemView) {
      super(itemView);
      tabletItemView = itemView.findViewById(R.id.tablet_id);
    }
  }

  protected final LayoutInflater layoutInflater;
  protected List<TabletDeviceEntity> mTablets;

  public TabletListAdapter (Context context) {
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public TabletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = layoutInflater.inflate(R.layout.activity_tablet_list_screen,parent,false);
    return new TabletViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull TabletViewHolder holder, int position) {
    if ( mTablets != null) {
      TabletDeviceEntity currentTablet = mTablets.get(position);
      holder.tabletItemView.setText(currentTablet.getTabletID());
    } else {
      holder.tabletItemView.setText("Empty tablet");
    }
  }

  public void setTablets (List<TabletDeviceEntity> tablets) {
    mTablets = tablets;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if (mTablets != null) {
      return mTablets.size();
    } else return 0;

  }
}
