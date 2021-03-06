package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml") //not required if name is hibernate.cfg.xml
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();		

		try {
			
			//update one record
			session.beginTransaction();
			//Student student = session.get(Student.class, 1); //1 = student id for example
			//System.out.println("Deleting: " + student);
			//session.delete(student);
			
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
