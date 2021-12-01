<?php

$cod = $_REQUEST['xcod'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("select nombreTra,apellidoTra,dniTra,codigoCar,telefonoTra,direccionTra,correoTra from trabajadores where codigoTra ='$cod'");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>

