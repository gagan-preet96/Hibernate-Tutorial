package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students last name :doe
			theStudents =session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the students
			System.out.println("\n\n\nStudents with last name Doe");
			displayStudents(theStudents);
			
			//query students last name :doe or firstname :daffy
			theStudents = session.createQuery("from Student s where"  + " s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			
			//display the students
			System.out.println("\n\n\nStudents with last name Doe or first name daffy");
			displayStudents(theStudents);
			
			
			//query students where email like '%luv2code.com'
			theStudents= session.createQuery("from Student s where" + " s.email LIKE '%luv2code.com'").getResultList();
			
			//display the students
			System.out.println("\n\n\nStudents with email like 'luv2code.com'");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
