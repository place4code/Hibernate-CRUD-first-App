package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
					
					//create 3 student object
					System.out.println("create 3 objects...");
					Student student1 = new Student("Jamie", "Doe", "jamie@rom.com");
					Student student2 = new Student("Mark", "Public", "mark@rom.com");
					Student student3 = new Student("Klif", "Apple", "klif@rom.com");
				
					//start a transaction
					session.beginTransaction();
					//save object
					System.out.println("saving objects...");
					session.save(student1);
					session.save(student2);
					session.save(student3);
					//commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				} finally {
					factory.close();
				}

	}

}
