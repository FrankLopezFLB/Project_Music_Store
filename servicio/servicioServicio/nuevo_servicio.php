<?php
$cod=NULL;
$nom = $_REQUEST['xnom'];
$desc = $_REQUEST['xdesc'];
$cat = $_REQUEST['xcat'];
$precio = $_REQUEST['xprecio'];
$estado = $_REQUEST['xestado'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("INSERT INTO servicios VALUES('$cod','$nom', '$desc', $cat, $precio, $estado)");

echo "Servicio grabado correctamente <br>".$cod."<br>".$nom."<br>".$desc."<br>".$cat."<br>".$precio."<br>".$estado;
?>