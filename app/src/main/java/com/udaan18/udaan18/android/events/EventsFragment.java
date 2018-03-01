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
import android.widget.Toast;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.adapters.EventAdapter;
import com.udaan18.udaan18.android.databinding.FragmentEventsBinding;
import com.udaan18.udaan18.android.model.eventCategory.Container;
import com.udaan18.udaan18.android.model.eventCategory.Event;
import com.udaan18.udaan18.android.model.eventCategory.Tech;
import com.udaan18.udaan18.android.util.RestClient;
import com.udaan18.udaan18.android.util.SharedPreferenceHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class EventsFragment extends Fragment {
  private FragmentEventsBinding dataBinding;
  
  public static EventsFragment newInstance() {
    EventsFragment fragment = new EventsFragment();
    return fragment;
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);
    fetchEvent();
    return this.dataBinding.getRoot();
  }

  public void fetchEvent(){
    try {
      RestClient client = new RestClient();
      Call<Container> call = client.getApiHelper().getEvents();
      call.enqueue(new Callback<Container>() {
        @Override
        public void onResponse(Call<Container> call, Response<Container> response) {
          Container container = response.body();
          SharedPreferenceHelper.storeEventsData(EventsFragment.this.getContext(), container);
          List<Tech> event = container.getTech();
          //Toast.makeText(dataBinding.getRoot().getContext(),"Error1"+event.get(0).getEvents().size(),Toast.LENGTH_LONG).show();
          RecyclerView view = dataBinding.getRoot().findViewById(R.id.event_recycleview);
          EventAdapter adapter = new EventAdapter(event.get(0).getEvents(), getContext());
          view.setAdapter(adapter);
          view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        }

        @Override
        public void onFailure(Call<Container> call, Throwable t) {
          // Toast.makeText(dataBinding.getRoot().getContext(),"Error0"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
      });
    } catch (Exception e) {
      Toast.makeText(getContext(), "error in eventfrag: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
  }
}
