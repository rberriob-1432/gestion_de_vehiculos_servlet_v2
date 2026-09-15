<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Vehículos</title>
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
<h1>Gestión de Vehículos</h1>
<a href="${pageContext.request.contextPath}/vehiculos?accion=nuevo">
    Crear Vehículo
</a>
<div class="buscar">
    <h2>Buscar vehículo</h2>
    <form action="${pageContext.request.contextPath}/vehiculos" method="get">
        <input type="hidden" name="accion" value="buscar">
        <label for="tipo">Buscar por:</label>
        <select id="tipo" name="tipo" required>
            <option value="placa">Placa</option>
            <option value="categoria">Categoría</option>
            <option value="combustible">Combustible</option>
        </select>
        <input type="text"
               name="valor"
               placeholder="Valor a buscar"
               required>
        <button type="submit">
            Buscar
        </button>
    </form>
</div>
<table>
    <thead>
    <tr>
        <th>Placa</th>
        <th>Marca</th>
        <th>Modelo</th>
        <th>Versión</th>
        <th>Color</th>
        <th>Puestos</th>
        <th>Puertas</th>
        <th>Combustible</th>
        <th>Kilómetros</th>
        <th>Cilindraje</th>
        <th>Categoría</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="vehiculo" items="${vehiculos}">
        <tr>
            <td>${vehiculo.placa}</td>
            <td>${vehiculo.marca}</td>
            <td>${vehiculo.modelo}</td>
            <td>${vehiculo.version}</td>
            <td>${vehiculo.color}</td>
            <td>${vehiculo.numPuestos}</td>
            <td>${vehiculo.numPuertas}</td>
            <td>${vehiculo.combustible}</td>
            <td>${vehiculo.kilometros}</td>
            <td>${vehiculo.cilindraje}</td>
            <td>${vehiculo.categoria}</td>
            <td>
                <div class="acciones">
                    <a href="${pageContext.request.contextPath}/vehiculos?accion=editar&placa=${vehiculo.placa}">
                        Editar
                    </a>
                    <a href="${pageContext.request.contextPath}/vehiculos?accion=eliminar&placa=${vehiculo.placa}"
                       onclick="return confirm('¿Está seguro de eliminar este vehículo?');">
                        Eliminar
                    </a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="${pageContext.request.contextPath}/">
    Volver al inicio
</a>
</body>
</html>