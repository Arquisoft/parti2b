Feature: Login de usuario
	Scenario: Un alcalde se loguea
		When un usuario de tipo alcalde se loguea con usuario "alcalde@gmail.com" y password "alcalde"
		Then el usuario logueado como alcalde recibe la pantalla inicial