package com.udaan18.udaan18.android.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Feed;
import com.udaan18.udaan18.android.notification.NotificationAdapter;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.RestClient;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class NewsFragment extends Fragment {
    public static int location = 5;
  RecyclerView recyclerView;
  NotificationAdapter myAdapter;
  List<Udaandata> list;
  FirebaseDatabase fdb;
  DatabaseReference dr;
    List<Feed> feed;
  private View rootView;
  
  public static NewsFragment newInstance() {
    NewsFragment fragment = new NewsFragment();
    return fragment;
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_news, container, false);
      ((MainActivity) getActivity()).removeTitle("Notifications");
      ((MainActivity) getActivity()).setAllColorChanage(R.color.color_news);
      intializeObjects();


      return rootView;

  }

    @Override
    public void onResume() {
        super.onResume();
        if (Helper.hasNetworkConnection(getContext()))
            getFeed();
        else
            Helper.showNetworkNotificationAlertPopup(getActivity());
    }

    public void intializeObjects() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rec);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        //list=new ArrayList<>();
        //myAdapter=new NotificationAdapter(getContext(),list);

//        fdb=FirebaseDatabase.getInstance();
//        getdatafirebase();


    }

    void getFeed() {
        RestClient client = new RestClient();
        Call<List<Feed>> call = client.getApiHelper().getFeed();
        call.enqueue(new Callback<List<Feed>>() {
            @Override
            public void onResponse(Call<List<Feed>> call, Response<List<Feed>> response) {
                feed = response.body();
                Collections.reverse(feed);
                myAdapter = new NotificationAdapter(getContext(), feed);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<Feed>> call, Throwable t) {

            }
        });
    }
  void getdatafirebase()
  {
    dr=fdb.getReference("udaan");
    dr.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Udaandata udaandata=dataSnapshot.getValue(Udaandata.class);
        udaandata.settime(getcurrenttime());
        list.add(udaandata);
        Collections.reverse(list);
        recyclerView.setAdapter(myAdapter);
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {

        Udaandata udaandata=dataSnapshot.getValue(Udaandata.class);
          //list.remove(udaandata);
          //Collections.reverse(list);
          myAdapter = new NotificationAdapter(getContext(), feed);
        recyclerView.setAdapter(myAdapter);

      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }

    });
    //dr.setValue(ServerValue.TIMESTAMP);
  }

  private String getcurrenttime()
  {
    DateFormat dateFormat=DateFormat.getDateTimeInstance();
    //Calendar calendar=Calendar.getInstance();
    String calendermil=dateFormat.format(new Date()).toString();
    //Long timestamp=System.currentTimeMillis();
    return calendermil;
  }



}
