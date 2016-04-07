package codingmentor.dto;

import java.util.Objects;
import java.util.UUID;
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

    public MobileDTO(String type, String manufacturer, int price, int piece) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.piece = piece;
        this.id= generateId();
    }
    

    public String getId() {
        return id;
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileDTO other = (MobileDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
