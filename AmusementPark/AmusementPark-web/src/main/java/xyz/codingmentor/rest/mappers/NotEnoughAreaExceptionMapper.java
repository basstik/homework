
package xyz.codingmentor.rest.mappers;

import xyz.codingmentor.exceptions.dto.ErrorDTO;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.exceptions.NotEnoughAreaException;



@Provider
public class NotEnoughAreaExceptionMapper implements ExceptionMapper<NotEnoughAreaException> {

    @Override
    public Response toResponse(NotEnoughAreaException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}

