package br.uem.apoioarestaurante.views.converter;

import br.uem.apoioarestaurante.metadata.entities.Pessoa;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Maicon
 */
@FacesConverter("personConverter")
public class PersonConverter implements Converter, Serializable {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s != null) {
            return this.getAttributesFrom(uiComponent).get(s);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null && !"".equals(o)) {
            Pessoa entity = (Pessoa) o;

            this.addAttribute(uiComponent, entity);

            String cpf = entity.getCpf();
            if (cpf != null) {
                return cpf;
            }
        }

        return (String) o;

    }

    protected void addAttribute(UIComponent component, Pessoa o) {
        String key = o.getCpf();
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
