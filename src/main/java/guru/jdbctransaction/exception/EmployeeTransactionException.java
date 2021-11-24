package guru.jdbctransaction.exception;

public class EmployeeTransactionException extends Exception{
    private static final long  serialVersionUID = -3128681006635769411L;

    public EmployeeTransactionException(String message) {
        super(message);
    }
}
