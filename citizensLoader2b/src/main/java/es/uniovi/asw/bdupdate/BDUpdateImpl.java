package es.uniovi.asw.bdupdate;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import es.uniovi.asw.bdupdate.util.Jpa;
import es.uniovi.asw.bdupdate.util.ParticipantFinder;
import es.uniovi.asw.model.Participant;


public class BDUpdateImpl implements BDUpdate{



	/**
	 * Añade ciudadanos a la base de datos
	 * 
	 * @param ciudadanos,
	 *            lista de ciudadanos a insertar en la base de datos
	 */
	@Override
	public void addParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(ParticipantFinder.findByDni(participant.getDni())==null){
			Jpa.getManager().persist(participant);
		}
		trx.commit();
		
	}

	/**
	 * Elimina 1 ciudadano cuyo dni se introduce como parametro
	 * 
	 * @param dni
	 *            del ciudadano a borrar
	 */
	@Override
	public void deleteParticipant(String dni) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Participant p = ParticipantFinder.findByDni(dni);
		
		p=Jpa.getManager().find(Participant.class, p.getId());
		if(p !=null ){
			Jpa.getManager().remove(p);
		}
		trx.commit();

	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un
	 * objeto ciudadano que sera el que se use para actualizar los datos (se
	 * basa en el dni)
	 * 
	 * @param ciudadano
	 *            a actualizar
	 */
	@Override
	public void updateParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(participant!=null)
			Jpa.getManager().merge(participant);
		trx.commit();
	}
	@Override
	public Participant findParticipant(String dni) {
		EntityManager mapper = Jpa.createEntityManager();
				EntityTransaction trx = mapper.getTransaction();
				trx.begin();
		
			Participant c =ParticipantFinder.findByDni(dni);
				return c;
	}


	/**
	 * Elimina todos los ciudadanos
	 */
	@Override
	public void deleteAllParticipants() {
		EntityManager mapper = Jpa.createEntityManager();
				EntityTransaction trx = mapper.getTransaction();
				trx.begin();
				List<Participant> participants = ParticipantFinder.findAll();
				for (Participant participant : participants) {
					Participant p=Jpa.getManager().find(Participant.class, participant.getId());
					if(p!=null){
						Jpa.getManager().remove(p);
					}
		 		}
				trx.commit();
	}

}