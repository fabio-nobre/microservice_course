package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data                                       // Lombok - Inserinddo gets, sets ... sem precisar cria-los
@JsonInclude(JsonInclude.Include.NON_NULL)  // Na hora de serializar os campos com valores nulos não retornam
@Entity                                     // Entidade JPA
@Table(name = "TB_MODULES")                 //Nome da tabela
public class ModuleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID moduleId;// Chave primaria

    @Column(nullable = false, length = 150) // Coluna da tabela, obrigatorio, numero de caracteres
    private String title;

    @Column(nullable = false, length = 250) // Coluna da tabela, obrigatorio, numero de caracteres
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false) // Coluna da tabela, obrigatorio
    private LocalDateTime creationDate;

    /**
     * - Relacionamentos
     * - Cada módulo vai pertencer a um determinado curso
     * - Módulo está vinculado a um determinado curso
     *
     * -  @ManyToOne: Vários módulos para um curso
     * - optional = false: Existência obrigatória
     *  fetch = FetchType.LAZY: Carregamento tardio. Executa consultas apenas se necessario
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CourseModel course;

    /**
     * - @OneToMany: Um Modulo para vários Lessons
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<LessonModel> lessons;
}
