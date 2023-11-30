package com.example.demo.convertToPdf;

import java.io.File;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;

public class GeneratePDFUsingHTML {

	 public static void main(String[] args) throws IOException {

	        HtmlConverter.convertToPdf(new File("./ticket.html"),new File("ticket.pdf"));
	    }
}
