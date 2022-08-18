package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.CourseDTOId;
import com.example.demo.dto.InstructorDTO;
import com.example.demo.dto.InstructorDTOId;
import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.exception.InvalidTitle;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.InstructorMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(value = "course")
@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    InstructorService instructorService;

    @Autowired
    InstructorMapper instructorMapper;

    @GetMapping(value = {"/all"})
    public HttpEntity<List<CourseDTO>>showMainPage(){
        List<Course>courses = courseService.getAll();
        return  new HttpEntity<>(courseMapper.coursesToDTOS(courses));
    }

    @PostMapping("/search")
    public HttpEntity<List<CourseDTO>>showSuitableCourses(@RequestBody CourseDTO course) throws InvalidTitle {
        courseService.checkTitle(course);
        List<Course>courses = courseService.getByTitle(course);
        return new HttpEntity<>(courseMapper.coursesToDTOS(courses));
    }

    @PostMapping("/suggestion")
    public HttpEntity<List<CourseDTO>>showAutoSuggestion(@RequestBody CourseDTO course){
        List<Course>courses = courseService.getByTitleLike(course);
        return new HttpEntity<>(courseMapper.coursesToDTOS(courses));
    }

    @GetMapping("/{id}")
    public HttpEntity<CourseDTO>showIdCourse(@PathVariable int id){
        Course course = courseService.getById(new CourseDTOId((long) id));
        return new HttpEntity<CourseDTO>(courseMapper.courseToDTO(course));
    }

    @PostMapping("/add")
    public HttpEntity<List<CourseDTO>>add(@RequestBody CourseDTO courseDTO) throws InvalidTitle {
        courseService.checkTitle(courseDTO);
        courseService.save(courseMapper.courseDTOToCourse(courseDTO));
        return new HttpEntity<>(courseMapper.coursesToDTOS(courseService.getAll()));
    }
    //NEFUNCTIONAL
    @PostMapping("/{id}/assign-instructor")
    public HttpEntity<Course> assignCourse(@PathVariable int id, @RequestBody InstructorDTOId instructorDTOId){
        CourseDTOId courseDTOId= new CourseDTOId((long) id);
        Course course=courseService.getById(courseDTOId);
        Instructor instructor=instructorService.getById(instructorDTOId);
        course.setInstructor(instructor);
        courseService.save(course);
        return new HttpEntity<>(courseService.getById(courseDTOId));
    }

}

