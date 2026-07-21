# API Documentation - Arquitetura Spring

Documentação completa dos endpoints REST disponíveis na aplicação.

## 📋 Índice

- [Base URL](#base-url)
- [Autenticação](#autenticação)
- [Formato de Dados](#formato-de-dados)
- [Endpoints](#endpoints)
- [Modelos de Dados](#modelos-de-dados)
- [Códigos de Resposta](#códigos-de-resposta)
- [Exemplos Completos](#exemplos-completos)

---

## Base URL

```
http://localhost:8080
```

A aplicação roda na porta 8080 por padrão.

---

## Autenticação

Atualmente, a aplicação **não requer autenticação**. Todos os endpoints são públicos.

---

## Formato de Dados

- **Content-Type**: `application/json`
- **Charset**: UTF-8

---

## Endpoints

### 1. Ligar Carro

**Descrição:** Cria um Honda HRV com o motor turbo injetado e tenta ligá-lo com a chave fornecida.

**Endpoint:**
```
POST /carros
```

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "montadora": "HONDA",
  "tipo": "normal"
}
```

**Parâmetros:**

| Campo | Tipo | Obrigatório | Descrição |
|-------|------|-----------|-----------|
| `montadora` | String | Sim | Montadora da chave: `HONDA` ou `TOYOTA` |
| `tipo` | String | Não | Tipo da chave (valor livre) |

**Response (Sucesso - HTTP 200):**
```json
{
  "mensagem": "Carro ligado. Rodando com o motor Motor{modelo='TH-40', potencia=250, cilindros=4, litragem=1.5, tipo=TURBO}"
}
```

**Response (Falha - HTTP 200):**
```json
{
  "mensagem": "Não é possível dar Ignição com essa chave"
}
```

**Notas:**
- O carro criado é sempre um **Honda HRV**
- O motor utilizado é sempre o **Motor Turbo** (injetado via @Turbo)
- A ignição terá sucesso apenas se a montadora da chave for **HONDA**
- Mesmo em caso de falha, a resposta é HTTP 200

---

## Modelos de Dados

### Chave

Representa a chave de um automóvel.

```json
{
  "montadora": "HONDA",
  "tipo": "normal"
}
```

**Propriedades:**

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `montadora` | String | Enum: `HONDA`, `TOYOTA` |
| `tipo` | String | Tipo da chave (ex: "normal", "presença") |

### CarroStatus

Resultado da operação de ligar o carro.

```json
{
  "mensagem": "Carro ligado. Rodando com o motor ..."
}
```

**Propriedades:**

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `mensagem` | String | Mensagem descrevendo o resultado da operação |

### Motor (Referência)

Informações do motor do carro (retornado na mensagem).

**Propriedades:**
- `modelo`: String - Nome do modelo
- `potencia`: Integer - Potência em HP
- `cilindros`: Integer - Número de cilindros
- `litragem`: Double - Deslocamento em litros
- `tipo`: String - Tipo: `ASPIRADO`, `TURBO`, `ELETRICO`

**Motor Turbo (Injetado por Padrão):**
```
Motor{modelo='TH-40', potencia=250, cilindros=4, litragem=1.5, tipo=TURBO}
```

### Carro (Referência)

O carro criado no endpoint POST /carros é sempre:

| Propriedade | Valor |
|------------|-------|
| Modelo | HRV |
| Montadora | HONDA |
| Cor | Preto (Color.BLACK) |
| Motor | Turbo (250 HP) |

---

## Códigos de Resposta

### 200 OK

Requisição bem-sucedida. O carro foi ligado ou a ignição falhou por chave incompatível.

```
POST /carros
Content-Type: application/json

{
  "montadora": "HONDA",
  "tipo": "normal"
}

HTTP/1.1 200 OK
Content-Type: application/json

{
  "mensagem": "Carro ligado. Rodando com o motor Motor{...}"
}
```

### 400 Bad Request

Corpo da requisição inválido ou formato JSON incorreto.

```
POST /carros
Content-Type: application/json

{ invalid json }

HTTP/1.1 400 Bad Request
```

### 404 Not Found

Endpoint não encontrado.

```
POST /carros-invalido

HTTP/1.1 404 Not Found
```

### 405 Method Not Allowed

Método HTTP não permitido.

```
GET /carros    <!-- Somente POST é permitido -->

HTTP/1.1 405 Method Not Allowed
```

---

## Exemplos Completos

### Exemplo 1: Ligar Carro com Chave Honda (Sucesso)

**Requisição:**
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{
    "montadora": "HONDA",
    "tipo": "normal"
  }'
```

**Resposta:**
```json
{
  "mensagem": "Carro ligado. Rodando com o motor Motor{modelo='TH-40', potencia=250, cilindros=4, litragem=1.5, tipo=TURBO}"
}
```

**Status:** 200 OK

---

### Exemplo 2: Ligar Carro com Chave Toyota (Falha)

**Requisição:**
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{
    "montadora": "TOYOTA",
    "tipo": "normal"
  }'
```

**Resposta:**
```json
{
  "mensagem": "Não é possível dar Ignição com essa chave"
}
```

**Status:** 200 OK

---

### Exemplo 3: Requisição com cURL e Pretty Print

```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{
    "montadora": "HONDA",
    "tipo": "presença"
  }' | jq .
```

**Resposta Formatada:**
```json
{
  "mensagem": "Carro ligado. Rodando com o motor Motor{modelo='TH-40', potencia=250, cilindros=4, litragem=1.5, tipo=TURBO}"
}
```

---

### Exemplo 4: Usando Python Requests

```python
import requests
import json

url = "http://localhost:8080/carros"
headers = {
    "Content-Type": "application/json"
}
payload = {
    "montadora": "HONDA",
    "tipo": "normal"
}

response = requests.post(url, json=payload, headers=headers)
print(json.dumps(response.json(), indent=2))

# Saída:
# {
#   "mensagem": "Carro ligado. Rodando com o motor Motor{...}"
# }
```

---

### Exemplo 5: Usando JavaScript Fetch

```javascript
const url = "http://localhost:8080/carros";
const payload = {
  montadora: "HONDA",
  tipo: "normal"
};

fetch(url, {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify(payload)
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error("Erro:", error));

// Saída no console:
// {mensagem: 'Carro ligado. Rodando com o motor Motor{...}'}
```

---

## Fluxo de Negócio

### Caso de Uso: Ligar um Honda HRV

```
┌─────────────────────────────────────────────────────────┐
│ 1. Cliente envia POST /carros com Chave                │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓
┌─────────────────────────────────────────────────────────┐
│ 2. TesteFabricaController recebe a requisição          │
│    e injeta o Motor Turbo (via @Turbo)                │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓
┌─────────────────────────────────────────────────────────┐
│ 3. Cria um novo HondaHRV com o Motor Turbo             │
│    (modelo: "HRV", cor: BLACK, montadora: HONDA)       │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓
┌─────────────────────────────────────────────────────────┐
│ 4. Chama carro.darIgnicao(chave)                       │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓
        ┌──────────────┴──────────────┐
        │                             │
        ↓                             ↓
┌──────────────────┐        ┌──────────────────────────┐
│ Chave.montadora  │        │ Carro.montadora          │
│ == HONDA?        │        │ == HONDA?                │
└──────────────────┘        └──────────────────────────┘
        │                             │
        ├──── SIM ────────────────────┤
        │                             │
        ↓                             ↓
        │                    ┌─────────────────────────┐
        │                    │ Ignição bem-sucedida    │
        │                    │ Retorna: "Carro ligado" │
        │                    │ HTTP 200                │
        │                    └─────────────────────────┘
        │
        ├──── NÃO
        │
        ↓
        │
        ↓
┌─────────────────────────────────────────┐
│ Ignição falhou                          │
│ Retorna: "Não é possível dar Ignição"   │
│ HTTP 200                                │
└─────────────────────────────────────────┘
```

---

## Validação de Entrada

### Valores Válidos para `montadora`:
- `HONDA` ✅
- `TOYOTA` ✅
- Qualquer outro valor ❌ (não causa erro, apenas falha na ignição)

### Tipo de Chave:
- Qualquer string é aceita
- Exemplos: `"normal"`, `"presença"`, `"cópia"`, etc.
- Se omitido, será nulo (null)

---

## Notas Importantes

1. **Motor Injetado**: O endpoint sempre usa o Motor Turbo (250 HP) porque está decorado com `@Turbo`

2. **Carro Criado**: Sempre um Honda HRV preto da montadora Honda

3. **Validação**: Apenas a montadora é validada para permitir a ignição

4. **Segurança**: Sem autenticação (é um exemplo educacional)

5. **Transações**: Não há persistência de dados (sem banco de dados)

6. **CORS**: Se precisar chamar de outro domínio, configure CORS no Spring

---

## Próximos Passos

Para expandir esta API, você poderia:

1. Adicionar persistência de dados com JPA/Hibernate
2. Implementar diferentes endpoints para cada tipo de motor
3. Adicionar validação de entrada com Jakarta Validation
4. Implementar tratamento de exceções customizado
5. Adicionar logging e monitoramento
6. Implementar autenticação e autorização
7. Documentar com Swagger/OpenAPI

---

**Versão da API:** 1.0  
**Última Atualização:** 2026  
**Status:** Ativo ✅
