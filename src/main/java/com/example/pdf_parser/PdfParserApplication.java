package com.example.pdf_parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PdfParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfParserApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PDFParser pdfParser) {
		return args -> {
			// Replace with paths to your PDF files
			List<File> pdfFiles = Arrays.asList(
					new File("C:\\Users\\abdul\\OneDrive\\Documents\\Thoufiq\\PDFParserUpwork\\71812 6,4mm.pdf"),
					new File("C:\\Users\\abdul\\OneDrive\\Documents\\Thoufiq\\PDFParserUpwork\\71813 12,7mm.pdf"),
					new File("C:\\Users\\abdul\\OneDrive\\Documents\\Thoufiq\\PDFParserUpwork\\72112 14mm.pdf"),
					new File("C:\\Users\\abdul\\OneDrive\\Documents\\Thoufiq\\PDFParserUpwork\\messer özet sayfa.pdf")
					// Add more files as needed
			);

			try {
				// Parse the PDFs and store the data in a list of PDFAttributes objects
				List<PDFAttributes> dataList = pdfParser.parsePdfs(pdfFiles);

				// Convert list of PDFAttributes objects to JSON and store it
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataList);

				// Write the JSON to a file
				try (FileWriter file = new FileWriter("output.json")) {
					file.write(json);
				}

				// Optionally print out the JSON for verification
				System.out.println("Stored JSON:");
				System.out.println(json);

			} catch (IOException e) {
				System.err.println("Error processing PDFs: " + e.getMessage());
			}
		};
	}
}
