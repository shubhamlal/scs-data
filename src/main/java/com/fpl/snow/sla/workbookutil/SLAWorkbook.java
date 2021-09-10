package com.fpl.snow.sla.workbookutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fpl.snow.sla.entity.Incidents;
import com.fpl.snow.sla.entity.TemplateBox;



public class SLAWorkbook {
	
	private String path = "C:\\WorkSpace\\Test\\Output\\";
	
	public static XSSFWorkbook book;
	private XSSFSheet summarySheet;
	private XSSFSheet incidentsSheet;
	private XSSFSheet scTaskSheet;

	IncidentsData data;
	
	public SLAWorkbook()
	{

		data = IncidentsData.getInstance();
		
	}
	
	
	
	//Created file
	private File createWorkBookFile(String path)
	{
		
		Month lastMonth = LocalDate.now().minusMonths(1).getMonth();
		
		File file = new File(path+"Customer Services SLA Data - "+lastMonth+".xlsx");
		book = new XSSFWorkbook();
		summarySheet = book.createSheet("Summary");
		incidentsSheet = book.createSheet("Incidents");
		scTaskSheet =  book.createSheet("SC Tasks");
		try 
		{
			
			FileOutputStream out = new FileOutputStream(file);
			  book.write(out);
		      out.close();
		}
		
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return file;
	}
	
	public void createWorkBookJob()
	{
		
		
		File file = createWorkBookFile(path);
		if(file.isFile() && file.exists())
		{
			System.out.println("File created");
			
			//create incidents sheet 
			fillIncidentsData(file);
			//create sctask sheet
			
			// create summary sheet 
			fillSummaryData(file);
		}
	
		
	
	}
	
	public void fillIncidentsData(File file)
	{
		
		
		XSSFRow row;
		int rowid = 0;
		row = incidentsSheet.createRow(rowid++);
		Cell cellIncH = row.createCell(0);
		cellIncH.setCellValue("Number");
		Cell affected_UserH = row.createCell(1);
		affected_UserH.setCellValue("Affected User");
		Cell affected_DesH = row.createCell(2);
		affected_DesH.setCellValue("Description");
		Cell prio_cellH = row.createCell(3);
		prio_cellH.setCellValue("Priority");
		Cell has_breach_cellH = row.createCell(4);
		has_breach_cellH.setCellValue("HasBreached(Resolution)");
		Cell has_Excemption_cellH = row.createCell(5);
		has_Excemption_cellH.setCellValue("HasExemption(Resolution)");
		Cell has_Breach_ResH = row.createCell(6);
		has_Breach_ResH.setCellValue("HasBreached(Response)");
		Cell hasBreach_Res_ExmH = row.createCell(7);
		hasBreach_Res_ExmH.setCellValue("HasExemption(Response)");
		
		for(Incidents inc :IncidentsData.finalList)
		{
			row = incidentsSheet.createRow(rowid++);
			Cell cellInc = row.createCell(0);
			cellInc.setCellValue(inc.getInc_no());
			Cell affected_User = row.createCell(1);
			affected_User.setCellValue(inc.getAffected_user());
			Cell affected_Des = row.createCell(2);
			affected_Des.setCellValue(inc.getDescription());
			Cell prio_cell = row.createCell(3);
			prio_cell.setCellValue(inc.getPriority());
			Cell has_breach_cell = row.createCell(4);
			has_breach_cell.setCellValue(inc.isHasBreached_Resolution());
			Cell has_Excemption_cell = row.createCell(5);
			has_Excemption_cell.setCellValue(inc.isHasExemption_Resolution());
			Cell has_Breach_Res = row.createCell(6);
			has_Breach_Res.setCellValue(inc.isHasBreached_Response());
			Cell hasBreach_Res_Exm = row.createCell(7);
			hasBreach_Res_Exm.setCellValue(inc.isHasExemption_Response());
			
			 try {
					FileOutputStream out = new FileOutputStream(file);
					
					book.write(out);
			        out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
		
	}
	
	public void fillSummaryData(File file)
	{
		// Header Template
		XSSFRow row;
		Cell cell = null;
		DecimalFormat df = new DecimalFormat("0.00");
		
		/******************************************** Response **********************************************************************************/
		///////////////
		//Header///////
		///////////////
		row = summarySheet.createRow(0);
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			cell.setCellStyle(CellStyles.slaHeaderStyle());
			if(i==0)
				cell.setCellValue("SLA for Ticket Response");
			
		}
		
		
		summarySheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		row = summarySheet.createRow(1);
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			cell.setCellStyle(CellStyles.slaHeaderStyle());
			if(i>0 && i<6)
				cell.setCellValue("P"+i);
			else if(i==6)
				cell.setCellValue("Total Tickets");
			else if(i==7)
				cell.setCellValue("Tasks / Service Requests");;
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());	
				
			
		}
		SummarySheet summaryData = SummarySheet.getInstance();
		row = summarySheet.createRow(2);
		int sumofTotal = 0;
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			TemplateBox responseBox = summaryData.getResponseBox();
			List<Integer> totalList = responseBox.totalInc;
			if(i==0)
				cell.setCellValue("Tickets Completed");
			else if(i>0 && i<6)
			{
				int cellDigit = totalList.get(i-1);
				cell.setCellValue(cellDigit);
				sumofTotal += cellDigit;
			}
			else if(i==6)
			{
				cell.setCellValue(sumofTotal);
				
			}
			else if(i==7)
				cell.setCellValue("0");
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());
				
			
		}
		
		row = summarySheet.createRow(3);
		int sumofTotalOOC = 0;
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			TemplateBox responseBox = summaryData.getResponseBox();
			List<Integer> oocList = responseBox.totalOOCInc;
			if(i==0)
				cell.setCellValue("Tickets Out of Compliance");
			else if(i>0 && i<6)
			{
				int cellDigit = oocList.get(i-1);
				cell.setCellValue(cellDigit);
				sumofTotalOOC += cellDigit;
			}
			else if(i==6)
			{
				cell.setCellValue(sumofTotalOOC);
				
			}
			else if(i==7)
				cell.setCellValue("0");
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());
				
			
		}
		
		row = summarySheet.createRow(4);
		float resPercentage = ((float)(sumofTotal - sumofTotalOOC)/(float)sumofTotal)*100;
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			TemplateBox responseBox = summaryData.getResponseBox();
			List<Float> slaPecList = responseBox.slaPercentage;
			if(i==0)
				cell.setCellValue("SLA %");
			else if(i>0 && i<6)
			{
				float cellDigit = slaPecList.get(i-1);
				System.out.println("Cell Digit"+(int)cellDigit);
				if((int)cellDigit==-1)
					cell.setCellValue("N.A.");
				else
				cell.setCellValue(cellDigit);
			}
			else if(i==6)
				
				cell.setCellValue(df.format(resPercentage));
			
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());
		}
		

		/******************************************** Resolution **********************************************************************************/
		row = summarySheet.createRow(6);
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			cell.setCellStyle(CellStyles.slaHeaderStyle());
			if(i==0)
				cell.setCellValue("SLA for Ticket Resolution");
			
		}
		summarySheet.addMergedRegion(new CellRangeAddress(6,6,0,7));
		row = summarySheet.createRow(7);
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			cell.setCellStyle(CellStyles.slaHeaderStyle());
			if(i>0 && i<6)
				cell.setCellValue("P"+i);
			else if(i==6)
				cell.setCellValue("Total Tickets");
			else if(i==7)
				cell.setCellValue("Tasks / Service Requests");;
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());	
				
			
		}
		
		row = summarySheet.createRow(8);
		sumofTotal = 0;
		
		
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			TemplateBox responseBox = summaryData.getResolutionBox();
			List<Integer> totalList = responseBox.totalInc;
			if(i==0)
				cell.setCellValue("Tickets Completed");
			else if(i>0 && i<6)
			{
				int cellDigit = totalList.get(i-1);
				cell.setCellValue(cellDigit);
				sumofTotal += cellDigit;
			}
			else if(i==6)
			{
				cell.setCellValue(sumofTotal);
				
			}
			else if(i==7)
				cell.setCellValue("0");
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());
				
			
		}
		
		row = summarySheet.createRow(9);
		sumofTotalOOC = 0;
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			TemplateBox responseBox = summaryData.getResolutionBox();
			List<Integer> oocList = responseBox.totalOOCInc;
			if(i==0)
				cell.setCellValue("Tickets Out of Compliance");
			else if(i>0 && i<6)
			{
				int cellDigit = oocList.get(i-1);
				cell.setCellValue(cellDigit);
				sumofTotalOOC += cellDigit;
			}
			else if(i==6)
			{
				cell.setCellValue(sumofTotalOOC);
				
			}
			else if(i==7)
				cell.setCellValue("0");
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());
				
			
		}
		
		row = summarySheet.createRow(10);
		float resolutionPercentage = ((float)(sumofTotal - sumofTotalOOC)/(float)sumofTotal)*100;
		System.out.println(resolutionPercentage);
		for(int i =0;i<8;i++)
		{
			cell = row.createCell(i);
			TemplateBox responseBox = summaryData.getResolutionBox();
			List<Float> slaPecList = responseBox.slaPercentage;
			if(i==0)
				cell.setCellValue("SLA %");
			else if(i>0 && i<6)
			{
				float cellDigit = slaPecList.get(i-1);
				System.out.println("Cell Digit"+(int)cellDigit);
				if((int)cellDigit==-1)
					cell.setCellValue("N.A.");
				else
				cell.setCellValue(cellDigit);
			}
			else if(i==6)
				
				cell.setCellValue(df.format(resolutionPercentage));
			
				
			cell.setCellStyle(CellStyles.slaHeaderStyle());
		}
		
		// Average SLA for Response
		row = summarySheet.createRow(14);
		cell = row.createCell(0);
		cell.setCellValue("Average SLA% for Response");
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(1);
		cell.setCellValue("Target SLA");
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(2);
		cell.setCellValue("Performace");
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(3);
		cell.setCellValue("High-level Reasons");
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(4);
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(5);
		summarySheet.addMergedRegion(new CellRangeAddress(14,14,3,5));
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		// Entry
		row = summarySheet.createRow(15);
		cell = row.createCell(0);
		cell.setCellValue(df.format(resPercentage));
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(1);
		cell.setCellValue("95%");
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(2);
		if(resPercentage >= 95.00)
		cell.setCellValue("Green");
		else if(resPercentage>=80.00 && resPercentage<=95.00)
			cell.setCellValue("Amber");
		else
			cell.setCellValue("RED");
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(3);
		cell.setBlank();
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(4);
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		cell = row.createCell(5);
		summarySheet.addMergedRegion(new CellRangeAddress(15,15,3,5));
		cell.setCellStyle(CellStyles.slaHeaderStyle());
		
		// Average SLA for Resolution
				row = summarySheet.createRow(18);
				cell = row.createCell(0);
				cell.setCellValue("Average SLA% for Resolution");
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(1);
				cell.setCellValue("Target SLA");
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(2);
				cell.setCellValue("Performace");
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(3);
				cell.setCellValue("High-level Reasons");
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(4);
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(5);
				summarySheet.addMergedRegion(new CellRangeAddress(18,18,3,5));
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				// Entry
				row = summarySheet.createRow(19);
				cell = row.createCell(0);
				cell.setCellValue(df.format(resolutionPercentage));
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(1);
				cell.setCellValue("95%");
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(2);
				if(resPercentage >= 95.00)
				cell.setCellValue("Green");
				else if(resPercentage>=80.00 && resPercentage<=95.00)
					cell.setCellValue("Amber");
				else
					cell.setCellValue("RED");
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(3);
				cell.setBlank();
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(4);
				cell.setCellStyle(CellStyles.slaHeaderStyle());
				cell = row.createCell(5);
				summarySheet.addMergedRegion(new CellRangeAddress(19,19,3,5));
				cell.setCellStyle(CellStyles.slaHeaderStyle());
		
		
		
		
		try {
			FileOutputStream out = new FileOutputStream(file);
			
			book.write(out);
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	
	

}
