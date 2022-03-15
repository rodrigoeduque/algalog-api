package br.com.rodrigoeduque.algalog.domain.model;

import br.com.rodrigoeduque.algalog.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.ClienteId.class)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Column(name = "fone")
    @NotBlank
    @Size(max = 20)
    private String telefone;


}
