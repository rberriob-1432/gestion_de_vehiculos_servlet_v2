```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        table {
            border-collapse: collapse;
            width: 600px;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Resultado de búsqueda</h1>
<c:choose>
    <c:when test="${not empty usuario}">
        <table>
            <tr>
                <th>ID</th>
                <td>${usuario.id}</td>
            </tr>
            <tr>
                <th>Nombre</th>
                <td>${usuario.nombre}</td>
            </tr>
            <tr>
                <th>Contraseña</th>
                <td>${usuario.contraseña}</td>
            </tr>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/usuario?accion=editar&id=${usuario.id}">
            Editar
        </a>
    </c:when>
    <c:otherwise>
        <p>
            No se encontró ningún usuario con ese ID.
        </p>
    </c:otherwise>
</c:choose>
<br><br>
<a href="${pageContext.request.contextPath}/usuario">
    Volver a usuarios
</a>
</body>
</html>