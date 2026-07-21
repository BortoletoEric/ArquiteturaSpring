# Arquitetura Spring

Um projeto educacional que demonstra os conceitos fundamentais de arquitetura em aplicações **Spring Boot**, utilizando o tema de uma **Montadora de Carros** como exemplo prático.

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Conceitos Demonstrados](#conceitos-demonstrados)
- [Como Executar](#como-executar)
- [Exemplos de Uso](#exemplos-de-uso)
- [Documentação das Classes](#documentação-das-classes)
- [Requisitos](#requisitos)

## 🎯 Visão Geral

Este projeto demonstra padrões e boas práticas de arquitetura em aplicações Spring Boot:

- **Dependency Injection (DI)**: Como o Spring gerencia e injeta dependências
- **Bean Configuration**: Definição e configuração de Beans do Spring
- **Custom Annotations**: Criação de anotações customizadas como Qualifiers
- **REST Controllers**: Exposição de endpoints HTTP
- **Design Patterns**: Uso de padrões de projeto como Strategy e Factory

### Tema: Montadora de Carros

O projeto utiliza uma analogia com uma montadora de automóveis onde:
- **Carros** são os produtos finais
- **Motores** são componentes que podem ser intercambiados (dependências)
- **Chaves** validam o acesso aos veículos
- **Montadoras** definem marcas e proprietários

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────┐
│          Spring Application Context                      │
├─────────────────────────────────────────────────────────┤
│                                                           │
│  ┌──────────────────────────────────────────────────┐   │
│  │     MontadoraConfiguration (Beans Factory)       │   │
│  ├──────────────────────────────────────────────────┤   │
│  │ • motorAspirado() → Motor (130 HP, 1.7L)        │   │
│  │ • motorEletrico() → Motor (200 HP, 1.0L)        │   │
│  │ • motorTurbo() → Motor (250 HP, 1.5L) [@Primary]│   │
│  └──────────────────────────────────────────────────┘   │
│                          ↓                                │
│  ┌──────────────────────────────────────────────────┐   │
│  │    TesteFabricaController (REST Endpoint)        │   │
│  ├──────────────────────────────────────────────────┤   │
│  │ @Autowired                                       │   │
│  │ @Turbo                                           │   │
│  │ private Motor motor; ← Injected by Spring        │   │
│  │                                                   │   │
│  │ POST /carros → ligarCarro(Chave)                 │   │
│  └──────────────────────────────────────────────────┘   │
│                          ↓                                │
│  ┌──────────────────────────────────────────────────┐   │
│  │    HondaHRV extends Carro                        │   │
│  │    (Concrete Implementation)                     │   │
│  └──────────────────────────────────────────────────┘   │
│                                                           │
└─────────────────────────────────────────────────────────┘
```

## 📁 Estrutura do Projeto

```
src/main/java/io/github/bortoletoeric/arquiteturaspring/
│
├── ArquiteturaspringApplication.java
│   └── Classe principal que inicia a aplicação Spring Boot
│
└── montadora/
    ├── Motor.java
    │   └── Classe que representa um motor com seus atributos
    │       (modelo, potência, cilindros, litragem, tipo)
    │
    ├── TipoMotor.java
    │   └── Enum com tipos: ASPIRADO, TURBO, ELETRICO
    │
    ├── Carro.java
    │   └── Classe abstrata que representa um carro genérico
    │       (modelo, cor, motor, montadora, darIgnicao)
    │
    ├── HondaHRV.java
    │   └── Especialização de Carro para o modelo HRV Honda
    │
    ├── Montadora.java
    │   └── Enum com montadoras: HONDA, TOYOTA
    │
    ├── Chave.java
    │   └── Classe que representa a chave de um carro
    │       (montadora, tipo)
    │
    ├── CarroStatus.java
    │   └── Record que encapsula o resultado de operações
    │
    ├── api/
    │   ├── TesteFabricaController.java
    │   │   └── REST Controller com endpoint POST /carros
    │   │
    │   ├── Turbo.java
    │   │   └── Anotação customizada para qualifier motorTurbo
    │   │
    │   ├── Aspirado.java
    │   │   └── Anotação customizada para qualifier motorAspirado
    │   │
    │   └── Eletrico.java
    │       └── Anotação customizada para qualifier motorEletrico
    │
    └── configuration/
        └── MontadoraConfiguration.java
            └── Classe @Configuration que define os Beans do Spring
                (motorAspirado, motorEletrico, motorTurbo)
```

## 💡 Conceitos Demonstrados

### 1. Dependency Injection (Injeção de Dependência)

**O que é?** Spring gerencia a criação e fornecimento de objetos dependentes.

**Exemplo:**
```java
@RestController
public class TesteFabricaController {
    
    @Autowired
    @Turbo
    private Motor motor;  // Spring injeta um Motor automaticamente
}
```

### 2. Bean Configuration

**O que é?** Definição de objetos gerenciados pelo Spring usando @Bean.

**Exemplo:**
```java
@Configuration
public class MontadoraConfiguration {
    
    @Bean("motorTurbo")
    @Primary  // Este Bean é a escolha padrão
    public Motor motorTurbo() {
        var motor = new Motor();
        motor.setPotencia(250);
        // ... configurações
        return motor;
    }
}
```

### 3. Custom Qualifiers (Anotações Customizadas)

**O que é?** Quando existem múltiplas implementações, Qualifiers especificam qual injetar.

**Exemplo:**
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Qualifier("motorTurbo")
public @interface Turbo {
}

// Uso:
@Autowired
@Turbo
private Motor motor;  // Injeta especificamente o motor turbo
```

### 4. REST Controllers

**O que é?** Endpoints HTTP que expõem funcionalidades da aplicação.

**Exemplo:**
```java
@RestController
@RequestMapping("/carros")
public class TesteFabricaController {
    
    @PostMapping
    public CarroStatus ligarCarro(@RequestBody Chave chave) {
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }
}
```

### 5. Strategy Pattern

**O que é?** Diferentes implementações de Motor (Turbo, Aspirado, Elétrico) que podem ser intercambiadas.

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** ou superior
- **Maven 3.6+**
- **Spring Boot 4.1.0**

### Passos

1. **Clone o repositório:**
```bash
git clone https://github.com/BortoletoEric/ArquiteturaSpring.git
cd ArquiteturaSpring
```

2. **Compile o projeto:**
```bash
mvn clean compile
```

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`

4. **Gere a documentação JavaDoc:**
```bash
mvn javadoc:javadoc
```

A documentação estará disponível em `target/site/apidocs/index.html`

## 📝 Exemplos de Uso

### Ligar um Carro (POST /carros)

**Requisição:**
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{
    "montadora": "HONDA",
    "tipo": "normal"
  }'
```

**Resposta (Sucesso - Chave compatível):**
```json
{
  "mensagem": "Carro ligado. Rodando com o motor Motor{modelo='TH-40', potencia=250, cilindros=4, litragem=1.5, tipo=TURBO}"
}
```

**Resposta (Erro - Chave incompatível):**
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{
    "montadora": "TOYOTA",
    "tipo": "normal"
  }'
```

Resposta:
```json
{
  "mensagem": "Não é possível dar Ignição com essa chave"
}
```

## 📚 Documentação das Classes

### Motor

Representa um motor de automóvel com suas características técnicas.

**Propriedades:**
- `modelo`: String - Nome do modelo (ex: "TH-40")
- `potencia`: Integer - Potência em HP
- `cilindros`: Integer - Número de cilindros
- `litragem`: Double - Deslocamento em litros
- `tipo`: TipoMotor - Tipo do motor

**Métodos Principais:**
- `getters/setters` - Acesso às propriedades
- `toString()` - Representação em string

### Carro

Classe abstrata que representa um automóvel genérico.

**Propriedades:**
- `modelo`: String - Modelo do carro
- `cor`: Color - Cor do veículo
- `motor`: Motor - Motor acoplado
- `montadora`: Montadora - Fabricante

**Métodos Principais:**
- `darIgnicao(Chave)` - Tenta ligar o carro
- `getters/setters` - Acesso às propriedades

### HondaHRV

Especialização de Carro para o modelo Honda HRV.

**Configurações Automáticas:**
- Modelo: "HRV"
- Cor: Preto
- Montadora: HONDA

### TesteFabricaController

REST Controller que demonstra injeção de dependência.

**Endpoints:**
- `POST /carros` - Cria um HondaHRV e tenta ligá-lo com a chave fornecida

**Injeção:**
- Motor Turbo injetado através da anotação @Turbo

### MontadoraConfiguration

Classe de configuração que define os Beans do Spring.

**Beans Definidos:**
1. `motorAspirado` - Motor de 130 HP, 1.7L
2. `motorEletrico` - Motor de 200 HP, 1.0L
3. `motorTurbo` - Motor de 250 HP, 1.5L (**@Primary**)

## 🔧 Configuração do Spring

### Properties (application.properties)

A aplicação utiliza a configuração padrão do Spring Boot. Para customizar:

```properties
# Port
server.port=8080

# Banner
spring.main.banner-mode=off

# Profiles
spring.profiles.active=production
```

### Profile Production

A aplicação é executada com o profile `production` ativo, conforme definido em `ArquiteturaspringApplication.java`.

## 📖 Padrões de Projeto

### 1. **Dependency Injection Pattern**
- Inversão de Controle (IoC)
- Gerenciamento de ciclo de vida pelos Beans

### 2. **Strategy Pattern**
- Diferentes tipos de Motor (Turbo, Aspirado, Elétrico)
- Intercambiáveis em tempo de execução

### 3. **Factory Pattern**
- MontadoraConfiguration cria e configura Beans
- Spring gerencia o ciclo de vida

### 4. **Template Method Pattern**
- Classe abstrata Carro define o comportamento comum
- HondaHRV estende com configurações específicas

## 🧪 Tecnologias Utilizadas

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| Spring Boot | 4.1.0 | Framework principal |
| Spring Data JPA | 4.1.0 | Persistência (demonstrativo) |
| H2 Database | Latest | Banco de dados em memória |
| Java | 21 | Linguagem |
| Maven | 3.6+ | Build tool |

## 📦 Dependências

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<!-- H2 Database -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

## 🎓 Objetivos de Aprendizado

Este projeto é ideal para aprender:

- ✅ Injeção de Dependência em Spring
- ✅ Configuração de Beans
- ✅ Anotações customizadas e Qualifiers
- ✅ REST Controllers e endpoints
- ✅ Padrões de Projeto
- ✅ Arquitetura de aplicações Spring Boot
- ✅ Como documentar código com JavaDocs

## 👤 Autor

**Eric Bortoleto**
- GitHub: [@BortoletoEric](https://github.com/BortoletoEric)

## 📄 Licença

Este projeto é um exemplo educacional e pode ser usado livremente.

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se livre para:
- Reportar bugs
- Sugerir melhorias
- Expandir a documentação
- Adicionar mais exemplos

## 📞 Suporte

Se tiver dúvidas sobre os conceitos demonstrados:

1. Consulte a documentação oficial do [Spring Boot](https://spring.io/projects/spring-boot)
2. Leia os JavaDocs das classes (execute `mvn javadoc:javadoc`)
3. Revise o código-fonte com comentários explicativos

## 🔗 Referências Úteis

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/reference/index.html)
- [Dependency Injection in Spring](https://docs.spring.io/spring-framework/reference/core/beans.html)
- [Spring Annotations](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config.html)

---

**Versão:** 0.0.1-SNAPSHOT  
**Última Atualização:** 2026  
**Status:** Ativo ✅
