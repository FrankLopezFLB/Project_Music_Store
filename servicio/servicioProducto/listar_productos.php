<?php

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("SELECT codigoProd,nombre,descripcion,nombrecat,stock,precio
FROM productos p join categorias  c on p.codigoCat=c.codigoCat WHERE estado = 1 order by codigoProd");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>