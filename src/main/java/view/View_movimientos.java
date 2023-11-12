package view;

/**
 *
 * @author TIVE
 */
import dto.Movimiento;
import java.util.List;
import consola.Consola;
import dto.Producto;
import java.util.Objects;

public class View_movimientos {

    private Consola consola;
    boolean opcion = true;

    public View_movimientos() {
        this.consola = new Consola();
    }

    public Integer menu() {
        Integer opc = null;
        StringBuilder sb = new StringBuilder();
        sb.append("\n1.Listar movimientos.")
                .append("\n2.Registrar movimientos.")
                .append("\n3.Actualizar movimientos.")
                .append("\n4.Eliminar movimientos.")
                .append("\n5.Consulta de stock por producto.")
                .append("\n6.Consulta de ventas.")
                .append("\n7.Consulta de compras.")
                .append("\n0.Salir.")
                .append("\n\tSeleccione una opcion: ");
        do {
            opc = consola.getInteger(sb.toString());
        } while (opc == null || opc < 0 || opc > 7);
        return opc;
    }

    public void movimientosQry(List<Object[]> list) {
        consola.println();
        consola.println("%-18s%-16s%-13s%s", "Id movimiento", "Producto", "Cantidad", "Tipo de movimiento");
        list.forEach(a -> {
            consola.println("%-18s%-16s%-13s%s", a[0], a[1], a[2], a[3]);
        });
    }

    public void movimientoIns(List<Object[]> productoMgrDatos, Movimiento movimiento) {
        movimiento.setTipo_movimiento(consola.getString("\n¿Que movimiento desea realizar, comprar o vender(c/v)? "));
        consola.println();
        boolean suficiente = false;
        if (movimiento.getTipo_movimiento().equalsIgnoreCase("c")) {
            consola.println("%-16s%-16s%-10s%s", "Id producto", "Producto",
                    "Stock", "Precio de compra");
            productoMgrDatos.forEach(a -> {
                consola.println("%-16s%-16s%-10s%s", a[0], a[1], a[2], a[3]);
            });
            do {
                movimiento.setId_producto(consola.getInteger("\n\tSeleccione un Id: "));
            } while (movimiento.getId_producto() == null || !existeId(productoMgrDatos, movimiento.getId_producto()));

            do {
                movimiento.setCantidad(consola.getInteger("\nCantidad: "));
            } while (movimiento.getCantidad() == null || movimiento.getCantidad() < 0);
        } else if (movimiento.getTipo_movimiento().equalsIgnoreCase("v")) {
            consola.println("%-16s%-16s%-10s%s", "Id producto", "Producto",
                    "Stock", "Precio de venta");
            productoMgrDatos.forEach(a -> {
                consola.println("%-16s%-16s%-10s%s", a[0], a[1], a[2], a[4]);
            });
            do {
                movimiento.setId_producto(consola.getInteger("\n\tSeleccione un Id: "));
            } while (movimiento.getId_producto() == null || !existeId(productoMgrDatos, movimiento.getId_producto()));

            do {
                String conf;
                Integer cantidad;
                cantidad = consola.getInteger("\nCantidad: ");
                if (!stockSuficiente(productoMgrDatos, movimiento.getId_producto(), cantidad)
                        || movimiento.getCantidad() < 0) {
                    consola.println("Stock insuficiente.");
                    conf = consola.getString("¿Desea continuar?(si/no): ");
                    if (conf.equalsIgnoreCase("si")) 
                        suficiente = true;
                    else
                        suficiente = false;             
                } 
                
                else
                    movimiento.setCantidad(cantidad);
                
            } while (suficiente);
        }
        //producto.setId_producto(movimiento.getId_producto());
        //producto.setStock(movimiento.getCantidad());
        //return producto;
    }

    public void movimientoActualizar(Movimiento movimiento, Producto producto, List<Object[]> productoMigrDatos) {
        Integer opc;
        consola.println();
        consola.println("\n[1]Producto: %s\n[2]Cantidad: %s", producto.getNombre(), movimiento.getCantidad());
        do {
            opc = consola.getInteger("\n\tSeleccione una opcion[1-2]: ");
        } while (opc == null || opc < 1 || opc > 2);
        switch (opc) {
            case 1:
                do {
                    consola.println("%-16s%s", "Id producto", "Producto");
                    productoMigrDatos.forEach(a -> {
                        consola.println("%-16s%s", a[0], a[1]);
                    });
                    movimiento.setId_producto(consola.getInteger("\n\tSeleccione un id: "));
                } while (movimiento.getId_producto() == null || !existeId(productoMigrDatos, movimiento.getId_producto()));
                break;
            case 2:
                do {
                    movimiento.setCantidad(consola.getInteger("Cantidad: "));
                } while (movimiento.getCantidad() == null || movimiento.getCantidad() == 0);
                break;
        }
    }

    public Integer movimientoGetId(List<Object[]> list) {
        Integer id_movimiento;
        consola.println();
        consola.println("%-16s%-15s%-13s%s", "Id movimiento", "Producto", "Cantidad", "Tipo de movimiento");
        list.forEach(a -> {
            consola.println("%-16s%-15s%-13s%s", a[0], a[1], a[2], a[3]);
        });

        do {
            id_movimiento = consola.getInteger("\n\tSeleccione un id: ");
        } while (id_movimiento == null || !existeId(list, id_movimiento));
        return id_movimiento;
    }

    public void stockProductoQry(List<Object[]> list) {
        consola.println("%-18s%-16s%s", "Id movimiento", "Producto", "Stock");
        list.forEach(a -> {
            consola.println("%-18s%-16s%s", a[0], a[1], a[2]);
        });
    }

    public void ventaProductoQry(List<Object[]> list) {
        consola.println("%-18s%-16s%-13s%s", "Id movimiento", "Producto", "Cantidad", "Tipo de movimiento");
        list.forEach(a -> {
            consola.println("%-18s%-16s%-13s%s", a[0], a[1], a[2], a[3]);
        });
    }

    public void compraProductoQry(List<Object[]> list) {
        consola.println("%-18s%-16s%-13s%s", "Id movimiento", "Producto", "Cantidad", "Tipo de movimiento");
        list.forEach(a -> {
            consola.println("%-18s%-16s%-13s%s", a[0], a[1], a[2], a[3]);
        });
    }

    public boolean stockSuficiente(List<Object[]> list, Integer id_producto, Integer cantidad) {
        boolean suficiente = false;
        int result;
        for (Object[] o : list) {
            if (Objects.equals(o[0], id_producto)) {
                result = Objects.compare((Integer) o[2], cantidad, Integer::compare);
                if (result > 0 || result == 0) {
                    suficiente = true;
                    opcion = true;
                }
                break;
            }
        }
        return suficiente;
    }

    public boolean existeId(List<Object[]> list, Integer id) {
        boolean existe = false;
        for (Object[] o : list) {
            if (Objects.equals(o[0], id)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public String msjConfirmacion(Integer opc) {
        String mensaje = null;
        switch (opc) {
            case 2:
                mensaje = consola.getString("\t¿Seguro que desea registrar el movimiento?: ");
                break;

            case 3:
                mensaje = consola.getString("\t¿Seguro que desea actualizar el movimiento?: ");
                break;

            case 4:
                mensaje = consola.getString("\t¿Seguro que desea eliminar el movimiento?: ");
                break;
        }
        return mensaje;
    }
}
