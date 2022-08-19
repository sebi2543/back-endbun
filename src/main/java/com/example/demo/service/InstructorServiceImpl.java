package com.example.demo.service;

import com.example.demo.dto.InstructorDTO;
import com.example.demo.dto.InstructorDTOId;
import com.example.demo.entity.Instructor;
import com.example.demo.exception.InvalidFirstName;
import com.example.demo.exception.InvalidLastName;
import com.example.demo.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }


    public Optional<List<Instructor>> findAll() {
        return Optional.of(instructorRepository.findAll());
    }

    public Optional<Instructor> findById(InstructorDTOId instructor) {
        return instructorRepository.findById(instructor.getId());
    }

    public Optional<List<Instructor>> findByFullName(InstructorDTO instructor) {
        return instructorRepository.findByFirstNameAndLastName(instructor.getFirstName(), instructor.getLastName());
    }

    @Override
    public List<Instructor> getAllOrderByRating() {
       return instructorRepository.findAllOrderByRating();
    }

    public List<Instructor> getAll() {
        return this.findAll().orElseThrow(InvalidParameterException::new);
    }

    public Instructor getById(InstructorDTOId instructor) {
        return this.findById(instructor).orElseThrow(InvalidParameterException::new);
    }

    public List<Instructor> getByFullName(InstructorDTO instructor) {
        return this.findByFullName(instructor).orElseThrow(InvalidParameterException::new);
    }

    @Override
    public void delete(Instructor instructor) {
        instructorRepository.delete(instructor);
    }

    @Override
    public void checkInstructor(InstructorDTO instructor){
        if (instructor.getLastName().length()==0)
            throw new InvalidLastName();
        if (instructor.getFirstName().length()==0)
            throw new InvalidFirstName();
    }
}

