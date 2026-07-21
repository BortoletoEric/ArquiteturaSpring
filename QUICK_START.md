# Quick Start Guide - Arquitetura Spring

Guia rápido para começar a trabalhar com este projeto.

## ⚡ 5 Minutos para Começar

### 1. Clonar o Repositório
```bash
git clone https://github.com/BortoletoEric/ArquiteturaSpring.git
cd ArquiteturaSpring
```

### 2. Compilar o Projeto
```bash
mvn clean compile
```

### 3. Executar a Aplicação
```bash
mvn spring-boot:run
```

A aplicação iniciará em **http://localhost:8080**

### 4. Testar o Endpoint
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{"montadora": "HONDA", "tipo": "normal"}'
```

Você verá a resposta:
```json
{
  "mensagem": "Carro ligado. Rodando com o motor Motor{modelo='TH-40', potencia=250, cilindros=4, litragem=1.5, tipo=TURBO}"
}
```

✅ **Pronto!** Sua aplicação está funcionando!

---

## 📚 Estrutura de Arquivos Importantes

```
ArquiteturaSpring/
├── README.md                    ← Leia primeiro!
├── API_DOCUMENTATION.md         ← Documentação da API
├── ARCHITECTURE.md              ← Guia de arquitetura
├── QUICK_START.md              ← Este arquivo
│
├── src/
│   └── main/java/
│       └── io/github/bortoletoeric/arquiteturaspring/
│           │
│           ├── ArquiteturaspringApplication.java
│           │   └── Ponto de entrada
│           │
│           └── montadora/
│               ├── Motor.java                 ← Componente injetável
│               ├── Carro.java                 ← Classe base
│               ├── HondaHRV.java              ← Implementação
│               ├── Chave.java                 ← Modelo de entrada
│               ├── CarroStatus.java           ← Modelo de resposta
│               ├── TipoMotor.java             ← Enum
│               ├── Montadora.java             ← Enum
│               │
│               ├── api/
│               │   ├── TesteFabricaController.java  ← REST Controller
│               │   ├── Turbo.java                   ← Custom Qualifier
│               │   ├── Aspirado.java                ← Custom Qualifier
│               │   └── Eletrico.java                ← Custom Qualifier
│               │
│               └── configuration/
│                   └── MontadoraConfiguration.java  ← Bean Factory
│
├── pom.xml                      ← Dependências Maven
└── target/
    └── site/apidocs/            ← JavaDocs (após mvn javadoc:javadoc)
```

---

## 🎓 Caminho de Aprendizado Recomendado

### Dia 1: Fundamentos
1. Leia [README.md](README.md)
2. Execute o projeto e teste o endpoint
3. Estude as classes simples:
   - `Montadora.java` (Enum simples)
   - `TipoMotor.java` (Enum simples)
   - `Motor.java` (POJO básico)

### Dia 2: Arquitetura
1. Leia [ARCHITECTURE.md](ARCHITECTURE.md)
2. Entenda o padrão MVC
3. Estude:
   - `Carro.java` (Classe abstrata)
   - `HondaHRV.java` (Herança)
   - `Chave.java` (POJO)
   - `CarroStatus.java` (Record)

### Dia 3: Spring e DI
1. Estude injeção de dependência
2. Leia sobre Beans do Spring
3. Examine:
   - `MontadoraConfiguration.java` (Configuration, @Bean)
   - `TesteFabricaController.java` (@Autowired, @Turbo)

### Dia 4: REST e Customização
1. Leia [API_DOCUMENTATION.md](API_DOCUMENTATION.md)
2. Teste todos os exemplos
3. Estude:
   - `TesteFabricaController.java` (REST, @PostMapping)
   - `Turbo.java`, `Aspirado.java`, `Eletrico.java` (Custom Annotations)

### Dia 5: Expansão
1. Implemente melhorias sugeridas em [ARCHITECTURE.md](ARCHITECTURE.md#extensibilidade)
2. Adicione validação
3. Crie novos carros e motores

---

## 🔍 Explorar o Código

### Entender Dependency Injection

**Arquivo:** `TesteFabricaController.java`

```java
@RestController
@RequestMapping("/carros")
public class TesteFabricaController {
    
    @Autowired      // ← Spring injeta automaticamente
    @Turbo          // ← Especifica qual Bean injetar
    private Motor motor;  // ← Não use 'new Motor()'
    
    @PostMapping
    public CarroStatus ligarCarro(@RequestBody Chave chave) {
        var carro = new HondaHRV(motor);  // ← Use o motor injetado
        return carro.darIgnicao(chave);
    }
}
```

### Entender Beans do Spring

**Arquivo:** `MontadoraConfiguration.java`

```java
@Configuration
public class MontadoraConfiguration {
    
    @Bean("motorTurbo")     // ← Cria um Bean nomeado
    @Primary                // ← Prioridade padrão
    public Motor motorTurbo() {
        var motor = new Motor();
        motor.setPotencia(250);
        // ... mais configurações
        return motor;
    }
    
    @Bean("motorAspirado")
    public Motor motorAspirado() {
        // ... outra configuração
    }
}
```

### Entender Custom Qualifiers

**Arquivo:** `Turbo.java`

```java
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("motorTurbo")    // ← Aponta para o Bean "motorTurbo"
public @interface Turbo {
}

// Uso:
@Autowired
@Turbo              // ← Injeta o Bean "motorTurbo"
private Motor motor;
```

---

## 🧪 Testes Rápidos

### Teste 1: Ligar com chave correta
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{"montadora": "HONDA", "tipo": "normal"}'
```

**Resposta esperada:** "Carro ligado..."

### Teste 2: Ligar com chave errada
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{"montadora": "TOYOTA", "tipo": "normal"}'
```

**Resposta esperada:** "Não é possível dar Ignição com essa chave"

### Teste 3: Com tipo de chave
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{"montadora": "HONDA", "tipo": "presença"}'
```

**Resposta esperada:** "Carro ligado..."

### Teste 4: Ligar e forçar formatação
```bash
curl -X POST http://localhost:8080/carros \
  -H "Content-Type: application/json" \
  -d '{"montadora": "HONDA"}' | jq .
```

**Resposta formatada com JQ**

---

## 💡 Conceitos Chave Explicados Rapidamente

### Dependency Injection (DI)

**O que é:** Spring fornece objetos (dependências) em vez de você criá-los.

**Código:**
```java
// ❌ Sem DI (você cria)
private Motor motor = new Motor();

// ✅ Com DI (Spring cria)
@Autowired
private Motor motor;  // Spring injeta
```

### Bean

**O que é:** Um objeto gerenciado pelo Spring.

**Código:**
```java
// Definir um Bean:
@Bean
public Motor motorTurbo() {
    return new Motor();
}

// Usar:
@Autowired
private Motor motor;  // É um Bean!
```

### Qualifier

**O que é:** Quando há múltiplos Beans, Qualifier especifica qual usar.

**Código:**
```java
// Múltiplos Beans:
@Bean("motorTurbo")
public Motor motorTurbo() { ... }

@Bean("motorAspirado")
public Motor motorAspirado() { ... }

// Qual injetar?
@Autowired
@Turbo  // ← Qualifier: use o motorTurbo
private Motor motor;
```

### REST Controller

**O que é:** Classe que expõe endpoints HTTP.

**Código:**
```java
@RestController
@RequestMapping("/carros")
public class TesteFabricaController {
    
    @PostMapping  // ← POST /carros
    public CarroStatus ligarCarro(@RequestBody Chave chave) {
        // Lógica aqui
    }
}
```

---

## 🛠️ Tarefas Práticas

### Tarefa 1: Adicionar um novo Motor

**Objetivo:** Criar um Bean para um novo tipo de motor

**Passos:**
1. Abra `MontadoraConfiguration.java`
2. Adicione um novo método @Bean
3. Configure com especificações diferentes
4. Compile e execute

**Solução:**
```java
@Bean("motorEconomico")
public Motor motorEconomico() {
    var motor = new Motor();
    motor.setModelo("ECO-20");
    motor.setPotencia(80);
    motor.setCilindros(3);
    motor.setLitragem(1.0);
    motor.setTipo(TipoMotor.ASPIRADO);
    return motor;
}
```

### Tarefa 2: Criar um novo Carro

**Objetivo:** Criar uma classe que estende Carro

**Passos:**
1. Crie `ToyotaCorolla.java` em `montadora/`
2. Estenda `Carro`
3. Configure no construtor
4. Use em novo endpoint

**Solução:**
```java
public class ToyotaCorolla extends Carro {
    public ToyotaCorolla(Motor motor) {
        super(motor);
        setModelo("Corolla");
        setCor(Color.BLUE);
        setMontadora(Montadora.TOYOTA);
    }
}
```

### Tarefa 3: Criar novo Qualifier

**Objetivo:** Criar uma anotação customizada

**Passos:**
1. Copie `Turbo.java` como base
2. Renomeie para `Economico.java`
3. Mude @Qualifier para "motorEconomico"
4. Use em novo endpoint

**Solução:**
```java
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("motorEconomico")
public @interface Economico {}
```

---

## 📖 Documentação Gerada

### Gerar JavaDocs

```bash
mvn javadoc:javadoc
```

**Resultado:** `target/site/apidocs/index.html`

Abra em navegador para ver:
- Diagrama de classes
- Documentação de cada método
- Links entre classes
- Histórico de versões

---

## 🐛 Troubleshooting

### Problema: "Porta 8080 já está em uso"

**Solução 1:** Usar outra porta
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

**Solução 2:** Matar processo na porta 8080
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID>

# Linux/Mac
lsof -i :8080
kill <PID>
```

### Problema: "Motor symbol not found"

**Causa:** Falta compilar

**Solução:**
```bash
mvn clean compile
```

### Problema: Import não reconhecido

**Causa:** Dependências não baixadas

**Solução:**
```bash
mvn clean install
```

---

## 🚀 Próximos Passos

### Aprofundamento
- [ ] Ler documentação oficial do Spring
- [ ] Estudar Spring Data JPA
- [ ] Aprender sobre Spring Security
- [ ] Explorar Spring Cloud

### Melhorias no Projeto
- [ ] Adicionar validação (@Valid)
- [ ] Adicionar persistência (JPA)
- [ ] Implementar logging
- [ ] Adicionar tratamento de erros
- [ ] Documentar com Swagger

### Prática
- [ ] Criar seu próprio projeto similar
- [ ] Implementar novas funcionalidades
- [ ] Escrever testes unitários
- [ ] Deploy em servidor

---

## 📚 Recursos Adicionais

### Documentação Oficial
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)

### Tutoriais
- [Spring Guides](https://spring.io/guides)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-tutorial)

### Comunidade
- [Stack Overflow - Spring Tag](https://stackoverflow.com/questions/tagged/spring)
- [Spring Community Forum](https://spring.io/community)

---

## ❓ Perguntas Frequentes

**P: Por que Spring em vez de fazer manual?**
A: Spring gerencia complexidade (DI, ciclo de vida) e deixa você focar em lógica.

**P: @Autowired vs constructor injection - qual usar?**
A: Constructor é melhor para produção (testável), mas @Autowired é mais simples para aprender.

**P: Posso ter múltiplos @Primary?**
A: Não, Spring lançará erro. Use @Qualifier para resolver.

**P: Como testar com Motor injetado?**
A: Crie um Mock e injete: `@MockBean private Motor motor;`

**P: Posso ter dois Beans do mesmo nome?**
A: Não, terá erro. Use nomes diferentes ou Qualifiers.

---

## ✅ Checklist Final

Antes de considerar pronto, verifique:

- [ ] Projeto compila sem erros
- [ ] Aplicação inicia em http://localhost:8080
- [ ] Endpoint POST /carros responde
- [ ] Teste com chave HONDA funciona
- [ ] Teste com chave TOYOTA falha corretamente
- [ ] JavaDocs foi gerado
- [ ] Leu README.md
- [ ] Entendeu Dependency Injection
- [ ] Entendeu Beans e @Bean
- [ ] Entendeu Custom Qualifiers

---

**Parabéns!** Você começou a explorar Arquitetura Spring! 🎉

---

**Versão:** 1.0  
**Última Atualização:** 2026  
**Tempo de Leitura:** ~15 minutos  
**Status:** Ativo ✅
