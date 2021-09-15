package facades;

import dtos.EmployeeDTO;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.stream.events.DTD;
import java.util.List;

public class EmployeeFacade {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EmployeeFacade instance;


    private EmployeeFacade() {
    }

    public static EmployeeFacade getEmployeeFacade() {
        if (instance == null) {
            emf = Persistence.createEntityManagerFactory("pu");
            instance = new EmployeeFacade();
        }
        return instance;
    }

    public EmployeeDTO getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> queryEmployee = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
            queryEmployee.setParameter("id", id);
            Employee employee = queryEmployee.getSingleResult();
            return new EmployeeDTO(employee);
        } finally {
            em.close();
        }
    }

    public EmployeeDTO getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> queryEmployee = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            queryEmployee.setParameter("name", name);
            Employee employee = queryEmployee.getSingleResult();
            return new EmployeeDTO(employee);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getAllEmployee() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> queryEmployee = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> employee = queryEmployee.getResultList();
            return (List<EmployeeDTO>)(List<?>) employee;
        } finally {
            em.close();
        }

    }

    public Employee getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> queryEmployee = em.createQuery("SELECT MAX(e.salary) FROM Employee e", Employee.class);
            Employee employee = queryEmployee.getSingleResult();
            return employee;
        } finally {
            em.close();
        }
    }
    public void createEmployee(Employee employee){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
        } finally {
            em.close();
        }
    }

}
