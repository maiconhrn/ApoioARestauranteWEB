package br.uem.apoioarestaurante.web.session;

import br.uem.apoioarestaurante.metadata.entities.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContext {
    private static SessionContext instance;

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }

        return instance;
    }

    private SessionContext() {

    }

    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public User getLoggedUser() {
        return (User) getAttribute("loggedUser");
    }

    public void setLoggedUser(User user) {
        setAttribute("loggedUser", user);
    }

    public void invalidateSession() {
        currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

}