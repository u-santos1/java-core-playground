# ☕ Java Core Playground

![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-blue?style=for-the-badge)

Projeto de estudo focado em **Java puro** — sem Spring, sem frameworks. O objetivo é dominar a linguagem em si: estruturas de dados implementadas do zero, Streams/Optional, concorrência e design patterns.

## 🎯 Objetivo

Provar (para mim e para quem for avaliar) que o domínio técnico não vem só de saber usar frameworks, mas de entender o que acontece *"por baixo do capô"* — como uma `HashMap` trata colisões, como uma `Thread` realmente funciona, por que um `Optional` evita `NullPointerException`.

## 📁 Estrutura do Projeto

```text
java-core-playground/
├── collections/
│   ├── MyLinkedList.java          # Lista encadeada implementada do zero
│   ├── MyHashMap.java             # Hash map com tratamento de colisão
│   ├── MyArrayList.java           # Lista dinâmica com resize automático
│   └── MyStack.java               # Pilha (LIFO) com array ou lista
├── streams/
│   ├── StreamExamples.java        # map, filter, reduce, collect
│   ├── OptionalExamples.java      # Boas práticas evitando NPE
│   └── CollectorsExamples.java    # groupingBy, partitioningBy, joining
├── concurrency/
│   ├── ThreadBasics.java          # Thread e Runnable
│   ├── ExecutorServiceDemo.java   # Pool de threads
│   ├── CompletableFutureDemo.java # Composição assíncrona
│   └── ProducerConsumer.java      # BlockingQueue na prática
├── patterns/
│   ├── creational/
│   │   ├── Singleton.java         # Thread-safe (double-checked locking)
│   │   ├── FactoryMethod.java
│   │   └── Builder.java
│   ├── structural/
│   │   ├── Adapter.java
│   │   └── Decorator.java
│   └── behavioral/
│       ├── Strategy.java
│       ├── Observer.java
│       └── Command.java
├── src/test/java/                 # Testes unitários (JUnit 5)
└── README.md
```

## 🧠 O que cada módulo ensina

### 📦 Collections
Implementações próprias das estruturas mais usadas no dia a dia, para entender complexidade de tempo/espaço (Big O) na prática, e não só de memória. Inclui tratamento de colisão (*chaining*) na `MyHashMap` e resize automático na `MyArrayList`.

### 🌊 Streams & Optional
Exemplos práticos de programação funcional em Java: pipelines de `map`/`filter`/`reduce`, uso correto de `Optional` (sem abusar dele como substituto de `if`), e os `Collectors` mais usados em código real.

### ⚡ Concorrência
Da `Thread` crua até `CompletableFuture`, passando por `ExecutorService` e o clássico problema *produtor-consumidor* com `BlockingQueue`. Foco em entender quando usar cada abordagem, não só a sintaxe.

### 🎨 Design Patterns
Implementações do zero (sem lib) dos padrões mais cobrados em entrevista técnica, organizados pela classificação clássica do GoF (Criacional, Estrutural, Comportamental).

## 🚀 Como rodar

```bash
# ==========================================
# No Linux / Mac (Git Bash)
# ==========================================
# Compilar todos os arquivos Java (considerando a pasta src/)
javac -d out $(find src -name "*.java")

# Rodar um exemplo específico
java -cp out collections.MyLinkedListDemo

# Rodar os testes (requer Maven/Gradle configurado)
mvn test
```

```powershell
# ==========================================
# No Windows (PowerShell)
# ==========================================
# Compilar todos os arquivos Java
javac -d out (Get-ChildItem -Path src -Filter *.java -Recurse).FullName

# Rodar um exemplo específico
java -cp out collections.MyLinkedListDemo

# Rodar os testes (requer Maven/Gradle configurado)
mvn test
```

## ✅ Requisitos

- **Java 21+**
- **Maven** ou **Gradle** (para rodar os testes com JUnit 5)

## 📚 Por que este projeto existe?

Este é o primeiro de uma série de projetos de estudo isolados por tema (**Java Core** → Spring Core → JPA → Testes → Mensageria → Spring AI), pensados para aprofundar fundamentos antes de empilhar frameworks. 

A ideia é: se eu entendo como uma `HashMap` funciona por dentro, eu uso a `HashMap` do Java com muito mais confiança — e sei debugar quando algo foge do esperado.

## 🗒️ Status

🚧 Em desenvolvimento — commits incrementais, um conceito por vez.

| Módulo | Status |
|---|---|
| **Collections** | 🚧 Em andamento |
| **Streams & Optional** | ⬜ Não iniciado |
| **Concorrência** | ⬜ Não iniciado |
| **Design Patterns** | ⬜ Não iniciado |

---

📌 *Parte da minha trilha de estudos backend Java. Confira também: spring-core-lab, jpa-deep-dive, testing-mastery.*
