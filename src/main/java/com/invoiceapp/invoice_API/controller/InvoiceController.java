package com.invoiceapp.invoice_API.controller;

import com.invoiceapp.invoice_API.dto.InvoiceDTO;
import com.invoiceapp.invoice_API.model.Invoice;
import com.invoiceapp.invoice_API.service.InvoiceFieldExtractionService;
import com.invoiceapp.invoice_API.service.InvoiceService;
import com.invoiceapp.invoice_API.service.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice-api/v1/invoices")
public class InvoiceController {

    @Autowired
    private OcrService ocrService;

    @Autowired
    InvoiceFieldExtractionService invoiceFieldExtractionService;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/upload")
    public InvoiceDTO uploadInvoice(@RequestParam("file") MultipartFile file,
                                    Authentication authentication) throws IOException, TesseractException {

        String ocrText = ocrService.extractTextFromFile(file);

        // directly get DTO from service
        return invoiceFieldExtractionService.extractFields(ocrText);

    }

    @PostMapping("/save")
    public ResponseEntity<InvoiceDTO> saveInvoice(@AuthenticationPrincipal String email, @RequestBody InvoiceDTO invoice){
        return ResponseEntity.ok(invoiceService.saveInvoice(email, invoice));
    }

    @GetMapping("/user")
    public ResponseEntity<List<InvoiceDTO>> getInvoiceByUser(@AuthenticationPrincipal String email){
        return ResponseEntity.ok(invoiceService.getInvoicesByUser(email));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> removeInvoice(@PathVariable String id){
        invoiceService.removeInvoice(id);
        return ResponseEntity.ok("Invoice deleted successfully");
    }

    @PostMapping("/move/{id}")
    public ResponseEntity<String> moveInvoice(@PathVariable String id){
        invoiceService.moveInvoiceToPaid(id);
        return ResponseEntity.ok("Invoice moved to paid invoices successfully");
    }
}
