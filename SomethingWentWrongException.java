/**
 * Will be used as a catch-all exception for anything which goes wrong during execution.
 *
 * Be sure to add a meaningful reason to the exception.
 */

public class SomethingWentWrongException extends RuntimeException {

    public SomethingWentWrongException(String reason) {
        super(reason);
        
        System.out.println("Exception "+reason);
    }
}
