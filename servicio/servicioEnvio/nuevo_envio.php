<?php

$descripcion = $_REQUEST['xdescripcion'];
$idProd = $_REQUEST['xidprod'];
$idCli = $_REQUEST['xidcli'];
$direccion = $_REQUEST['xdireccion'];
$distrito = $_REQUEST['xdistrito'];
$cantidad = $_REQUEST['xcantidad'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("insert into envios values(null, '$descripcion',$idProd,$idCli, '$direccion', '$distrito', $cantidad, 1)");

echo "Envio grabado correctamente <br>".$descripcion."<br>".$idProd."<br>".$idCli."<br>".$cantidad;
?>