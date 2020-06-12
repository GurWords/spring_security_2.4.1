package web.service.usersservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.dao.userdao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserDao implUserDao;


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public User getUserByID(int id) {
        return implUserDao.getUserById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public List<User> getAllUsers() {
        return implUserDao.getAllUsers();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void deleteUser(int id) {
        implUserDao.deleteUser(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void updateUser(User user) {
        implUserDao.updateUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void insertUser(User user) {
        implUserDao.insertUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public User loadUserByUsername(String name) {
        return implUserDao.loadUserByUsername(name);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public Role getRole(String name_role) {
        return implUserDao.getRole(name_role);
    }

}
