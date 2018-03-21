package com.udaan18.udaan18.android.photo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentPhotoBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class PhotoFragment extends Fragment {
  public static int location = 2;
  private FragmentPhotoBinding dataBinding;
  
  public static PhotoFragment newInstance() {
    PhotoFragment fragment = new PhotoFragment();
    return fragment;
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false);
      ((MainActivity) getActivity()).removeTitle("Udaan Filter");
      ((MainActivity) getActivity()).setAllColorChanage(R.color.color_photo);
    return this.dataBinding.getRoot();
  }
}
