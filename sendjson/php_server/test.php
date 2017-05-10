<?php

if(function_exists($_GET['f'])) {
   $_GET['f']();
}

function writeJson(){
  $fp = fopen('out.txt', 'w');
  $jsonString = $_POST['json'];
  fwrite($fp, $jsonString);
  fclose($fp);
}



 ?>
