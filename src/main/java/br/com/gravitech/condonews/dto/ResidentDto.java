package br.com.gravitech.condonews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDto implements Serializable {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String apartment;
    private UUID idCondo;

}
