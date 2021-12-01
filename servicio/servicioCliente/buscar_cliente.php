<?php

$cod = $_REQUEST['xcod'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("select nom, ape, tel, dir, ema, dni from cliente where cod='$cod' and est=1");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>