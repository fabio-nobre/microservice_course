package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

}
