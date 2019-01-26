package com.example.hubert.come2me;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteFragment extends Fragment {


    TextView tv;
    Button btn_find;

    RecyclerView rv;
    List<Friend> friendList;
    RecyclerView.Adapter adapter;
    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_delete, container, false);
        tv=view.findViewById(R.id.find);
        tv.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        btn_find=view.findViewById(R.id.btn_find);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name=tv.getText().toString();
                friendList =FriendDB.getInstance(getContext()).friendDao().find(name);

                if(friendList.size()==0)
                {
                    Toast.makeText(getActivity(),R.string.notexist,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    rv=view.findViewById(R.id.redcycler_view);
                    adapter=new FriendAdapter(getContext(),friendList);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);
                }

                tv.setText("");


            }
        });
        return view;
    }


}
