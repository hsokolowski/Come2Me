package com.example.hubert.come2me;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateUser extends AppCompatActivity {

    EditText firstname,lastname,city,street,numberOfHouse;
    Button btn;
    ImageView img;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        firstname=findViewById(R.id.first_name);
        lastname=findViewById(R.id.last_name);
        city=findViewById(R.id.city);
        street=findViewById(R.id.street);
        numberOfHouse=findViewById(R.id.number);
        img=findViewById(R.id.img);
//        final FriendDB db = Room.databaseBuilder(getApplicationContext(),FriendDB.class,"production")
//                .allowMainThreadQueries()
//                .build();
        final FriendDB db = FriendDB.getInstance(this);

        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08.12.2018 save to db
                db.friendDao().insertAll(new Friend(firstname.getText().toString(),
                        lastname.getText().toString(),
                        city.getText().toString(),
                        street.getText().toString(),
                        numberOfHouse.getText().toString(),
                        img.getResources().toString()));

                startActivity(new Intent(CreateUser.this,MainActivity.class));
            }

        });
    }
}
