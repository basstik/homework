
package com.mycompany.main;

import com.mycompany.jpafeladat.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author basstik
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private Main() {
    }

    
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntMgr_name_practice");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //School
        School school = new School();
        school.setCountry("Budapset");
        school.setZipCode("1202");
        em.persist(school);
        School school2 = new School();
        school2.setCountry("Szeged");
        school2.setZipCode("8787");
        em.persist(school2);

        tx.commit();
        tx.begin();
        //<<<------------------------------------------------------->>
        //Add Student and Subject entity to database

        //Student
        Student student = new Student();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        student.setDateOfBirth(sdf.parse("21/12/2012"));
        student.setName("Admin");
        Student student2 = new Student();
        student2.setDateOfBirth(sdf.parse("21/12/2012"));
        student2.setName("Demo");
        em.persist(student);
        em.persist(student2);

        //Subject
        Subject subject = new Subject();
        Subject subject2 = new Subject();
        em.persist(subject);
        em.persist(subject2);
        tx.commit();
        tx.begin();
        //<<<-------------------------------------------------------->>
        //Create relation between Student and Subject

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        subject.setStudents(students);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        subjects.add(subject2);

        student.setSubjects(subjects);

        em.persist(student);
        em.persist(subject);
        tx.commit();
        tx.begin();

        //<<<-------------------------------------------------------->>
        //Add friends to student
        ArrayList<String> friends = new ArrayList<>(
                Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        student.setFriends(friends);
        tx.commit();
        tx.begin();
        
        //<<<-------------------------------------------------------->>
        //Add teacher
        Teacher teacher1 = new Teacher();
        teacher1.setBankCardType(BankCardType.MASTER_CARD);
        teacher1.setStartOfJob(sdf.parse("8/1/2010"));
        
        tx.commit();
        tx.begin();
        //<<<-------------------------------------------------------->>
        //Query the School.findById

        TypedQuery<School> findById = em.createNamedQuery("School.findById", School.class);
        findById.setParameter("my_id", 1);
        LOG.info(findById.getResultList().toString());
        tx.commit();
        tx.begin();

        //<<<-------------------------------------------------------->>
        //Query the Student.countOfFriends
        TypedQuery<Student> countOfFriends = em.createNamedQuery(
                "Student.countOfFriends", Student.class);
        LOG.info(countOfFriends.getResultList().toString());
        tx.commit();
        tx.begin();

        //<<<-------------------------------------------------------->>
        //Query the Student.findByBankCardType
        TypedQuery<Student> bankcardOfStudent = em.createNamedQuery(
                "Student.findByBankCardType", Student.class);
        bankcardOfStudent.setParameter("type", BankCardType.MASTER_CARD);
        LOG.info(bankcardOfStudent.getResultList().toString());
        tx.commit();
        tx.begin();

        //<<<-------------------------------------------------------->>
        //Query the School.findByHUNCountry
        TypedQuery<School> findByHUNCountry = em.createNamedQuery(
                "School.findByHUNCountry", School.class);
        LOG.info(findByHUNCountry.getResultList().toString());
        tx.commit();

        em.close();
        emf.close();

    }
}
