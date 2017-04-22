package es.uniovi.asw.bdupdate;


import es.uniovi.asw.model.Participant;

public interface BDUpdate {

	void insertarCiudadano(Participant participant);

	void eliminarCiudadano(String dni);

	void updateCiudadano(Participant ciudadano);

	Participant obtenerCiudadano(String dni);

	void guardaarPasswordUsuario(String dni, String password);

	void eliminarCiudadanos();

}
