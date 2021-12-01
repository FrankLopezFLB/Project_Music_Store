<?php

$cod = $_REQUEST['xcod'];
$nom = $_REQUEST['xnom'];
$ape = $_REQUEST['xape'];
$tel = $_REQUEST['xtel'];
$dir = $_REQUEST['xdir'];
$ema = $_REQUEST['xema'];
$dni = $_REQUEST['xdni'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("update cliente set nom='$nom', ape = '$ape', tel = '$tel', dir = '$dir', ema = '$ema', dni = '$dni' WHERE cod =$cod and est=1");

echo "Cliente Actualizado <br>".$nom."<br>".$ape."<br>".$tel."<br>".$dir."<br>".$ema."<br>".$dni;
?>