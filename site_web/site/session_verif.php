<?php
session_start();
if(!$_SESSION['logged'])
{
	header('location:connexion.php');
}
if (isset($_SESSION['identifiant']))
{
	$login=htmlentities($_SESSION[identifiant]);
}
if (isset($_SESSION['password']))
{
	$pass=htmlentities($_SESSION['password']);
}
?>
