package com.example.hubert.come2me;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FriendDao {
    @Query("SELECT * FROM friends WHERE id=:idFriend")
    List<Friend> getFriendsById(int idFriend);

    @Query("SELECT * FROM friends WHERE first_name=:name")
    List<Friend> find(String name);

    @Query("SELECT * FROM friends")
    List<Friend> getAllFriends();

    @Insert
    void insertAll(Friend... friends);

    @Update
    void updateFriend(Friend... friends);

    @Delete
    void deleteFriend(Friend friends);

    @Query("DELETE FROM friends")
    void deleteAllUsers();
}