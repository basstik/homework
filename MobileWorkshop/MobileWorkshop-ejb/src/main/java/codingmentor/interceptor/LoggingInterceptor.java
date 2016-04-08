package codingmentor.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import codingmentor.interceptor.Loggable;


@Loggable
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        logger.log(Level.INFO, "Loggable entering: {0} {1}", new Object[]{ic.getTarget().getClass().getName(), ic.getMethod().getName()});
        try {
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
            logger.log(Level.INFO, "Loggable exiting: {0} {1}", new Object[]{ic.getTarget().getClass().getName(), ic.getMethod().getName()});
        }
    }

}
