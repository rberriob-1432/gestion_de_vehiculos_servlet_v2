<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Vehículo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
        }
        th {
            background-color: #f2f2f2;
        }
        .acciones {
            display: flex;
            gap: 8px;
        }
    </style>
</head>
<body>
<h1>Resultado de búsqueda</h1>
<%-- Búsqueda por placa --%>
<c:choose>
    <c:when test="${not empty vehiculo}">
        <table>
            <tr>
                <th>Placa</th>
                <td>${vehiculo.placa}</td>
            </tr>
            <tr>
                <th>Marca</th>
                <td>${vehiculo.marca}</td>
            </tr>
            <tr>
                <th>Modelo</th>
                <td>${vehiculo.modelo}</td>
            </tr>
            <tr>
                <th>Versión</th>
                <td>${vehiculo.version}</td>
            </tr>
            <tr>
                <th>Color</th>
                <td>${vehiculo.color}</td>
            </tr>
            <tr>
                <th>Puestos</th>
                <td>${vehiculo.numPuestos}</td>
            </tr>
            <tr>
                <th>Puertas</th>
                <td>${vehiculo.numPuertas}</td>
            </tr>
            <tr>
                <th>Combustible</th>
                <td>${vehiculo.combustible}</td>
            </tr>
            <tr>
                <th>Kilómetros</th>
                <td>${vehiculo.kilometros}</td>
            </tr>
            <tr>
                <th>Cilindraje</th>
                <td>${vehiculo.cilindraje}</td>
            </tr>
            <tr>
                <th>Categoría</th>
                <td>${vehiculo.categoria}</td>
            </tr>
        </table>
        <br>
        <div class="acciones">
            <a href="${pageContext.request.contextPath}/vehiculos?accion=editar&placa=${vehiculo.placa}">
                Editar
            </a>
            <a href="${pageContext.request.contextPath}/vehiculos?accion=eliminar&placa=${vehiculo.placa}"
               onclick="return confirm('¿Está seguro de eliminar este vehículo?');">
                Eliminar
            </a>
        </div>
    </c:when>
    <%-- Búsqueda por categoría o combustible --%>
    <c:when test="${not empty vehiculos}">
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
    </c:when>
    <c:otherwise>
        <p>
            No se encontraron vehículos.
        </p>
    </c:otherwise>
</c:choose>
<br>
<a href="${pageContext.request.contextPath}/vehiculos">
    Volver a vehículos
</a>
</body>
</html>