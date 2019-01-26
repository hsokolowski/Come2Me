package com.example.hubert.come2me.NotNess;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.come2me.Friend;
import com.example.hubert.come2me.MainActivity;
import com.example.hubert.come2me.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {


    RecyclerView rv;
    List<Friend> friendList;
    RecyclerView.Adapter adapter;
    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        rv=view.findViewById(R.id.redcycler_view);
        adapter=new UpdateAdapter(getContext(), friendList);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        friendList=new ArrayList<>();
        friendList=MainActivity.db.friendDao().getAllFriends();
    }
}
