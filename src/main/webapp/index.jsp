<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Vehículos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 80%;
            margin: 80px auto;
            text-align: center;
        }

        h1 {
            margin-bottom: 10px;
        }

        p {
            color: #555;
        }

        .menu {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 40px;
        }

        .card {
            background-color: white;
            width: 220px;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }

        .card h2 {
            margin-bottom: 20px;
        }

        .card a {
            display: inline-block;
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            background-color: #333;
            border-radius: 5px;
        }

        .card a:hover {
            background-color: #555;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Gestión de Vehículos</h1>

    <p>
        Sistema de gestión de usuarios y vehículos
    </p>

    <div class="menu">

        <div class="card">

            <h2>Usuarios</h2>

            <p>
                Gestionar usuarios del sistema.
            </p>

            <a href="${pageContext.request.contextPath}/usuario">
                Gestionar Usuarios
            </a>

        </div>

        <div class="card">

            <h2>Vehículos</h2>

            <p>
                Gestionar vehículos registrados.
            </p>

            <a href="${pageContext.request.contextPath}/vehiculos">
                Gestionar Vehículos
            </a>

        </div>

    </div>

</div>

</body>
</html>
