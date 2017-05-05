package es.uniovi.asw.reportWriter;



import org.apache.log4j.Logger;

import es.uniovi.asw.model.Participant;

public class WreportP {

	public static Logger log = Logger.getLogger(Participant.class);
	public void writeReport(String report) {
		log.info(report);

	}

}
