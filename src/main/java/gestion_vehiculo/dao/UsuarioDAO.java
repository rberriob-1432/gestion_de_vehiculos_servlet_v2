package gestion_vehiculo.dao;

import gestion_vehiculo.config.ConexionBD;
import gestion_vehiculo.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection getConnection() throws SQLException {
        return ConexionBD.getConnection();
    }

    public void insertar(Usuario usuario) throws SQLException {

        String sql = """
                INSERT INTO usuario (
                    id,
                    nombre,
                    contraseña
                )
                VALUES (?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getContraseña());

            statement.executeUpdate();
        }
    }

    public List<Usuario> listar() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = """
                SELECT id,
                       nombre,
                       contraseña
                FROM usuario
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {
                usuarios.add(mapearUsuario(resultSet));
            }
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) throws SQLException {

        String sql = """
                SELECT id,
                       nombre,
                       contraseña
                FROM usuario
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapearUsuario(resultSet);
                }
            }
        }

        return null;
    }

    public void actualizar(Usuario usuario) throws SQLException {

        String sql = """
                UPDATE usuario
                SET nombre = ?,
                    contraseña = ?
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
            statement.setInt(3, usuario.getId());

            statement.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {

        String sql = """
                DELETE FROM usuario
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    private Usuario mapearUsuario(ResultSet resultSet)
            throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setId(resultSet.getInt("id"));
        usuario.setNombre(resultSet.getString("nombre"));
        usuario.setContraseña(
                resultSet.getString("contraseña")
        );

        return usuario;
    }
}
