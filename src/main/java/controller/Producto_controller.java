
package controller;

/**
 *
 * @author TIVE
 */
import java.util.List;
import dto.Producto;
import view.View_producto;
import dao.dao_producto;
import dao.impl.dao_impl_producto;

public class Producto_controller {
    private final View_producto view_producto;
    private final dao_producto dao_producto;
    public Producto_controller(){
        this.view_producto = new View_producto();
        this.dao_producto = new dao_impl_producto();
    }
    
    public void inicio(){
        Integer opc;
        while(true){
            opc = view_producto.menu();
            if(opc == 0)
                break;
            switch(opc){
                case 1:
                    List<Producto> list = dao_producto.productoQry();
                    if(list != null)
                        view_producto.productoQry(list);
                    break;
                case 2:
                    String msj;
                    Producto producto = new Producto();
                    view_producto.productoIns(producto);
                    msj = view_producto.msjConfirmacion(opc);
                    if(msj.equalsIgnoreCase("si"))
                        dao_producto.productoIns(producto);
                    break;
                case 3:
                    Integer id_producto;
                    list = dao_producto.productoQry();
                    id_producto = view_producto.productoGetId(list);
                    producto = dao_producto.productoGetId(id_producto);
                    view_producto.productoActualizar(producto);
                    msj = view_producto.msjConfirmacion(opc);
                    if(msj.equalsIgnoreCase("si"))
                        dao_producto.productoActualizar(producto);
                    break;
                case 4:
                    list = dao_producto.productoQry();
                    id_producto = view_producto.productoGetId(list);
                    msj = view_producto.msjConfirmacion(opc);
                    if(msj.equalsIgnoreCase("si"))
                        dao_producto.productoEliminar(id_producto);
                    break;
                case 5:
                    String nomb_product;
                    list = dao_producto.productoQry();
                    nomb_product = view_producto.buscarProducto(list);
                    if(nomb_product != null){
                        producto = dao_producto.productoNombre(nomb_product);
                        view_producto.productoBuscado(producto);
                    }
                    break;
            }
        }
    }
}
