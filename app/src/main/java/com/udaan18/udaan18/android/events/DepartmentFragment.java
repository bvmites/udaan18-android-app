package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentDepartmentBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Department;
import com.udaan18.udaan18.android.util.SharedPreferenceHelper;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import org.json.JSONException;

import java.util.List;

/**
 * Created by jack on 03-03-2018.
 */

public class DepartmentFragment extends Fragment implements ListItemClickCallBack {
    static int lastPos;
    FragmentDepartmentBinding binding;
    DepartmentAdapter adapter;
    List<Department> department;

    public static DepartmentFragment newInstance() {
        DepartmentFragment fragment = new DepartmentFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setToolTitle("TECH");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_department, container, false);

        try {
            //((MainActivity) getActivity()).setBack();
            RecyclerView view = binding.departmentRecycleview;
            department = SharedPreferenceHelper.getInstance(getActivity()).getDepartmentsList();
            if (adapter == null) {

                adapter = new DepartmentAdapter(SharedPreferenceHelper.getInstance(getActivity()).getDepartmentsList(), getContext());
            }
            view.setAdapter(adapter);
            view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            adapter.setItemClickCallBack(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.binding.getRoot();
    }

    @Override
    public void onItemClick(int position, int viewId) {
        ((MainActivity) getActivity()).loadTechEvents(position);
        lastPos = position;
        //((MainActivity) getActivity()).setToolTitle(department.get(position).getName());

    }
}
