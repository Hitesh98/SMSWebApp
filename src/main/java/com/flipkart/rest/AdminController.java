package com.flipkart.rest;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.business.AdminService;
import com.flipkart.business.AdminServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
public class AdminController {

    private AdminService adminService = new AdminServiceImpl();

    @GET
    @Path("/AllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @POST
    @Path("/AddCourse")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) {
        System.out.println("Adding new course");
        System.out.println(course.getCourseId());
        System.out.println(course.getCourseName());
        System.out.println(course.getDescription());
        System.out.println(course.getFees());
        adminService.addNewCourse(course);
        String result = "Added "  + course + " to course catalog.";
        return Response.status(201).entity(result).build();
    }

    // Drop course
    @DELETE
    @Path("/DropCourse/{courseId}")
    public Response deleteCourse(@PathParam("courseId") int courseId) {
        Course course = new Course();
        course.setCourseId(courseId);
        adminService.deleteCourse(course);
        return Response.status(200).entity("Course " + " with course id " + courseId + " successfully deleted").build();
    }

    // Delete User
    @DELETE
    @Path("/DeleteUser/{userId}")
    public Response deleteUser(@PathParam("userId") int userId) {
        adminService.deleteUser(userId);
        return Response.status(200).entity("user " + " with user id " + userId + " successfully deleted").build();
    }

    @PUT
    @Path("/approveuser/{userId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveUser(@PathParam("userId") int userId) {
        adminService.approveUser(userId);
        return Response.status(200).entity("User " + "with id : " + userId + " was approved by admin.").build();
    }


    // Update professor for course
    @PUT
    @Path("/AssignCourseToProfessor/{courseId}/{professorId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void assignProfessorToCourse(@PathParam("professorId") int professorId, @PathParam("courseId") int courseId) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        adminService.assignCourseToProfessor(professor, courseId);
    }
}
