package com.example.pdf_parser.Controller;

import com.example.pdf_parser.PDFAttributes;
import com.example.pdf_parser.PDFParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PdfController {

    private final PDFParser pdfParser;

    public PdfController(PDFParser pdfParser) {
        this.pdfParser = pdfParser;
    }

    @PostMapping("/parse")
    public ResponseEntity<List<PDFAttributes>> parse(@RequestParam("file") MultipartFile file) {
        try {
            File temp = File.createTempFile("upload", ".pdf");
            file.transferTo(temp);

            List<PDFAttributes> data = pdfParser.parsePdfs(Collections.singletonList(temp));
            return ResponseEntity.ok(data);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

