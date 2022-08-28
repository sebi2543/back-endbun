package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Course;

import com.example.demo.mapper.CourseMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    final CourseService courseService;
    final CourseMapper courseMapper;
    final InstructorService instructorService;

    @GetMapping(value = {"/all"})
    public List<BasicCourseDTO>showMainPage(){
       return courseService.showMainPage();
    }

    @PostMapping("/search")
    public List<BasicCourseDTO>showSuitableCourses(@Valid @RequestBody BasicCourseDTO course)  {
        return courseService.showSuitableCourses(course);
    }

    @PostMapping("/suggestion")
    public List<BasicCourseDTO>showAutoSuggestion(@Valid @RequestBody BasicCourseDTO course){
        return courseService.showAutoSuggestion(course);
    }

    @GetMapping("/{id}")
    public BasicCourseDTO showIdCourse(@PathVariable int id){
        return courseService.showIdCourse(id);
    }

    @PostMapping("/add")
    public void addCourse(@Valid @RequestBody BasicCourseDTO basicCourseDTO){
        courseService.add(basicCourseDTO);
    }

    @PostMapping("/{id}/assign-instructor")
    public void  assignInstructor(@PathVariable int id, @RequestBody long  instructorId){
     courseService.assignInstructor(id,instructorId);
    }

    @PutMapping("/{id}/update")
    public void updateCourse(@PathVariable int id,@Valid @RequestBody BasicCourseDTO basicCourseDTO){
       courseService.update(id,basicCourseDTO);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteCourse(@PathVariable int id){
        courseService.delete(id);
    }

    @GetMapping("/best")
    public List<SortCourseDTO>bestCourse(){
        return (courseMapper.toSort(courseService.getAllOrderByRatingDesc()));
    }

    @PostMapping("/{id}/give-grade")
    public void giveGrade(@PathVariable int id ,@RequestBody long grade){
        courseService.update(id,grade);
    }

    @GetMapping("/{id}/average")
    public Float calculateAverage(@PathVariable  long id){
         return  courseService.calculateAverage(id);
    }
}
