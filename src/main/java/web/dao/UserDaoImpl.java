package web.dao;

import org.hibernate.Session;
import org.hibernate.query.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    public UserDaoImpl(){}

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.createQuery("from User where id = :id");
        query.setParameter("id",id);
        User user = query.getSingleResult();
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList =session.createQuery("from User").list();
        return userList;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("UPDATE User set name=:name,age = :age,password=:password where id=:id");
        query.setParameter("name",user.getName());
        query.setParameter("age", user.getAge());
        query.setParameter("password",user.getPassword());
        query.setParameter("id",user.getId());
        query.executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public User loadUserByUsername(String name) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User where name=:name");
            query.setParameter("name", name);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception ex){
            return null;
        }
    }
}
