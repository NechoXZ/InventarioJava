package controlador;

import modelo.Producto;
import modelo.ProductoDAO;
import vista.ProductoVista;
import vista.FormularioProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoControlador {

    private final ProductoVista vista;
    private final ProductoDAO dao;

    public ProductoControlador(ProductoVista vista) {
        this.vista = vista;
        this.dao = new ProductoDAO();
    }

    public void cargarProductos() {
        try {
            List<Producto> lista = dao.obtenerTodos();
            DefaultTableModel modelo = vista.getModeloTabla();
            modelo.setRowCount(0); // Limpiar tabla

            for (Producto p : lista) {
                modelo.addRow(new Object[]{
                        p.getId(), p.getNombre(), p.getCodigo(), p.getProveedor(),
                        p.getCategoria(), p.getStock(), p.getValorCompra(), p.getValorVenta()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al cargar productos: " + e.getMessage());
        }
    }

    public void abrirFormularioAgregar() {
        try {
            FormularioProducto dialogo = new FormularioProducto(null);
            dialogo.setVisible(true);
            if (dialogo.isGuardado()) {
                dao.insertarProducto(dialogo.getProducto());
                cargarProductos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al agregar producto: " + e.getMessage());
        }
    }

    public void abrirFormularioEditar(int fila) {
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "⚠️ Debes seleccionar un producto para editar.");
            return;
        }

        try {
            Producto p = obtenerProductoDesdeFila(fila);
            FormularioProducto dialogo = new FormularioProducto(p);
            dialogo.setVisible(true);

            if (dialogo.isGuardado()) {
                dao.actualizarProducto(dialogo.getProducto());
                cargarProductos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al editar producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int fila) {
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "⚠️ Debes seleccionar un producto para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Producto p = obtenerProductoDesdeFila(fila);
                dao.eliminarProducto(p.getId());
                cargarProductos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "❌ Error al eliminar producto: " + e.getMessage());
            }
        }
    }

    private Producto obtenerProductoDesdeFila(int fila) {
        DefaultTableModel modelo = vista.getModeloTabla();
        return new Producto(
                (int) modelo.getValueAt(fila, 0),
                (String) modelo.getValueAt(fila, 1),
                (String) modelo.getValueAt(fila, 2),
                (String) modelo.getValueAt(fila, 3),
                (String) modelo.getValueAt(fila, 4),
                (int) modelo.getValueAt(fila, 5),
                (double) modelo.getValueAt(fila, 6),
                (double) modelo.getValueAt(fila, 7)
        );
    }
}