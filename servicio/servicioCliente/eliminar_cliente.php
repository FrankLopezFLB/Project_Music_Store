<?php

$cod = $_REQUEST['xcod'];

$cnx = new PDO("mysql:host=localhost;dbname=bd_musicstore","root","mysql");

$res = $cnx->query("update cliente set est=2 WHERE cod =$cod");

echo "Cliente Eliminado";
?>