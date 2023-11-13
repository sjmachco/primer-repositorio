//Agregando un comentario
package com.mycompany.productos;

import controller.CategoriasController;
import controller.ProductosController;
import parainfo.io.Consola;

public class Main {
    
    private static Consola consola;
    private static CategoriasController categoriasController;
    private static ProductosController productosController;
    
    public static void main(String[] args) {
        consola = new Consola();
        categoriasController = new CategoriasController();
        productosController = new ProductosController();
        
        while (true) {            
            Integer opt = menu();
            
            if(opt == 0){
                break;
            }
            switch(opt){
                case 1:
                    categoriasController.inicio();
                    break;
                case 2:
                    productosController.inicio();
                    break;
            }
        }
    }
    
    public static Integer menu(){
        StringBuilder m = new StringBuilder();
        m.append("\n[1] Mantenimiento de Categorias")
                .append("\n[2] Mantenimiento de Productos")
                .append("\n[0] Salir")
                .append("\n\tOpcion: ");
        
        Integer opt;
        do {            
            opt = consola.getInteger(m.toString());
        } while (opt == null || opt < 0 || opt > 4);
        
        return opt;
    }
}
