package com.invoiceapp.invoice_API.service;

import com.invoiceapp.invoice_API.dto.InvoiceDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceFieldExtractionService {

    public InvoiceDTO extractFields(String ocrText) {

        String invoiceNumber = extractInvoiceNumber(ocrText);
        String vendorName = extractVendorName(ocrText);
        String invoiceDate = extractInvoiceDate(ocrText);
        String dueDate = extractDueDate(ocrText);
        String totalAmount = extractTotalAmount(ocrText);

        String daysLeft = calculateDaysLeft(dueDate);

        return InvoiceDTO.builder()
                .invoiceId(invoiceNumber)
                .vendorName(vendorName)
                .totalAmount(totalAmount)
                .dueDate(dueDate)
                .daysLeft(daysLeft)
                .build();
    }

    // Each field extractor method will be added below
    private String extractInvoiceNumber(String text) {
        Pattern pattern = Pattern.compile("(Invoice\\s*(No|Number)[:\\-]?)\\s*(\\S+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(3).trim();
        }
        return "Not Found";
    }

    private String extractVendorName(String text) {
        Pattern pattern = Pattern.compile("Vendor[:\\-]?\\s*(.+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "Not Found";
    }

    private String extractInvoiceDate(String text) {
        Pattern pattern = Pattern.compile("(Date|Invoice Date)[:\\-]?\\s*([0-9]{1,2}[-/\\s][A-Za-z]+[-/\\s][0-9]{4})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(2).trim();
        }
        return "Not Found";
    }

    private String extractDueDate(String text) {
        Pattern pattern = Pattern.compile("Due\\s*(Date)?[:\\-]?\\s*([0-9]{1,2}[-/\\s][A-Za-z]+[-/\\s][0-9]{4})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(2).trim();
        }
        return "Not Found";
    }

    private String extractTotalAmount(String text) {
        Pattern pattern = Pattern.compile("(Total Amount|Total)[:\\-]?\\s*[₹$]?\\s*([0-9,]+\\.?[0-9]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(2).trim();
        }
        return "Not Found";
    }

    private String calculateDaysLeft(String dueDate) {
        if ("Not Found".equals(dueDate)) {
            return "Unknown";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMMM-yyyy"); // e.g. 10-July-2025
            LocalDate due = LocalDate.parse(dueDate, formatter);
            long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), due);
            if(daysBetween==0){
                return "Today";
            }else if(daysBetween < 0){
                return "Overdue";
            }else{
                return String.valueOf(daysBetween);
            }
        } catch (Exception e) {
            return "Invalid Date Format";
        }
    }
}
