package com.example.hubert.come2me.NotNess;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubert.come2me.EditFragment;
import com.example.hubert.come2me.Friend;
import com.example.hubert.come2me.MainActivity;
import com.example.hubert.come2me.R;

import java.util.ArrayList;
import java.util.List;

class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener{

    List<Friend> friends=new ArrayList();;
    Context context;

    onItemClickListner onItemClickListner;

    public UpdateAdapter(UpdateAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(Friend str);//pass your object types.
    }

    public UpdateAdapter(List<Friend> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    public UpdateAdapter(Context context, List<Friend> friends)
    {
        this.friends=friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_row,parent,false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.firstname.setText(friends.get(i).getId()+" "+friends.get(i).getFirstName());
        viewHolder.lastname.setText(friends.get(i).getLastName());
        viewHolder.adres.setText(friends.get(i).getCity()+", "+friends.get(i).getStreet()+" "+friends.get(i).getNumberHouse());


        viewHolder.firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "OnCLick "+i,Toast.LENGTH_LONG).show();


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

    }



    @Override
    public int getItemCount() {
        return friends.size();
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
        TextView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname=itemView.findViewById(R.id.first_name);
            lastname=itemView.findViewById(R.id.last_name);
            adres=itemView.findViewById(R.id.adres);
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