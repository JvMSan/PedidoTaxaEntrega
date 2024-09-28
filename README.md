# API de Processamento de Descontos para Pedidos

Esta é uma API RESTful desenvolvida em Spring Boot para cadastro de clientes, criação e processamento de pedidos, e aplicação de descontos conforme regras de negócio definidas.

## Requisitos

- **Java 17**
- **Maven**
- **Docker** (opcional, para executar via container)

## Dependências

- Spring Boot Web
- Spring Data JPA
- H2 Database

## Estrutura do Projeto

### 1. Cliente

A entidade **Cliente** representa os clientes cadastrados no sistema.

| Campo       | Tipo   | Descrição                                      |
|-------------|--------|------------------------------------------------|
| `id`        | Long   | Identificador único do cliente                 |
| `nome`      | String | Nome do cliente                                |
| `tipo`      | String | Tipo do cliente (comum, VIP, etc.)             |
| `fidelidade`| Double | Percentual de fidelidade                       |
| `logradouro`| String | Endereço do cliente                            |
| `bairro`    | String | Bairro do cliente                              |
| `cidade`    | String | Cidade do cliente                              |

### 2. Item

A entidade **Item** representa os itens contidos em um pedido.

| Campo         | Tipo   | Descrição                                      |
|---------------|--------|------------------------------------------------|
| `id`          | Long   | Identificador único do item                    |
| `nome`        | String | Nome do item                                   |
| `quantidade`  | Int    | Quantidade de itens                            |
| `valorUnitario`| Double | Valor unitário do item                         |
| `tipo`        | String | Tipo de item (produto, serviço, etc.)          |

### 3. Pedido

A entidade **Pedido** representa um pedido realizado por um cliente.

| Campo        | Tipo   | Descrição                                      |
|--------------|--------|------------------------------------------------|
| `id`         | Long   | Identificador único do pedido                  |
| `data`       | Date   | Data do pedido                                 |
| `taxaEntrega`| Double | Valor da taxa de entrega (padrão 10.0)         |
| `cliente`    | Cliente| Cliente que realizou o pedido                  |
| `itens`      | List   | Lista de itens incluídos no pedido             |

## Endpoints da API

### 1. Cadastro de Cliente

**POST** `/clientes`

Cria um novo cliente.

#### Exemplo de Requisição:

```json
{
    "nome": "João da Silva",
    "tipo": "VIP",
    "fidelidade": 0.15,
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cidade": "São Paulo"
}
```
#### Exemplo de Resposta:

```json
{
    "id": 1,
    "nome": "João da Silva",
    "tipo": "VIP",
    "fidelidade": 0.15,
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cidade": "São Paulo"
}
```
### 2. Criação de Pedido
**POST** `/pedidos`

Cria um novo pedido.

#### Exemplo de Requisição:
```json
{
    "data": "2024-09-28",
    "cliente": {
        "id": 1
    },
    "itens": [
        {
            "nome": "Produto A",
            "quantidade": 2,
            "valorUnitario": 50.0,
            "tipo": "produto"
        },
        {
            "nome": "Serviço B",
            "quantidade": 1,
            "valorUnitario": 100.0,
            "tipo": "serviço"
        }
    ]
}

```

#### Exemplo de Resposta:
```json
{
  "id": 1,
  "data": "2024-09-28",
  "taxaEntrega": 10.0,
  "cliente": {
    "id": 1,
    "nome": "João da Silva",
    "tipo": "VIP"
  },
  "itens": [
    {
      "id": 1,
      "nome": "Produto A",
      "quantidade": 2,
      "valorUnitario": 50.0,
      "tipo": "produto"
    },
    {
      "id": 2,
      "nome": "Serviço B",
      "quantidade": 1,
      "valorUnitario": 100.0,
      "tipo": "serviço"
    }
  ]
}
```

### 3. Processamento de Descontos
**POST** `/pedidos/{pedidoId}/processar-descontos`

Aplica as regras de desconto ao pedido. O pedido pode ser beneficiado por diferentes formas de desconto, como descontos por valor de pedido, tipo de cliente ou região de entrega.

#### Exemplo de Requisição:
```bash
POST /pedidos/1/processar-descontos
```

#### Exemplos de Resposta:
```json
{
    "id": 1,
    "data": "2024-09-28",
    "taxaEntrega": 5.0,
    "cliente": {
        "id": 1,
        "nome": "João da Silva",
        "tipo": "VIP"
    },
    "itens": [
        {
            "id": 1,
            "nome": "Produto A",
            "quantidade": 2,
            "valorUnitario": 50.0
        },
        {
            "id": 2,
            "nome": "Serviço B",
            "quantidade": 1,
            "valorUnitario": 100.0
        }
    ]
}

```

### 4. Consulta de Pedido

**GET** ``/pedidos/{pedidoId}``

Consulta um pedido pelo seu ID.

#### Exemplo de Resposta:
```json
{
    "id": 1,
    "data": "2024-09-28",
    "taxaEntrega": 5.0,
    "cliente": {
        "id": 1,
        "nome": "João da Silva",
        "tipo": "VIP"
    },
    "itens": [
        {
            "id": 1,
            "nome": "Produto A",
            "quantidade": 2,
            "valorUnitario": 50.0
        },
        {
            "id": 2,
            "nome": "Serviço B",
            "quantidade": 1,
            "valorUnitario": 100.0
        }
    ]
}
```

## Execução Local

### 1. Clone o repositório:
```bash
git clone https://github.com/JvMSan/PedidoTaxaEntrega.git
cd desconto-api
```

### 2. Construa o projeto com Maven:
```bash
mvn clean install
```

### 3. Execute a aplicação:
```bash
mvn spring-boot:run
```
### 4. Acesse a API em ``http://localhost:8080``.

## Testes no Postman

+ Importe o arquivo ``Postman_Collection.json`` disponível neste repositório para o Postman.
+ Utilize os endpoints ``/clientes``, ``/pedidos``, ``/pedidos/{pedidoId}/processar-descontos``, e ``/pedidos/{pedidoId}`` para testar a API.

## Regras de Negócio
+ Desconto de até **5%** para pedidos cujo valor total exceda **100 reais**.
+ Desconto de até **10%** baseado na fidelidade do cliente (exemplo: cliente VIP).
+ Desconto especial para clientes de determinadas regiões (bairro/cidade).

## Autores
#### João Victor Mascarenhas, Ruan Vieira Ribeiro e Caio Lapa.