package com.place4code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml") //not required if name is hibernate.cfg.xml
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();		

		try {
			//start a transaction
			session.beginTransaction();
			
			//query objects
			List<Student> students = session.createQuery("from Student").getResultList();
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			displayStudents(students);
			
			students = session
					.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Klif'")
					.getResultList();
			displayStudents(students);
			
			students = session
					.createQuery("from Student s where s.email LIKE '%rom.com'")
					.getResultList();
			displayStudents(students);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		for(Student s: students) {
			System.out.println(s);
		}
	}

}
