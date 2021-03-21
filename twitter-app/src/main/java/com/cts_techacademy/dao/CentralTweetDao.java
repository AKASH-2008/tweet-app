package com.cts_techacademy.dao;

import com.cts_techacademy.entity.Tweets;
import com.cts_techacademy.entity.Users;
import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CentralTweetDao {

    Session session = null;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public CentralTweetDao() {
        session = HibernateUtil.buildSessionFactory().openSession();
    }

    public void addTweet(Tweets newTweet) throws MasterException {
        Transaction transaction = null;
        Users users = new Users();
        List<Tweets> listTweet = new ArrayList<>();
        listTweet.add(newTweet);
        try{
            {
                users = getUserByEmail(newTweet.getTweetExtn()).get(0);
                users.setTweets(listTweet);
                updateUser(users);
            }
        }
        catch(Exception e){
            throw new MasterException("Unable to add tweet");
        }
    }

    public void resetPassword(String username, String newPassword) throws MasterException {
        Users users = new Users();
        try{
            {
                users = getUserByEmail(username).get(0);
                //session = HibernateUtil.buildSessionFactory().openSession();
                users.setPassword(newPassword);
                updateUser(users);
            }
        }
        catch(Exception e){
            throw new MasterException("Unable to reset Password");
        }
    }

    public void setUserActive(String email) throws MasterException{
        Transaction transaction = null;
        //Session session = null;
        String hqlUpdate = "update Users s set s.status = 'A' where s.email = :email";
        int updatedEntities1;
        try{
            {
                //session = HibernateUtil.buildSessionFactory().openSession();
                transaction = session.beginTransaction();
                updatedEntities1 = session.createQuery( hqlUpdate )
                        .setParameter("email", email)
                        .executeUpdate();
            }
            transaction.commit();
        }
        catch(Exception e){
            transaction.rollback();
            throw new MasterException("Unable to set User Active");
        }
    }

    public List<Users> getUserByEmail(String email) throws MasterException {
        Transaction transaction = null;
        List<Users> userByEmail = new ArrayList<Users>();
        //Session session = null;
        try {
            Query theQuery = session.createQuery("select u from Users u where u.email=:email");
            theQuery.setParameter("email", email);
            userByEmail = (List<Users>) theQuery.list();

        }
        catch(Exception e){
            throw new MasterException("Unable to get User by Email");
        }
        return userByEmail;
    }

    public void setUserInActive(String email) throws MasterException{
        Transaction transaction = null;
        //Session session = null;
        String hqlUpdate = "update Users s set s.status = 'D' where s.email = :email";
        int updatedEntities1;
        try{
            {
                //session = HibernateUtil.buildSessionFactory().openSession();
                transaction = session.beginTransaction();
                updatedEntities1 = session.createQuery( hqlUpdate )
                        .setParameter("email", email)
                        .executeUpdate();
            }
            transaction.commit();
        }
        catch(Exception e){
            assert transaction != null;
            transaction.rollback();
            throw new MasterException("Unable to set user inactive");
        }
    }

    public Boolean userLoggedInStatusCheck(String email) throws MasterException {

        //Session session = null;
        List results = null;
        String Active = "A";
        try{
            {
                //session = HibernateUtil.buildSessionFactory().openSession();
                Query theQuery = session.createQuery("from Users u where u.email=:email AND u.status=:A");
                theQuery.setParameter("email", email);
                theQuery.setParameter("A", Active);
                results = theQuery.list();
            }
        }
        catch(Exception e){
            throw new MasterException("Unable to userLoggedInStatusCheck");
        }
        if ((results!=null) && (results.size()>0)){
            //killSession(session);
            return true;
        }
        else {
            //killSession(session);
            return false;}

    }

    public Boolean forgotPasswordCheck(String email, String dateOfBirth) throws MasterException{

        List results = null;
         try {
            Query theQuery = session.createQuery("select u from Users u where u.email=:email AND u.dateOfBirth=:dateOfBirth");
            theQuery.setParameter("email", email);
            theQuery.setParameter("dateOfBirth", dateOfBirth);
            results = theQuery.list();
        }
        catch(Exception e){
            e.printStackTrace();
            throw new MasterException("Unable to userLoginCheck");

        }
        if ((results!=null) && (results.size()>0)){
            return true;
        }
        else
            return false;
    }

    public Boolean userLoginCheck(String email, String password) throws MasterException{

        //Session session = HibernateUtil.buildSessionFactory().openSession();
        List results = null;
        try {

            Query theQuery = session.createQuery("select u from Users u where u.email=:email AND u.password=:password");
            theQuery.setParameter("email", email);
            theQuery.setParameter("password", password);
            results = theQuery.list();

            }
        catch(Exception e){
            throw new MasterException("Unable to userLoginCheck");
        }
        if ((results!=null) && (results.size()>0)){
            return true;
        }
        else
            return false;
    }

    public void killSession(Session session){
        HibernateUtil.buildSessionFactory().close();
        session.close();
    }

    public void saveUser(Users user) throws MasterException{
        Transaction transaction = null;
        //Session session = null;
        try{
            {
                //session = HibernateUtil.buildSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(user);
            }
        transaction.commit();
        }
        catch(Exception e){
            transaction.rollback();
            throw new MasterException("USER WITH THIS EMAIL ALREADY EXISTS");
        }

    }

    public void updateUser(Users user) throws MasterException {
        Transaction transaction = null;
        try{
            {
                //session = HibernateUtil.buildSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.saveOrUpdate(user);
            }
            transaction.commit();

        }
        catch(Exception e){
            transaction.rollback();
            throw new MasterException("Unable to update user");
        }
    }

    /*public Users deleteById(long userId) {
        Transaction transaction = null;
        Users users = null;
        //Session session = null;
        try{
            {
                //session = HibernateUtil.buildSessionFactory().openSession();
                transaction = session.beginTransaction();
                //getById
                users = session.load(Users.class, userId);
                //System.out.println("student 1 is deleted");
                if (users != null) session.delete(users);
                else
                    throw new MasterException("The user is not available");
                //session.delete(users);
            }
            transaction.commit();
            //session.flush();
        }
        catch(Exception e){
            transaction.rollback();
        }
        return users;
    }*/

    public List<Users> getAllUsers() throws MasterException{
        return session.createQuery("SELECT a FROM Users a", Users.class).getResultList();
    }

}
