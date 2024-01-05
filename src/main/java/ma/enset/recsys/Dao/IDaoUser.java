package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.User;

public interface IDaoUser extends Dao<User>{

//    boolean register ();
    User Login(String email, String password);
}
