package com.flipkart.rest;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professor")
public class ProfessorController {

    private static ProfessorService professorService = new ProfessorServiceImpl();

    // Get students being tutored by particular professor
    @GET
    @Path("/GetStudents/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewStudents(@PathParam("professorId") int professorId) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        return professorService.viewAssignedStudents(professor);
    }

    // Get students being tutored by particular professor
    @GET
    @Path("/GetCourses/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourses(@PathParam("professorId") int professorId) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        return professorService.getAssignedCourse(professor);
    }

    //Post student grades
    @POST
    @Path("GradeStudent/{professorId}/{studentId}/{courseId}/{grade}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gradeStudent(@PathParam("professorId") int professorId, @PathParam("studentId") int studentId,
                                 @PathParam("courseId") int courseId, @PathParam("grade") String grade) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        professorService.recordStudentGrades(professor, studentId, grade, courseId);
        String result = "Grades for student Id " + studentId + " with course " + courseId + "updated with grade " + grade;
        return Response.status(201).entity(result).build();
    }
}