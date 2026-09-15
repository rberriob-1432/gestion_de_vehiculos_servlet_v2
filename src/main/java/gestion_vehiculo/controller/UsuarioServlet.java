
package gestion_vehiculo.controller;

import gestion_vehiculo.dao.UsuarioDAO;
import gestion_vehiculo.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
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
                        buscarUsuario(request, response);

                case "editar" ->
                        mostrarFormularioEditar(request, response);

                case "eliminar" ->
                        eliminarUsuario(request, response);

                default ->
                        listarUsuarios(request, response);
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
                        crearUsuario(request, response);

                case "actualizar" ->
                        actualizarUsuario(request, response);

                default ->
                        response.sendRedirect(
                                request.getContextPath()
                                        + "/usuario"
                        );
            }

        } catch (SQLException e) {
            throw new ServletException(
                    "Error en la base de datos",
                    e
            );
        }
    }

    private void mostrarFormularioNuevo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/usuario/formulario.jsp"
        ).forward(request, response);
    }

    private void listarUsuarios(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Usuario> usuarios =
                usuarioDAO.listar();

        request.setAttribute(
                "usuarios",
                usuarios
        );

        request.getRequestDispatcher(
                "/usuario/listar.jsp"
        ).forward(request, response);
    }

    private void buscarUsuario(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String idParametro =
                request.getParameter("id");

        if (idParametro == null ||
                idParametro.isBlank()) {

            listarUsuarios(request, response);
            return;
        }

        int id = Integer.parseInt(idParametro);

        Usuario usuario =
                usuarioDAO.buscarPorId(id);

        request.setAttribute(
                "usuario",
                usuario
        );

        request.getRequestDispatcher(
                "/usuario/buscar.jsp"
        ).forward(request, response);
    }

    private void mostrarFormularioEditar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        Usuario usuario =
                usuarioDAO.buscarPorId(id);

        request.setAttribute(
                "usuario",
                usuario
        );

        request.getRequestDispatcher(
                "/usuario/formulario.jsp"
        ).forward(request, response);
    }

    private void crearUsuario(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        String nombre =
                request.getParameter("nombre");

        String contraseña =
                request.getParameter("contraseña");

        Usuario usuario = new Usuario(
                id,
                nombre,
                contraseña
        );

        usuarioDAO.insertar(usuario);

        response.sendRedirect(
                request.getContextPath()
                        + "/usuario"
        );
    }

    private void actualizarUsuario(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        String nombre =
                request.getParameter("nombre");

        String contraseña =
                request.getParameter("contraseña");

        Usuario usuario = new Usuario(
                id,
                nombre,
                contraseña
        );

        usuarioDAO.actualizar(usuario);

        response.sendRedirect(
                request.getContextPath()
                        + "/usuario"
        );
    }

    private void eliminarUsuario(
            HttpServletRequest request,
            HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        usuarioDAO.eliminar(id);

        response.sendRedirect(
                request.getContextPath()
                        + "/usuario"
        );
    }
}