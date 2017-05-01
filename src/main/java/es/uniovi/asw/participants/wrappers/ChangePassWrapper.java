package es.uniovi.asw.participants.wrappers;

/**
 * Clase envoltorio de los parametros del cambio de contraseña.
 * 
 * @author karol
 *
 */
public class ChangePassWrapper {
    private String login;
    private String password;
    private String newPassword;

    public ChangePassWrapper() {
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

    public String getNewPassword() {
	return newPassword;
    }

    public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
    }

    @Override
    public String toString() {
	return "UpdatePassWrapper [login=" + login + ", password=" + password
		+ ", newPassword=" + newPassword + "]";
    }

}
