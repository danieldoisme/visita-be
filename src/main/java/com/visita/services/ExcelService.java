package com.visita.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.visita.dto.response.TransactionResponse;

@Service
public class ExcelService {

    public ByteArrayInputStream exportTransactionsToExcel(List<TransactionResponse> transactions) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Transactions");

            // Header
            Row headerRow = sheet.createRow(0);
            String[] columns = { "ID", "User Name", "Email", "Amount", "Date", "Status", "Method" };

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Data
            int rowIdx = 1;
            for (TransactionResponse tx : transactions) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(tx.getTransactionId());
                row.createCell(1).setCellValue(tx.getUserName());
                row.createCell(2).setCellValue(tx.getUserEmail());
                row.createCell(3).setCellValue(tx.getAmount().doubleValue());
                row.createCell(4).setCellValue(tx.getPaymentDate().toString());
                row.createCell(5).setCellValue(tx.getStatus());
                row.createCell(6).setCellValue(tx.getPaymentMethod());
            }

            // Auto-size columns
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }
}
