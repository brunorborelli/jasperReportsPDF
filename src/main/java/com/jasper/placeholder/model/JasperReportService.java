package com.jasper.placeholder.model;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JasperReportService {
    public static final Logger LOGGER = LoggerFactory.getLogger(JasperReportService.class);
    public static final String CERTIFICADOS = "classpath:jasperReports/certificado/";
    public static final String IMAGEBG = "classpath:jasperReports/img/jasper-img.png";
    public static final String ARQUIVOJRXML = "cert.jrxml";

    public byte[] gerar(Aluno aluno) throws IOException {

        byte[] imagebg = this.loadImage(IMAGEBG);

        Map<String,Object> params = new HashMap<>();
        params.put("nome", aluno.getNome());
        params.put("curso", aluno.getCurso());
        params.put("cargaHoraria", aluno.getCargaHoraria());
        params.put("dataInicioCurso", aluno.getDataInicioCurso());
        params.put("dataTerminoCruso", aluno.getDataTerminoCurso());
        params.put("imageJasper",imagebg);

        String pathAbsolute = getAbsolutePath();
        try{
            JasperReport report = JasperCompileManager.compileReport(pathAbsolute);
            LOGGER.info("report compilado: {}", pathAbsolute);
            JasperPrint print = JasperFillManager.fillReport(report, params,new JREmptyDataSource());
            LOGGER.info("Jasper print");
            return JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadImage(String imagebg) throws IOException {
        String image = ResourceUtils.getFile(imagebg).getAbsolutePath();
        File file = new File(image);
        try (InputStream inputStream = new FileInputStream(file)){
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAbsolutePath() throws FileNotFoundException {
        return ResourceUtils.getFile(CERTIFICADOS+ARQUIVOJRXML).getAbsolutePath();
    }
}

