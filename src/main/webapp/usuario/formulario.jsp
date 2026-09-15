<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        form {
            width: 400px;
        }
        label {
            display: block;
            margin-top: 15px;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }
        button {
            margin-top: 20px;
            padding: 10px 15px;
        }
    </style>
</head>
<body>
<h1>
    <c:choose>
        <c:when test="${not empty usuario}">
            Editar Usuario
        </c:when>
        <c:otherwise>
            Crear Usuario
        </c:otherwise>
    </c:choose>
</h1>
<form action="${pageContext.request.contextPath}/usuario"
      method="post">
    <c:choose>
        <c:when test="${not empty usuario}">
            <input type="hidden"
                   name="accion"
                   value="actualizar">
        </c:when>
        <c:otherwise>
            <input type="hidden"
                   name="accion"
                   value="crear">
        </c:otherwise>
    </c:choose>
    <label for="id">
        ID
    </label>
    <input type="number"
           id="id"
           name="id"
           value="${usuario.id}"
           <c:if test="${not empty usuario}">
               readonly
           </c:if>
           required>
    <label for="nombre">
        Nombre
    </label>
    <input type="text"
           id="nombre"
           name="nombre"
           value="${usuario.nombre}"
           required>
    <label for="contraseña">
        Contraseña
    </label>
    <input type="password"
           id="contraseña"
           name="contraseña"
           value="${usuario.contraseña}"
           required>
    <button type="submit">
        <c:choose>
            <c:when test="${not empty usuario}">
                Actualizar
            </c:when>
            <c:otherwise>
                Crear
            </c:otherwise>
        </c:choose>
    </button>
</form>
<br>
<a href="${pageContext.request.contextPath}/usuario">
    Volver a usuarios
</a>
</body>
</html>