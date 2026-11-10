# Budget AI API

API REST de orçamento financeiro construída com Java 21, Spring Boot, Spring AI, OpenAI e H2.

## Executar

Configure uma chave da OpenAI no ambiente antes de iniciar:

```powershell
$env:OPENAI_API_KEY = "sua-chave"
.\gradlew.bat bootRun
```

Execute os testes com `.\gradlew.bat test`. Os testes usam uma chave fictícia e não
fazem chamadas externas.

## Endpoints

- `POST /api/transactions`: cria uma transação (`description`, `amount`, `category`,
  `type`, com `INCOME` ou `EXPENSE`).
- `GET /api/transactions`: lista transações; `?category=Alimentação` filtra por categoria.
- `GET /api/transactions/{id}`: consulta uma transação.
- `POST /api/assistant/prompt`: recebe JSON como `{"prompt":"Registre ..."}`
  e permite ao assistente registrar uma transação explicitamente solicitada.
- `POST /api/assistant/audio`: recebe áudio em `multipart/form-data`, no campo `file`,
  transcreve e processa o comando.

O H2 é executado em memória; os dados são descartados quando a aplicação encerra.
Em desenvolvimento, o console do H2 fica disponível em `/h2-console`.
