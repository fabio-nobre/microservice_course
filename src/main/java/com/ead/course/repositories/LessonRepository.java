package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JpaRepository<ModuleModel, UUID> - Extendendo JPA e informando a entidade
 */
public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

}
