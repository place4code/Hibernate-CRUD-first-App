package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml") //not required if name is hibernate.cfg.xml
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();		

		try {
			//use the session object to save Java object
			
			//create an object
			System.out.println("create a object...");
			Student student = new Student("Kaczor", "Donald", "donald@rom.com");
			//start a transaction
			session.beginTransaction();
			//save object
			System.out.println("saving object...");
			session.save(student);
			//commit transaction
			session.getTransaction().commit();
			
			//find put the object's id
			System.out.println("Saved, ID: " + student.getId());
			//get a new session, and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//retrieve object based on the id
			System.out.println("\nGetting object with ID: " + student.getId());
			Student object = session.get(Student.class, student.getId());
			System.out.println("Get complete: " + object);
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
