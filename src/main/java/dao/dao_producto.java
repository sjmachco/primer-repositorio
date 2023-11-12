package dao;

/**
 *
 * @author TIVE
 */
import dto.Producto;
import java.util.List;
public interface dao_producto {
    public List<Producto> productoQry();
    
    public boolean productoIns(Producto producto);
    
    public boolean productoActualizar(Producto producto);
    
    public boolean productoEliminar(Integer id_producto);
    
    public Producto productoGetId(Integer id_producto);
    
    public Producto productoNombre(String nombre);
    
    public List<Object[]> productoMigrDatos();
}
