package com.udaan18.udaan18.android.news;

import android.databinding.DataBindingUtil;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentNewsBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.notification.NotificationAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class NewsFragment extends Fragment {
  RecyclerView recyclerView;
  NotificationAdapter myAdapter;
  List<Udaandata> list;
  FirebaseDatabase fdb;
  DatabaseReference dr;
  private View rootView;

  public static int location = 5;
  private View dataBinding;
  
  public static NewsFragment newInstance() {
    NewsFragment fragment = new NewsFragment();
    return fragment;
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_news, container, false);
      intializeObjects();
      ((MainActivity) getActivity()).removeTitle("Notifications");
      ((MainActivity) getActivity()).setAllColorChanage(R.color.color_news);
    //return this.dataBinding.getRoot();
      return rootView;

  }

    public void intializeObjects()
    { recyclerView=(RecyclerView)rootView.findViewById(R.id.rec);
        //recyclerView= container.findViewById(R.id.rec);
        // recyclerView=(RecyclerView)findViewById(R.id.rec);
       // recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        //linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));
        list=new ArrayList<>();
        myAdapter=new NotificationAdapter(getContext(),list);


        fdb=FirebaseDatabase.getInstance();
        getdatafirebase();


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
        list.remove(udaandata);
        Collections.reverse(list);
        myAdapter = new NotificationAdapter(getContext(),list);
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

  //private Long getcurrenttime()
  //{
  //  Calendar calendar=Calendar.getInstance();
  //long calendermil=calendar.getTimeInMillis();
  //Long timestamp=System.currentTimeMillis();
  //return calendermil;
  //}



}
