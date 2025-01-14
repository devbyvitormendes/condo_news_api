package br.com.gravitech.condonews.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "condo")
public class Condo {

    @Id
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;

}
