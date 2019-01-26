package com.example.hubert.come2me.NotNess;

import com.example.hubert.come2me.Friend;
import com.example.hubert.come2me.FriendDao;

import java.util.List;

public class FriendDataSource implements IFriendDataSource {

    private FriendDao friendDao;
    private static FriendDataSource instance;

    public FriendDataSource(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public static FriendDataSource getInstance(FriendDao friendDao) {
        if(instance==null)
        {
            instance= new FriendDataSource(friendDao);
        }
        return instance;

    }

    @Override
    public List<Friend> getFriendsById(int idFriend) {
        return friendDao.getFriendsById(idFriend);
    }

    @Override
    public List<Friend> getAllFriends() {
        return friendDao.getAllFriends();
    }

    @Override
    public void insertAll(Friend... friends) {
        friendDao.insertAll(friends);
    }

    @Override
    public void updateFriend(Friend... friends) {
        friendDao.updateFriend(friends);
    }

    @Override
    public void deleteFriend(Friend friends) {
        friendDao.deleteFriend(friends);
    }

    @Override
    public void deleteAllUsers() {
        friendDao.deleteAllUsers();
    }
}
