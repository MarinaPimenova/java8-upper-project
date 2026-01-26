# Java 21 Module Practical Task

This project is a multi-module **Java 21** Maven application demonstrating modular design, clean code principles, and the use of modern Java features such as `var`, lambdas, and the Stream API.

The project is built on top of the **jmp-parent** base module and implements banking and service logic across several modules.

---

## 📦 Project Structure

jmp-parent
│
├── jmp-bank-api
├── jmp-dto
├── jmp-service-api
├── jmp-cloud-bank-impl
├── jmp-cloud-service-impl
└── pom.xml

### Module Overview

| Module | Description |
|------|-------------|
| **jmp-bank-api** | Bank-related public interfaces and abstractions |
| **jmp-dto** | Shared DTOs (User, BankCard, etc.) |
| **jmp-service-api** | Service-level interfaces |
| **jmp-cloud-bank-impl** | Bank API implementation |
| **jmp-cloud-service-impl** | Service API implementation |
| **jmp-parent** | Parent Maven module with dependency and plugin management |

---

## 🛠️ Technology Stack

- **Java Development Kit**: Java 21  
- **Build Tool**: Apache Maven  
- **Version Control**: Git  
- **Testing Framework**: JUnit 5  
- **IDE**: IntelliJ IDEA / Eclipse  

---

## ⚙️ Prerequisites

Make sure the following tools are installed:

- **JDK 21**
- **Apache Maven 3.9+**
- **Git**

Verify installations:

```bash
java -version
mvn -version
git --version
````

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd jmp-parent
```

### Build the Project

```bash
mvn clean install
```

---

## 🧩 Implemented Tasks

### 1. Bank Card Creation Logic

**Module**: `jmp-cloud-bank-impl`

Implemented the method:

```java
BankCard createBankCard(User user, BankCardType cardType)
```

**Behavior**:

* Returns a `CreditBankCard` when `BankCardType.CREDIT` is provided
* Returns a `DebitBankCard` when `BankCardType.DEBIT` is provided
* Uses modern Java features (`var`, switch expressions where applicable)

---

### 2. Service Implementation

**Module**: `jmp-cloud-service-impl`

* Added a full implementation of `ServiceImpl`
* Integrated bank API logic
* Applied functional programming concepts (lambdas, streams)

---

### 3. Modern Java Usage

The project demonstrates:

* `var` for local variable inference
* Lambda expressions
* Java Stream API
* Java 21 language improvements where applicable
* Clean and readable code structure

---

## 🧪 Testing

* **Framework**: JUnit 5
* Unit tests cover:

    * Bank card creation logic
    * Core service functionality
* Tests are executed automatically during the Maven build phase

Run tests explicitly:

```bash
mvn test
```

---

## 📄 Maven Configuration

Key configuration details:

* Java source/target compatibility: **21**
* Centralized plugin and dependency management via `jmp-parent`
* JUnit 5 configured via `maven-surefire-plugin`

---

## ✅ Evaluation Criteria Alignment

| Criterion         | Status                                      |
| ----------------- | ------------------------------------------- |
| **Code Quality**  | Clean, modular, readable structure          |
| **Correctness**   | All task requirements implemented           |
| **Java Features** | Streams, lambdas, `var`, modern Java idioms |
| **Testing**       | Unit tests provided with JUnit 5            |

---

## 📌 Notes

* This project is intended for educational and practical assessment purposes.
* All implementations follow clean code and SOLID principles.
* The modular structure allows easy extension and maintenance.

---

## 📜 License

This project is provided for learning and evaluation purposes. No commercial license is implied.


# DEV Notes
## Parent POM
The warning is straightforward: when you are using maven-compiler-plugin without explicitly declaring its version. Since Maven 3.9+, this is strongly discouraged and results in the warning you see.

Even if the version is inherited implicitly, Maven wants it declared explicitly either in:

the module POM, or

the parent POM via <pluginManagement>

the best practice is to define the plugin version once in the parent under pluginManagement.

Parent POM (jmp-parent) — recommended change:
```xml
<build>
    <pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.12.1</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </pluginManagement>
</build>

```
Then your child POM stays clean and needs no version:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
</plugin>
```