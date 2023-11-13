
package controller;

import dao.DaoCategorias;
import dao.DaoProductos;
import dao.impl.DaoCategoriasImpl;
import dao.impl.DaoProductosImpl;
import dto.Categorias;
import dto.Productos;
import java.util.List;
import view.ProductosView;


public class ProductosController {
    
    private final DaoProductos daoProductos;
    public final DaoCategorias daoCategorias;
    private final ProductosView productosView;
    
    public ProductosController(){
        this.daoProductos = new DaoProductosImpl();
        this.daoCategorias = new DaoCategoriasImpl();
        this.productosView = new ProductosView();
    }
    
    public void inicio(){
        while(true){
            Integer opt = productosView.menu();
            if(opt == 0){
                break;
            }
            switch(opt){
                case 1: 
                    List<Object[]> list = daoProductos.productosQry();
                    
                    if (list != null) {
                        productosView.productosQry(list);
                    }else{
                        productosView.productosMsg(daoProductos.getMessage());
                    }
                    break;
                case 2:
                    List<Object[]> categoriasCbo = daoCategorias.categoriasCbo();
                    Productos productos = new Productos();
                    
                    productosView.productosIns(categoriasCbo, productos);
                    
                    String msg = daoProductos.productosIns(productos);
                    if(msg != null){
                        productosView.productosMsg(msg);
                    }
                    break;
                case 3:
                    list = daoProductos.productosQry();
                    Integer idproducto = productosView.productosGetId(list);
                    
                    msg = daoProductos.productosDel(idproducto);
                    if(msg != null){
                        productosView.productosMsg(msg);
                    }
                    break;
                case 4:
                    list = daoProductos.productosQry();
                    idproducto = productosView.productosGetId(list);
                    
                    productos = daoProductos.productosGet(idproducto);
                    Categorias categorias = daoCategorias.categoriasGet(productos.getIdcategoria());
                    categoriasCbo = daoCategorias.categoriasCbo();
                    
                    productosView.productosUpd(productos, categorias, categoriasCbo);
                    msg = daoProductos.productosUpd(productos);
                    if(msg != null){
                        productosView.productosMsg(msg);
                    }
                    break;
            }           
        }      
    }   
}
