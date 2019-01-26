package com.example.hubert.come2me.NotNess;

import com.example.hubert.come2me.Friend;

import java.util.List;

public class FriendRepository implements IFriendDataSource {


    private IFriendDataSource local;
    private  static FriendRepository instance;

    public FriendRepository(IFriendDataSource local) {
        this.local = local;
    }

    public static FriendRepository getInstance(IFriendDataSource local)
    {
        if(instance==null)
        {
            instance=new FriendRepository(local);
        }
        return instance;
    }

    @Override
    public List<Friend> getFriendsById(int idFriend) {
        return local.getFriendsById(idFriend);
    }

    @Override
    public List<Friend> getAllFriends() {
        return local.getAllFriends();
    }

    @Override
    public void insertAll(Friend... friends) {
        local.insertAll(friends);
    }

    @Override
    public void updateFriend(Friend... friends) {
        local.updateFriend(friends);
    }

    @Override
    public void deleteFriend(Friend friends) {
        local.deleteFriend(friends);
    }

    @Override
    public void deleteAllUsers() {
        local.deleteAllUsers();
    }
}
