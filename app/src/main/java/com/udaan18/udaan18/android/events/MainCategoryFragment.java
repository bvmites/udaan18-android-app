package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentMainCategoryBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;

/**
 * Created by jack on 01-03-2018.
 */

public class MainCategoryFragment extends Fragment implements View.OnClickListener {
    public static int location = 3;
    FragmentMainCategoryBinding dataBinding;

    public static MainCategoryFragment newInstance() {
        MainCategoryFragment fragment = new MainCategoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_category, container, false);
        Typeface custom_font_tagline = Typeface.createFromAsset(getContext().getAssets(), "fonts/BungeeRegular.ttf");
        this.dataBinding.categoriesFragmentCardView1.setOnClickListener(this);
        this.dataBinding.categoriesFragmentCardView2.setOnClickListener(this);
        this.dataBinding.categoriesFragmentCardView3.setOnClickListener(this);
        this.dataBinding.categoriesFragmentCardView4.setOnClickListener(this);

        this.dataBinding.categoriesFragmentTextView1.setTypeface(custom_font_tagline);
        this.dataBinding.categoriesFragmentTextView2.setTypeface(custom_font_tagline);
        this.dataBinding.categoriesFragmentTextView3.setTypeface(custom_font_tagline);
        this.dataBinding.categoriesFragmentTextView4.setTypeface(custom_font_tagline);
        return this.dataBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) this.getActivity()).removeBack();
        ((MainActivity) getActivity()).removeTitle("Udaan-18");
        ((MainActivity) getActivity()).setAllColorChanage(R.color.color_events);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.categories_fragment_cardView1:

                ((MainActivity) getActivity()).loadDepartments();
                //((MainActivity) getActivity()).setToolTitle("Tech");
                break;
            case R.id.categories_fragment_cardView2:
                ((MainActivity) getActivity()).loadNonTechEvents();
                ((MainActivity) getActivity()).setAllColorChanage(R.color.fighter);
                //((MainActivity) getActivity()).setToolTitle("Non Tech");
                break;
            case R.id.categories_fragment_cardView3:
                //((MainActivity) getActivity()).setToolTitle("Cultural");
                ((MainActivity) getActivity()).loadCulturalEvents();
                ((MainActivity) getActivity()).setAllColorChanage(R.color.alladin);
                break;
            case R.id.categories_fragment_cardView4:
                //((MainActivity) getActivity()).setToolTitle("Advanture");
                ((MainActivity) getActivity()).loadAdventure();
                ((MainActivity) getActivity()).setAllColorChanage(R.color.duckhunt);
            default:
                break;
        }

    }
}
