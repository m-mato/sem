package cz.muni.pa165.sem.editor;

import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.facade.SportFacade;

import java.beans.PropertyEditorSupport;

/**
 * @author Vit Hovezak
 */
public class SportEditor extends PropertyEditorSupport {

    private final SportFacade sportFacade;

    public SportEditor(SportFacade sportFacade) {
        this.sportFacade = sportFacade;
    }

    public void setAsText(String value) {
        if (value.equals("")) {
            setValue(null);
            return;
        }
        setValue(sportFacade.getSportById(Long.valueOf(value)));
    }

    public String getAsText() {
        SportDTO sportDTO = (SportDTO) this.getValue();
        if (sportDTO == null) {
            return "";
        }
        return String.valueOf(sportDTO.getId());
    }

}
