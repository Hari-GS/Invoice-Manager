package com.invoiceapp.invoice_API.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class OcrService {

    public String extractTextFromFile(MultipartFile file) throws IOException, TesseractException {

        // Save MultipartFile to a temporary file
        File tempFile = File.createTempFile("invoice", ".jpg"); // or .png or .pdf
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        ITesseract tesseract = new Tesseract();

        // OPTIONAL: Set path to tesseract.exe if using Windows
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata/");// path to tessdata folder

        String result = tesseract.doOCR(tempFile);

        tempFile.delete();  // Cleanup temp file

        return result;
    }
}
