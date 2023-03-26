package com.mustafa.HibernateCRUD;

import com.mustafa.HibernateCRUD.dao.StudentDAO;
import com.mustafa.HibernateCRUD.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernateCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(HibernateCrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner-> {
//			createStudent(studentDAO);
//			readStudent(studentDAO);
//			queryForAllStudents(studentDAO);
//			queryForStudentByFirstName(studentDAO);
//			updateStudent(studentDAO);
			deleteStudent(studentDAO);
		};
		
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentID=2;
		studentDAO.delete(studentID);
		System.out.println("Successfully Deleted");
	}
	private void deleteAllStudents(StudentDAO studentDAO){
		int numRowsDeleted=studentDAO.deleteAllStudent();
		System.out.println("ROWS deleted: "+ numRowsDeleted);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on id
		int studentId=1;
		Student myStudent=studentDAO.findByID(studentId);

		//change first name
		myStudent.setFirstName("Scooby");
		studentDAO.update(myStudent);
		System.out.println("UPDATED: "+myStudent);
	}

	private void queryForStudentByFirstName(StudentDAO studentDAO) {
		//get list of student
		List<Student> theStudents= studentDAO.findByFirstName("mustafa");

		for(Student t:theStudents){
			System.out.println(t);
		}

	}

	private void queryForAllStudents(StudentDAO studentDAO) {
		List<Student> theStudents=studentDAO.findAll();
		for(Student t:theStudents){
			System.out.println(t);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("Creating new student object");
		Student tempStudent=new Student("mujtaba","bawani","mujtaba_bawani@hotmail.com");

		//ssave a student

		studentDAO.save(tempStudent);

		//display id of the saved student
		int theId=tempStudent.getId();
		//retrieve student based on id
		System.out.println("retriveing student by id"+theId);
		Student myStudent=studentDAO.findByID(theId);
		//display student
		System.out.println("Found student"+ myStudent);
	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Creating new student object");
		Student tempStudent=new Student("mustafa","bawani","mustafa_bawani@hotmail.com");

		//save the student object
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. generated Id:" +tempStudent.getId());
	}
}
