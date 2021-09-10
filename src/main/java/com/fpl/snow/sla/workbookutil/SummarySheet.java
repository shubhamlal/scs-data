package com.fpl.snow.sla.workbookutil;

import java.util.ArrayList;
import java.util.List;

import com.fpl.snow.sla.entity.TemplateBox;

public class SummarySheet {
	

	

	IncidentsData incData = IncidentsData.getInstance();
	private static SummarySheet summarySheet = null;
	
	private SummarySheet() {
		
	}
	
	
	public static SummarySheet getInstance()
	{
		if(summarySheet == null)
		{
			summarySheet = new SummarySheet();
		}
		return summarySheet;
	}
	
	public List<Integer> calulcateTotal()
	{
		
		IncidentsData.finalList.forEach(inc -> System.out.println(inc.getPriority()));
		List<Integer> totalList = new ArrayList<>();

		//System.out.println((int)IncidentsData.finalList.stream().filter(inc -> inc.getPriority().contains("P1")).count());
		totalList.add((int)IncidentsData.finalList.stream().filter(inc -> inc.getPriority().contains("P1")).count());
		totalList.add((int) IncidentsData.finalList.stream().filter(inc -> inc.getPriority().contains("P2")).count());
		totalList.add((int) IncidentsData.finalList.stream().filter(inc -> inc.getPriority().contains("P3")).count());
		totalList.add((int) IncidentsData.finalList.stream().filter(inc -> inc.getPriority().contains("P4")).count());
		totalList.add((int) IncidentsData.finalList.stream().filter(inc -> inc.getPriority().contains("P5")).count());
		
		System.out.println(totalList);
		return totalList;
	}
	
	public TemplateBox calculateSLAPercentage(TemplateBox templateBox)
	{
		templateBox.slaPercentage = new ArrayList<Float>();
		for (int i = 0; i < templateBox.totalInc.size(); i++) {
			templateBox.slaPercentage.add(processFormula(templateBox.totalInc.get(i),templateBox.totalOOCInc.get(i)));
		}
		return templateBox;
	}
	
	public TemplateBox getResponseBox()
	{
		
		TemplateBox response = new TemplateBox();
		response.totalInc = calulcateTotal();
		response.totalOOCInc = new ArrayList<>();
		
		response.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
			((inc.getPriority().contains("P1")) && inc.isHasBreached_Response() && !inc.isHasExemption_Response())).count()));
		
		response.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P2")) && inc.isHasBreached_Response() && !inc.isHasExemption_Response())).count()));
		
		response.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P3")) && inc.isHasBreached_Response() && !inc.isHasExemption_Response())).count()));
		
		response.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P4")) && inc.isHasBreached_Response() && !inc.isHasExemption_Response())).count()));
		
		response.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P5")) && inc.isHasBreached_Response() && !inc.isHasExemption_Response())).count()));
		
		
		return calculateSLAPercentage(response);
	}
	

	
	public Float processFormula(int total, int ooc)
	{
		try
		{
			Float slapec = (float)(total-ooc)/(float)total;
			if(slapec.isNaN())
				return -1.0f;
			return slapec*100;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0.0f;
		}
	}
	
	public TemplateBox getResolutionBox()
	{
		
		TemplateBox resolution = new TemplateBox();
		resolution.totalInc = calulcateTotal();
		resolution.totalOOCInc = new ArrayList<>();
		
		resolution.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
			((inc.getPriority().contains("P1")) && inc.isHasBreached_Resolution() && !inc.isHasExemption_Resolution())).count()));
		
		resolution.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P2")) && inc.isHasBreached_Resolution() && !inc.isHasExemption_Resolution())).count()));
		
		resolution.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P3")) && inc.isHasBreached_Resolution() && !inc.isHasExemption_Resolution())).count()));
		
		resolution.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P4")) && inc.isHasBreached_Resolution() && !inc.isHasExemption_Resolution())).count()));
		
		resolution.totalOOCInc.add(((int)IncidentsData.finalList.stream().filter(inc -> 
		((inc.getPriority().contains("P5")) && inc.isHasBreached_Resolution() && !inc.isHasExemption_Resolution())).count()));
		
		
		return calculateSLAPercentage(resolution);
	}
	

	//Method will return the Actual sheet
	
	
	

}
