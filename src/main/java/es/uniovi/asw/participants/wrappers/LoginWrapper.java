package es.uniovi.asw.participants.wrappers;

/**
 * Clase envoltorio de los datos del login.
 * 
 * @author karol
 *
 */
public class LoginWrapper {

    private String login;
    private String password;

    public LoginWrapper() {
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "LoginWrapper [login=" + login + ", password=" + password + "]";
    }
}
