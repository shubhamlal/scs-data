package com.fpl.snow.sla.workbookutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fpl.snow.sla.entity.Incidents;
import com.fpl.snow.sla.entity.Incident;

public class IncidentsData {
	
	
	 List<Incident> resolutionData;
	public static List<Incidents> finalList;
	static XSSFRow row;
	private static IncidentsData incData = null;
	private static XSSFWorkbook xssfWorkbook;
	
	public static IncidentsData getInstance()
	{
		if(incData==null)
			incData = new IncidentsData();
		return incData;
	}
	
	
	

	
	private IncidentsData()
	{
		createIncidentsData(Constants.responsePath, Constants.resolutionPath);
	}
	
	
	
	public static List<Incidents> createIncidentsData(String response_path, String resolution_path)
	{
		List<Incident> responseData = new ArrayList<>();
		List<Incident> resolutionData = new ArrayList<>();
		responseData = getList(response_path);
		resolutionData = getList(resolution_path);
		
		finalList = createFinalList(responseData, resolutionData);
		
		return finalList;
		
	}
	
	
	
	
	public static List<Incidents> createFinalList(List<Incident> responseList, List<Incident> resolutionList)
	{
		
		System.out.println(resolutionList);
		Map<String,Incident> responseMap = getResponseMap(responseList);
		
		List<Incidents> finalList = new ArrayList<Incidents>();
		
		for(Incident res: resolutionList)
		{
			Incidents inc = new Incidents();
			inc.setInc_no(res.getInc_no());
			
			inc.setAffected_user(res.getAffected_user());
			
			inc.setDescription(res.getDescription());
			
			inc.setPriority(res.getPriority());
			
			inc.setSupport_group(res.getSupport_group());
			
			inc.setAssigned_to(res.getAssigned_to());
			
			inc.setHasBreached_Resolution(res.isHasBreached());
			
			inc.setHasExemption_Resolution(res.isHasExemption());
			if(responseMap.containsKey(res.getInc_no()))
			{
				inc.setHasBreached_Response(responseMap.get(res.getInc_no()).isHasBreached());
				inc.setHasExemption_Response(responseMap.get(res.getInc_no()).isHasExemption());
			}
			else
				inc.setHasBreached_Response(false);
				inc.setHasExemption_Response(false);
			
			
			
			finalList.add(inc);
		}
		
		System.out.println(finalList);
		
		return finalList;
	}
	
	public static Map<String,Incident> getResponseMap(List<Incident> list)
	{
		Map<String, Incident> responseMap = new HashMap<String,Incident>();
		list.stream().forEach(r ->
		{
			responseMap.put(r.getInc_no(),r);
		});
		
		return responseMap;
	}
	
	
	
	
	public  static List<Incident> getList(String path)
	{
		List<Incident> incidentslist = new ArrayList<Incident>();
		try
		{
			FileInputStream fis = new FileInputStream(new File(path));
			 xssfWorkbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = xssfWorkbook.getSheetAt(0);
			 Iterator <Row>  rowIterator = spreadsheet.iterator();
			 rowIterator.next();
			  while (rowIterator.hasNext()) {
			         row = (XSSFRow) rowIterator.next();
			        
			        Incident resObj = new Incident();
			         Iterator <Cell>  cellIterator = row.cellIterator();
			         
			         
			        Cell cell_inc_no = cellIterator.next();
			        //System.out.println(cell_inc_no.getStringCellValue());
			        resObj.setInc_no(cell_inc_no.getStringCellValue());
			       
			        Cell cell_affected_user = cellIterator.next();
			        //System.out.println(cell_affected_user.toString());
			        resObj.setAffected_user(cell_affected_user.toString());
			        
			        Cell cell_description = cellIterator.next();
			        resObj.setDescription(cell_description.toString());
			        
			        Cell cell_priority = cellIterator.next();
			        resObj.setPriority(cell_priority.toString());
			        
			        Cell cell_sppport_group = cellIterator.next();
			        resObj.setSupport_group(cell_sppport_group.toString());
			        
			        Cell cell_assigned_to = cellIterator.next();
			        resObj.setAssigned_to(cell_assigned_to.getStringCellValue());
			        
			        Cell cell_has_breached = cellIterator.next();
			        resObj.setHasBreached(cell_has_breached.getBooleanCellValue());
			        
			        Cell cell_has_exemption = cellIterator.next();
			        resObj.setHasExemption(cell_has_exemption.getBooleanCellValue());
			     
			        incidentslist.add(resObj);
			      fis.close();
			  }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return incidentslist;
		
	}

}
