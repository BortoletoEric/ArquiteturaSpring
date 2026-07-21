# Guia de Arquitetura - Arquitetura Spring

Documentação detalhada sobre a arquitetura e padrões de projeto utilizados nesta aplicação.

## 📋 Índice

- [Visão Geral da Arquitetura](#visão-geral-da-arquitetura)
- [Padrões de Projeto](#padrões-de-projeto)
- [Fluxo de Execução](#fluxo-de-execução)
- [Camadas da Aplicação](#camadas-da-aplicação)
- [Dependency Injection](#dependency-injection)
- [Configuração de Beans](#configuração-de-beans)
- [Anotações Customizadas](#anotações-customizadas)
- [Design Decisions](#design-decisions)
- [Extensibilidade](#extensibilidade)

---

## Visão Geral da Arquitetura

A aplicação segue uma arquitetura em camadas simples com foco em demonstrar conceitos do Spring:

```
┌─────────────────────────────────────────────────────────┐
│                    REST API Layer                        │
│         (TesteFabricaController)                         │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓ (HTTP Request)
┌─────────────────────────────────────────────────────────┐
│               Spring Application Context                 │
│          (Gerenciamento de Beans e DI)                  │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓ (Injeção de dependências)
┌─────────────────────────────────────────────────────────┐
│               Business Logic Layer                        │
│    (Carro, Motor, HondaHRV, Chave)                      │
└─────────────────────────────────────────────────────────┘
```

### Características Principais

- ✅ **Simples e Educacional**: Fácil de entender e aprender
- ✅ **Sem Banco de Dados**: Foco em arquitetura, não em persistência
- ✅ **Dependency Injection**: Uso completo do Spring IoC
- ✅ **Anotações Customizadas**: Demonstração de criação de Qualifiers
- ✅ **REST API**: Exemplos de REST Controllers
- ✅ **Bem Documentado**: JavaDocs completos

---

## Padrões de Projeto

### 1. Dependency Injection (IoC Pattern)

**O que é?**
Padrão onde as dependências são fornecidas externamente em vez de serem criadas internamente.

**Como é implementado?**
```java
@RestController
public class TesteFabricaController {
    
    @Autowired              // Spring injeta automaticamente
    @Turbo                  // Qualifier especifica qual Bean
    private Motor motor;    // Não criamos com 'new'
}
```

**Benefícios:**
- Desacoplamento entre classes
- Fácil de testar (mock/stub)
- Flexibilidade para trocar implementações
- Ciclo de vida gerenciado automaticamente

**Fluxo:**
```
1. Spring lê @RestController
2. Processa @Autowired
3. Busca Bean do tipo Motor
4. Encontra @Turbo e seleciona "motorTurbo"
5. Injeta a instância
6. Controller está pronto para usar
```

---

### 2. Factory Pattern

**O que é?**
Padrão para criar objetos sem especificar suas classes concretas.

**Como é implementado?**
```java
@Configuration
public class MontadoraConfiguration {
    
    @Bean("motorTurbo")
    public Motor motorTurbo() {
        var motor = new Motor();
        // Configurações...
        return motor;
    }
}
```

**Benefícios:**
- Centralização da criação de objetos
- Fácil configuração e customização
- Reutilização de instâncias (singleton)

---

### 3. Strategy Pattern

**O que é?**
Padrão que permite definir uma família de algoritmos e deixá-los intercambiáveis.

**Como é implementado?**
```
Motor (Strategy)
├── ASPIRADO (130 HP)
├── TURBO (250 HP)
└── ELETRICO (200 HP)

Todos podem ser injetados e intercambiados
```

**Benefícios:**
- Flexibilidade em tempo de execução
- Novo motor? Crie um novo Bean!
- Sem alterar código cliente

---

### 4. Template Method Pattern

**O que é?**
Padrão onde uma classe define o esqueleto de um método, deixando detalhes para subclasses.

**Como é implementado?**
```java
public class Carro {  // Template
    public CarroStatus darIgnicao(Chave chave) {
        if(chave.getMontadora() != this.montadora) {
            return new CarroStatus("Não é possível dar Ignição com essa chave");
        }
        return new CarroStatus("Carro ligado. Rodando com o motor " + motor);
    }
}

public class HondaHRV extends Carro {  // Implementação específica
    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.BLACK);
        setMontadora(Montadora.HONDA);
    }
}
```

---

### 5. Qualifier Pattern (Custom Annotations)

**O que é?**
Padrão para resolver ambiguidade quando múltiplos Beans do mesmo tipo existem.

**Como é implementado?**
```java
// Definição
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("motorTurbo")
public @interface Turbo {}

// Uso
@Autowired
@Turbo
private Motor motor;  // Injeta especificamente motorTurbo
```

**Benefícios:**
- Seleção clara de qual Bean injetar
- Tipos de injeção mais seguros
- Anotações semânticas

---

## Fluxo de Execução

### Inicialização da Aplicação

```
1. main() em ArquiteturaspringApplication
    ↓
2. SpringApplication inicia
    ↓
3. Spring escaneia componentes (@Configuration, @Controller, etc)
    ↓
4. MontadoraConfiguration é processada
    ↓
5. Beans são criados:
   • motorAspirado (@Bean)
   • motorEletrico (@Bean)
   • motorTurbo (@Primary @Bean)
    ↓
6. TesteFabricaController é instanciado
    ↓
7. @Autowired processa:
   • Busca Bean do tipo Motor
   • Encontra @Turbo
   • Injeta motorTurbo
    ↓
8. Aplicação está pronta
    ↓
9. Spring Boot inicia servidor (porta 8080)
```

### Requisição HTTP (POST /carros)

```
1. Cliente envia POST /carros com JSON
    ↓
2. Spring mapeia para @PostMapping
    ↓
3. JSON é desserializado para objeto Chave
    ↓
4. ligarCarro(Chave) é executado
    ↓
5. Novo HondaHRV é criado com motor injetado
    ↓
6. carro.darIgnicao(chave) é chamado
    ↓
7. Validação:
   • chave.montadora == carro.montadora ?
    ↓
8. CarroStatus é criado com mensagem
    ↓
9. CarroStatus é serializado para JSON
    ↓
10. Resposta HTTP 200 é retornada
```

---

## Camadas da Aplicação

### 1. REST API Layer (API Gateway)

**Classe:** `TesteFabricaController`

**Responsabilidades:**
- Receber requisições HTTP
- Desserializar JSON em objetos
- Chamar lógica de negócio
- Serializar resposta em JSON
- Retornar HTTP Response

**Endpoints:**
- `POST /carros` - Ligar carro

**Tecnologias:**
- Spring Web (@RestController, @PostMapping)
- Jackson (serialização JSON)

---

### 2. Configuration Layer

**Classe:** `MontadoraConfiguration`

**Responsabilidades:**
- Definir Beans do Spring
- Configurar instâncias
- Gerenciar ciclo de vida
- Resolver dependências

**Beans:**
- motorAspirado
- motorEletrico
- motorTurbo (Primary)

**Tecnologias:**
- Spring Framework (@Configuration, @Bean, @Primary)

---

### 3. Business Logic Layer

**Classes:**
- `Carro` (abstract)
- `HondaHRV`
- `Motor`
- `Chave`
- `CarroStatus`

**Responsabilidades:**
- Implementar regras de negócio
- Validar dados
- Executar operações
- Retornar resultados

**Tecnologias:**
- Plain Java Objects (POJOs)
- Enums (Montadora, TipoMotor)
- Records (CarroStatus)

---

## Dependency Injection

### How Spring Resolves Dependencies

```
@Autowired
@Turbo
private Motor motor;

Processo:
1. Spring encontra @Autowired
2. Identifica tipo: Motor
3. Procura por Beans do tipo Motor
4. Encontra: motorAspirado, motorEletrico, motorTurbo
5. @Turbo especifica qual: motorTurbo
6. Injeta a instância
```

### Tipos de Injeção Suportados

1. **Constructor Injection** (Recomendado)
```java
@RestController
public class TesteFabricaController {
    private Motor motor;
    
    public TesteFabricaController(@Turbo Motor motor) {
        this.motor = motor;
    }
}
```

2. **Setter Injection**
```java
private Motor motor;

@Autowired
public void setMotor(@Turbo Motor motor) {
    this.motor = motor;
}
```

3. **Field Injection** (Usado neste projeto)
```java
@Autowired
@Turbo
private Motor motor;
```

---

## Configuração de Beans

### Bean Lifecycle

```
1. Instantiation (Criação)
   └─ new Motor()

2. Population of Properties (Preenchimento)
   └─ setPotencia(250)
   └─ setCilindros(4)
   └─ setLitragem(1.5)
   └─ setTipo(TipoMotor.TURBO)

3. Initialization (Inicialização)
   └─ Bean está pronto
   └─ Pode ser injetado

4. Usage (Uso)
   └─ Injetado em TesteFabricaController

5. Destruction (Destruição)
   └─ Quando aplicação fecha
   └─ Spring limpa recursos
```

### Bean Definition

```java
@Configuration
public class MontadoraConfiguration {
    
    @Bean("motorTurbo")      // Nome do Bean
    @Primary                 // Prioridade
    public Motor motorTurbo() {
        var motor = new Motor();
        
        // Configuração
        motor.setModelo("TH-40");
        motor.setPotencia(250);
        motor.setCilindros(4);
        motor.setLitragem(1.5);
        motor.setTipo(TipoMotor.TURBO);
        
        return motor;
    }
}
```

---

## Anotações Customizadas

### Criação

```java
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("motorTurbo")
public @interface Turbo {
    // Anotação customizada = Qualifier + meta-annotations
}
```

**Componentes:**

1. **@Target** - Onde pode ser usada
   - `ElementType.FIELD` - Em campos
   - `ElementType.METHOD` - Em métodos

2. **@Retention** - Quando está disponível
   - `RetentionPolicy.RUNTIME` - Durante execução (Spring precisa)

3. **@Qualifier** - Qual Bean qualificar
   - Referencia o nome do Bean

### Uso

```java
@RestController
public class TesteFabricaController {
    
    @Autowired
    @Turbo              // Anotação customizada
    private Motor motor; // Spring injeta motorTurbo
}
```

### Vantagens

- Menos verboso que `@Qualifier("motorTurbo")`
- Mais semântico
- Reutilizável
- Type-safe

---

## Design Decisions

### 1. Por que REST em vez de MVC?

**Decisão:** Usar @RestController em vez de @Controller

**Razão:**
- Demonstrar APIs modernas
- JSON é padrão em arquiteturas de microserviços
- Mais direto para demonstrar injeção de dependência

---

### 2. Por que HondaHRV estende Carro?

**Decisão:** Usar herança em vez de composição

**Razão:**
- Demonstrar Template Method Pattern
- Especialização de tipo
- Reutilizar comportamento comum (darIgnicao)

---

### 3. Por que usar Custom Annotations?

**Decisão:** Criar @Turbo, @Aspirado, @Eletrico

**Razão:**
- Demonstrar criação de anotações
- Mais semântico que @Qualifier("motorTurbo")
- Prática comum em código real
- Type-safe

---

### 4. Por que usar Records para CarroStatus?

**Decisão:** CarroStatus como Record em vez de classe

**Razão:**
- Java 16+ feature
- Imutável por padrão
- Menos código (boilerplate)
- Perfeito para DTOs

---

### 5. Por que não usar persistência?

**Decisão:** Sem JPA/Database

**Razão:**
- Foco em arquitetura, não em persistência
- Simplicidade para aprendizado
- Spring Data JPA é tópico separado

---

## Extensibilidade

### Como Adicionar um Novo Tipo de Motor?

**Passo 1:** Adicionar novo Bean
```java
@Configuration
public class MontadoraConfiguration {
    
    @Bean("motorHibrido")
    public Motor motorHibrido() {
        var motor = new Motor();
        motor.setModelo("HB-100");
        motor.setPotencia(180);
        motor.setCilindros(3);
        motor.setLitragem(1.2);
        motor.setTipo(TipoMotor.ASPIRADO); // ou novo tipo
        return motor;
    }
}
```

**Passo 2:** Criar anotação
```java
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("motorHibrido")
public @interface Hibrido {}
```

**Passo 3:** Usar em outro endpoint
```java
@Autowired
@Hibrido
private Motor motor;
```

---

### Como Adicionar um Novo Carro?

**Passo 1:** Criar classe
```java
public class ToyotaCorolla extends Carro {
    public ToyotaCorolla(Motor motor) {
        super(motor);
        setModelo("Corolla");
        setCor(Color.WHITE);
        setMontadora(Montadora.TOYOTA);
    }
}
```

**Passo 2:** Usar no controller
```java
@PostMapping("/toyota")
public CarroStatus ligarToyota(@RequestBody Chave chave) {
    var carro = new ToyotaCorolla(motor);
    return carro.darIgnicao(chave);
}
```

---

### Como Adicionar Validação?

**Passo 1:** Adicionar Jakarta Validation
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

**Passo 2:** Anotar modelo
```java
public class Chave {
    @NotNull
    private Montadora montadora;
    
    @NotBlank
    private String tipo;
}
```

**Passo 3:** Validar no controller
```java
@PostMapping
public CarroStatus ligarCarro(@Valid @RequestBody Chave chave) {
    // Será validado automaticamente
}
```

---

### Como Adicionar Persistência?

**Passo 1:** Adicionar JPA
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

**Passo 2:** Fazer Carro uma entidade
```java
@Entity
public class Carro {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Motor motor;
    
    // ...
}
```

**Passo 3:** Criar repository
```java
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {}
```

---

## Comparação com Alternativas

### Dependency Injection com Spring vs Manual

**Com Spring:**
```java
@Autowired
private Motor motor;  // Gerenciado por Spring
```

**Sem Spring:**
```java
private Motor motor = new MotorFactory.createTurbo();  // Manual
```

**Vantagens do Spring:**
- Menos código boilerplate
- Fácil testar (injetar mocks)
- Ciclo de vida gerenciado
- Configuração centralizada

---

## Métricas de Arquitetura

| Métrica | Valor | Avaliação |
|---------|-------|-----------|
| Acoplamento | Baixo | ✅ Classes desacopladas |
| Coesão | Alta | ✅ Classes bem definidas |
| Complexidade | Baixa | ✅ Fácil entender |
| Testabilidade | Alta | ✅ Fácil de testar |
| Manutenibilidade | Alta | ✅ Bem organizado |
| Escalabilidade | Média | ⚠️ Sem persistência |

---

## Checklist de Aprendizado

Ao estudar este projeto, você deve entender:

- [ ] O que é Dependency Injection
- [ ] Como Spring gerencia Beans
- [ ] @Autowired vs @Qualifier
- [ ] Como criar anotações customizadas
- [ ] Factory Pattern na prática
- [ ] Strategy Pattern na prática
- [ ] Template Method Pattern na prática
- [ ] REST Controllers e @PostMapping
- [ ] Serialização/Desserialização JSON
- [ ] Ciclo de vida de um Bean Spring

---

**Versão:** 1.0  
**Última Atualização:** 2026  
**Status:** Ativo ✅
