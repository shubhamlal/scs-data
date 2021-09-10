package com.fpl.snow.sla;




import com.fpl.snow.sla.workbookutil.IncidentsData;
import com.fpl.snow.sla.workbookutil.SLAWorkbook;
import com.fpl.snow.sla.workbookutil.SummarySheet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("===========================================Job Started================================================");
    	System.out.println("1. Reading Incidents Data");
    
          SLAWorkbook book = new SLAWorkbook();
          book.createWorkBookJob();
          System.out.println("===========================================Completed================================================"); 
          
        
    }
}
