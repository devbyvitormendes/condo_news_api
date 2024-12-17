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
public class NewsDto implements Serializable {

    private UUID id;
    private String title;
    private String content;
    private String image;
    private String date;
    private boolean breaking;
    private UUID idCondo;
}
