package com.ead.course.services.impl;

import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Service: Informar ao spring que as classes são Beans para serem gerenciadas
 */
@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;
}

