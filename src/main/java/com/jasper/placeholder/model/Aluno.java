package com.jasper.placeholder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    private String nome;
    private String curso;
    private Integer cargaHoraria;
    private Date dataInicioCurso;
    private Date dataTerminoCurso;

}
