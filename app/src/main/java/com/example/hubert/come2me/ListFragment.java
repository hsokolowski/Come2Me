package com.example.hubert.come2me;


import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    

    RecyclerView rv;
    List<Friend> friendList;
    //RecyclerView.Adapter adapter;
    FriendAdapter adapter;
    private TextView tv;
    public ListFragment() {
        // Required empty public constructor
    }

    SwipeController swipeController = null;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        rv=view.findViewById(R.id.redcycler_view);
        adapter=new FriendAdapter(getContext(),friendList);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {



                        int position = viewHolder.getAdapterPosition();
                        Friend myWord = adapter.getFriendAtPosition(position);
                        Toast.makeText(getContext(), "Usuwanie " +
                                myWord.getFirstName(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        FriendDB.getInstance(getContext()).friendDao().deleteFriend(myWord);
                        friendList.remove(position);
                        adapter.notifyItemRemoved(position);

                    }
//                    @SuppressLint("ResourceAsColor")
//                    @Override
//                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                            // Get RecyclerView item from the ViewHolder
//                            View itemView = viewHolder.itemView;
//
//                            Paint p = new Paint();
//                            if (dX > 0) {
//                                /* Set your color for positive displacement */
//                                itemView.setBackgroundColor(R.color.green);
//                                // Draw Rect with varying right side, equal to displacement dX
//                                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
//                                        (float) itemView.getBottom(), p);
//                            } else {
//                                /* Set your color for negative displacement */
//                                itemView.setBackgroundColor(R.color.red);
//                                // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
//                                c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
//                                        (float) itemView.getRight(), (float) itemView.getBottom(), p);
//                            }
//
//                            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                        }
//                    }
                });

        helper.attachToRecyclerView(rv);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        friendList=new ArrayList<>();
        friendList=FriendDB.getInstance(getContext()).friendDao().getAllFriends();
    }



}
