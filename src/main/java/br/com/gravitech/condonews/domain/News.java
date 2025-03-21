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
@Document(collection = "news")
public class News {

    @Id
    private UUID id;
    private String title;
    private String content;
    private String image;
    private String date;
    private String updatedAt;
    private boolean breaking;
    private UUID idCondo;
}
