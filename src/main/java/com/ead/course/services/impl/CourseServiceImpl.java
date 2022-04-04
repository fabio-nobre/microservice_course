package com.ead.course.services.impl;

import com.ead.course.repositories.CourseRepository;
import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Service: Informar ao spring que as classes são Beans para serem gerenciadas
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
}
