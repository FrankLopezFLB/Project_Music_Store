<?php

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("SELECT codigoTra,nombreTra,apellidoTra,dniTra,nombreCar,telefonoTra,correoTra
FROM trabajadores t join cargos  c on t.codigoCar=c.codigoCar WHERE estado = 1 order by codigoTra");

$datos = array();

foreach($res as $row)
{
	$datos[] = $row;
}

echo json_encode($datos);
?>