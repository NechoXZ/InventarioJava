package modelo;

import database.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        Connection con = Conexion.conectar();

        if (con == null) {
            System.out.println("❌ No se pudo establecer conexión a la base de datos.");
            return lista;
        }

        String sql = "SELECT * FROM productos";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCodigo(rs.getString("codigo"));
                p.setProveedor(rs.getString("proveedor"));
                p.setCategoria(rs.getString("categoria"));
                p.setStock(rs.getInt("stock"));
                p.setValorCompra(rs.getDouble("valor_compra"));
                p.setValorVenta(rs.getDouble("valor_venta"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener productos: " + e.getMessage());
        }

        return lista;
    }

    public void insertarProducto(Producto p) {
        String sql = "INSERT INTO productos(nombre, codigo, proveedor, categoria, stock, valor_compra, valor_venta) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("❌ No se pudo conectar para insertar producto.");
                return;
            }

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getProveedor());
            ps.setString(4, p.getCategoria());
            ps.setInt(5, p.getStock());
            ps.setDouble(6, p.getValorCompra());
            ps.setDouble(7, p.getValorVenta());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto: " + e.getMessage());
        }
    }

    public void actualizarProducto(Producto p) {
        String sql = "UPDATE productos SET nombre=?, codigo=?, proveedor=?, categoria=?, stock=?, valor_compra=?, valor_venta=? "
                   + "WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("❌ No se pudo conectar para actualizar producto.");
                return;
            }

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getProveedor());
            ps.setString(4, p.getCategoria());
            ps.setInt(5, p.getStock());
            ps.setDouble(6, p.getValorCompra());
            ps.setDouble(7, p.getValorVenta());
            ps.setInt(8, p.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("❌ No se pudo conectar para eliminar producto.");
                return;
            }

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto: " + e.getMessage());
        }
    }
}