package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Setor;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joel
 */

@FacesConverter(value = "converterSetor")
public class ConverterSetor implements Serializable, Converter {
    
    @PersistenceContext(unitName = "TA-2018-1-6N1-WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro"))  {
            return null;
        }
        return em.find(Setor.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Setor obj = (Setor) o;
        return obj.getId().toString();
    }
    
}
