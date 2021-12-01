<?php
$cod=$_REQUEST['xcod'];
$nom = $_REQUEST['xnom'];
$ape = $_REQUEST['xape'];
$dni = $_REQUEST['xdni'];
$car = $_REQUEST['xcar'];
$tele = $_REQUEST['xtele'];
$dire = $_REQUEST['xdire'];
$corre = $_REQUEST['xcorre'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("update trabajadores set nombreTra='$nom',apellidoTra='$ape',dniTra='$dni',codigoCar=$car,telefonoTra='$tele',direccionTra='$dire',correoTra='$corre' where codigoTra=$cod");

echo "Trabajador actualizado correctamente <br>".$cod."<br>".$nom."<br>".$ape."<br>".$dni."<br>".$car."<br>".$tele."<br>".$dire."<br>".$corre;
?>