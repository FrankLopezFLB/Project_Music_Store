<?php

$cod = $_REQUEST['xcod'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("SELECT codigoProd,nombre,descripcion,c.codigoCat,stock,precio
FROM productos p join categorias  c on p.codigoCat=c.codigoCat WHERE estado = 1 and codigoProd='$cod'");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>