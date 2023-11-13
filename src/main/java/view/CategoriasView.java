package view;

import dto.Categorias;
import java.util.List;
import java.util.Objects;
import parainfo.io.Consola;

public class CategoriasView {

    private final Consola consola;

    public CategoriasView() {
        this.consola = new Consola();
    }

    public Integer menu() {
        StringBuilder m = new StringBuilder();
        m.append("\n[1] Lista de Categorias")
                .append("\n[2] Nueva Categoria")
                .append("\n[3] Retirar Categoria")
                .append("\n[4] Actualizar datos de Categoria")
                .append("\n[0] Salir")
                .append("\n\tOpción: ");

        Integer opt;
        do {
            opt = consola.getInteger(m.toString());
        } while (opt == null || opt < 0 || opt > 4);

        return opt;
    }
    
    public void categoriasQry(List<Categorias> list) {
        consola.println();
        list.forEach(a -> {
            consola.println("%3d %s",
                    a.getIdcategoria(), a.getCategoria());
        });
    }
    
    public void categoriasIns(Categorias categorias) {
        // categoria
        do {
            categorias.setCategoria(consola.getString("\nCategoria: "));
        } while (categorias.getCategoria().trim().length() == 0
                || categorias.getCategoria().trim().length() > 40);
        
    }
    
    public Integer categoriasGetId(List<Categorias> list) {
        Integer idcategoria = null;

        do {
            consola.println();
            list.forEach(c -> consola.println("[%3d] %s", c.getIdcategoria(), c.getCategoria()));

            idcategoria = consola.getInteger("\tID de Categoria: ");

        } while (idcategoria == null || !existeId(list, idcategoria));

        return idcategoria;
    }
    
    public void categoriasUpd(Categorias categorias) {
        
        String m = "\n[1] Categoria: %s";
        Integer col;

        do {
            consola.println(m, categorias.getCategoria());

            col = consola.getInteger("\tQué columna [1 - 1]: ");

        } while (col == null || col < 1 || col > 1);

        switch (col) {
            case 1: // categoria
                do {
                    categorias.setCategoria(consola.getString("\nCategoria: "));
                } while (categorias.getCategoria().trim().length() == 0
                        || categorias.getCategoria().trim().length() > 40);
                break;

        }
    }
    
    public void categoriasMsg(String msg) {
        consola.println("\n%s\n", msg);
    }
    
    private boolean existeId(List<Categorias> list, Integer idcategoria) {
        boolean existe = false;

        for (Categorias c : list) {

            if (Objects.equals(c.getIdcategoria(), idcategoria)) {

                existe = true;
                break;
            }
        }

        return existe;
    }
}
