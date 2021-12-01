<?php

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("SELECT codigoCar,nombreCar FROM cargos");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>