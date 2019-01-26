package com.example.hubert.come2me;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Friend.class},version = 1)
public abstract class FriendDB extends RoomDatabase {
    public abstract FriendDao friendDao();

    public static FriendDB instance;

    public static FriendDB getInstance(Context context) {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context,FriendDB.class,"production")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
