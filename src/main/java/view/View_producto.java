
package view;

/**
 *
 * @author TIVE
 */
import dto.Producto;
import consola.Consola;
import java.util.List;
import java.util.Objects;
public class View_producto {
    private Consola consola;
    
    public View_producto(){
        this.consola = new Consola();
    }
    
    public Integer menu(){
        Integer opc = null;
        StringBuilder sb = new StringBuilder();
        sb.append("\n1.Listar productos.")
                .append("\n2.Registrar producto.")
                    .append("\n3.Actualizar producto.")
                        .append("\n4.Eliminar producto.")
                            .append("\n5.Buscar producto")
                                .append("\n0.Salir.")
                                    .append("\n\tSeleccione una opcion: ");
        do {            
            opc = consola.getInteger(sb.toString());
        } while (opc == null || opc < 0 || opc > 5);
        return opc;
    }
    
    public void productoQry(List<Producto>list){
        consola.println();
        consola.println("%-16s%-16s%-10s%-22s%s", "Id producto", "Producto", 
                "Stock", "Precio de compra", "Precio de venta");
        list.forEach(a ->{
            consola.println("%-16s%-16s%-10s%-22s%s", a.getId_producto(), a.getNombre(), 
                    a.getStock(), a.getPrecio_compra(), a.getPrecio_venta());
        });
    }
    
    public void productoIns(Producto producto){
        do {            
            producto.setNombre(consola.getString("\nProducto: "));
        } while (producto.getNombre() == null || producto.getNombre().trim().length() == 0);
        
        do {            
            producto.setStock(consola.getInteger("\nStock: "));
        } while (producto.getStock() == null || producto.getStock() == 0);
        
        do {            
            producto.setPrecio_compra(consola.getDouble("\nPrecio de compra: "));
        } while (producto.getPrecio_compra() ==  null || producto.getPrecio_compra() == 0);
        
        do {            
            producto.setPrecio_venta(consola.getDouble("\nPrecio de venta: "));
        } while (producto.getPrecio_venta() == null || producto.getPrecio_venta() == 0);
    }
    
    public void productoActualizar(Producto producto){
        Integer opc;
        consola.println();
        consola.println("\n[1]Producto: %s\n[2]Stock: %s\n[3]Precio de compra: %s\n[4]Precio de venta: %s", 
                producto.getNombre(), producto.getStock(), producto.getPrecio_compra(), producto.getPrecio_venta());
        opc = consola.getInteger("\n\tElija una opcion[1-4]: ");
        
        switch(opc){
            case 1:
                do {                    
                    producto.setNombre(consola.getString("\nProducto: "));
                } while (producto.getNombre() == null || producto.getNombre().trim().length() == 0);
                break;
            case 2:
                do {                    
                    producto.setStock(consola.getInteger("\nStock: "));
                } while (producto.getStock() == null || producto.getStock() == 0);
                break;
            case 3:
                do {                    
                    producto.setPrecio_compra(consola.getDouble("\nPrecio de compra: "));
                } while (producto.getPrecio_compra() == null || producto.getPrecio_compra() == 0);
                break;
            case 4:
                do {                    
                    producto.setPrecio_venta(consola.getDouble("\nPrecio de venta: "));
                } while (producto.getPrecio_venta() == null || producto.getPrecio_venta() == 0);      
        }
    }
    
    public Integer productoGetId(List<Producto>list){
        Integer id_producto;
        consola.println();
        consola.println("%-16s%-16s%-10s%-22s%s", "Id producto", "Producto", 
                "Stock", "Precio de compra", "Precio de venta");
        list.forEach(a ->{
            consola.println("%-16s%-16s%-10s%-22s%s", a.getId_producto(), a.getNombre(), 
                    a.getStock(), a.getPrecio_compra(), a.getPrecio_venta());
        });
        
        do {            
            id_producto = consola.getInteger("\n\tSeleccione un Id: ");
        } while (id_producto == null ||  !existeId(list, id_producto));
        return id_producto;
    }
    
    public String buscarProducto(List<Producto>list){
        String producto, conf;
        boolean suficiente;
        do {
            suficiente = false;
            producto = consola.getString("Ingrese el producto a buscar: ");
            if(!existeProducto(list, producto) || producto.trim().length() < 0){
                consola.println("Producto inexistente.");
                producto = null;
                conf = consola.getString("¿Desea continuar?(si/no): ");
                if (conf.equalsIgnoreCase("si")) 
                    suficiente = true;
                else
                    suficiente = false; 
            } 
        } while (suficiente);
        return producto;
    }
    
    public void productoBuscado(Producto producto){
        consola.println("%-16s%-16s%-10s%-22s%s", "Id Producto", "Producto", 
                "Stock", "Precio compra", "Precio venta");
        consola.println("%-16s%-16s%-10s%-22s%s", producto.getId_producto(), producto.getNombre(), producto.getStock(),
                producto.getPrecio_compra(), producto.getPrecio_venta());
    }
    
    public boolean existeProducto(List<Producto>list, String nombre){
        boolean existe = false;
        for (Producto p : list) {
            if(Objects.equals(p.getNombre(), nombre)){
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public boolean existeId(List<Producto>list ,Integer id_producto){
        boolean existe = false;
        for(Producto p:list){
            if(Objects.equals(p.getId_producto(), id_producto)){
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public String msjConfirmacion(Integer opc){
        String mensaje = null;
        switch(opc){
            case 2:
                mensaje = consola.getString("\t¿Seguro que desea registrar el producto?: ");
                break;

            case 3:
                mensaje = consola.getString("\t¿Seguro que desea actualizar el producto?: ");
                break;

            case 4:
                mensaje = consola.getString("\t¿Seguro que desea eliminar el producto?: ");
                break;
        }
        return mensaje;
    }
}
