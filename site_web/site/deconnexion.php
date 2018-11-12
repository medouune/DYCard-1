<?php
require ('session_verif.php');
$_SESSION=array();
session_destroy();
header('Location: index.html');
?>