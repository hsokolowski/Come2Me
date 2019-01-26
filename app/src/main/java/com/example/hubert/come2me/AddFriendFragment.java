package com.example.hubert.come2me;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStoragePublicDirectory;



/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment {

    EditText firstname,lastname,city,street,numberOfHouse;
    Button btn,photo;
    String pathToFile="/0";
    Float mrating;
    Bitmap bitmap;
    ImageView imageView;
    public AddFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_friend, container, false);

        firstname=view.findViewById(R.id.first_name);
        lastname=view.findViewById(R.id.last_name);
        city=view.findViewById(R.id.city);
        street=view.findViewById(R.id.street);
        numberOfHouse=view.findViewById(R.id.number);
        photo=view.findViewById(R.id.photo);
        imageView=view.findViewById(R.id.imageV);

        firstname.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        lastname.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }

        btn=view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=firstname.getText().toString();
                String surname=lastname.getText().toString();
                String c=city.getText().toString();
                String s=street.getText().toString();
                String n=numberOfHouse.getText().toString();

                Friend f =new Friend(name,surname,c,s,n, pathToFile);

                MainActivity.db.friendDao().insertAll(f);
                Toast.makeText(getActivity(),"Added succesfully",Toast.LENGTH_SHORT).show();

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

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPicture();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==1){

                bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void dispatchPicture() {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getActivity().getPackageManager())!=null){
            File photoFile;
            photoFile = createPhotoFile();

            if(photoFile!=null){
                pathToFile = photoFile.getAbsolutePath();

                Uri photoUri = FileProvider.getUriForFile(getActivity(),"com.example.hubert.come2me.fileprovider",photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(takePic,1);
            }
        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storeDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try {
            image = File.createTempFile(name,".png",storeDir);

        } catch (IOException e) {
            Log.d("myLog","Excep: "+ e.toString());
        }
        return image;
    }

}
