package MAIN;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategory;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main2 {

    private static EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();

    public static void main(String[] args) {

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Department department = new Department();
        department.setName("Finance");

        JobCategory jobCategory = new JobCategory();
        jobCategory.setName("Accountant");

        Employee employee1 = new Employee();
        employee1.setFirstName("Alina");
        employee1.setLastName("Dobre");
        employee1.setJobCategory(jobCategory);
        employee1.setDepartment(department);
        employee1.setManager(true);
        employee1.setStartDate(LocalDate.parse("2017-09-18", formatter));
        employee1.setActive(true);
        employee1.setAddress("Str. Independentei nr. 78");
        employee1.setCp("020551");
        employee1.setTelephone("0722201036");
        employee1.setEmail("dobrealina90@yahoo.com");
        employee1.setBirthday(LocalDate.parse("1990-10-15", formatter));
        employee1.setNoChildren(1);
        employee1.setSalary(5360);
        employee1.setStudies("Bucharest University of Economic Studies");
        employee1.setSocialSecurityNumber("955246887");
        employee1.setHasDrivingLicense(true);

        Employee employee2 = new Employee();
        employee2.setFirstName("Elena");
        employee2.setLastName("Radu");
        employee2.setJobCategory(jobCategory);
        employee2.setDepartment(department);
        employee2.setManager(false);
        employee2.setStartDate(LocalDate.parse("2020-04-11", formatter));
        employee2.setActive(true);
        employee2.setAddress("Str. Doamna Ghica nr. 108");
        employee2.setCp("211622");
        employee2.setTelephone("0765149327");
        employee2.setEmail("elena_radu@gmail.com");
        employee2.setBirthday(LocalDate.parse("1997-02-12", formatter));
        employee2.setNoChildren(0);
        employee2.setSalary(4850);
        employee2.setStudies("Bucharest University of Economic Studies");
        employee2.setSocialSecurityNumber("482671168");
        employee2.setHasDrivingLicense(false);


        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        department.setEmployees(employees);
        jobCategory.setEmployees(employees);

        entityTransaction.begin();

        entityManager.persist(department);
        entityManager.persist(jobCategory);

        entityManager.persist(employee1);
        entityManager.persist(employee2);


        entityTransaction.commit();
        entityManager.close();*/

        //findEmployeeById(1);

        //listEmployeesByName();

        //listEmployeesBySalary();

        findEmployeesByDepartmentId(2);

        //updateProductPrice(2, 5.62);

        //deleteEmployee(9);

        //findDepartmentById(1);



        entityManager.close();
    }

    public static void findEmployeeById(int id){

        Employee e = entityManager.find(Employee.class, id);

        if(e != null) {
            System.out.println();
            System.out.println(e.toString());
        }
        else{
            System.out.println("Could not find employee!");
        }

    }

    public static void listEmployees(){

        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        List<Employee> resultList = query.getResultList();

        if(resultList.size() != 0) {
            for (Employee e : resultList) {
                System.out.println();
                System.out.println(e.toString());
            }
        }
        else{
            System.out.println("Could not find any records!");
        }
    }

    public static void listEmployeesByName(){

        Query query = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.lastName");
        List<Employee> resultList = query.getResultList();

        if(resultList.size() != 0) {
            for (Employee e : resultList) {
                System.out.println();
                System.out.println(e.toString());
            }
        }
        else{
            System.out.println("Could not find any records!");
        }
    }

    public static void listEmployeesBySalary(){

        Query query = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC");
        List<Employee> resultList = query.getResultList();

        if(resultList.size() != 0) {
            for (Employee e : resultList) {
                System.out.println();
                System.out.println(e.toString());
            }
        }
        else{
            System.out.println("Could not find any records!");
        }
    }

    public static void findEmployeesByDepartmentId(int id){

        Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.id = :id");
        query.setParameter("id", id);
        List<Employee> resultList = query.getResultList();

        if(resultList.size() != 0) {
            for (Employee e : resultList) {
                System.out.println();
                System.out.println(e.toString());
            }
        }
        else{
            System.out.println("Could not find any records!");
        }

    }

    /*public static void updateProductPrice(int id, double price){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Product p = entityManager.find(Product.class, id);

        if(p != null) {

            entityTransaction.begin();
            p.setPrice(price);
            entityTransaction.commit();
        }
        else{
            System.out.println("Could not find product!");
        }

        entityManager.close();

    }

    public static void deleteEmployee(int id){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Employee e = entityManager.find(Employee.class, id);

        if(e != null) {

            entityTransaction.begin();
            entityManager.remove(e);
            entityTransaction.commit();
        }
        else{
            System.out.println("Could not find employee!");
        }
    }

    public static void findDepartmentById(int id){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Department d = entityManager.find(Department.class, id);

        if(d != null) {
            System.out.println(d.toString());
        }
        else{
            System.out.println("Could not find department!");
        }
    }*/
}
