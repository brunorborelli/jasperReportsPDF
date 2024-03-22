package com.jasper.placeholder.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<byte[]> gerar(@RequestBody Aluno aluno) throws IOException {
        byte[] pdfBytes = this.service.gerar(aluno);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "certificado.pdf");
        headers.setContentLength(pdfBytes.length);
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
