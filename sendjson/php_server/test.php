<?php


if (function_exists($_GET['f'])) {
    $_GET['f']();
}



function writeJsonInFile()
{
    $fp = fopen('out.txt', 'w');
    $jsonString = $_POST['json'];

    fwrite($fp, $jsonString);
    fclose($fp);


    $fp_format = fopen('out_format.txt', 'w');
    $json = json_decode($jsonString, true);

    // default
    // fwrite($fp_format, "1. ");
    // fwrite($fp_format, $json['some_variable_name_with_many_characters_1']);
    // fwrite($fp_format, "  2. ");
    // fwrite($fp_format, $json['some_variable_name_with_many_characters_2']);
    // fwrite($fp_format, "  3. ");
    // fwrite($fp_format, $json['some_variable_name_with_many_characters_3']);
    // fclose($fp_format);




    $ks = "";
    $vs = "";

    // compressed json
    foreach ($json[0] as $item => $val) {
        $ks.=$item.",";
        $vs.=$json[1][$val].",";
    }

    $ks = substr_replace($ks, "", -1);
    $vs = substr_replace($vs, "", -1);

    fwrite($fp_format, $ks);
    fwrite($fp_format, "__");
    fwrite($fp_format, $vs);

    fclose($fp_format);
}



function createDatabase()
{
    $db_user = "root";
    $db_pw = "";
    $db_name = "the_database";

    // Connecting with server
    $conn = mysqli_connect("localhost", $db_user, $db_pw);

    $tbl_data = "data_table";

    // Check connection
    if ($conn === false) {
        die("Error while connecting to database: " . mysqli_connect_error());
    }

    // Attempt create database query execution
    $sql = "CREATE DATABASE $db_name;";
    if (mysqli_query($conn, $sql)) {
        echo "Database created successfully";
    } else {
        echo "Error while creating database: $sql. " . mysqli_error($conn);
    }


    // Connecting with database
    $conn = mysqli_connect("localhost", $db_user, $db_pw, $db_name);

    // Check connection
    if ($conn === false) {
        die("Error while connecting to database:" . mysqli_connect_error());
    }

    // Attempt create table query execution
    $sql = "CREATE TABLE $tbl_data(
        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        some_variable_name_with_many_characters_1 VARCHAR(70) NOT NULL,
        some_variable_name_with_many_characters_2 VARCHAR(70) NOT NULL,
        some_variable_name_with_many_characters_3 VARCHAR(70) NOT NULL
    )";
    if (mysqli_query($conn, $sql)) {
        echo "Table created successfully.";
    } else {
        echo "Error while creating table: $sql. " . mysqli_error($conn);
    }

    // Close connection
    mysqli_close($conn);
}



function insertJsonInDatabase()
{
    $db_user = "root";
    $db_pw = "";
    $db_name = "the_database";

    $tbl_data = "data_table";

    // Connecting with database
    $conn = mysqli_connect("localhost", $db_user, $db_pw, $db_name);

    // Check connection
    if ($conn === false) {
        die("Error while connecting to database:" . mysqli_connect_error());
    }


    $jsonString = $_POST['json'];
    $json = json_decode($jsonString, true);
    $var1 = $json['some_variable_name_with_many_characters_1'];
    $var2 = $json['some_variable_name_with_many_characters_2'];
    $var3 = $json['some_variable_name_with_many_characters_3'];

    // Attempt insert query execution
    $sql = "INSERT INTO $tbl_data (
      some_variable_name_with_many_characters_1,
      some_variable_name_with_many_characters_2,
      some_variable_name_with_many_characters_3)
      VALUES
      ('$var1',
       '$var2',
       '$var3')";
    if (mysqli_query($conn, $sql)) {
        echo "Records inserted successfully.";
    } else {
        echo "Error while inserting: $sql. " . mysqli_error($conn);
    }

    // Close connection
    mysqli_close($conn);
}



function insertJsonInDatabaseCompressed()
{
    $db_user = "root";
    $db_pw = "";
    $db_name = "the_database";

    $tbl_data = "data_table";

    // Connecting with database
    $conn = mysqli_connect("localhost", $db_user, $db_pw, $db_name);

    // Check connection
    if ($conn === false) {
        die("Error while connecting to database:" . mysqli_connect_error());
    }


    $ks = "";
    $vs = "";


    $jsonString = $_POST['json'];
    $json = json_decode($jsonString, true);

    // compressed json
    foreach ($json[0] as $item => $val) {
        $ks.=$item.",";
        $vs.="'".$json[1][$val]."'".",";
    }

    $ks = substr_replace($ks, "", -1);
    $vs = substr_replace($vs, "", -1);


    $sql = "INSERT INTO $tbl_data ( ".$ks." ) VALUES (  ".$vs." )";
    if (mysqli_query($conn, $sql)) {
        echo "Records inserted successfully.";
    } else {
        echo "Error while inserting: $sql. " . mysqli_error($conn);
    }

    $fp_format = fopen('out_format.txt', 'w');


    fwrite($fp_format, $sql);

    fclose($fp_format);

    // Close connection
    mysqli_close($conn);
}



function printPhpInfo()
{
    $fp = fopen('info.txt', 'w');
    ob_start();
    phpinfo();
    $info = ob_get_contents();
    fwrite($fp, $info);
}
