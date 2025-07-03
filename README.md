# 📦 Projeto: Cadastro de Clientes

Sistema backend para cadastro de clientes (PF ou PJ) com validação de endereço via ViaCEP.

---

## ✅ Funcionalidades

- Cadastro de clientes:
  - Pessoa Física (PF)
  - Pessoa Jurídica (PJ)
- Validação do CEP informado, consultando a API do [ViaCEP](https://viacep.com.br).
- Relacionamento **One-to-One** entre Cliente e Endereço.
- Testes unitários completos (JUnit + Mockito).

---

## 🗄️ Tecnologias

- **Java 11+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (para testes)
- **JUnit 5**
- **Mockito**
- **Maven**

---

## 💾 Estrutura de Domínio

### 🧑‍💼 Client

| Campo             | Tipo         | Observações                           |
|-------------------|--------------|---------------------------------------|
| clientId          | Long         | PK                                    |
| name              | String       | Nome do cliente                       |
| clientCPF         | String       | Obrigatório para PF                   |
| clientCNPJ        | String       | Obrigatório para PJ                   |
| clientType        | Enum         | PF ou PJ                              |
| email             | String       | -                                     |
| mobileTelephone   | String       | -                                     |
| fixedTelephone    | String       | -                                     |
| address           | Address      | One-to-One relationship               |

---

### 🏠 Address

| Campo              | Tipo     | Observações                  |
|--------------------|----------|------------------------------|
| addressId          | Long     | PK                           |
| zipCode            | String   | CEP do endereço              |
| streetName         | String   | Logradouro                   |
| addressComplement  | String   | Complemento                  |
| city               | String   | Cidade                       |
| district           | String   | Bairro                       |
| state              | String   | UF                           |

---

## 🔗 Integração ViaCEP

- Valida o CEP antes de persistir dados no banco.
- Endpoint consumido: https://viacep.com.br/ws/{cep}/json/


Exemplo de resposta (CEP válido):

```json
{
"cep": "01310-200",
"logradouro": "Avenida Paulista",
"complemento": "de 1001 a 2033 - lado ímpar",
"bairro": "Bela Vista",
"localidade": "São Paulo",
"uf": "SP"
}
```
Se o CEP não existir, a API retorna:

```json
{
  "erro": true
}
```
## 🧪 Testes Unitários

### ClientServiceTest:

- Cadastro de cliente PF.
- Cadastro de cliente PJ.
- Validação obrigatória do CNPJ para PJ.

### AddressServiceTest:

- Validação de CEP existente ou não.
- Construção do objeto Address.
- Para rodar os testes:

bash
Copiar
Editar
mvn test
🔧 Como executar
Clone o repositório:

bash
git clone https://github.com/seu-usuario/seu-repositorio.git

### Navegue até o diretório:

bash
cd seu-repositorio

### Execute o projeto:

bash
mvn spring-boot:run

## 📄 Exemplo JSON de Cadastro
```json

{
  "name": "João da Silva",
  "email": "joao@email.com",
  "clientCPF": "12345678901",
  "clientType": "PF",
  "mobileTelephone": "11999999999",
  "fixedTelephone": "1133334444",
  "address": {
    "zipCode": "01310-200",
    "streetName": "Avenida Paulista",
    "addressComplement": "Apto 101",
    "city": "São Paulo",
    "district": "Bela Vista",
    "state": "SP"
  }
}
```
## 📝 Observações

- O projeto não persiste dados em produção. O banco é H2 para ambiente de testes.
- ViaCEP pode limitar requisições em massa — utilize com responsabilidade.

