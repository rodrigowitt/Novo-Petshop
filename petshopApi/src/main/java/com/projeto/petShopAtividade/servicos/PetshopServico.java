package com.projeto.petShopAtividade.servicos;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.projeto.petShopAtividade.modelos.PetshopModelo;
import com.projeto.petShopAtividade.repositorios.PetshopRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PetshopServico {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final PetshopRepositorio petshopRepositorio;




    public PetshopServico(PetshopRepositorio petshopRepositorio) {
        this.petshopRepositorio = petshopRepositorio;
    }


    public PetshopModelo save(PetshopModelo petshopModelo) {
        return petshopRepositorio.save(petshopModelo);
    }

    public List<PetshopModelo> findAll() {
        return petshopRepositorio.findAll();
    }

    public Optional<PetshopModelo> findById(UUID id) {
        return petshopRepositorio.findById(id);
    }

    public void delete(PetshopModelo petshopModelo) {
        petshopRepositorio.delete(petshopModelo);
    }

    public List<PetshopModelo> ultimosDias(String dias, String status) {
        if(status.equals("TODOS")){
            status = "PREPARANDO', 'FINALIZADO', 'CANCELADO";
            System.out.println(status);
        }
        String sql = "SELECT *\n" +
                "FROM tb_petshop\n" +
                "WHERE entrada > current_date - interval '" + dias + "' day AND " +
                "status_tratamento IN ( '" + status+ "')";


        List <PetshopModelo>  resultado= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PetshopModelo.class));

        return resultado;
    }

    public List<PetshopModelo> maisRecentes() {
        String sql = "SELECT * FROM tb_petshop\n" +
                "WHERE EXTRACT(MONTH FROM entrada) = EXTRACT(MONTH FROM CURRENT_DATE)\n" +
                "ORDER BY entrada DESC ";



        List <PetshopModelo>  resultado= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PetshopModelo.class));
        return resultado;
    }






    public Double calcularSoma() {

        String sql = "SELECT SUM(valor) FROM tb_petshop\n" + "WHERE EXTRACT(MONTH FROM entrada) = EXTRACT(MONTH FROM CURRENT_DATE)";
        Double soma = jdbcTemplate.queryForObject(sql, Double.class);

        return soma != null ? soma : 0;
    }

    public void gerarPdf(String nome, String especie, String raca, String peso, String tratamento, String telefone, String responsavel, double valor) throws IOException {
        String entradaPdf = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        DecimalFormat df = new DecimalFormat("0.00");
        String valorConvertido = df.format(valor);
        String imgSrc = "Assets//Pets_1200x1200.png";
        ImageData data = ImageDataFactory.create(imgSrc);
        Image image1 = new Image(data);
        image1.scaleToFit(120,120);
        image1.setMarginLeft(230);
        String path = "C:\\Users\\rodri\\OneDrive\\Área de Trabalho\\Novo-Petshop\\petshopapp\\src\\assets\\Cadastro.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        document.setMargins(0,10,0,0);
        PdfFont fontLabel = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont fontTitulo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLDOBLIQUE);

        Paragraph p0 = new Paragraph("Cadastro do Pet").setFont(fontTitulo).setFontColor(Color.BLACK).setFontSize(16).setTextAlignment(TextAlignment.CENTER).setMarginBottom(20);
        Paragraph p1 = new Paragraph("Nome").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50);
        Paragraph p2 = new Paragraph(nome).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p3 = new Paragraph("Raça").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p4 = new Paragraph(raca).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p5 = new Paragraph("Tratamento").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p6 = new Paragraph(tratamento).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p7 = new Paragraph("Responsável").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p8 = new Paragraph(responsavel).setFont(font).setMarginTop(0).setMarginLeft(50);

        p1.add(new Tab());
        p1.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p1.add("Espécie").setFont(font).setFontColor(Color.GRAY);
        p2.add(new Tab());
        p2.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p2.add(especie).setFont(font);
        p3.add(new Tab());
        p3.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p3.add("Peso").setFont(font).setFontColor(Color.GRAY);
        p4.add(new Tab());
        p4.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p4.add(String.valueOf(peso)).setFont(font);
        p5.add(new Tab());
        p5.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p5.add("Telefone").setFont(font).setFontColor(Color.GRAY);
        p6.add(new Tab());
        p6.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p6.add(telefone).setFont(font);
        p7.add(new Tab());
        p7.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p7.add("Valor").setFont(font).setFontColor(Color.GRAY);
        p8.add(new Tab());
        p8.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p8.add(valorConvertido).setFont(font);
        Paragraph p9 = new Paragraph("Documento gerado em: "+ entradaPdf).setFont(font).setMarginTop(0).setMarginLeft(50).setMarginTop(30);

        document.add(image1);
        document.add(p0);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.add(p6);
        document.add(p7);
        document.add(p8);
        document.add(p9);
        document.close();

    }

//    List<Message> getLastThreeDays() {
//        // subtract 3 days from today
//        LocalDate threeDaysAgoDate = LocalDate.now().minusDays(3);
//        return this.petshopRepositorio.findAllWithDateAfter(threeDaysAgoDate);
//
//    }


}
