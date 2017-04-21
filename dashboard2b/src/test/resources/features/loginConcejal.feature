Feature: Login de usuario
	Scenario: Un concejal se loguea
		When un usuario de tipo concejal se loguea con usuario "concejal@gmail.com" y password "concejal"
		Then el usuario logueado como concejal recibe la pantalla inicial
