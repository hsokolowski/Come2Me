package com.example.hubert.come2me.NotNess;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubert.come2me.Friend;

import java.util.List;

import io.reactivex.Flowable;

public interface IFriendDataSource {

    List<Friend> getFriendsById(int idFriend);


    List<Friend> getAllFriends();


    void insertAll(Friend... friends);


    void updateFriend(Friend... friends);


    void deleteFriend(Friend friends);


    void deleteAllUsers();
}
