package com.example.hubert.come2me;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

Button btn,btn_list,btn_update,btn_delete;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        btn=view.findViewById(R.id.btn_add);
        btn_list=view.findViewById(R.id.btn_list);
        btn_delete=view.findViewById(R.id.btn_del);
        //btn_update=view.findViewById(R.id.btn_edit);

        btn.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        //btn_update.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_add:
            {
                //Toast.makeText(getActivity(),"Change",Toast.LENGTH_SHORT).show();

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new AddFriendFragment())
                        .addToBackStack(null).commit();
                break;
            }
            case R.id.btn_list:
            {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ListFragment())
                        .addToBackStack(null).commit();
                break;
            }
//            case R.id.btn_edit:
//            {
//                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ListFragment())
//                        .addToBackStack(null).commit();
//                break;
//            }
            case R.id.btn_del:
            {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new DeleteFragment())
                        .addToBackStack(null).commit();
                break;
            }
        }
    }
}
