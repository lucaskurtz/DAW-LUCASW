
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class Util {
    public static String getMensagemErro(Exception e){
        while(e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if(retorno.contains("viola restrição de chave estrangeira")){
            retorno = "Retono não pode ser excluído por possuir referencia em outros objetos!";
        }
        return retorno;
    }
    
    public static void mensagemInformacao(String msg){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        contexto.addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String msg){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        contexto.addMessage(null, mensagem);
    }
}
