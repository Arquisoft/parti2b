package es.uniovi.asw.participants.wrappers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import es.uniovi.asw.dbManagement.model.Participant;
import es.uniovi.asw.util.DateUtil;

/**
 * Clase envoltorio de la información que se envía en la respuesta a la petición
 * al servicio web.
 * 
 * @author karol
 *
 */
@XmlRootElement
public class ParticipantInfo {

    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String ndi;
    private String email;

    public ParticipantInfo(String nombre, String apellidos,
	    Date fechaNacimiento, String ndi, String email) {
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.fechaNacimiento = new Date(fechaNacimiento.getTime());
	this.ndi = ndi;
	this.email = email;
    }

    public ParticipantInfo(Participant participant) {
	this.nombre = participant.getNombre();
	this.apellidos = participant.getApellidos();
	this.fechaNacimiento = participant.getFechaNacimiento();
	this.ndi = participant.getNdi();
	this.email = participant.getEmail();
    }

    @XmlElement
    public String getNombre() {
	return nombre;
    }

    @XmlElement
    public String getApellidos() {
	return apellidos;
    }

    @XmlElement
    public int getEdad() {
	Period edad = Period.between(DateUtil.asLocalDate(fechaNacimiento),
		LocalDate.now());
	return edad.getYears();
    }

    @XmlElement
    public String getNdi() {
	return ndi;
    }

    @XmlElement
    public String getEmail() {
	return email;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((apellidos == null) ? 0 : apellidos.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
	result = prime * result + ((ndi == null) ? 0 : ndi.hashCode());
	result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ParticipantInfo other = (ParticipantInfo) obj;
	if (apellidos == null) {
	    if (other.apellidos != null)
		return false;
	} else if (!apellidos.equals(other.apellidos))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (fechaNacimiento == null) {
	    if (other.fechaNacimiento != null)
		return false;
	} else if (!fechaNacimiento.equals(other.fechaNacimiento))
	    return false;
	if (ndi == null) {
	    if (other.ndi != null)
		return false;
	} else if (!ndi.equals(other.ndi))
	    return false;
	if (nombre == null) {
	    if (other.nombre != null)
		return false;
	} else if (!nombre.equals(other.nombre))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ParticipantInfo [nombre=" + nombre + ", apellidos=" + apellidos
		+ ", fechaNacimiento=" + fechaNacimiento + ", ndi=" + ndi
		+ ", email=" + email + "]";
    }

}