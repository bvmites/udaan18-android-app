package com.udaan18.udaan18.android.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentNewsBinding;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class NewsFragment extends Fragment {
    public static int location = 5;
  private FragmentNewsBinding dataBinding;
  
  public static NewsFragment newInstance() {
    NewsFragment fragment = new NewsFragment();
    return fragment;
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
    return this.dataBinding.getRoot();
  }
}
