package com.example.hubert.come2me;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
public class EditFragment extends Fragment {

    EditText firstname,lastname,city,street,numberOfHouse;
    Button btn,editPhoto;
    ImageView imgg_edit;
    Bitmap bitmap;
    String pathToFile="/0";
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

        bitmap = BitmapFactory.decodeFile(f.getmPath());
        imgg_edit.setImageBitmap(bitmap);


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
                String p=f.getmPath();

                f.setFirstName(name);
                f.setLastName(surname);
                f.setCity(c);
                f.setStreet(s);
                f.setNumberHouse(n);
                if(pathToFile!=null)
                {
                    f.setmPath(pathToFile);
                }
                else
                {
                    f.setmPath(p);
                }

                FriendDB.getInstance(getContext()).friendDao().updateFriend(f);
                Toast.makeText(getActivity(),R.string.toast_edit,Toast.LENGTH_SHORT).show();

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
        editPhoto=view.findViewById(R.id.edit_photo);
        editPhoto.setOnClickListener(new View.OnClickListener() {
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
                imgg_edit.setImageBitmap(bitmap);
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
