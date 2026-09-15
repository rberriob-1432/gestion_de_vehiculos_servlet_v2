<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Vehículo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        form {
            width: 500px;
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
        <c:when test="${not empty vehiculo}">
            Editar Vehículo
        </c:when>
        <c:otherwise>
            Crear Vehículo
        </c:otherwise>
    </c:choose>
</h1>
<form action="${pageContext.request.contextPath}/vehiculos"
      method="post">
    <c:choose>
        <c:when test="${not empty vehiculo}">
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
    <label for="placa">
        Placa
    </label>
    <input type="text"
           id="placa"
           name="placa"
           value="${vehiculo.placa}"
           <c:if test="${not empty vehiculo}">
               readonly
           </c:if>
           required>
    <label for="marca">
        Marca
    </label>
    <input type="text"
           id="marca"
           name="marca"
           value="${vehiculo.marca}"
           required>
    <label for="modelo">
        Modelo
    </label>
    <input type="text"
           id="modelo"
           name="modelo"
           value="${vehiculo.modelo}"
           required>
    <label for="version">
        Versión
    </label>
    <input type="text"
           id="version"
           name="version"
           value="${vehiculo.version}"
           required>
    <label for="color">
        Color
    </label>
    <input type="text"
           id="color"
           name="color"
           value="${vehiculo.color}"
           required>
    <label for="numPuestos">
        Número de puestos
    </label>
    <input type="number"
           id="numPuestos"
           name="numPuestos"
           value="${vehiculo.numPuestos}"
           min="1"
           required>
    <label for="numPuertas">
        Número de puertas
    </label>
    <input type="number"
           id="numPuertas"
           name="numPuertas"
           value="${vehiculo.numPuertas}"
           min="1"
           required>
    <label for="combustible">
        Combustible
    </label>
    <input type="text"
           id="combustible"
           name="combustible"
           value="${vehiculo.combustible}"
           required>
    <label for="kilometros">
        Kilómetros
    </label>
    <input type="number"
           id="kilometros"
           name="kilometros"
           value="${vehiculo.kilometros}"
           min="0"
           step="0.01"
           required>
    <label for="cilindraje">
        Cilindraje
    </label>
    <input type="number"
           id="cilindraje"
           name="cilindraje"
           value="${vehiculo.cilindraje}"
           min="0"
           step="0.01"
           required>
    <label for="categoria">
        Categoría
    </label>
    <input type="text"
           id="categoria"
           name="categoria"
           value="${vehiculo.categoria}"
           required>
    <button type="submit">
        <c:choose>
            <c:when test="${not empty vehiculo}">
                Actualizar
            </c:when>
            <c:otherwise>
                Crear
            </c:otherwise>
        </c:choose>
    </button>
</form>
<br>
<a href="${pageContext.request.contextPath}/vehiculos">
    Volver a vehículos
</a>
</body>
</html>