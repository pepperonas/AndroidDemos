<?php

// handle function calls
if (function_exists($_GET['f'])) {
    $_GET['f']();
}


function printPhpInfo()
{
    $fp = fopen('info.txt', 'w');
    ob_start();
    phpinfo();
    $info = ob_get_contents();
    fwrite($fp, $info);
}

?>

<!doctype html>
<html>
<head>

    <!--jQuery-->
    <!--    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>-->

    <!--XML Serializer-->
    <script src="https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/vkbeautify/vkbeautify.0.99.00.beta.js"></script>

    <!--GMDL fonts-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!--GMDL css-->
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-deep_orange.min.css"/>
    <!--GMDL js-->
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <!--Customized styles-->
    <style>

        body {
            padding: 30px;
        }

        .demo-card-wide.mdl-card {
            width: 386px;
        }

        .demo-card-wide > .mdl-card__title {
            color: #00796B;
        }

        .desc_value {
            width: 45%;
            display: inline-block;
            float: left;
            margin: 0 1% 1% 0;
        }

        .btn-add {
            position: absolute;
            top: 112px;
            right: 12px;
        }

    </style>

</head>
<body>

<div class="demo-card-wide mdl-card mdl-shadow--2dp">

    <div class="mdl-card__title">Title</div>

    <div class="mdl-card__media" style="padding: 12px;">Headline</div>

    <div class="mdl-card__supporting-text">Question

        <!--        <form name="TextForm" id="container_layout_text" answerable="--><?php //echo time() ?><!--">-->
        <form name="TextForm" id="container_layout_text" answerable="">
            <!--First text field (description)-->
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label desc_value" id="des_div_0">
                <input class="mdl-textfield__input" type="text" id="des_in_0">
                <label class="mdl-textfield__label" for="des_in_0" id="des_la_0">Description...</label>
            </div>
            <!--Second text field (value)-->
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label desc_value" id="val_div_0">
                <input class="mdl-textfield__input" type="text" id="val_in_0">
                <label class="mdl-textfield__label" for="val_in_0" id="val_la_0">Value...</label>
            </div>
            <!--      GENERATED ROWS      -->
        </form>
    </div>

    <!--Action buttons to reset and save layout-->
    <div class="mdl-card__actions mdl-card--border">
        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" id="btnRemove">REMOVE</a>
        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" id="addText">ADD</a>
        <a download="layout.xml" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" id="btnSave">SAVE</a>
    </div>

</div>

<div id="the_snackbar" class="mdl-js-snackbar mdl-snackbar">
    <div class="mdl-snackbar__text"></div>
    <button class="mdl-snackbar__action" type="button"></button>
</div>


<script type="text/javascript">

    fields = []; // keeps track of added elements and allows to delete items

    let answerableId = new Date().getTime();

    //    window.onblur = function () {
    //        console.log('blurred');
    //    };

    //    window.onclick = function () {
    //        console.log('clicked');
    //    };


    //    setInterval(function () {
    //        console.log(new Date().getTime());
    //    }, 1000);

    document.getElementById("addText").onclick = function () {
        let container_layout = document.getElementById("container_layout_text");

        let _tmp_id = Math.floor(((fields.length + 2) / 2)); // offset since we providing one predefined and increment by 2

        // Left
        let newDivL = document.createElement("div");
        newDivL.className = "mdl-textfield mdl-js-textfield mdl-textfield--floating-label desc_value";
        newDivL.id = "des_div_" + _tmp_id;

        let newInputL = document.createElement("input");
        newInputL.className = "mdl-textfield__input";
        newInputL.type = "text";
        newInputL.id = "des_in_" + _tmp_id; // set the id
        newDivL.appendChild(newInputL);

        let newLabelL = document.createElement("label");
        newLabelL.className = "mdl-textfield__label";
        newLabelL.textContent = "Description...";
        newLabelL.id = "des_la_" + _tmp_id; // set the id
        newDivL.appendChild(newLabelL);

        // Right
        let newDivR = document.createElement("div");
        newDivR.className = "mdl-textfield mdl-js-textfield mdl-textfield--floating-label desc_value";
        newDivR.id = "val_div_" + _tmp_id;

        let newInputR = document.createElement("input");
        newInputR.className = "mdl-textfield__input";
        newInputR.type = "text";
        newInputR.id = "val_in_" + _tmp_id; // set the id
        newDivR.appendChild(newInputR);

        let newLabelR = document.createElement("label");
        newLabelR.className = "mdl-textfield__label";
        newLabelR.textContent = "Value...";
        newLabelR.id = "val_la_" + _tmp_id; // set the id
        newDivR.appendChild(newLabelR);


        // fix animation
        componentHandler.upgradeElement(newDivL);
        componentHandler.upgradeElement(newDivR);

        // add
        fields.push(newDivL);
        fields.push(newDivR);
        container_layout.appendChild(newDivL);
        container_layout.appendChild(newDivR);
    };

    // Save
    document.getElementById("btnSave").onclick = function () {
        let container_layout = document.getElementById("container_layout_text");

        container_layout.setAttribute("answerable", answerableId);

        // todo: load values into xml
        let divsInRow = container_layout.getElementsByTagName('div');
        container_layout.setAttribute("container_size", Math.floor((divsInRow.length + 1) / 2));
        let i = 0;
        for (i = 0; i < divsInRow.length; i++) {
            let childrenInRow = divsInRow[i];

            let children = childrenInRow.getElementsByTagName('input');

            for (j = 0; j < children.length; j++) {
                let subChild = children[j];
                let subChildId = subChild.id;
                console.log("" + subChildId + "=>" + subChild.value);
                console.log(subChildId + '---> val_in_' + Math.floor(i / 2));

                subChild.setAttribute("fk_answerable", answerableId);

                if (((subChildId === ('val_in_' + Math.floor(i / 2))) && subChild.value !== '')) {
                    console.log("WARNING! You set a value.");
                    let snackbarContainer = document.querySelector('#the_snackbar');
                    let data = {
                        message: 'WARNING! You set a value.',
                        timeout: 2000
                    };
                    snackbarContainer.MaterialSnackbar.showSnackbar(data);
                }

                subChild.setAttribute("description", subChild.value);
            }
        }

        // use XMLSerializer
        let xml = new XMLSerializer().serializeToString(container_layout);

        // remove namespace declaration
        let xmlWithoutNamespace = xml.replace(' xmlns="http://www.w3.org/1999/xhtml"', '');

        // prettify
        let formXml = vkbeautify.xml(xmlWithoutNamespace);
        makeTextFile(formXml);

        let link = document.getElementById('btnSave');
        link.href = makeTextFile(formXml);
    }
    ;

    document.getElementById("btnRemove").onclick = function () {
        let container_layout = document.getElementById("container_layout_text");

        if (fields.length === 0) {
            let snackbarContainer = document.querySelector('#the_snackbar');
            let data = {
                message: 'Don\'t be silly...',
                timeout: 2000
            };
            snackbarContainer.MaterialSnackbar.showSnackbar(data);
        } else {
            container_layout.removeChild(fields.pop());
            container_layout.removeChild(fields.pop());
        }
    };

    // function to store xml
    let textFile = null, makeTextFile = function (text) {
        let data = new Blob([text], {type: 'text/plain'});
        // If we are replacing a previously generated file we need to
        // manually revoke the object URL to avoid memory leaks.
        if (textFile !== null) {
            window.URL.revokeObjectURL(textFile);
        }
        textFile = window.URL.createObjectURL(data);
        return textFile;
    };

</script>


</body>

</html>
