
package xyz.codingmentor.exceptions.dto;


public class ErrorDTO {
    private String errorMessage;

    public ErrorDTO() {
        //default constructor
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
