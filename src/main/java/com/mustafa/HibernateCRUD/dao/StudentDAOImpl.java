package com.mustafa.HibernateCRUD.dao;

import com.mustafa.HibernateCRUD.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using consturcto
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findByID(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery=entityManager.createQuery("From Student order by lastName",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        //create query
        TypedQuery<Student> theQuery=entityManager.createQuery(
                "From Student WHERE firstName=:theData",Student.class);

        //set query param
        theQuery.setParameter("theData",firstName);
        //return query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student std=entityManager.find(Student.class,id);
        entityManager.remove(std);
    }

    @Override
    @Transactional
    public int deleteAllStudent() {
        int numRowsDeleted=entityManager.createQuery("Delete FROM Student").executeUpdate();
        return numRowsDeleted;
    }

}
