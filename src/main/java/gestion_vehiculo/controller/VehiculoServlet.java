package gestion_vehiculo.controller;

import gestion_vehiculo.dao.VehiculoDAO;
import gestion_vehiculo.model.Vehiculo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/vehiculos")
public class VehiculoServlet extends HttpServlet {

    private VehiculoDAO vehiculoDAO;

    @Override
    public void init() {
        vehiculoDAO = new VehiculoDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            switch (accion == null ? "" : accion) {

                case "nuevo" ->
                        mostrarFormularioNuevo(request, response);

                case "buscar" ->
                        buscarVehiculos(request, response);

                case "editar" ->
                        mostrarFormularioEditar(request, response);

                case "eliminar" ->
                        eliminarVehiculo(request, response);

                default ->
                        listarVehiculos(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException(
                    "Error en la base de datos",
                    e
            );
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            switch (accion == null ? "" : accion) {

                case "crear" ->
                        crearVehiculo(request, response);

                case "actualizar" ->
                        actualizarVehiculo(request, response);

                default ->
                        response.sendRedirect(
                                request.getContextPath()
                                        + "/vehiculos"
                        );
            }

        } catch (SQLException e) {
            throw new ServletException(
                    "Error en la base de datos",
                    e
            );
        }
    }

    private void listarVehiculos(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Vehiculo> vehiculos =
                vehiculoDAO.listar();

        request.setAttribute(
                "vehiculos",
                vehiculos
        );

        request.getRequestDispatcher(
                "/vehiculo/listar.jsp"
        ).forward(request, response);
    }

    private void buscarVehiculos(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String valor = request.getParameter("valor");

        switch (tipo == null ? "" : tipo) {

            case "categoria" -> {

                List<Vehiculo> vehiculos =
                        vehiculoDAO.buscarPorCategoria(valor);

                request.setAttribute(
                        "vehiculos",
                        vehiculos
                );

                request.getRequestDispatcher(
                        "/vehiculo/buscar.jsp"
                ).forward(request, response);
            }

            case "combustible" -> {

                List<Vehiculo> vehiculos =
                        vehiculoDAO.buscarPorCombustible(valor);

                request.setAttribute(
                        "vehiculos",
                        vehiculos
                );

                request.getRequestDispatcher(
                        "/vehiculo/buscar.jsp"
                ).forward(request, response);
            }

            case "placa" -> {

                Vehiculo vehiculo =
                        vehiculoDAO.buscarPorPlaca(valor);

                request.setAttribute(
                        "vehiculo",
                        vehiculo
                );

                request.getRequestDispatcher(
                        "/vehiculo/buscar.jsp"
                ).forward(request, response);
            }

            default ->
                    listarVehiculos(request, response);
        }
    }

    private void mostrarFormularioEditar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String placa = request.getParameter("placa");

        Vehiculo vehiculo =
                vehiculoDAO.buscarPorPlaca(placa);

        request.setAttribute(
                "vehiculo",
                vehiculo
        );

        request.getRequestDispatcher(
                "/vehiculo/formulario.jsp"
        ).forward(request, response);
    }

    private void crearVehiculo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, IOException {

        Vehiculo vehiculo =
                obtenerVehiculoDesdeRequest(request);

        vehiculoDAO.insertar(vehiculo);

        response.sendRedirect(
                request.getContextPath()
                        + "/vehiculos"
        );
    }

    private void actualizarVehiculo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, IOException {

        Vehiculo vehiculo =
                obtenerVehiculoDesdeRequest(request);

        vehiculoDAO.actualizar(vehiculo);

        response.sendRedirect(
                request.getContextPath()
                        + "/vehiculos"
        );
    }

    private void eliminarVehiculo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, IOException {

        String placa = request.getParameter("placa");

        vehiculoDAO.eliminar(placa);

        response.sendRedirect(
                request.getContextPath()
                        + "/vehiculos"
        );
    }

    private void mostrarFormularioNuevo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/vehiculo/formulario.jsp"
        ).forward(request, response);
    }

    private Vehiculo obtenerVehiculoDesdeRequest(
            HttpServletRequest request) {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setPlaca(
                request.getParameter("placa")
        );

        vehiculo.setMarca(
                request.getParameter("marca")
        );

        vehiculo.setModelo(
                request.getParameter("modelo")
        );

        vehiculo.setVersion(
                request.getParameter("version")
        );

        vehiculo.setColor(
                request.getParameter("color")
        );

        vehiculo.setNumPuestos(
                Integer.parseInt(
                        request.getParameter("numPuestos")
                )
        );

        vehiculo.setNumPuertas(
                Integer.parseInt(
                        request.getParameter("numPuertas")
                )
        );

        vehiculo.setCombustible(
                request.getParameter("combustible")
        );

        vehiculo.setKilometros(
                Double.parseDouble(
                        request.getParameter("kilometros")
                )
        );

        vehiculo.setCilindraje(
                Double.parseDouble(
                        request.getParameter("cilindraje")
                )
        );

        vehiculo.setCategoria(
                request.getParameter("categoria")
        );

        return vehiculo;
    }
}
