package com.productmanagementsystem.services;


//import com.example.productmanagementsystem.model.Product;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.productmanagementsystem.model.Product;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class DatabasePDFService {
	 
    public static ByteArrayInputStream ProductPDFReport(List<Product> products) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
 
        try {
 
            PdfWriter.getInstance(document, out);
            document.open();
 
            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22);
            Paragraph para = new Paragraph("Product List", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
 
            PdfPTable table = new PdfPTable(5);
            // Add PDF Table Header ->
            Stream.of("Product Id", "Product Name", "Product Category", "Product Price", "Available Stock").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.COURIER_OBLIQUE);
                header.setBackgroundColor(Color.ORANGE);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });
 
            for (Product product : products) {
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(product.getProduct_id())));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
 
                PdfPCell firstNameCell = new PdfPCell(new Phrase(product.getProduct_name()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);
 
                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(product.getProduct_category())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
 
                PdfPCell cat = new PdfPCell(new Phrase(String.valueOf(product.getProduct_price())));
                cat.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cat.setHorizontalAlignment(Element.ALIGN_CENTER);
                cat.setPaddingRight(4);
                table.addCell(cat);
 
                PdfPCell stock = new PdfPCell(new Phrase(String.valueOf(product.getStock())));
                stock.setVerticalAlignment(Element.ALIGN_MIDDLE);
                stock.setHorizontalAlignment(Element.ALIGN_CENTER);
                stock.setPaddingRight(4);
                table.addCell(stock);



            }
            document.add(table);
 
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
 
        return new ByteArrayInputStream(out.toByteArray());
    }
}
