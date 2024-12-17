package br.com.gravitech.condonews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondoDto implements Serializable {

    private UUID id;
    private String name;
    private List<ResidentDto> residents;
    private List<NewsDto> news;

}
