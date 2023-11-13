package view;

import dto.Categorias;
import dto.Productos;
import java.util.List;
import java.util.Objects;
import parainfo.io.Consola;

public class ProductosView {

    private final Consola consola;

    public ProductosView() {
        this.consola = new Consola();
    }

    public Integer menu() {
        StringBuilder m = new StringBuilder();
        m.append("\n[1] Lista de Productos")
                .append("\n[2] Nuevo Producto")
                .append("\n[3] Retirar Producto")
                .append("\n[4] Actualizar datos de Producto")
                .append("\n[0] Salir")
                .append("\n\tOpción: ");

        Integer opt;
        do {
            opt = consola.getInteger(m.toString());
        } while (opt == null || opt < 0 || opt > 4);

        return opt;
    }

    public void productosQry(List<Object[]> list) {
        consola.println();
        list.forEach(f -> {
            consola.println("%3d %-30s %-20s %-10d %.2f", f[0], f[1], f[2], f[3], f[4]);
        });
    }

    public void productosIns(List<Object[]> categoriasCbo, Productos productos) {
        //mostrando productos y pidiendo idcategoria
        do {
            consola.println();
            categoriasCbo.forEach(a -> {
                consola.println("[%3d] %s",
                        a[0], a[1]);
            });

            productos.setIdcategoria(consola.getInteger("\tID de categoria: "));

        } while (productos.getIdcategoria() == null || !existeId(categoriasCbo, productos.getIdcategoria()));

        // producto
        do {
            productos.setProducto(consola.getString("\nProducto: "));
        } while (productos.getProducto().trim().length() == 0
                || productos.getProducto().trim().length() > 60);
        //Stock
        do {
            productos.setStock(consola.getInteger("\nStock: "));
        } while (productos.getStock() == null || productos.getStock() < 1 || productos.getStock() > 1000);
        //Precio
        do {
            productos.setPrecio(consola.getDouble("\nPrecio: "));
        } while (productos.getPrecio() == null || productos.getPrecio() < 1 || productos.getPrecio() > 10000);
    }

    public Integer productosGetId(List<Object[]> list) {
        Integer idproducto = null;

        do {
            consola.println();
            list.forEach(f -> consola.println("%-3d %-30s %-20s %-10d %.2f",
                    f[0], f[1], f[2], f[3], f[4]));

            idproducto = consola.getInteger("\tID de producto: ");

        } while (idproducto == null || !existeId(list, idproducto));

        return idproducto;
    }

    public void productosUpd(Productos productos, Categorias categorias, List<Object[]> categoriasCbo) {
        String m = "\n[1] Categoria: %s\n[2] Producto: %s\n[3] Stock: %d\n[4] Precio: %.2f";
        Integer col;

        do {
            consola.println(m, categorias.getCategoria(), productos.getProducto(), productos.getStock(), productos.getPrecio());
            col = consola.getInteger("\tQué columna [1 al 4]: ");

        } while (col == null || col < 1 || col > 4);

        switch (col) {
            case 1:
                do {
                    categoriasCbo.forEach(a -> consola.println("[%d] %s", a[0], a[1]));
                    productos.setIdcategoria(consola.getInteger("\nID Categoria: "));
                } while (productos.getIdcategoria() == null || productos.getIdcategoria() < 1 || !existeId(categoriasCbo, productos.getIdcategoria()));
                break;
            case 2:
                do {
                    productos.setProducto(consola.getString("\nProducto: "));
                } while (productos.getProducto().trim().length() == 0
                        || productos.getProducto().trim().length() > 60);
                break;
            case 3:
                do {
                    productos.setStock(consola.getInteger("\nStock: "));
                } while (productos.getStock() == null || productos.getStock() < 1 || productos.getStock() > 1000);
                break;
            case 4:
                do {
                    productos.setPrecio(consola.getDouble("\nPrecio: "));
                } while (productos.getPrecio() == null || productos.getPrecio() < 1 || productos.getPrecio() > 10000);
                break;
        }
    }
    public void productosMsg(String msg) {
        consola.println("\n%s\n", msg);
    }
    
    private boolean existeId(List<Object[]> list, Integer idproducto) {
        boolean existe = false;

        for (Object[] p : list) {

            if (Objects.equals(p[0], idproducto)) {

                existe = true;
                break;
            }
        }

        return existe;
    }
}
