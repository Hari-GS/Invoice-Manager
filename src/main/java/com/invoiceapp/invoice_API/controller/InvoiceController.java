package com.invoiceapp.invoice_API.controller;

import com.invoiceapp.invoice_API.service.InvoiceFieldExtractionService;
import com.invoiceapp.invoice_API.service.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/is-api/invoices")
public class InvoiceController {

    @Autowired
    private OcrService ocrService;

    @Autowired
    InvoiceFieldExtractionService invoiceFieldExtractionService;

    @PostMapping("/upload")
    public Map<String, String> uploadInvoice(@RequestParam("file") MultipartFile file,
                                             Authentication authentication) throws IOException, TesseractException {

        String ocrText = ocrService.extractTextFromFile(file);
        Map<String, String> extractedFields = invoiceFieldExtractionService.extractFields(ocrText);

        return extractedFields;

    }
}
