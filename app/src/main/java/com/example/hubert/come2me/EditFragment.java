package com.example.hubert.come2me;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    EditText firstname,lastname,city,street,numberOfHouse;
    Button btn;
    ImageView imgg_edit;
    public EditFragment() {
        // Required empty public constructor
    }
    Friend f;
    @SuppressLint("ValidFragment")
    public EditFragment(Friend f)
    {
        this.f=f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit, container, false);

        firstname=view.findViewById(R.id.editname);
        lastname=view.findViewById(R.id.editlast);
        city=view.findViewById(R.id.editcity);
        street=view.findViewById(R.id.editstreet);
        numberOfHouse=view.findViewById(R.id.editnumber);
imgg_edit=view.findViewById(R.id.editimg);

        // TODO: 10.12.2018  set vars
        firstname.setText(f.getFirstName());
        lastname.setText(f.getLastName());
        city.setText(f.getCity());
        street.setText(f.getStreet());
        numberOfHouse.setText(f.getNumberHouse());

        firstname.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        lastname.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        btn=view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=firstname.getText().toString();
                String surname=lastname.getText().toString();
                String c=city.getText().toString();
                String s=street.getText().toString();
                String n=numberOfHouse.getText().toString();

                f.setFirstName(name);
                f.setLastName(surname);
                f.setCity(c);
                f.setStreet(s);
                f.setNumberHouse(n);

                MainActivity.db.friendDao().updateFriend(f);
                Toast.makeText(getActivity(),"Edit succesfully",Toast.LENGTH_SHORT).show();

                //----------tmp------------
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ListFragment())
                        .addToBackStack(null).commit();
                //-------------------------

                firstname.setText("");
                lastname.setText("");
                city.setText("");
                street.setText("");
                numberOfHouse.setText("");

            }
        });


        return view;
    }

}
