
package dto;

/**
 *
 * @author TIVE
 */
public class Movimiento {
    private Integer id_movimiento;
    private Integer id_producto;
    private Integer cantidad;
    private String tipo_movimiento;
    
    public Movimiento(){
        this.cantidad = 0;
    }

    public Integer getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(Integer id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getTipo_movimiento(){
        return tipo_movimiento;
    }
    
    public void setTipo_movimiento(String tipo_movimiento){
        this.tipo_movimiento = tipo_movimiento;
    }
}
