<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Test jQuery</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript"> 
    function warna( ){
        $('#ke')
        .toggleClass('diwarnai');;
    }
    function warna2( ){
        $('p.kedua')
        .toggleClass('diwarnai');;
    }
    function hitung(){
        alert("ada "+ $("p").size() + " Paragraf .");
    }
    </script>
    <style type="text/css">
    p.diwarnai {
        background-color: yellow;
    }
    p.kedua {
        font-weight: normal;
    }
    </style>
</head>
<body>
    <h1>Pilih Sebuah Paragraf</h1>
    <div>
        <p>P 1.</p>
        <p class="kedua">P 2.</p>
        <p id="ke">P 3.</p>
        <p>P 4.</p>
    </div>
    <form>
        <input type="button" value="Warnai" onclick="warna()"> </input>
        <input type="button" value="warnai 2" onclick="warna2()"> </input>
        <input type="button" value="Hitung" onclick="hitung()"> </input>
    </form>
</body>
</html>