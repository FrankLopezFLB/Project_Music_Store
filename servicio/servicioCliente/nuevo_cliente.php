<?php

$nom = $_REQUEST['xnom'];
$ape = $_REQUEST['xape'];
$tel = $_REQUEST['xtel'];
$dir = $_REQUEST['xdir'];
$ema = $_REQUEST['xema'];
$dni = $_REQUEST['xdni'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("insert into cliente values('null', '$nom', '$ape' , '$tel', '$dir','$ema','$dni',1)");

echo "Cliente grabada correctamente <br>".$nom."<br>".$ape."<br>".$tel."<br>".$dir."<br>".$ema."<br>".$dni;
?>