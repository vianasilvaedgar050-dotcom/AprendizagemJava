package edgartaskmanager.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByOrderByCreatedAtDesc();

    List<Transaction> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category);
}
