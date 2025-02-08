package br.com.gravitech.condonews.domain;

import br.com.gravitech.condonews.domain.enums.ContactTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contacts")
public class Contact {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private ContactTypeEnum type;
    private UUID idCondo;
}
