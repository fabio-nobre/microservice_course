package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JpaRepository<ModuleModel, UUID> - Extendendo JPA e informando a entidade
 */
public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {

}
