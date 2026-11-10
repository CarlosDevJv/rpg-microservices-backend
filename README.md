# 🛡️ RPG Microservices & Trade Engine

Este projeto é uma arquitetura backend de microsserviços orientada a eventos, inspirada em sistemas de economia de MMORPGs (como casas de leilão, inventários globais e carteiras de jogadores). O principal objetivo é explorar desafios reais de engenharia de software, como consistência eventual, transações distribuídas (Padrão Saga) e comunicação assíncrona de alta confiabilidade.

---

## 🏗️ Arquitetura e Microsserviços

O ecossistema é dividido em domínios isolados, onde cada microsserviço possui seu próprio banco de dados independente:

* **`player`**: Gerencia contas de usuários, perfis e o saldo de moedas virtuais.
* **`inventory`**: Controla o armazenamento de itens, atributos e transferência de propriedade de forma isolada.
* **`auction`**: Orquestra o mercado global (Casa de Leilões), gerindo lances, listagens e o fluxo de compra e venda.

[//]: # (* **`reward`**: Microsserviço worker responsável por integrar com APIs externas &#40;ex: webhooks/recompensas&#41; e injetar recursos no jogo de forma assíncrona.)

---

## 🛠️ Tecnologias e Stack Utilizada

* **Linguagem & Framework:** Java 17+, Spring Boot 3.x, Spring Cloud.
* **Mensageria:** RabbitMQ / Apache Kafka (Comunicação assíncrona e desacoplada).
* **Banco de Dados:** PostgreSQL (Isolado por microsserviço) e Redis (Cache/Controle de concorrência).
* **Infraestrutura:** Docker & Docker Compose.
* **Padrões de Arquitetura:** Padrão Saga (Orquestração/Coreografia), Transactional Outbox, Idempotência.

---

## 🔄 Fluxo de Transações Distribuídas (Padrão Saga)
Para evitar bugs críticos como duplicação de itens (*dupe bugs*) ou perda de saldo devido a falhas de rede, o sistema implementa o **Padrão Saga** via mensageria:
1. Uma transação de compra na Casa de Leilão inicia um fluxo rastreado por um `transactionId único`.
2. O saldo do comprador é debitado/congelado de forma atômica no `player`.
3. O item é transferido de inventário no `inventory`.
4. Em caso de falha em qualquer etapa, eventos compensatórios (*rollbacks lógicos*) são disparados automaticamente pelo barramento para desfazer as operações anteriores.

---

[//]: # (## 🚀 Como Executar o Projeto Localmente)

[//]: # ()
[//]: # (*&#40;Instruções passo a passo de como subir o ambiente via Docker Compose e rodar os serviços&#41;*)

[//]: # ()
[//]: # (1. Clone o repositório:)

[//]: # (   ```bash)

[//]: # (   git clone [https://github.com/SEU-USUARIO/rpg-microservices-backend.git]&#40;https://github.com/SEU-USUARIO/rpg-microservices-backend.git&#41;)