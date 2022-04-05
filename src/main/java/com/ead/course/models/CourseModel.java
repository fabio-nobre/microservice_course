package com.ead.course.models;


import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "TB_COURSES")                 //Nome da tabela
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // id da entidade
    @GeneratedValue(strategy = GenerationType.AUTO) //Gerando ID automaticamente
    private UUID courseId;

    @Column(nullable = false, length = 150) // Coluna da tabela, obrigatorio, numero de caracteres
    private String name;

    @Column(nullable = false, length = 250) // Coluna da tabela, obrigatorio, numero de caracteres
    private String description;

    @Column // Coluna da tabela
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(nullable = false)
    private UUID userInstructor;

    /**
     * - Relacionamentos
     * - Um curso pode ter vários módulos
     *
     * - @OneToMany: Um curso para vários módulos
     * - mappedBy = "course": Atributo dentro de ModuleModel que será Chave estrangeira
     * - @JsonProperty: Tipo de acesso e serialização
     *  -obs:
     *      1-  Utilizando Set<> e não List<>:
     *          Set: Não é ordenada e não permite duplicação
     *          list: Ordenada e permite duplicação
     *      2-  Várias associações/mapeamento dentro de uma unica entidade:
     *          Quando em uma única consulta trazer todos esse relacionamentos ao utilizar
     *          List<> o Hibernate só retornará a primeira lista ignorando as demais, para
     *          corrigir isso utilizar o Set<>.
     *      3-    Em operações de alteração quando utilizado o list<> o Hibernate poderá ter problemas perfomance
     *      4-  Evitar utilizar relacionamento UNIDIRECIONAL, pois caso não seja explicitado o Hibernate irá realizar
     *          de qualque forma gerando query e tabelas extras desnecessarias afetando a perfornance
     *
     *   fetch = FetchType.LAZY: Carregamento tardio. Executa consultas apenas se necessario.
     *   @Fetch(FetchMode.SUBSELECT): Será realiza uma consulta para curso e outra para todos os modulos desse curso
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)

    @Fetch(FetchMode.SUBSELECT)
    private Set<ModuleModel> modules;

}
