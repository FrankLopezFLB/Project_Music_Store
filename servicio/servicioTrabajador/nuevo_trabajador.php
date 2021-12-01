<?php
$cod=NULL;
$nom = $_REQUEST['xnom'];
$ape = $_REQUEST['xape'];
$dni = $_REQUEST['xdni'];
$car = $_REQUEST['xcar'];
$tele = $_REQUEST['xtele'];
$dire = $_REQUEST['xdire'];
$corre = $_REQUEST['xcorre'];
$clav = $_REQUEST['xclav'];
$estado = $_REQUEST['xestado'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("insert into trabajadores values('$cod', '$nom', '$ape','$dni', $car,'$tele','$dire','$corre','$clav',$estado)");

echo "Trabajador grabado correctamente <br>".$cod."<br>".$nom."<br>".$ape."<br>".$dni."<br>".$car."<br>".$tele."<br>".$dire."<br>".$corre."<br>".$clav."<br>".$estado;
?>