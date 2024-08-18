package com.example.pdf_parser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PDFParser {

    public List<PDFAttributes> parsePdfs(List<File> files) throws IOException {
        List<PDFAttributes> allAttributes = new ArrayList<>();

        for (File file : files) {
            try (PDDocument document = PDDocument.load(file)) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                // Extract text while preserving spaces and line breaks
                String text = pdfStripper.getText(document);
                allAttributes.addAll(parseDataFrame(text));
            }
        }

        return allAttributes;
    }

    private List<PDFAttributes> parseDataFrame(String text) {
        List<PDFAttributes> attributesList = new ArrayList<>();
        String[] lines = text.split("\n");

        if (lines.length > 1) {
            // The first line is assumed to be the header
            String[] headers = lines[0].split("\\s+");

            // Process each subsequent line as a row
            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split("\\s+");
                if (values.length == headers.length) {
                    PDFAttributes pdfAttributes = new PDFAttributes();
                    for (int j = 0; j < headers.length; j++) {
                        pdfAttributes.addAttribute(headers[j], values[j]);
                    }
                    attributesList.add(pdfAttributes);
                }
            }
        }

        return attributesList;
    }
}
