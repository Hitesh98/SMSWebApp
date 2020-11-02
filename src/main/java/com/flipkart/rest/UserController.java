package com.flipkart.rest;

import com.flipkart.bean.*;
import com.flipkart.business.CourseCatalogService;
import com.flipkart.business.CourseCatalogServiceImpl;
import com.flipkart.business.UserService;
import com.flipkart.business.UserServiceImpl;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserController {

    private UserService userService = new UserServiceImpl();
    private CourseCatalogService courseCatalogService = new CourseCatalogServiceImpl();

    @GET
    @Path("/get/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Admin getAdmin() {
        return new Admin();
    }

    @GET
    @Path("/get/professor")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor() {
        return new Professor();
    }

    @GET
    @Path("/get/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent() {
        return new Student();
    }

    @GET
    @Path("/courses/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        return courseCatalogService.getAllCourses();
    }

    @POST
    @Path("/register/student/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Student student, @PathParam("password") String password) {
        boolean reg = userService.registerUser(student, password);
        String result = "Student : " + student.getName() + " was Registered Successfully.";
        if (!reg) {
            result = "Unable to register student. Try again!";
            return Response.status(401).entity(result).build();
        }
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/register/professor/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerProfessor(Professor professor, @PathParam("password") String password) {
        boolean reg = userService.registerUser(professor, password);
        String result = "Professor : " + professor.getName() + " was Registered Successfully.";
        if (!reg) {
            result = "Unable to register professor. Try again!";
            return Response.status(401).entity(result).build();
        }
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/register/admin/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerAdmin(Admin admin, @PathParam("password") String password) {
        boolean reg = userService.registerUser(admin, password);
        String result = "Admin : " + admin.getName() + " was Registered Successfully.";
        if (!reg) {
            result = "Unable to register admin. Try again!";
            return Response.status(401).entity(result).build();
        }
        return Response.status(201).entity(result).build();
    }


}
