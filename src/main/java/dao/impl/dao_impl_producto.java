
package dao.impl;

/**
 *
 * @author TIVE
 */
import java.util.List;
import dto.Producto;
import java.sql.PreparedStatement;
import consola.Consola;
import conexion_mysql.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class dao_impl_producto implements dao.dao_producto{
    Connection conexion;
    private final Consola consola;
    
    public dao_impl_producto(){
        Conexion cn = new Conexion();
        this.conexion = cn.conexion();
        this.consola = new Consola();
    }

    @Override
    public List<Producto> productoQry() {
        PreparedStatement ps;
        ResultSet rs;
        List<Producto>list = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("select id_producto, nombre, stock, precio_compra, precio_venta from producto");
            rs = ps.executeQuery();
            while(rs.next()){
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setStock(rs.getInt(3));
                producto.setPrecio_compra(rs.getDouble(4));
                producto.setPrecio_venta(rs.getDouble(5));
                list.add(producto);
            }
            return list;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public boolean productoIns(Producto producto) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("insert into producto(nombre, stock, precio_compra, precio_venta) values(?, ?, ?, ?)");
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStock());
            ps.setDouble(3, producto.getPrecio_compra());
            ps.setDouble(4, producto.getPrecio_venta());
            ps.execute();
            consola.println("Producto registrado!");
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean productoActualizar(Producto producto) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("update producto set nombre = ?, stock = ?, precio_compra = ?, precio_venta = ? where id_producto = ?");
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStock());
            ps.setDouble(3, producto.getPrecio_compra());
            ps.setDouble(4, producto.getPrecio_venta());
            ps.setInt(5, producto.getId_producto());
            ps.execute();
            consola.println("Producto actualizado!");
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean productoEliminar(Integer id_producto) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("delete from producto where id_producto = ?");
            ps.setInt(1, id_producto);
            ps.execute();
            consola.println("Producto eliminado.");
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public Producto productoGetId(Integer id_producto) {
        PreparedStatement ps;
        ResultSet rs;
        Producto producto = new Producto();
        try {
            ps = conexion.prepareStatement("select id_producto, nombre, stock, precio_compra, precio_venta from producto where id_producto = ?");
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            while(rs.next()){
                producto.setId_producto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setStock(rs.getInt(3));
                producto.setPrecio_compra(rs.getDouble(4));
                producto.setPrecio_venta(rs.getDouble(5));
            }
            return producto;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public List<Object[]> productoMigrDatos() {
        PreparedStatement ps;
        ResultSet rs;        
        List<Object[]> list = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("select id_producto, nombre, stock, precio_compra, precio_venta from producto");
            rs = ps.executeQuery();           
            while(rs.next()){
                Object[] fil = new Object[5];
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getInt(3);
                fil[3] = rs.getDouble(4);
                fil[4] = rs.getDouble(5);
                list.add(fil);
            }
            return list;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public Producto productoNombre(String nombre) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conexion.prepareStatement("select id_producto, nombre, stock, precio_compra, precio_venta "
                    + "from producto where nombre = ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            Producto producto = new Producto();
            while(rs.next()){
                producto.setId_producto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setStock(rs.getInt(3));
                producto.setPrecio_compra(rs.getDouble(4));
                producto.setPrecio_venta(rs.getDouble(5));
            }
            return producto;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }
}
