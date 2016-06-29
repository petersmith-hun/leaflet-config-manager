package hu.psprog.lcm.exception;

public class LCMException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public LCMException() {
        // Serializable
    }
    
    public LCMException(String message) {
        
        super(message);
    }
}
