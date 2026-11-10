package edgartaskmanager.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction create(TransactionRequest request) {
        return repository.save(new Transaction(
                request.description().trim(),
                request.amount(),
                request.category().trim(),
                request.type()));
    }

    public Transaction create(String description, BigDecimal amount, String category, String type) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("A descrição da transação é obrigatória.");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("A categoria da transação é obrigatória.");
        }

        TransactionType transactionType;
        try {
            transactionType = type == null || type.isBlank()
                    ? TransactionType.EXPENSE
                    : TransactionType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("O tipo deve ser INCOME ou EXPENSE.", exception);
        }

        return repository.save(new Transaction(
                description.trim(),
                amount,
                category.trim(),
                transactionType));
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<Transaction> findByCategory(String category) {
        return repository.findByCategoryIgnoreCaseOrderByCreatedAtDesc(category);
    }

    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }
}
