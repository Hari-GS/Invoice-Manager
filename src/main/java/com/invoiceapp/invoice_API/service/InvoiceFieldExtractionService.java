package com.invoiceapp.invoice_API.service;

import org.springframework.stereotype.Service;

import java.util.regex.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceFieldExtractionService {

    public Map<String, String> extractFields(String ocrText) {

        Map<String, String> invoiceFields = new HashMap<>();

        invoiceFields.put("invoiceNumber", extractInvoiceNumber(ocrText));
        invoiceFields.put("vendorName", extractVendorName(ocrText));
        invoiceFields.put("invoiceDate", extractInvoiceDate(ocrText));
        invoiceFields.put("dueDate", extractDueDate(ocrText));
        invoiceFields.put("totalAmount", extractTotalAmount(ocrText));

        return invoiceFields;
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

}
