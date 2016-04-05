package codingmentor.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MobileDTO {

    private String id;

    @NotNull
    @Pattern(regexp = "....*")
    private String type;

    @NotNull
    @Pattern(regexp = "....*")
    private String manufacturer;

    @NotNull
    @Min(1)
    private int price;
    
    @NotNull
    @Min(0)
    private int piece;
    
    
}
