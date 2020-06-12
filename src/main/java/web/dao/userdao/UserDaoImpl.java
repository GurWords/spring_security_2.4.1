package web.dao.userdao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
    }

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        return userList;
    }


    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");

        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User loadUserByUsername(String name) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User where name=:name");
            query.setParameter("name", name);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Role getRole(String name_role) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Role where role = :name_role");
        query.setParameter("name_role", name_role);
        Role role = (Role) query.getSingleResult();
        return role;
    }
}
