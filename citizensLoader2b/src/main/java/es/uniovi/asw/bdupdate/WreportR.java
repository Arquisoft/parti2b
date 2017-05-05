package es.uniovi.asw.bdupdate;

import es.uniovi.asw.reportWriter.WreportP;

public class WreportR implements WriteReport{

	@Override
	public void addReport(String report) {
		if(report!=null){
			if(!report.equals("")){
				WreportP r = new WreportP();
				r.writeReport(report);
			}
			
		}
		
	}

}
