package dao;

import dto.Productos;
import java.util.List;

public interface DaoProductos {

    public List<Object[]> productosQry();
    
    public String productosIns(Productos productos);
    
    public String productosDel(Integer idproducto);
    
    public Productos productosGet(Integer idproducto); 
    
    public String productosUpd(Productos productos);
    
    public String getMessage();
}
