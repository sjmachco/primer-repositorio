package dao.impl;

import dao.DaoProductos;
import dto.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProductosImpl extends Dao implements DaoProductos {

    @Override
    public List<Object[]> productosQry() {
        List<Object[]> list = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT  ")
                .append("productos.id_producto, ")
                .append("categorias.categoria, ")
                .append("productos.producto, ")
                .append("productos.stock, ")
                .append("productos.precio ")
                .append("FROM productos ")
                .append("INNER JOIN categorias ")
                .append("ON productos.id_categoria = categorias.id_categoria");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new ArrayList<>();

            while (rs.next()) {
                Object[] fil = new Object[5];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getString(3);
                fil[3] = rs.getInt(4);
                fil[4] = rs.getDouble(5);
                        
                list.add(fil);
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String productosIns(Productos productos) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO productos(")
                .append("id_categoria,")
                .append("producto, ")
                .append("stock, ")
                .append("precio")
                .append(") VALUES (?, ?, ?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, productos.getIdcategoria());
            ps.setString(2, productos.getProducto());
            ps.setInt(3, productos.getStock());
            ps.setDouble(4, productos.getPrecio());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                message = "0 filas afectadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String productosDel(Integer idproducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idproducto);

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "0 filas afectadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public Productos productosGet(Integer idproducto) {
        Productos productos = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ")
                .append("id_producto,")
                .append("id_categoria, ")
                .append("producto, ")
                .append("stock, ")
                .append("precio ")
                .append("from productos ")
                .append("WHERE id_producto = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idproducto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productos = new Productos();

                    productos.setIdproducto(rs.getInt(1));
                    productos.setIdcategoria(rs.getInt(2));
                    productos.setProducto(rs.getString(3));
                    productos.setStock(rs.getInt(4));
                    productos.setPrecio(rs.getDouble(5));
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return productos;
    }

    @Override
    public String productosUpd(Productos productos) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE productos SET ")
                .append("id_categoria = ? ,")
                .append("producto = ?,")
                .append("stock = ?,")
                .append("precio = ? ")
                .append("WHERE id_producto = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, productos.getIdcategoria());
            ps.setString(2, productos.getProducto());
            ps.setInt(3, productos.getStock());
            ps.setDouble(4, productos.getPrecio());
            
            ps.setInt(5, productos.getIdproducto());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                message = "0 filas afectadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
