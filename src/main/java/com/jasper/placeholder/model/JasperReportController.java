package com.jasper.placeholder.model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/jasper-report")
public class JasperReportController {

    private final JasperReportService service;

    public JasperReportController(JasperReportService service) {
        this.service = service;
    }

    @PostMapping("/gerar-certificado")
    public void gerar(@RequestBody Aluno aluno) throws IOException {
        this.service.gerar(aluno);
    }

}
