package com.employee.demoproject.service;

import com.employee.demoproject.dto.PaySlipDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;

public class PDFExporter {
    private PaySlipDTO paySlipDTO;

    public PDFExporter(PaySlipDTO paySlipDTO){
        this.paySlipDTO = paySlipDTO;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        cell.setPhrase(new Phrase("Employee ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Department", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NetSalary", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Month", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
            table.addCell(String.valueOf(paySlipDTO.getEmpId()));
            table.addCell(paySlipDTO.getEmpName());
            table.addCell(paySlipDTO.getDeptName());
            table.addCell(paySlipDTO.getDesignation());
            table.addCell(String.valueOf(paySlipDTO.getNetSalary()));
            table.addCell(paySlipDTO.getMonth());
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(BaseColor.BLUE.BLUE);

        Paragraph p = new Paragraph("Pay Slip Of Employee", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 2.0f,3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
