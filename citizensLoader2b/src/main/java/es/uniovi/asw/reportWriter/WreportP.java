package es.uniovi.asw.reportWriter;



import org.apache.log4j.Logger;

import es.uniovi.asw.model.Participant;

public class WreportP implements WriteReport {

	public static Logger log = Logger.getLogger(Participant.class);
	@Override
	public void writeReport(String report) {
		log.info(report);

	}

}
