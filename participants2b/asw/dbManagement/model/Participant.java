package es.uniovi.asw.dbManagement.model;

import es.uniovi.asw.dbManagement.types.ParticipantType;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    @Column(unique = true)
    private String email;
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    private String residencia;
    private String nacionalidad;
    private String ndi;
    @Enumerated(EnumType.STRING)
    private ParticipantType type;

    Participant() {
    }

    public Participant(String nombre, String apellidos, String email) {
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.email = email;
    }

    public Participant(String nombre, String apellidos, String email,
	    Date fechaNacimiento) {
	this(nombre, apellidos, email);
	this.fechaNacimiento = new Date(fechaNacimiento.getTime());
    }

    public Participant(String ndi) {
	this.ndi = ndi;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellidos() {
	return apellidos;
    }

    public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Date getFechaNacimiento() {
	return new Date(fechaNacimiento.getTime());
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
	this.fechaNacimiento = new Date(fechaNacimiento.getTime());
    }

    public String getResidencia() {
	return residencia;
    }

    public void setResidencia(String residencia) {
	this.residencia = residencia;
    }

    public String getNacionalidad() {
	return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
	this.nacionalidad = nacionalidad;
    }

    public String getNdi() {
	return ndi;
    }

    public void setNdi(String ndi) {
	this.ndi = ndi;
    }

    public Long getId() {
	return id;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public ParticipantType getType() {
        return type;
    }

    public void setType(ParticipantType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ndi == null) ? 0 : ndi.hashCode());
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
	Participant other = (Participant) obj;
	if (ndi == null) {
	    if (other.ndi != null)
		return false;
	} else if (!ndi.equals(other.ndi))
	    return false;
	return true;
    }

    public String getPassword() {
	return password;
    }

}
