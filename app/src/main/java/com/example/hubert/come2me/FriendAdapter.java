package com.example.hubert.come2me;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    List<Friend> friends = new ArrayList();
    String localize;
    Context context;

    double longitude;
    double latitude;
    private GoogleApiClient googleApiClient;
    private Location mLastLocation;

    public FriendAdapter(List<Friend> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    FriendAdapter.onItemClickListner onItemClickListner;


    public interface onItemClickListner {
        void onClick(Friend str);//pass your object types.
    }

    public FriendAdapter(Context context, List<Friend> friends) {
        this.friends = friends;
    }

    public Friend getFriendAtPosition(int position) {
        return friends.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_row, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.firstname.setText( friends.get(i).getFirstName());
        viewHolder.lastname.setText(friends.get(i).getLastName());
        viewHolder.adres.setText(friends.get(i).getCity() + ", " + friends.get(i).getStreet() + " " + friends.get(i).getNumberHouse());

        if(friends.get(i).getmPath()!="/0"){
            Bitmap bitmap = BitmapFactory.decodeFile(friends.get(i).getmPath());
            viewHolder.imageView.setImageBitmap(bitmap);
        }else {
            viewHolder.imageView.setImageResource(R.drawable.tlo1);
        }




        viewHolder.firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String uri = "http://maps.google.com/maps?saddr=" + 53.116611 + "," + 23.146638 + "&daddr=" + 53.121837 + "," + 23.163906;
                //--------------
//                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
////                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
////                double longitude = location.getLongitude();
////                double latitude = location.getLatitude();
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    //nie przechodzi ifa
//                    ActivityCompat.requestPermissions((Activity) context,new String[]{
//                            Manifest.permission.ACCESS_COARSE_LOCATION,
//                            Manifest.permission.ACCESS_FINE_LOCATION},7171);
//
//
//                }
//                MainActivity.mFusedLocationClient.getLastLocation()
//                            .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
//                                @Override
//                                public void onSuccess(Location location) {
//                                    // Got last known location. In some rare situations this can be null.
//                                    if (location != null) {
//                                        longitude = location.getLongitude();
//                                        latitude = location.getLatitude();
//                                        localize = String.valueOf(latitude) + "," + String.valueOf(longitude);
//                                        //System.out.println(localize);
//                                    }
//                                }
//                            });

//                try {
//                    MapsActivity.mFusedLocationClient.getLastLocation()
//                            .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
//                                @Override
//                                public void onSuccess(Location location) {
//                                    // Got last known location. In some rare situations this can be null.
//                                    if (location != null) {
//                                        longitude = location.getLongitude();
//                                        latitude = location.getLatitude();
//                                        localize = String.valueOf(latitude) + "," + String.valueOf(longitude);
//                                        //System.out.println(localize);
//                                    }
//                                }
//                            });
//                } catch (SecurityException e) {
//                    e.printStackTrace();
//                }

                latitude=MainActivity.latitude;
                longitude=MainActivity.longitude;

                String uri = "http://maps.google.com/maps?saddr=" + latitude+","+longitude + "&daddr=" + friends.get(i).getCity()+", "+friends.get(i).getStreet()+" "+friends.get(i).getNumberHouse();
                //--------------
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new GPSFragment())
                        .addToBackStack(null).commit();
                Toast.makeText(context, "GPS onClick: "+i,Toast.LENGTH_LONG).show();

            }
        });
        viewHolder.firstname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String name=friends.get(i).getFirstName();
                String surname=friends.get(i).getFirstName();
                String c=friends.get(i).getFirstName();
                String s=friends.get(i).getFirstName();
                String n=friends.get(i).getFirstName();
                String p=friends.get(i).getmPath();

                Friend f =new Friend(name,surname,c,s,n,p);
                f.setId(friends.get(i).getId());

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new EditFragment(friends.get(i)))
                        .addToBackStack(null).commit();


                return true;
            }
        });
//        viewHolder.firstname.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                Friend f = new Friend(friends.get(i).getFirstName(),friends.get(i).getLastName(),friends.get(i).getCity(),friends.get(i).getStreet(),friends.get(i).getNumberHouse());
//                f.setId(friends.get(i).getId());
//                MainActivity.db.friendDao().deleteFriend(f);
//                friends.remove(i);
//                notifyItemRemoved(i);
//                Toast.makeText(context, "OnLongCLick delete:"+i,Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void deleteItem (int i) {
        Friend f = new Friend(friends.get(i).getFirstName(),friends.get(i).getLastName(),friends.get(i).getCity(),friends.get(i).getStreet(),friends.get(i).getNumberHouse(),friends.get(i).getmPath());
        f.setId(friends.get(i).getId());
        MainActivity.db.friendDao().deleteFriend(f);
        friends.remove(i);
        notifyDataSetChanged();
        Toast.makeText(context, "swipe delete:"+i,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    //    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public TextView firstname,lastname,city,street,number,adres;
        ImageView imageView;
        TextView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname=itemView.findViewById(R.id.first_name);
            lastname=itemView.findViewById(R.id.last_name);
            adres=itemView.findViewById(R.id.adres);
            imageView=itemView.findViewById(R.id.img);
            card=itemView.findViewById(R.id.first_name);
            card.setOnCreateContextMenuListener(this);
//            city=itemView.findViewById(R.id.city);
//            street=itemView.findViewById(R.id.street);
//            number=itemView.findViewById(R.id.number);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("What would you like to do with "+friends.get(getAdapterPosition()).getFirstName()+" "+friends.get(getAdapterPosition()).getLastName()+"?");


        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
    }

}