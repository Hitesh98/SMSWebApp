package com.flipkart.rest;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.exception.CourseNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/student")
public class StudentController {

    private StudentService studentService = new StudentServiceImpl();
    private CourseCatalogService courseCatalogService = new CourseCatalogServiceImpl();

    // View registered courses of particular student
    @GET
    @Path("/courses/registered/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getRegisteredCourses(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.viewRegisteredCourses(student);
    }

    // View Report card of particular student
    @GET
    @Path("ReportCard/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> viewGrades(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.viewReportCard(student);
    }

    // Get total courses a student has registered for
    @GET
    @Path("courses/registered/count/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getNumberOfCoursesRegistered(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.getNumberOfCoursesRegistered(student);
    }


    //get total fees to be paid by the student
    @GET
    @Path("fees/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getTotalFee(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.getTotalFee(student);
    }

    // select course for particular student
    @POST
    @Path("/SelectCourse/{studentId}/{courseId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourses(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) {
        System.out.println("hit post service");
        System.out.println("value of course id :" + courseId);
        System.out.println("value of student id :" + studentId);
        String result = "Registered " + studentId + " for " + courseId;
        Student student = new Student();
        student.setStudentId(studentId);
        studentService.selectCourse(student, courseId);
        return Response.status(201).entity(result).build();
    }

    // make payment
    @POST
    @Path("/payment/{studentId}/{paymentMethod}/{fees}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makePayment(@PathParam("studentId") int studentId, @PathParam("paymentMethod") int paymentMethod, @PathParam("fees") int fees) {
        System.out.println("hit post service");
        System.out.println("value of student id :" + studentId);
        System.out.println("value of payment Method :" + paymentMethod);
        System.out.println("value of fees :" + fees);
        Student student = new Student();
        student.setStudentId(studentId);
        String result = "Made fee Payment for student with student Id " + studentId;
        studentService.makePayment(student, paymentMethod, fees);
        return Response.status(201).entity(result).build();
    }

    // drop course for particular student
    @DELETE
    @Path("/DropCourse/{studentId}/{courseId}")
    public Response deleteCustomer(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) throws CourseNotFoundException {
        Course course = new Course();
        Student student = new Student();
        course.setCourseId(courseId);
        student.setStudentId(studentId);
        studentService.dropCourse(course, student);
        return Response.status(200).entity("The course " + courseId + " for student " + studentId + "deleted").build();
    }
}