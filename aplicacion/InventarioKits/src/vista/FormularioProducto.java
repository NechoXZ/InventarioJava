package vista;

import modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class FormularioProducto extends JDialog {

    private JTextField txtNombre, txtCodigo, txtProveedor, txtCategoria, txtStock, txtValorCompra, txtValorVenta;
    private JButton btnGuardar, btnCancelar;
    private boolean guardado = false;
    private Producto producto;

    public FormularioProducto(Producto p) {
        setTitle(p == null ? "Agregar Producto" : "Editar Producto");
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 10, 10));

        txtNombre = new JTextField();
        txtCodigo = new JTextField();
        txtProveedor = new JTextField();
        txtCategoria = new JTextField();
        txtStock = new JTextField();
        txtValorCompra = new JTextField();
        txtValorVenta = new JTextField();

        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Código:")); add(txtCodigo);
        add(new JLabel("Proveedor:")); add(txtProveedor);
        add(new JLabel("Categoría:")); add(txtCategoria);
        add(new JLabel("Stock:")); add(txtStock);
        add(new JLabel("Valor Compra:")); add(txtValorCompra);
        add(new JLabel("Valor Venta:")); add(txtValorVenta);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar); add(btnCancelar);

        if (p != null) {
            producto = p;
            cargarProductoEnFormulario(p);
        }

        btnGuardar.addActionListener(e -> guardarProducto());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarProductoEnFormulario(Producto p) {
        txtNombre.setText(p.getNombre());
        txtCodigo.setText(p.getCodigo());
        txtProveedor.setText(p.getProveedor());
        txtCategoria.setText(p.getCategoria());
        txtStock.setText(String.valueOf(p.getStock()));
        txtValorCompra.setText(String.valueOf(p.getValorCompra()));
        txtValorVenta.setText(String.valueOf(p.getValorVenta()));
    }

    private void guardarProducto() {
        try {
            // Validaciones básicas
            if (txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty() || txtStock.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Los campos Nombre, Código y Stock son obligatorios.");
                return;
            }

            if (producto == null) {
                producto = new Producto();
            }

            producto.setNombre(txtNombre.getText());
            producto.setCodigo(txtCodigo.getText());
            producto.setProveedor(txtProveedor.getText());
            producto.setCategoria(txtCategoria.getText());
            producto.setStock(Integer.parseInt(txtStock.getText()));
            producto.setValorCompra(Double.parseDouble(txtValorCompra.getText()));
            producto.setValorVenta(Double.parseDouble(txtValorVenta.getText()));

            guardado = true;
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stock, Valor Compra y Valor Venta deben ser numéricos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar. Verifica los datos.");
        }
    }

    public boolean isGuardado() {
        return guardado;
    }

    public Producto getProducto() {
        return producto;
    }
}