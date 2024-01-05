package ma.enset.recsys.Service;

import ma.enset.recsys.Dao.Entities.User;

public interface IServiceUser {
    User login() ;
    User register();
}
