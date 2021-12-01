<?php

$cod = $_REQUEST['xcod'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("SELECT codigoServ,nombre,descripcion,c.codigoCat,precio
FROM servicios s join categorias  c on s.codigoCat=c.codigoCat WHERE estado = 1 and codigoServ='$cod'");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>