package tetofo.spring.tetofospringdashboard.Service.Exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * 
 * Extension for UsernameNotFoundException
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public class UsernameNotFoundExtensionException extends UsernameNotFoundException {
    public UsernameNotFoundExtensionException(String msg) {super(msg);}
    public UsernameNotFoundExtensionException(String msg, Exception e) {super(msg, e);}
    public static <R> R requireNonNull(R r, String message) throws UsernameNotFoundException {
        if (r == null) {
            throw new UsernameNotFoundException(message);
        }
        return r;
    }
}
