
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleCondominio")
@SessionScoped
public class ControleCondominio implements Serializable{
    private CondominioDAO dao;
    private Condominio objeto;

    public ControleCondominio() {
        dao = new CondominioDAO();
    }

    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Condominio();
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
    public CondominioDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(CondominioDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Condominio getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }
    
    
}
