<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="main.model.*"%>
<%
    Vector modeles = (Vector) request.getAttribute("listeModeles");
    String action = (String) request.getAttribute("action");
    Ordinateur ordinateur = null;

    if(action != null && action.equals("modifier")) {
        ordinateur = (Ordinateur) request.getAttribute("ordinateur");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire Ordinateur</title>

    <style>
        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:Arial, Helvetica, sans-serif;
        }

        body{
            background:#f4f6f9;
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .container{
            width:420px;
            background:#fff;
            padding:30px;
            border-radius:10px;
            box-shadow:0 8px 20px rgba(0,0,0,0.15);
        }

        h2{
            text-align:center;
            margin-bottom:25px;
            color:#333;
        }

        p{
            margin-bottom:18px;
        }

        label{
            display:block;
            margin-bottom:6px;
            font-weight:bold;
            color:#444;
        }

        input[type="text"],
        select{
            width:100%;
            padding:10px;
            border:1px solid #ccc;
            border-radius:5px;
            font-size:15px;
            transition:0.3s;
        }

        input[type="text"]:focus,
        select:focus{
            border-color:#007BFF;
            outline:none;
            box-shadow:0 0 5px rgba(0,123,255,0.4);
        }

        input[type="submit"]{
            width:100%;
            padding:12px;
            background:#007BFF;
            color:white;
            border:none;
            border-radius:5px;
            font-size:16px;
            cursor:pointer;
            transition:0.3s;
        }

        input[type="submit"]:hover{
            background:#0056b3;
        }
    </style>

</head>
<body>

<div class="container">

    <p><%= request.getAttribute("message") %></p>

</div>

</body>
</html>