<?php

$cod = $_REQUEST['xcod'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("update trabajadores set estado=0 where codigoTra='$cod'");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>

