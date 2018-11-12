<?php

if(isset($_POST) && !empty($_POST['identifiant']) && !empty($_POST['password'])) //Si le tableau est défini et ses cases sont non vides
{
	extract($_POST);
	include 'bdd.php';
	
	
	//Vérifie si l'indentifiant et le mot de passe saisis sont dans la table
	$req=$bdd->prepare('SELECT * FROM Admin WHERE login=:login AND password=:password');
	$req->execute(array(
	'login'=>$identifiant,
	'password'=>$password,
	));
	
	$donnees=$req->fetchAll();//On compte le nombre de valeurs trouvées
	
	if(count($donnees)==0){//S'il n'y a aucune valeur
		header('Location: connexion.php');//On redirige vers la page de connexion
	}
	else//Sinon
	{
		//var_dump($donnees);
		session_start();//On demarre le session
		$_SESSION['login']=$identifiant;
	    $_SESSION['password']=md5($password);
		$_SESSION['logged']=true;
		
		/*if($donnees[0][5] != null)
		{*/
			header('Location: commandes.php');
		/*}
		else//Sinon
		{
			header('Location: connexion.php');
		}*/
		
		
		
		
		
	}
}

?>