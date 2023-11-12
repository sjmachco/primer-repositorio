

package main;

/**
 *
 * @author TIVE
 */
import controller.Movimiento_controller;
import controller.Producto_controller;
import consola.Consola;
public class main {
    private static Movimiento_controller mov_controller;
    private static Producto_controller prod_controller;
    
    public static Integer menu(){
        Consola consola = new Consola();
        StringBuilder sb = new StringBuilder();
        Integer opc;
        sb.append("\n1.Administracion de productos.")
                .append("\n2.Administracion de movimientos.")
                    .append("\n0. Salir.")
                        .append("\n\tSeleccione una opcion: ");
        do {            
            opc = consola.getInteger(sb.toString());
        } while (opc == null || opc < 0 || opc > 2);
        return opc;
    }
    public static void main(String[] args) {
        prod_controller = new Producto_controller();
        mov_controller = new Movimiento_controller();
        while(true){
            Integer opt = menu();
            if(opt == 0)
                break;
            switch(opt){
                case 1:
                    prod_controller.inicio();
                    break;
                    
                case 2:
                    mov_controller.inicio();
                    break;
            }
        }       
    }
}
