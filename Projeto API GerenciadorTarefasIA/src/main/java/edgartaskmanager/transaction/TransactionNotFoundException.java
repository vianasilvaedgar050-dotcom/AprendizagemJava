package edgartaskmanager.transaction;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(Long id) {
        super("Transação não encontrada: " + id);
    }
}
