<?php

// handle function calls
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

    // compressed json
    $size = $json[0]["size"];
    fwrite($fp_format, "size=".$size);
    fwrite($fp_format, PHP_EOL);
    fwrite($fp_format, PHP_EOL);

    for ($i = 0; $i < $size; $i++) {
        $ks = "";
        $vs = "";

        foreach ($json[1] as $item => $val) {
            $ks.=$item.",";
            $vs.="'".$json[2][$i][$val]."',";
        }
        $ks = substr_replace($ks, "", -1);
        $vs = substr_replace($vs, "", -1);
        fwrite($fp_format, $ks);
        fwrite($fp_format, " ");
        fwrite($fp_format, $vs);
        fwrite($fp_format, PHP_EOL);
    }


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
        some_variable_name_with_many_characters_3 INTEGER NOT NULL
    )";
    if (mysqli_query($conn, $sql)) {
        echo "Table created successfully.";
    } else {
        echo "Error while creating table: $sql. " . mysqli_error($conn);
    }

    // Close connection
    mysqli_close($conn);
}


function insertJson()
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

    // write output to verify (not needed)
    $fp_format = fopen('out_format.txt', 'w');

    // receive the content
    $jsonString = $_POST['json'];
    $json = json_decode($jsonString, true);

    fwrite($fp_format, "Mapping...");
    fwrite($fp_format, PHP_EOL); // new line
    fwrite($fp_format, $jsonString);
    fwrite($fp_format, PHP_EOL . PHP_EOL); // new line

    fwrite($fp_format, "Insert...");
    fwrite($fp_format, PHP_EOL); // new line
    // get size of the container and iterate
    $size = $json[0]["size"];
    for ($i = 0; $i < $size; $i++) {
        $ks = "";
        $vs = "";

        foreach ($json[1] as $item => $val) {
            $ks.=$item.","; // key
            // data is stored in the third json-object
            $vs.="'".$json[2][$i][$val]."',";
        }
        $ks = substr_replace($ks, "", -1);
        $vs = substr_replace($vs, "", -1);

        $sql = "INSERT INTO $tbl_data ( ".$ks." ) VALUES (  ".$vs." )";

        // print sql statement to verify (not needed)
        fwrite($fp_format, $sql);

        if (mysqli_query($conn, $sql)) {
            echo "Records inserted successfully.";
        } else {
            fwrite($fp_format, "Error while inserting: $sql. " . mysqli_error($conn));
            // close the file
            fclose($fp_format);
            // close the connection
            mysqli_close($conn);
            return "no!";
        }
    }

    // close the file
    fclose($fp_format);

    // close the connection
    mysqli_close($conn);

    return "success!";
}



function printPhpInfo()
{
    $fp = fopen('info.txt', 'w');
    ob_start();
    phpinfo();
    $info = ob_get_contents();
    fwrite($fp, $info);
}
