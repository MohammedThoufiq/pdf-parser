package com.example.pdf_parser;

import java.util.HashMap;
import java.util.Map;

public class PDFAttributes {
    private Map<String, String> attributes;

    public PDFAttributes() {
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "{" + "attributes=" + attributes + '}';
    }
}
