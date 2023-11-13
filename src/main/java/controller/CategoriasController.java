
package controller;

import dao.DaoCategorias;
import dao.impl.DaoCategoriasImpl;
import dto.Categorias;
import java.util.List;
import view.CategoriasView;

public class CategoriasController {
    private final DaoCategorias daoCategorias;
    private final CategoriasView categoriasView;
    
    public CategoriasController(){
        this.daoCategorias = new DaoCategoriasImpl();
        this.categoriasView = new CategoriasView();
    }
    
    public void inicio(){
        while(true){
            Integer opt = categoriasView.menu();
        
        if(opt == 0){
            break;
        }
        
        switch(opt){
            case 1:
                List<Categorias> list = daoCategorias.categoriasQry();
                if(list != null){
                    categoriasView.categoriasQry(list);
                }else{
                    categoriasView.categoriasMsg(daoCategorias.getMessage());
                }
                break;
            case 2:
                Categorias categorias = new Categorias();
                categoriasView.categoriasIns(categorias);
                
                String result = daoCategorias.categoriasIns(categorias);
                if (result != null) {
                    categoriasView.categoriasMsg(result);
                }
                break;
            case 3:
                list = daoCategorias.categoriasQry();
                Integer idcategoria = categoriasView.categoriasGetId(list);
                
                result = daoCategorias.categoriasDel(idcategoria);
                
                if(result != null){
                    categoriasView.categoriasMsg(result);
                }
                break;
            case 4:
                list = daoCategorias.categoriasQry();
                idcategoria = categoriasView.categoriasGetId(list);
                
                categorias = daoCategorias.categoriasGet(idcategoria);
                
                categoriasView.categoriasUpd(categorias);
                
                result = daoCategorias.categoriasUpd(categorias);
                if(result != null){
                    categoriasView.categoriasMsg(result);
                }
                break;
            }
        }
    }
}