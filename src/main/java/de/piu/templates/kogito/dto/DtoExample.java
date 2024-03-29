package de.piu.templates.kogito.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DtoExample {
    
    private String name;
    private String description;
    private String id;

}
