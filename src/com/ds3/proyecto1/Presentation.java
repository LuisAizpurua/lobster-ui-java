package com.ds3.proyecto1;

import java.util.List;

public class Presentation implements IteratorFields{
    private final String university = "Universidad Tecnologica de Panama";
    private final String faculty = "Facultad De Sistema Computacional";
    private final String career = "Desarrollo Y Gestion De Software";
    private final String subject = "Desarrollo De Software";
    private final String teacher = "******";

    @Override
    public List<String> listFields() {
        Presentation thisClass = null;
        try {
            thisClass = (Presentation) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return this.streamFields(thisClass);
    }
}
