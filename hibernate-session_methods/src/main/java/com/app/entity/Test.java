package com.app.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Test {

	private static SessionFactory sessionFactory = null;

	static {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public void get() {
		Session session=sessionFactory.openSession();
		Employee employee1=(Employee) session.get(Employee.class, 1);
		System.out.println(employee1);
		Employee employee2=(Employee) session.get(Employee.class, 2);
		System.out.println(employee2);
	}
	
	public void load() {
		Session session=sessionFactory.openSession();
		Employee employee1=(Employee) session.load(Employee.class, 1);
		System.out.println(employee1.getId());
		System.out.println(employee1.getName());
		System.out.println(employee1.getMobile());
		System.out.println(employee1.getSalary());
		//System.out.println(employee1);
		Employee employee2=(Employee) session.load(Employee.class, 1);
		System.out.println(employee2);
		session.flush();
	}
	
	public void saveAndPersist() {
		Session session=sessionFactory.openSession();
		Employee employee=new Employee();
		employee.setName("Rutuja");
		employee.setMobile("123456");
		employee.setSalary(30500d);
		
		/*Integer mxid= (Integer) session.save(employee);
		System.out.println(mxid);
		session.beginTransaction().commit();
		*/
		session.persist(employee);
		session.beginTransaction().commit();
	
	}
	
	public void saveOrUpdate() {
		Session session=sessionFactory.openSession();
		Employee employee=new Employee();
		//employee.setId(4);
		employee.setName("Rutuja");
		employee.setMobile("123542");
		employee.setSalary(40000d);
		
		session.saveOrUpdate(employee);
		session.beginTransaction().commit();
	}
	
	public void merge() {
		Session session=sessionFactory.openSession();
		Employee employee=(Employee) session.get(Employee.class,4);
		employee.setName("pqr");
		//session.close();
		
		Session session2=sessionFactory.openSession();
		Employee employee2=(Employee) session2.get(Employee.class, 4);
		employee.setSalary(45000d);
		
		session2.merge(employee);
		session.beginTransaction().commit();
	}
	
	public static void main(String[] args) {
		Test t=new Test();
		//t.get();
		t.load();
		//t.saveAndPersist();
		//t.saveOrUpdate();
		//t.merge();
	}

}
