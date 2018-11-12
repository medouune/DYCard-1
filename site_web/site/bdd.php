<?php
	
	
		try{
		$bdd= new PDO('mysql:host=localhost;dbname=DYCard', 'root', 'root');//Connection a la base de données
		array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION);
		}
		catch (Exception $e)//Sinon
		{
			die('Erreur : ' . $e->getMessage());//Affiche un message d'erreur
		}

		
       
	
	

?>