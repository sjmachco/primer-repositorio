package dao.impl;

/**
 *
 * @author TIVE
 */
//modificado
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import consola.Consola;
import dto.Movimiento;
import conexion_mysql.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dao_impl_movimiento implements dao.dao_movimiento {

    Connection cn;
    private final Consola consola;

    public dao_impl_movimiento() {
        Conexion conexion = new Conexion();
        this.consola = new Consola();
        this.cn = conexion.conexion();
    }

    @Override
    public List<Object[]> movimientoQry() {
        PreparedStatement ps;
        ResultSet rs;
        List<Object[]> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("select id_movimiento, p.nombre, cantidad, tipo_movimiento from movimiento as m "
                    + "inner join producto as p on p.id_producto = m.id_producto order by m.id_movimiento");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fil = new Object[4];
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getInt(3);
                fil[3] = rs.getString(4);
                list.add(fil);
            }
            return list;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public boolean movimientoIns(Movimiento movimiento) {
        PreparedStatement ps;
        //Integer id_producto;
        try {
            ps = cn.prepareStatement("insert into movimiento(id_producto, cantidad, tipo_movimiento) values(?, ?, ?)");
            ps.setInt(1, movimiento.getId_producto());
            ps.setInt(2, movimiento.getCantidad());
            if (movimiento.getTipo_movimiento().equalsIgnoreCase("c")) {
                ps.setString(3, "compra");
            } else if (movimiento.getTipo_movimiento().equalsIgnoreCase("v")) {
                ps.setString(3, "venta");
            }
            ps.execute();
            consola.println("Movimiento registrado!");
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean movimientoActualizar(Movimiento movimiento) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cn.prepareStatement("update movimiento set id_producto = ?, cantidad = ? where id_movimiento = ?");
            ps.setInt(1, movimiento.getId_producto());
            ps.setInt(2, movimiento.getCantidad());
            ps.setInt(3, movimiento.getId_movimiento());
            ps.execute();
            consola.println("Movimiento actualizado!");
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean movimientoEliminar(Integer id_movimiento) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cn.prepareStatement("delete from movimiento where id_movimiento  = ?");
            ps.setInt(1, id_movimiento);
            ps.execute();
            consola.println("Movimiento eliminado.");
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public Movimiento movimientoGetId(Integer id_movimiento) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cn.prepareStatement("select id_movimiento, m.id_producto, p.nombre, cantidad, tipo_movimiento from movimiento as m "
                    + "inner join producto as p on p.id_producto = m.id_producto where id_movimiento = ?");
            ps.setInt(1, id_movimiento);
            rs = ps.executeQuery();
            Movimiento movimiento = new Movimiento();
            while (rs.next()) {
                movimiento.setId_movimiento(rs.getInt(1));
                movimiento.setId_producto(rs.getInt(2));
                movimiento.setNombre_producto(rs.getString(3));
                movimiento.setCantidad(rs.getInt(4));
                movimiento.setTipo_movimiento(rs.getString(5));
            }
            return movimiento;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public Integer stockGet(Movimiento movimiento) {
        PreparedStatement ps;
        ResultSet rs;
        Integer stock_prod = 0;
        try {
            ps = cn.prepareStatement("select stock from producto where id_producto = ?");
            ps.setInt(1, movimiento.getId_producto());
            rs = ps.executeQuery();
            while (rs.next()) {
                stock_prod = rs.getInt(1);
            }
            return stock_prod;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public boolean stockActualizar(Integer stock, Movimiento movimiento) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cn.prepareStatement("update producto set stock = ? where id_producto = ?");
            if (movimiento.getTipo_movimiento().equalsIgnoreCase("c")) {
                ps.setInt(1, stock + movimiento.getCantidad());
            } else if (movimiento.getTipo_movimiento().equalsIgnoreCase("v")) {
                ps.setInt(1, stock - movimiento.getCantidad());
            }
            ps.setInt(2, movimiento.getId_producto());
            ps.execute();
            return true;
        } catch (SQLException e) {
            consola.println(e.toString());
            return false;
        }
    }

    @Override
    public List<Object[]> stockProductoQry() {
        PreparedStatement ps;
        ResultSet rs;
        List<Object[]> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("select id_movimiento, p.nombre, p.stock from movimiento as m "
                    + "inner join producto as p on p.id_producto = m.id_producto");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fil = new Object[3];
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getInt(3);
                list.add(fil);
            }
            return list;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public List<Object[]> ventaMovimientoQry() {
        PreparedStatement ps;
        ResultSet rs;
        List<Object[]> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("select id_movimiento, p.nombre, cantidad, tipo_movimiento from movimiento as m "
                    + "inner join producto as p on p.id_producto = m.id_producto where tipo_movimiento = 'venta'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fil = new Object[4];
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getInt(3);
                fil[3] = rs.getString(4);
                list.add(fil);
            }
            return list;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }

    @Override
    public List<Object[]> compraMovimientoQry() {
        PreparedStatement ps;
        ResultSet rs;
        List<Object[]> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("select id_movimiento, p.nombre, cantidad, tipo_movimiento from movimiento as m "
                    + "inner join producto as p on p.id_producto = m.id_producto where tipo_movimiento = 'compra'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fil = new Object[4];
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getInt(3);
                fil[3] = rs.getString(4);
                list.add(fil);
            }
            return list;
        } catch (SQLException e) {
            consola.println(e.toString());
            return null;
        }
    }
}
