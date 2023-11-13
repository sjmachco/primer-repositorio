package dao.impl;

import dao.DaoCategorias;
import dto.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoriasImpl extends Dao implements DaoCategorias {

    @Override
    public List<Categorias> categoriasQry() {
        List<Categorias> list = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ")
                .append("id_categoria,")
                .append("categoria ")
                .append("FROM categorias");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new ArrayList<>();
            while (rs.next()) {
                Categorias categorias = new Categorias();

                categorias.setIdcategoria(rs.getInt(1));
                categorias.setCategoria(rs.getString(2));

                list.add(categorias);
            }

        } catch (Exception e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public String categoriasIns(Categorias categorias) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO categorias(")
                .append("categoria")
                .append(") VALUES (?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, categorias.getCategoria());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "0 filas afectadas";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String categoriasDel(Integer idcategoria) {
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idcategoria);

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
    public Categorias categoriasGet(Integer idcategoria) {
        Categorias categorias = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ")
                .append("id_categoria,")
                .append("categoria from categorias ")
                .append("WHERE id_categoria = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idcategoria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categorias = new Categorias();

                    categorias.setIdcategoria(rs.getInt(1));
                    categorias.setCategoria(rs.getString(2));
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return categorias;
    }

    @Override
    public String categoriasUpd(Categorias categorias) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE categorias SET ")
                .append("categoria = ? ")
                .append("WHERE id_categoria = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, categorias.getCategoria());
            ps.setInt(2, categorias.getIdcategoria());

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
    public List<Object[]> categoriasCbo() {
        List<Object[]> list = null;
        StringBuilder sql = new StringBuilder();    
        sql.append("SELECT ")
                .append("id_categoria,")
                .append("categoria ")
                .append("FROM categorias");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new ArrayList<>();

            while (rs.next()) {
                Object[] fil = new Object[2];//2 becaus there is 2 select columns 

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
