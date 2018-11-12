<?php

if(isset($_POST) && !empty($_POST['nom']) && !empty($_POST['prenom']) && !empty($_POST['mail']) && !empty($_POST['identifiant']) && !empty($_POST['password']))
{
	
	extract($_POST);
	
	
		include 'bdd.php';

		
        
		$req=$bdd->prepare('INSERT INTO utilisateur(nom, prenom, login, password, mail) VALUES(:nom, :prenom, :login, :password, :mail) ');
		$req->execute(array(
		'nom'=>$nom,
		'prenom'=>$prenom,
		'login'=>$identifiant,
		'password'=>md5($password),
		'mail'=>$mail,
		));
		
	header('Location: connexion.php');
	
	
}
?>