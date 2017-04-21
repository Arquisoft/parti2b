Feature: Dashboard alcalde
	Scenario: El alcalde accede al dashboard
		When el alcalde se loguea con usuario "alcalde@gmail.com" y password "alcalde"
		Then el alcalde se encuentra con el dashboard para alcalde