package MAIN;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategory;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2 {

    private static EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();
    private static Logger logger = Logger.getLogger(Main2.class.getName());

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

        Scanner in = new Scanner(System.in);
        options();
        int option = in.nextInt();


        while(true) {

            switch(option) {
                case 1:
                    System.out.println("Introduceti ID-ul: ");
                    int idEmployee = in.nextInt();
                    logger.log(Level.INFO, "Afisare angajat dupa ID");
                    findEmployeeById(idEmployee);
                    break;
                case 2:
                    logger.log(Level.INFO, "Afisare angajati");
                    listEmployees();
                    break;
                case 3:
                    logger.log(Level.INFO, "Afisare angajati sortati dupa nume");
                    listEmployeesByName();
                    break;
                case 4:
                    logger.log(Level.INFO, "Afisare angajati sortati dupa salariu descrescator");
                    listEmployeesBySalary();
                    break;
                case 5:
                    System.out.println("Introduceti ID-ul: ");
                    int idDepartment = in.nextInt();
                    logger.log(Level.INFO, "Afisare angajati dintr-un departament");
                    findEmployeesByDepartmentId(idDepartment);
                    break;
                case 6:
                    logger.log(Level.INFO, "Afisare departamente");
                    listDepartments();
                    break;
                case 7:
                    logger.log(Level.INFO, "Afisare categorii job-uri");
                    listJobCategories();
                    break;
                case 8:
                    System.out.println("Introduceti ID-ul: ");
                    int idEmployeeDelete = in.nextInt();
                    logger.log(Level.INFO, "Stergere angajat");
                    deleteEmployee(idEmployeeDelete);
                    break;
                case 9:
                    System.out.println("Introduceti ID-ul: ");
                    int idDepartmentDelete = in.nextInt();
                    logger.log(Level.INFO, "Stergere departament");
                    deleteDepartment(idDepartmentDelete);
                    break;
                case 10:
                    System.out.println("Introduceti ID-ul: ");
                    int idJobCategoryDelete = in.nextInt();
                    logger.log(Level.INFO, "Stergere categorie job");
                    deleteEmployee(idJobCategoryDelete);
                    break;
                case 11:
                    System.out.println("Introduceti ID-ul: ");
                    int idEmployeeUpdate = in.nextInt();
                    logger.log(Level.INFO, "Actualizare angajat");
                    updateEmployee(idEmployeeUpdate);
                    break;
                case 12:
                    System.out.println("Introduceti ID-ul: ");
                    int idDepartmentUpdate = in.nextInt();
                    logger.log(Level.INFO, "Actualizare departament");
                    updateDepartment(idDepartmentUpdate);
                    break;
                case 13:
                    System.out.println("Introduceti ID-ul: ");
                    int idJobCategoryUpdate = in.nextInt();
                    logger.log(Level.INFO, "Actualizare categorie job");
                    updateJobCategory(idJobCategoryUpdate);
                    break;
                case 0:
                    entityManager.close();
                    return;
                default:
                    logger.log(Level.WARNING, "Incorrect value!");
            }

            options();
            option = in.nextInt();

        }



    }

    public static void findEmployeeById(int id){

        Employee e = entityManager.find(Employee.class, id);

        if(e != null) {
            System.out.println();
            System.out.println(e.toString());
        }
        else{
            logger.log(Level.INFO, "Could not find employee!");
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
            logger.log(Level.INFO, "Could not find any records!");
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
            logger.log(Level.INFO, "Could not find any records!");
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
            logger.log(Level.INFO, "Could not find any records!");
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
            logger.log(Level.INFO, "Could not find any records!");
        }

    }

    public static void listDepartments(){

        Query query = entityManager.createQuery("SELECT d FROM Department d");
        List<Department> resultList = query.getResultList();

        if(resultList.size() != 0) {
            for (Department d : resultList) {
                System.out.println();
                System.out.println(d.toString());
            }
        }
        else{
            logger.log(Level.INFO, "Could not find any records!");
        }
    }

    public static void listJobCategories(){

        Query query = entityManager.createQuery("SELECT j FROM JobCategory j");
        List<JobCategory> resultList = query.getResultList();

        if(resultList.size() != 0) {
            for (JobCategory j : resultList) {
                System.out.println();
                System.out.println(j.toString());
            }
        }
        else{
            logger.log(Level.INFO, "Could not find any records!");
        }
    }

    public static void deleteEmployee(int id){

        Employee e = entityManager.find(Employee.class, id);

        if(e != null) {

            entityTransaction.begin();
            entityManager.remove(e);
            entityTransaction.commit();
            logger.log(Level.INFO, "Stergere efectuata!");
        }
        else{
            logger.log(Level.INFO, "Could not find employee!");
        }
    }

    public static void deleteDepartment(int id){

        Department d = entityManager.find(Department.class, id);

        if(d != null) {

            entityTransaction.begin();
            entityManager.remove(d);
            entityTransaction.commit();
            logger.log(Level.INFO, "Stergere efectuata!");
        }
        else{
            logger.log(Level.INFO, "Could not find department!");
        }
    }

    public static void deleteJobCategory(int id){

        JobCategory j = entityManager.find(JobCategory.class, id);

        if(j != null) {

            entityTransaction.begin();
            entityManager.remove(j);
            entityTransaction.commit();
            logger.log(Level.INFO, "Stergere efectuata!");
        }
        else{
            logger.log(Level.INFO, "Could not find job category!");
        }
    }

    public static void updateDepartment(int id){

        Department d = entityManager.find(Department.class, id);

        if(d != null) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("New name for department: ");
                String name = scanner.nextLine();
                entityTransaction.begin();
                d.setName(name);
                entityTransaction.commit();
                scanner.close();
                logger.log(Level.INFO, "Actualizare efectuata!");
            } catch (Exception e){
                entityTransaction.rollback();
                logger.log(Level.SEVERE, "Failed operation!");
            }
        }
        else{
            logger.log(Level.INFO, "Could not find department!");
        }

    }

    public static void updateJobCategory(int id){

        JobCategory j = entityManager.find(JobCategory.class, id);

        if(j != null) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("New name for job category: ");
                String name = scanner.nextLine();
                entityTransaction.begin();
                j.setName(name);
                entityTransaction.commit();
                scanner.close();
                logger.log(Level.INFO, "Actualizare efectuata!");
            } catch (Exception e){
                entityTransaction.rollback();
                logger.log(Level.SEVERE, "Failed operation!");
            }
        }
        else{
            logger.log(Level.INFO, "Could not find job category!");
        }

    }

    public static void updateEmployee(int id){

        Employee e = entityManager.find(Employee.class, id);

        if(e != null) {

            Scanner scanner = new Scanner(System.in);
            employeeOptions();
            int option = scanner.nextInt();
            boolean ok = true;

            while(ok) {

                switch(option) {
                    case 1:
                        try {
                            System.out.println("New last name: ");
                            String lastName = scanner.nextLine();
                            entityTransaction.begin();
                            e.setLastName(lastName);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("New first name: ");
                            String firstName = scanner.nextLine();
                            entityTransaction.begin();
                            e.setFirstName(firstName);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Is manager? ");
                            boolean isManager = scanner.nextBoolean();
                            entityTransaction.begin();
                            e.setManager(isManager);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("New address: ");
                            String address = scanner.nextLine();
                            entityTransaction.begin();
                            e.setAddress(address);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("New number of children: ");
                            int noChildren = scanner.nextInt();
                            entityTransaction.begin();
                            e.setNoChildren(noChildren);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 6:
                        try {
                            System.out.println("New salary: ");
                            double salary = scanner.nextDouble();
                            entityTransaction.begin();
                            e.setSalary(salary);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 7:
                        try {
                            System.out.println("Has driving license? ");
                            boolean hasDrivingLicense = scanner.nextBoolean();
                            entityTransaction.begin();
                            e.setHasDrivingLicense(hasDrivingLicense);
                            entityTransaction.commit();
                            logger.log(Level.INFO, "Actualizare efectuata!");
                        }catch (Exception ex){
                            entityTransaction.rollback();
                            logger.log(Level.SEVERE, "Failed operation!");
                        }
                        break;
                    case 0:
                        ok = false;
                        break;
                    default:
                        logger.log(Level.WARNING, "Incorrect value!");
                    }
                    if(ok) {
                        employeeOptions();
                        option = scanner.nextInt();
                    }

                }

                scanner.close();

        }
        else{
            logger.log(Level.INFO, "Could not find employee!");
        }

    }

    public static void employeeOptions(){
        System.out.println("What would you want to update?");
        System.out.println("1) Nume\n2) Prenume\n" +
                "3) Este manager?\n4) Adresa\n" +
                "5) Numar copii\n6) Salariu\n7) Are permis de conducere?\n" +
                "0) Iesire\n");
    }

    public static void options(){
        System.out.println();
        System.out.println("1) Afisare angajat dupa ID\n2) Afisare angajati\n" +
                "3) Afisare angajati dupa nume\n4) Afisare angajati dupa salariu descrescator\n" +
                "5) Afisare angajati dintr-un departament\n6) Afisare departamente\n7) Afisare categorii job-uri\n" +
                "8) Stergere angajat\n9) Stergere departament\n10) Stergere categorie job\n" +
                "11) Actualizare angajat\n12) Actualizare departament\n13) Actualizare categorie job\n" +
                "0) Iesire\n");
    }
}
