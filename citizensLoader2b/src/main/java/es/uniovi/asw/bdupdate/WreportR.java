package es.uniovi.asw.bdupdate;

import es.uniovi.asw.reportWriter.WreportP;
import es.uniovi.asw.reportWriter.WriteReport;

public class WreportR implements WriteReportBD{

	@Override
	public void addReport(String report) {
		if(report!=null){
			if(!report.equals("")){
				WriteReport r = new WreportP();
				r.writeReport(report);
			}
			
		}
		
	}

}
