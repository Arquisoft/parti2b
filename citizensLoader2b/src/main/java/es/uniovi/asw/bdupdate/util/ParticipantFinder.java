package es.uniovi.asw.bdupdate.util;

import java.util.List;

import es.uniovi.asw.model.Participant;


public class ParticipantFinder {

	public static Participant findByDni(String dni){
		List<Participant> r =  Jpa.getManager().createNamedQuery("Participant.findByDNI",Participant.class).setParameter(1,dni).getResultList();
		//Participant p= Jpa.getManager().createQuery("select p from participant p where dni = 1?",Participant.class).setParameter(1, dni).getSingleResult();
		if(r.isEmpty())return null;
		return r.get(0);
	}

	public static Participant findLogableUser(String usuario, String password) {
		List<Participant> p = Jpa.getManager().createNamedQuery("Participant.findLogableUser",Participant.class).setParameter(1, usuario).setParameter(2, password).getResultList();
		if(p.isEmpty()) return null;
		return p.get(0);
	}

	public static List<Participant> findAll() {
		return Jpa.getManager().createNamedQuery("Participant.findAll",Participant.class).getResultList();
	}


}
