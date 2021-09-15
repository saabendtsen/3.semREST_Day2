package rest;

import com.google.gson.Gson;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/employee")
public class EmployeeResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }


    @Path("/name/{name}")
    @GET
    @Produces("application/json")
    public String getEmployeeByName(@PathParam("name") String name) {
        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade();
        try {
            EmployeeDTO res = ef.getEmployeeByName(name);
            return new Gson().toJson(res);
        } catch (Exception e ){
            return new Gson().toJson(e.getMessage());
        }
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllEmployee() {
        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade();
        try {
            List<EmployeeDTO> res = ef.getAllEmployee();
            return new Gson().toJson(res);
        } catch (Exception e){
            return new Gson().toJson(e);
        }
    }



}