# PDF Parser Application

## Description

The PDF Parser Application is a Java tool designed to extract structured data from PDF files and convert it into JSON format. It supports multilingual text extraction and is capable of processing tabular or structured data.

## Features

- **Multilingual Support:** Extracts text from PDFs in various languages.
- **Structured Data Extraction:** Converts tabular data into JSON objects with key-value pairs.
- **Output Handling:** Generates a JSON file for easy integration and analysis.

## Technologies

- **Java:** Programming language used for implementation.
- **Apache PDFBox:** Library for PDF text extraction.
- **Spring Boot:** Framework for managing application configuration.

## Usage

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/MohammedThoufiq/pdf-parser.git
   ```

2. **Install Dependencies:**
   Use Maven to install the required dependencies:
   ```bash
   mvn install
   ```

3. **Configure File Paths:**
   Update the `PdfParserApplication` class with the paths to your PDF files.

4. **Run the Application:**
   Execute the application to parse the PDFs and generate a JSON file:
   ```bash
   mvn spring-boot:run
   ```

5. **Check Output:**
   The JSON file will be created in the specified directory.

## Example

If your PDF contains data in various languages, the output JSON might look like this:

json
[
  {
     "attributes": {
      "Name": "John",
      "Age": "30",
      "Occupation": "Software Developer",
      "Email": "john.doe@example.com"
    }
  },
  {
    "attributes": {
      "Name": "Jane",
      "Age": "28",
      "Occupation": "Data Scientist",
      "Email": "jane.smith@example.com"
    }
  },
  {
    "attributes": {
      "Name": "李四",
      "Age": "25",
      "Occupation": "数据分析师",
      "Email": "li.si@example.com"
    }
  }
]

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

Replace `https://github.com/MohammedThoufiq/pdf-parser.git` with the actual URL of your GitHub repository. Adjust any specifics based on your project needs.
