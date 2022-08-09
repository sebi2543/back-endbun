package com.example.demo.service;


import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@ToString
public class InstructorRepositoryServiceImpl implements InstructorRepositoryService{

    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;

    public void save(Instructor instructor){
        instructorRepository.save(instructor);
    }

    public List<Instructor> showAll(){
         return  instructorRepository.findAll();
    }

    public void delete(Instructor instructor){
        instructorRepository.delete(instructor);
    }

    public Optional<Instructor> findById(int id){
        return instructorRepository.findById((long) id);
    }

    public void addCourse(Course course,Instructor instructor) throws  InvalidDataAccessApiUsageException {
        Optional<Instructor> instructorOptional = instructorRepository.findById(instructor.getId());
            if (instructorOptional.isPresent()) {
                course.setInstructor(instructorOptional.get());
                courseRepository.save(course);
            }
    }
   public  ArrayList<Instructor> findByFullName(String firstname, String lastname){
       return instructorRepository.findByFullName(firstname,lastname);
   }
}
