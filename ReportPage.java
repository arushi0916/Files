package com.cg.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.dto.Claim;
import com.cg.dto.ShowClaimDetails;
import com.cg.dto.UserResponses;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportPage {
	
	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	public String createPage(ShowClaimDetails userDetails, Claim claim, List<UserResponses> responseList) {
		
		Document document = new Document();
		
		LOGGER.info("IN the pdf creation page");
		
		String path="D:\\PLP_Copy_project_tesing\\PLPCaseStudy\\WebContent\\";
		String accountNumber = Integer.toString(userDetails.getAccountNumber());
		String claimNumber = Integer.toString(userDetails.getClaimNumber());
		String filename=claim.getUserName()+claimNumber+".pdf";
		path = path + filename;
		
		LOGGER.info("File path = "+path);
		
	      try{
	    	 
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
	         document.open();
	         
	         Font font = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
	         Paragraph preface = new Paragraph("Report for Claim", font); 
	         preface.setAlignment(Element.ALIGN_CENTER);
	         document.add(preface);
	         document.add( Chunk.NEWLINE );
	         
	         
	         Font font1 = new Font(FontFamily.HELVETICA, 16);
	         
	         Paragraph para1 = new Paragraph("Claim Number : "+Integer.toString(claim.getClaimNumber())+"\n"+"Claim Reason : "+claim.getClaimReason()
	         +"\n"+"Accident Location : "+(claim.getAccidentLocationStreet())+"\n"+"Accident City : "+claim.getAccidentCity()+"\n"+"Accident State : "
	        +claim.getAccidentState()+"\n"+"Accident Zip : "+Integer.toString(claim.getAccidentZip())+"\n"+"Claim Type : "+claim.getClaimType()+
	        "Policy Number : "+Integer.toString(claim.getPolicyNumber())+"\n"+"User Name : "+claim.getUserName(), font1);
	         para1.setAlignment(Element.ALIGN_LEFT);
	         document.add(para1);
	         
	         document.add( Chunk.NEWLINE );
	         document.add( Chunk.NEWLINE );
	         
	         Font font2 = new Font(FontFamily.HELVETICA, 15, Font.BOLD);
	         Font font3 = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
	         para1.setAlignment(12);
	         Paragraph userAnswers = new Paragraph("User Responses", font2);
	         document.add(userAnswers);
	         PdfPTable questionsTable = new PdfPTable(2);
	         document.add( Chunk.NEWLINE );
	         PdfPCell cell1 = new PdfPCell(new Paragraph("Question", font3));
	         PdfPCell cell2 = new PdfPCell(new Paragraph("Answer", font3));
	         questionsTable.addCell(cell1);
	         questionsTable.addCell(cell2);
	         for(UserResponses answers: responseList) {
	        	 PdfPCell question = new PdfPCell(new Paragraph(answers.getQuestion()));
		         PdfPCell answer = new PdfPCell(new Paragraph(answers.getAnswer())); 
		         questionsTable.addCell(question);
		         questionsTable.addCell(answer);
	         }
	         document.add(questionsTable);
	   
	        	 
	         document.close();
	         
	         writer.close();
	      } catch (DocumentException e){
	         
	    	  LOGGER.error("Error while creating the document");
	    	  
	      } catch (FileNotFoundException e){
	    	  
	    	  LOGGER.error("Could not fetch the file");
	    	  
	      }
		return path;
	 }


}
