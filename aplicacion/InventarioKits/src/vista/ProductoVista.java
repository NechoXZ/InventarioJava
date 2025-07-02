package vista;

import controlador.ProductoControlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoVista extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnCargar, btnAgregar, btnEditar, btnEliminar;

    private ProductoControlador controlador;

    public ProductoVista() {
        super("Gestión de Productos Didácticos");

        // Inicialización del controlador
        controlador = new ProductoControlador(this);

        // Configuración de ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializar tabla y modelo
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{
            "ID", "Nombre", "Código", "Proveedor", "Categoría",
            "Stock", "Valor Compra", "Valor Venta"
        }) {
            // Evitar edición directa en la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Panel de botones
        btnCargar = new JButton("Cargar Productos");
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(btnCargar);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        // Agregar componentes a la ventana
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Asociar acciones a botones
        btnCargar.addActionListener(e -> controlador.cargarProductos());
        btnAgregar.addActionListener(e -> controlador.abrirFormularioAgregar());
        btnEditar.addActionListener(e -> controlador.abrirFormularioEditar(tabla.getSelectedRow()));
        btnEliminar.addActionListener(e -> controlador.eliminarProducto(tabla.getSelectedRow()));
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTabla() {
        return tabla;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductoVista().setVisible(true);
        });
    }
}