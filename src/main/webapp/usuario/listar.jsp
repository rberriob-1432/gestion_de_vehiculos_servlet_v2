<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        h1 {
            margin-bottom: 20px;
        }
        a, button {
            padding: 8px 12px;
            text-decoration: none;
            cursor: pointer;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .acciones {
            display: flex;
           gap: 8px;
        }
        .buscar {
            margin-top: 20px;
       }
    </style>
</head>
<body>
<h1>Gestión de Usuarios</h1>
<a href="${pageContext.request.contextPath}/usuario/formulario.jsp">
    Crear Usuario
</a>
<div class="buscar">
    <h2>Buscar usuario por ID</h2>
    <form action="${pageContext.request.contextPath}/usuario"
          method="get">
        <input type="hidden"
               name="accion"
               value="buscar">
        <label for="id">ID:</label>
        <input type="number"
               id="id"
               name="id"
               required>
        <button type="submit">
            Buscar
        </button>
    </form>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Contraseña</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="usuario" items="${usuarios}">
        <tr>
            <td>
                ${usuario.id}
            </td>
            <td>
                ${usuario.nombre}
            </td>
            <td>
                ${usuario.contraseña}
            </td>
            <td>
                <div class="acciones">
                    <a href="${pageContext.request.contextPath}/usuario?accion=editar&id=${usuario.id}">
                        Editar
                    </a>
                    <a href="${pageContext.request.contextPath}/usuario?accion=eliminar&id=${usuario.id}"
                       onclick="return confirm('¿Está seguro de eliminar este usuario?');">
                        Eliminar
                    </a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody
</table>
</body>
</html>
