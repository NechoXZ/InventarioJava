package modelo;

public class Producto {

    private int id;
    private String nombre;
    private String codigo;
    private String proveedor;
    private String categoria;
    private int stock;
    private double valorCompra;
    private double valorVenta;

    public Producto() {}

    public Producto(int id, String nombre, String codigo, String proveedor, String categoria,
                    int stock, double valorCompra, double valorVenta) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.stock = stock;
        this.valorCompra = valorCompra;
        this.valorVenta = valorVenta;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }
}