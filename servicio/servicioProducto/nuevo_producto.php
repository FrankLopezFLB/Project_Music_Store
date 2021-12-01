<?php
$cod=NULL;
$nom = $_REQUEST['xnom'];
$desc = $_REQUEST['xdesc'];
$cat = $_REQUEST['xcat'];
$stock = $_REQUEST['xstock'];
$precio = $_REQUEST['xprecio'];
$estado = $_REQUEST['xestado'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("insert into productos values('$cod', '$nom', '$desc', $cat,$stock,$precio,$estado)");

echo "Producto grabado correctamente <br>".$cod."<br>".$nom."<br>".$desc."<br>".$cat."<br>".$stock."<br>".$precio."<br>".$estado;
?>