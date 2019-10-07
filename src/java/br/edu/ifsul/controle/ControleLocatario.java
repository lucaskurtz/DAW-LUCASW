
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleLocatario")
@SessionScoped
public class ControleLocatario implements Serializable{
    private LocatarioDAO dao;
    private Locatario objeto;

    public ControleLocatario() {
        dao = new LocatarioDAO();
    }

    public String listar(){
        return "/privado/locatario/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Locatario();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.localizar(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    /**
     * @return the da
     */
    public LocatarioDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(LocatarioDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Locatario getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }
    
    
}
