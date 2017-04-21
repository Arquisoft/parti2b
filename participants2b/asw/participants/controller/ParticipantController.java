package es.uniovi.asw.participants.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbManagement.ParticipantData;
import es.uniovi.asw.dbManagement.model.Participant;
import es.uniovi.asw.participants.wrappers.ChangePassWrapper;
import es.uniovi.asw.participants.wrappers.LoginWrapper;
import es.uniovi.asw.participants.wrappers.ParticipantInfo;

/**
 * Controlador principal de los servicios web de la aplicación.
 * 
 * @author karol
 *
 */
@Controller
@RestController
public class ParticipantController {

    @Autowired
    private ParticipantData data;

    /**
     * Devuelve los datos del usuario en una peticion POST con la cabecera OK si
     * este existe en la base de datos. En caso contrario envia una peticion
     * POST con la cabecera NOT_FOUND.
     * 
     * @param loginWrapper
     * @return HTTP OK si el usuario existe, HTTP NOT_FOUND si no existe o HTTP
     *         BAD_REQUEST si la peticion es incorrecta
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<ParticipantInfo> queryInfo(
	    @RequestBody @Valid final LoginWrapper loginWrapper) {

	if (loginWrapper == null)
	    return new ResponseEntity<ParticipantInfo>(HttpStatus.BAD_REQUEST);

	Participant p = data.getData(loginWrapper.getLogin());

	if (p == null)
	    return new ResponseEntity<ParticipantInfo>(HttpStatus.NOT_FOUND);

	if (p.getPassword().equals(loginWrapper.getPassword()))
	    return new ResponseEntity<ParticipantInfo>(new ParticipantInfo(p),
		    HttpStatus.OK);

	return new ResponseEntity<ParticipantInfo>(HttpStatus.NOT_FOUND);
    }

    /**
     * Cambia la contraseña del usuario si el usuario existe en la base de
     * datos.
     * 
     * @param changePassWrapper
     * @return HTTP OK si la contraseña se actualiza correctamente, HTTP
     *         NOT_FOUND si el usuario no exiate en la base de datos o HTTP
     *         BAD_REQUEST la peticion es incorrecta
     */
    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public ResponseEntity<Void> updateInfo(
	    @RequestBody @Valid final ChangePassWrapper changePassWrapper) {

	if (changePassWrapper == null)
	    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

	Participant p = data.getData(changePassWrapper.getLogin());

	if (p == null)
	    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	if (!p.getPassword().equals(changePassWrapper.getPassword()))
	    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	p.setPassword(changePassWrapper.getNewPassword());

	data.updateInfo(p);

	return new ResponseEntity<Void>(HttpStatus.OK);

    }

}