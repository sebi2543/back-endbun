package com.example.demo.mapper;

import com.example.demo.dto.InstructorDTO;
import com.example.demo.entity.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

//    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);
    InstructorDTO InstructorToDTO(Instructor instructor);
}
