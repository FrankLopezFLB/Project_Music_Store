<?php
$cod=$_REQUEST['xcod'];
$nom = $_REQUEST['xnom'];
$desc = $_REQUEST['xdesc'];
$cat = $_REQUEST['xcat'];
$stock = $_REQUEST['xstock'];
$precio = $_REQUEST['xprecio'];
$estado = $_REQUEST['xestado'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("update productos set nombre='$nom',descripcion='$desc',codigoCat=$cat,stock=$stock,precio=$precio,estado=$estado where codigoProd=$cod");

echo "Producto grabado correctamente <br>".$cod."<br>".$nom."<br>".$desc."<br>".$cat."<br>".$stock."<br>".$precio."<br>".$estado;
?>