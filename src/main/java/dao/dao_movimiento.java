package dao;

import dto.Movimiento;
import dto.Producto;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author TIVE
 */
public interface dao_movimiento {
    
    public List<Object[]>movimientoQry();
    
    public boolean movimientoIns(Movimiento movimiento);
    
    public Integer stockGet(Movimiento movimiento);
    
    public boolean stockActualizar(Integer stock, Movimiento movimiento);
    
    public boolean movimientoActualizar(Movimiento movimiento);
    
    public boolean movimientoEliminar(Integer id_movimiento);
    
    public Movimiento movimientoGetId(Integer id_movimiento);

    public List<Object[]> stockProductoQry();
    
    public List<Object[]> ventaMovimientoQry();
    
    public List<Object[]> compraMovimientoQry();
    
}
