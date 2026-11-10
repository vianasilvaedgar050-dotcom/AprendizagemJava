# JavaAPIecommerce

API REST de checkout desenvolvida com Java e Spring Boot. O projeto demonstra os padrões de projeto Singleton, Strategy e Facade em um fluxo simples de catálogo e criação de pedidos.

> **Observação:** catálogo e pedidos são mantidos em memória. Os pedidos são perdidos quando a aplicação é encerrada.

## Tecnologias

- Java 25 LTS ou superior
- Spring Boot 3.5.0
- Maven 3.9 ou superior
- Spring Web
- Bean Validation
- JUnit e Spring MockMvc para testes

## Requisitos

Instale um JDK 25 ou superior e o Maven. Confira as instalações no terminal:

```powershell
java -version
mvn -version
```

## Executar no Windows

Na raiz do projeto — a pasta que contém `pom.xml` — execute:

```powershell
mvn spring-boot:run
```

Se o Maven foi instalado em `%USERPROFILE%\.maven\apache-maven-3.10.0` e o terminal ainda não reconhece `mvn`, use o caminho completo:

```powershell
& "$HOME\.maven\apache-maven-3.10.0\bin\mvn.cmd" spring-boot:run
```

Por padrão, a API fica disponível em `http://localhost:8080`. Para usar outra porta, por exemplo 8081:

```powershell
& "$HOME\.maven\apache-maven-3.10.0\bin\mvn.cmd" spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

Mantenha o terminal aberto enquanto estiver usando a API. Para encerrá-la, pressione `Ctrl+C` nesse terminal.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/products` | Lista os produtos disponíveis. |
| `POST` | `/api/checkout` | Valida os dados e cria um pedido. |
| `GET` | `/api/orders/{id}` | Consulta um pedido pelo identificador retornado no checkout. |

### Listar produtos

Abra `http://localhost:8080/api/products` no navegador ou execute:

```powershell
curl.exe http://localhost:8080/api/products
```

O catálogo inicial contém:

| ID | Produto | Preço |
|---|---|---:|
| `BOOK-001` | Livro de padrões de projeto | R$ 25,00 |
| `HEAD-001` | Fone de ouvido | R$ 75,00 |

### Criar um pedido

Envie uma requisição `POST` para `/api/checkout`, com `Content-Type: application/json`:

```json
{
  "customerEmail": "cliente@example.com",
  "items": [
    { "productId": "HEAD-001", "quantity": 2 }
  ],
  "discountType": "PERCENTAGE",
  "shippingType": "STANDARD"
}
```

Exemplo pelo PowerShell:

```powershell
$body = @{
  customerEmail = "cliente@example.com"
  items = @(
    @{ productId = "HEAD-001"; quantity = 2 }
  )
  discountType = "PERCENTAGE"
  shippingType = "STANDARD"
} | ConvertTo-Json -Depth 4

Invoke-RestMethod -Uri "http://localhost:8080/api/checkout" -Method Post -ContentType "application/json" -Body $body
```

Uma criação bem-sucedida retorna `201 Created` e os dados do pedido, incluindo `id`, itens, subtotal, desconto, frete, total e data de criação. Para consultar o pedido, use o `id` retornado:

```powershell
curl.exe http://localhost:8080/api/orders/ID_DO_PEDIDO
```

### Descontos e frete

- `discountType`: `NONE` (padrão), `PERCENTAGE` (10%) ou `LOYALTY` (5%).
- `shippingType`: `STANDARD` (padrão, R$ 5,00) ou `EXPRESS` (R$ 15,00).
- Se omitidos, `discountType` e `shippingType` usam `NONE` e `STANDARD`, respectivamente.

O e-mail deve ser válido; a lista `items` não pode estar vazia; cada item precisa de um `productId` e uma quantidade positiva. Um produto inexistente retorna `404 Not Found`.

## Executar os testes

Na raiz do projeto, rode:

```powershell
mvn test
```

Os testes verificam a listagem do catálogo, o cálculo de desconto e frete no checkout e a resposta para um produto desconhecido.

## Organização do código

O código de produção fica em `src/main/java/com/example/ecommerce`, organizado por responsabilidade:

- `controller`: endpoints REST.
- `domain`: modelos de produto e pedido.
- `dto`: dados de entrada do checkout e itens.
- `service`: catálogo e coordenação do checkout.
- `strategy`: tipos e implementações das regras de desconto e frete.
- `config`: configuração dos parâmetros do checkout.

Os testes ficam em `src/test/java`.

## Padrões de projeto

- **Singleton:** `CheckoutConfiguration` é um bean Spring de escopo singleton e centraliza parâmetros do checkout.
- **Strategy:** as estratégias de desconto e frete encapsulam regras alternativas, selecionadas pelos tipos informados.
- **Facade:** `CheckoutFacade` coordena catálogo, cálculo, estratégias e armazenamento para oferecer uma operação única de checkout à camada HTTP.

## Limitações

Este projeto é uma demonstração. Os dados são armazenados apenas em memória, sem banco de dados ou autenticação. Para produção, seria necessário adicionar persistência, segurança e configuração apropriada para cada ambiente.
