package br.com.gravitech.condonews.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resident")
public class Resident {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String apartment;
    private UUID idCondo;

}
