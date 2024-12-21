package br.com.gravitech.condonews.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto implements Serializable {

    private List<?> content;
    private int number;
    private int size;
    private int totalElements;
    private int totalPages;
    private boolean last;

}
