package edgartaskmanager.transaction;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionTools {

    private final TransactionService transactionService;

    public TransactionTools(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Tool(description = "Registra uma receita ou despesa informada explicitamente pelo usuário.")
    public Transaction registerTransaction(
            @ToolParam(description = "Descrição curta da transação") String description,
            @ToolParam(description = "Valor positivo da transação") BigDecimal amount,
            @ToolParam(description = "Categoria, por exemplo alimentação ou transporte") String category,
            @ToolParam(description = "Tipo da transação: INCOME para receita ou EXPENSE para despesa",
                    required = false) String type) {
        return transactionService.create(description, amount, category, type);
    }
}
