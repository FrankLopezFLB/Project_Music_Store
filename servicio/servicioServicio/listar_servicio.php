<?php

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("SELECT codigoServ,nombre,descripcion,nombrecat,precio
FROM servicios s join categorias  c on s.codigoCat=c.codigoCat WHERE estado = 1 order by codigoServ");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>