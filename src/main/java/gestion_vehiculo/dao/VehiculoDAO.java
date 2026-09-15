package gestion_vehiculo.dao;
import gestion_vehiculo.config.ConexionBD;
import gestion_vehiculo.model.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    private static final String SELECT_BASE = """
            SELECT placa,
                   marca,
                   modelo,
                   version,
                   color,
                   num_puestos,
                   num_puertas,
                   combustible,
                   kilometros,
                   cilindraje,
                   categoria
            FROM vehiculo
            """;

    private Connection getConnection() throws SQLException {
        return ConexionBD.getConnection();
    }

    public void insertar(Vehiculo vehiculo) throws SQLException {

        String sql = """
                INSERT INTO vehiculo (
                    placa,
                    marca,
                    modelo,
                    version,
                    color,
                    num_puestos,
                    num_puertas,
                    combustible,
                    kilometros,
                    cilindraje,
                    categoria
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, vehiculo.getPlaca());
            statement.setString(2, vehiculo.getMarca());
            statement.setString(3, vehiculo.getModelo());
            statement.setString(4, vehiculo.getVersion());
            statement.setString(5, vehiculo.getColor());
            statement.setInt(6, vehiculo.getNumPuestos());
            statement.setInt(7, vehiculo.getNumPuertas());
            statement.setString(8, vehiculo.getCombustible());
            statement.setDouble(9, vehiculo.getKilometros());
            statement.setDouble(10, vehiculo.getCilindraje());
            statement.setString(11, vehiculo.getCategoria());

            statement.executeUpdate();
        }
    }

    public List<Vehiculo> listar() throws SQLException {

        List<Vehiculo> vehiculos = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_BASE);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {
                vehiculos.add(mapearVehiculo(resultSet));
            }
        }

        return vehiculos;
    }

    public Vehiculo buscarPorPlaca(String placa)
            throws SQLException {

        String sql = SELECT_BASE + """
                WHERE placa = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, placa);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapearVehiculo(resultSet);
                }
            }
        }

        return null;
    }

    public List<Vehiculo> buscarPorCategoria(
            String categoria) throws SQLException {

        String sql = SELECT_BASE + """
                WHERE categoria = ?
                """;

        return buscarLista(sql, categoria);
    }

    public List<Vehiculo> buscarPorCombustible(
            String combustible) throws SQLException {

        String sql = SELECT_BASE + """
                WHERE combustible = ?
                """;

        return buscarLista(sql, combustible);
    }

    private List<Vehiculo> buscarLista(
            String sql,
            String parametro) throws SQLException {

        List<Vehiculo> vehiculos = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, parametro);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {
                    vehiculos.add(mapearVehiculo(resultSet));
                }
            }
        }

        return vehiculos;
    }

    public void actualizar(Vehiculo vehiculo)
            throws SQLException {

        String sql = """
                UPDATE vehiculo
                SET marca = ?,
                    modelo = ?,
                    version = ?,
                    color = ?,
                    num_puestos = ?,
                    num_puertas = ?,
                    combustible = ?,
                    kilometros = ?,
                    cilindraje = ?,
                    categoria = ?
                WHERE placa = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, vehiculo.getMarca());
            statement.setString(2, vehiculo.getModelo());
            statement.setString(3, vehiculo.getVersion());
            statement.setString(4, vehiculo.getColor());
            statement.setInt(5, vehiculo.getNumPuestos());
            statement.setInt(6, vehiculo.getNumPuertas());
            statement.setString(7, vehiculo.getCombustible());
            statement.setDouble(8, vehiculo.getKilometros());
            statement.setDouble(9, vehiculo.getCilindraje());
            statement.setString(10, vehiculo.getCategoria());
            statement.setString(11, vehiculo.getPlaca());

            statement.executeUpdate();
        }
    }

    public void eliminar(String placa) throws SQLException {

        String sql = """
                DELETE FROM vehiculo
                WHERE placa = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, placa);

            statement.executeUpdate();
        }
    }

    private Vehiculo mapearVehiculo(ResultSet resultSet)
            throws SQLException {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setPlaca(
                resultSet.getString("placa")
        );
        vehiculo.setMarca(
                resultSet.getString("marca")
        );
        vehiculo.setModelo(
                resultSet.getString("modelo")
        );
        vehiculo.setVersion(
                resultSet.getString("version")
        );
        vehiculo.setColor(
                resultSet.getString("color")
        );
        vehiculo.setNumPuestos(
                resultSet.getInt("num_puestos")
        );
        vehiculo.setNumPuertas(
                resultSet.getInt("num_puertas")
        );
        vehiculo.setCombustible(
                resultSet.getString("combustible")
        );
        vehiculo.setKilometros(
                resultSet.getDouble("kilometros")
        );
        vehiculo.setCilindraje(
                resultSet.getDouble("cilindraje")
        );
        vehiculo.setCategoria(
                resultSet.getString("categoria")
        );

        return vehiculo;
    }
}