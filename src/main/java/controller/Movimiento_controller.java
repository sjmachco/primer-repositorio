package controller;

/**
 *
 * @author TIVE
 */
import dto.Movimiento;
import dto.Producto;
import view.View_movimientos;
import dao.dao_movimiento;
import dao.impl.dao_impl_movimiento;
import dao.impl.dao_impl_producto;
import dao.dao_producto;

import java.util.List;

public class Movimiento_controller {

    private final View_movimientos view_mov;
    private final dao_movimiento dao_mov;
    private final dao_producto dao_prod;

    public Movimiento_controller() {
        this.view_mov = new View_movimientos();
        this.dao_mov = new dao_impl_movimiento();
        this.dao_prod = new dao_impl_producto();
    }

    public void inicio() {
        Integer opc;
        while (true) {
            opc = view_mov.menu();
            if (opc == 0) {
                break;
            }
            switch (opc) {
                case 1:
                    List<Object[]> list = dao_mov.movimientoQry();
                    if (list != null) {
                        view_mov.movimientosQry(list);
                    }
                    break;

                case 2:
                    Integer stock;
                    String msj;
                    Movimiento movimiento = new Movimiento();
                    List<Object[]> listPro = dao_prod.productoMigrDatos();
                    view_mov.movimientoIns(listPro, movimiento);
                    msj = view_mov.msjConfirmacion(opc);
                    if (msj.equalsIgnoreCase("si"))
                        dao_mov.movimientoIns(movimiento);
                    stock = dao_mov.stockGet(movimiento);
                    dao_mov.stockActualizar(stock, movimiento);
                    break;

                case 3:
                    Integer id_mov;
                    list = dao_mov.movimientoQry();
                    id_mov = view_mov.movimientoGetId(list);
                    movimiento = dao_mov.movimientoGetId(id_mov);
                    Producto producto = dao_prod.productoGetId(movimiento.getId_producto());
                    listPro = dao_prod.productoMigrDatos();
                    view_mov.movimientoActualizar(movimiento, producto, listPro);
                    msj = view_mov.msjConfirmacion(opc);
                    if (msj.equalsIgnoreCase("si"))
                        dao_mov.movimientoActualizar(movimiento);
                    break;

                case 4:
                    list = dao_mov.movimientoQry();
                    id_mov = view_mov.movimientoGetId(list);
                    msj = view_mov.msjConfirmacion(opc);
                    if (msj.equalsIgnoreCase("si"))
                        dao_mov.movimientoEliminar(id_mov);
                    break;
                    
                case 5:
                    list = dao_mov.stockProductoQry();
                    if(list != null)
                        view_mov.stockProductoQry(list);
                    break;
                    
                case 6:
                    list = dao_mov.ventaMovimientoQry();
                    if(list != null)
                        view_mov.ventaProductoQry(list);
                    break;
                    
                case 7:
                    list = dao_mov.compraMovimientoQry();
                    if(list != null)
                        view_mov.compraProductoQry(list);
                    break;
            }
        }
    }
}
