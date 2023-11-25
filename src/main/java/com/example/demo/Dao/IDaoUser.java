package com.example.demo.Dao;

import com.example.demo.Dao.Entities.User;

public interface IDaoUser extends Dao<User>{

//    boolean register ();
    User Login(String email, String password);
}
