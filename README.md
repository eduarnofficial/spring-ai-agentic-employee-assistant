# spring-ai-agentic-employee-assistant
EduArn: Build an Agentic AI Employee Assistant with Spring AI, Qwen, tool calling, H2 database integration, and RAG using Ollama.

# AI Employee Assistant — Spring Boot, Spring AI & Ollama

A practical **Agentic AI Employee Assistant** built with **Spring Boot, Spring AI, Ollama and Qwen 2.5**. The application demonstrates how an AI model can interact with application-defined tools to retrieve employee information, check leave balances, and combine multiple tool calls to answer complex HR questions.

This project is designed as a hands-on learning example for developers exploring **Generative AI, Agentic AI, LLM tool calling, Spring AI, local AI models, RAG and AI-powered enterprise applications**.

---

## 🚀 Project Overview

The **AI Employee Assistant** demonstrates how a Spring Boot application can connect a locally running LLM with business logic and application data.

Instead of using an AI model only for text generation, the application gives the model access to Java tools such as:

* `getEmployee()`
* `getLeaveBalance()`

The AI can determine which tool is required, retrieve information from the application's database, and use the retrieved data to generate a natural-language response.

### High-Level Architecture

```text
                    User
                      │
                      ▼
              REST API Controller
                      │
                      ▼
             EmployeeAIService
                      │
                      ▼
                Spring AI
                      │
                      ▼
               Qwen 2.5 LLM
                      │
             Tool Calling Decision
                ┌─────┴─────┐
                ▼           ▼
        EmployeeTools   LeaveTools
                │           │
                ▼           ▼
          Employee DB     Leave DB
                │           │
                └─────┬─────┘
                      ▼
                Retrieved Data
                      │
                      ▼
                  Qwen 2.5
                      │
                      ▼
                Final Response
```

---

# Spring AI Agentic Employee Assistant

An AI-powered Employee Assistant built with **AI Online Live Class on weekend**.

## 🎥 Video Tutorial

[![Spring AI Agentic Employee Assistant - Video Tutorial](https://img.youtube.com/vi/oyWtxjWufj8/maxresdefault.jpg)](https://youtu.be/oyWtxjWufj8)

**▶️ Watch the complete tutorial on YouTube**

# 🎯 What This Project Demonstrates

This project provides a practical introduction to several important AI application development concepts.

### Generative AI

Use a local Qwen model to understand natural-language questions and generate responses.

### Spring AI

Connect Java and Spring Boot applications with modern AI models through Spring AI abstractions.

### AI Tool Calling

Allow the LLM to invoke Java application capabilities when it needs additional information.

### Agentic AI

Demonstrate the beginning of an agentic workflow where the model decides which tools are required to answer a user request.

### Local LLM

Run Qwen locally using Ollama instead of sending application data to an external model provider.

### Enterprise AI

Demonstrate how AI can interact with structured employee and leave-management information.

### RAG Foundation

Introduce the architecture required to extend the application with HR documents, embeddings and vector search.

---

# 🏗️ Project Structure

```text
ai-employee-assistant/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── aiemployee/
│   │   │               │
│   │   │               ├── ai/
│   │   │               │   ├── EmployeeAIController.java
│   │   │               │   ├── EmployeeAIService.java
│   │   │               │   ├── EmployeeTools.java
│   │   │               │   └── LeaveTools.java
│   │   │               │
│   │   │               ├── config/
│   │   │               │   └── DataLoader.java
│   │   │               │
│   │   │               ├── employee/
│   │   │               │
│   │   │               ├── leave/
│   │   │               │
│   │   │               └── AiEmployeeApplication.java
│   │   │
│   │   └── resources/
│   │
│   └── test/
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# 🧩 Main Components

## EmployeeAIController

Provides the REST endpoint used to send natural-language questions to the AI assistant.

Example endpoint:

```text
GET /api/ai/ask
```

---

## EmployeeAIService

Acts as the main AI service layer.

It connects the user's question with Spring AI and provides the available tools to the language model.

Conceptually:

```java
return chatClient
        .prompt()
        .user(question)
        .tools(employeeTools, leaveTools)
        .call()
        .content();
```

The important concept is that the application does not need to manually determine every tool call.

The LLM can decide when application tools are useful.

---

## EmployeeTools

Provides employee-related functionality to the AI model.

For example:

```text
getEmployee(101)
```

can retrieve information about employee 101.

---

## LeaveTools

Provides leave-management functionality to the AI model.

For example:

```text
getLeaveBalance(101)
```

can retrieve the employee's available, sick and casual leave information.

---

## DataLoader

Loads demonstration employee and leave data into the application's database.

This makes the project easy to run as a training and demonstration application.

---

# 🛠️ Technology Stack

| Technology             | Purpose                         |
| ---------------------- | ------------------------------- |
| Java                   | Application development         |
| Spring Boot            | Backend application framework   |
| Spring AI              | AI application integration      |
| Ollama                 | Local LLM runtime               |
| Qwen 2.5               | Local chat/generation model     |
| H2                     | Demo relational database        |
| Maven                  | Build and dependency management |
| REST API               | Application interface           |
| Spring AI Tool Calling | AI-to-application interaction   |
| Vector Store           | Future RAG implementation       |

---

# ⚙️ Prerequisites

Before running the application, make sure you have:

* Java installed
* Maven installed
* Spring Boot project dependencies available
* Ollama installed
* Qwen 2.5 model downloaded
* Sufficient system resources for running the selected local model

Verify Ollama:

```bash
ollama --version
```

Pull the Qwen model:

```bash
ollama pull qwen2.5:1.5b
```

Start Ollama if it is not already running:

```bash
ollama serve
```

The application expects Ollama to be available at:

```text
http://localhost:11434
```

---

# ▶️ Run the Spring Boot Application

From the project root directory:

```bash
mvn spring-boot:run
```

The application should start on:

```text
http://localhost:8080
```

---

# 🤖 Test Basic AI

After starting the application, open:

```text
http://localhost:8080/api/ai/ask?question=What%20is%20Spring%20Boot
```

You should receive a response generated by your local Qwen model.

This verifies the basic AI integration:

```text
User Question
     ↓
Spring Boot
     ↓
Spring AI
     ↓
Ollama
     ↓
Qwen 2.5
     ↓
AI Response
```

---

# 👨‍💼 Test the Employee Tool

Try:

```text
http://localhost:8080/api/ai/ask?question=Tell%20me%20about%20employee%20101
```

The intended flow is:

```text
User
  ↓
REST Controller
  ↓
EmployeeAIService
  ↓
Qwen 2.5
  ↓
"I need employee 101 information"
  ↓
getEmployee(101)
  ↓
H2 Database
  ↓
Employee 101
  ↓
Qwen 2.5
  ↓
Final Answer
```

For the demonstration data, the response may contain information similar to:

```text
Rahul Sharma is a Senior Developer in the IT department.
His email is rahul@company.com.
```

The exact wording may vary because the final response is generated by the LLM.

---

# 🏖️ Test the Leave Tool

Ask:

```text
http://localhost:8080/api/ai/ask?question=How%20many%20leaves%20does%20employee%20101%20have
```

The tool can retrieve information such as:

```text
Employee ID: 101
Available: 18
Sick: 8
Casual: 10
```

The LLM can then transform the structured information into a natural-language response:

```text
Employee 101 currently has 18 available leaves,
including 8 sick leaves and 10 casual leaves.
```

---

# 🤖 The Agentic AI Example

The most interesting part of this project is combining multiple tools.

Try:

```text
Tell me about employee 101 and how many leaves they have.
```

The model has access to two application capabilities:

```text
getEmployee()
getLeaveBalance()
```

A potential execution flow is:

```text
                    Qwen
                      │
             ┌────────┴────────┐
             ↓                 ↓
      getEmployee()     getLeaveBalance()
             ↓                 ↓
       Employee DB          Leave DB
             │                 │
             └────────┬────────┘
                      ↓
                 Retrieved Data
                      ↓
                    Qwen
                      ↓
                Final Answer
```

The important concept is that the application provides tools to the model, while the model determines which capabilities are necessary to answer the user's request.

This is a fundamental building block of **Agentic AI applications**.

---

# 🧠 What Is Agentic AI?

A traditional chatbot may primarily follow this pattern:

```text
User
 ↓
LLM
 ↓
Text Response
```

An agentic application can extend that pattern:

```text
User
 ↓
LLM
 ↓
Reason About Required Information
 ↓
Select Tool
 ↓
Execute Application Function
 ↓
Retrieve Data
 ↓
LLM
 ↓
Final Response
```

This allows AI applications to interact with real application capabilities instead of relying only on information contained in the model.

In enterprise applications, these tools could eventually represent:

* Employee systems
* Leave-management systems
* Payroll systems
* CRM systems
* ERP systems
* Ticketing systems
* Internal APIs
* Databases
* Knowledge bases
* Business workflows

---

# 📚 HR Documents and RAG

The next logical extension of this project is **Retrieval-Augmented Generation (RAG)**.

For example, an HR assistant could answer questions about:

* Company policies
* Leave policies
* Employee handbooks
* Benefits
* Attendance policies
* Travel policies
* Remote-work policies
* Internal procedures

A simplified RAG architecture looks like:

```text
HR Documents
      ↓
Document Processing
      ↓
Embeddings
      ↓
Vector Store
      ↓
Semantic Search
      ↓
Relevant Documents
      ↓
Qwen
      ↓
Grounded Answer
```

For a proper RAG implementation, you need an **embedding model** in addition to the chat model.

---

# 🔢 Chat Model vs Embedding Model

Keep the chat model and embedding model separate.

For example:

```text
qwen2.5:1.5b
        ↓
Chat / Generation
```

and:

```text
nomic-embed-text
        ↓
Embeddings
```

Install the embedding model with:

```bash
ollama pull nomic-embed-text
```

The conceptual model configuration is:

```text
Qwen 2.5
    ↓
Text Generation

Nomic Embed Text
    ↓
Vector Embeddings
```

Do not use the chat model as a replacement for the embedding model in a RAG architecture.

---

# 🗃️ Vector Store

For a training or demonstration implementation, Spring AI provides vector-store abstractions that can be used to build the RAG workflow.

A simple in-memory vector store can be useful for experimentation and demonstrations.

For production applications, evaluate a persistent and scalable vector database based on the application's requirements.

The important RAG components are:

```text
Documents
   ↓
Chunking
   ↓
Embedding Model
   ↓
Vector Store
   ↓
Similarity Search
   ↓
Relevant Context
   ↓
Chat Model
   ↓
Answer
```

---

# 🔌 Switching AI Model Providers

One of the major advantages of using Spring AI is that your application can be designed around Spring AI abstractions rather than tightly coupling your business logic to a single AI provider.

The application service can remain conceptually similar:

```java
return chatClient
        .prompt()
        .user(question)
        .tools(employeeTools, leaveTools)
        .call()
        .content();
```

The model provider can then be changed through dependencies and configuration.

Conceptually:

```text
                 Same Java Application
                         │
                      Spring AI
                         │
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
        Ollama         OpenAI         Gemini
          ↓              ↓              ↓
        Qwen             GPT          Gemini
```

---

# 🦙 Ollama

The current implementation uses Ollama for local model execution.

Example configuration:

```properties
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=qwen2.5:1.5b
```

The corresponding Spring AI Ollama model starter should be included in the Maven configuration for the Spring AI release used by the project.

---

# 🟢 OpenAI

The application can also be adapted to an OpenAI-backed model provider.

Conceptually:

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=<your-model>
```

Store credentials using environment variables rather than committing API keys to source control.

Example:

```bash
export OPENAI_API_KEY="your-api-key"
```

---

# 🔵 Google Gemini

A Gemini-based implementation can similarly use the appropriate Spring AI Google GenAI model starter.

Conceptually:

```properties
spring.ai.google.genai.api-key=${GEMINI_API_KEY}
spring.ai.google.genai.chat.options.model=<your-model>
```

Use environment variables or a secure secrets-management solution for credentials.

---

# 🟠 Cohere

The application can also be adapted to other supported Spring AI model providers.

For example:

```properties
spring.ai.cohere.api-key=${COHERE_API_KEY}
```

The exact dependency and configuration should match the Spring AI version and provider integration being used.

---

# 🔐 Security Considerations

This project is intended primarily as a learning and demonstration application.

For production deployment, consider:

* Authentication and authorization
* Role-based access control
* API security
* Input validation
* Tool authorization
* Audit logging
* Secure secrets management
* Database security
* PII protection
* Prompt-injection defenses
* Tool-call validation
* Rate limiting
* Observability
* Model access controls

Most importantly, an AI model should not automatically receive unrestricted access to sensitive business operations.

Tools should be explicitly designed with appropriate permissions and validation.

---

# 🧪 Example Questions

Once the application is running, try questions such as:

```text
What is Spring Boot?
```

```text
Tell me about employee 101.
```

```text
How many leaves does employee 101 have?
```

```text
Tell me about employee 101 and how many leaves they have.
```

You can then experiment with additional natural-language questions to observe how the model interacts with the available tools.

---

# 📈 Learning Path

This project can be used as a progression from basic LLM integration to more advanced enterprise AI.

```text
Level 1
Spring Boot
   ↓
Level 2
Spring AI + Ollama
   ↓
Level 3
Qwen Local LLM
   ↓
Level 4
AI Tool Calling
   ↓
Level 5
Agentic AI
   ↓
Level 6
RAG + Embeddings
   ↓
Level 7
Vector Database
   ↓
Level 8
Enterprise AI Assistant
```

---

# 🎓 Skills You Can Learn

By studying and extending this project, developers can gain practical exposure to:

* Java AI application development
* Spring Boot
* Spring AI
* Large Language Models
* Ollama
* Qwen
* Prompt-based application development
* AI tool calling
* Agentic AI
* REST APIs
* H2 databases
* Embeddings
* Vector stores
* Retrieval-Augmented Generation
* Enterprise AI architecture
* Local LLM development

---

# 🔮 Possible Future Enhancements

The project can be extended into a more complete enterprise HR AI assistant.

Potential enhancements include:

### HR Policy RAG

Allow users to ask questions about company HR documents.

### Employee Search

Support employee lookup by name, department or role.

### Leave Management

Add leave-request workflows.

### Approval Workflows

Allow authorized managers to approve or reject requests.

### Authentication

Integrate Spring Security and role-based access control.

### Persistent Vector Database

Move from a demonstration vector store to a production-oriented vector database.

### Conversation Memory

Maintain context across multiple user interactions.

### Observability

Add logging, metrics and tracing for AI requests and tool calls.

### Multi-Agent Architecture

Separate HR, leave, payroll and policy capabilities into specialized agents or tools.

---

# 🏢 From Demo to Enterprise AI

The architecture demonstrated in this project can evolve from a simple AI assistant into a broader enterprise AI platform.

```text
                         AI Assistant
                              │
             ┌────────────────┼────────────────┐
             ↓                ↓                ↓
        Employee Tool     Leave Tool       HR RAG
             │                │                │
             ↓                ↓                ↓
        Employee DB       Leave DB       HR Documents
                              │                │
                              └───────┬────────┘
                                      ↓
                                  Spring AI
                                      ↓
                                  LLM
                                      ↓
                               Final Response
```

This pattern can be adapted to many enterprise use cases where an LLM needs controlled access to business data and application capabilities.

---

# 🌐 Eduarn Learning Perspective

This project is particularly useful as a practical learning example for developers interested in the intersection of:

**Java + Spring Boot + AI + LLMs + Agentic AI + RAG + Enterprise Applications**

At **Eduarn**, the focus is on practical technology learning where concepts are connected to real development scenarios.

The project can serve as a foundation for learning how modern Java applications can integrate with AI models and gradually evolve from simple chatbot functionality into tool-enabled and retrieval-augmented enterprise applications.

Explore more technology learning resources and training programs at **Eduarn.com**.

---

# 🔍 SEO Topics Covered

This project naturally targets several high-intent technology topics:

* Spring AI tutorial
* Spring Boot AI application
* Spring AI Ollama
* Spring Boot Ollama integration
* Qwen with Spring AI
* Qwen 2.5 Ollama
* Java LLM application
* Java Generative AI
* Agentic AI with Java
* Spring AI tool calling
* Spring AI tool calling example
* AI employee assistant
* AI HR assistant
* HR chatbot with Spring Boot
* RAG with Spring AI
* Spring AI embeddings
* Ollama embeddings
* Nomic Embed Text
* Vector database for Spring AI
* Local LLM with Java
* Enterprise AI with Spring Boot
* Generative AI application development
* Agentic AI tutorial
* Spring Boot AI tutorial
* Java AI project
* Spring AI tutorial for beginners

---

# 📌 SEO-Friendly Project Summary

**AI Employee Assistant is a Spring Boot and Spring AI project that demonstrates local Generative AI, Qwen 2.5, Ollama, AI tool calling and Agentic AI. The application allows an LLM to retrieve employee information and leave balances from an H2 database and generate natural-language responses. The project also introduces RAG, embeddings, vector stores and enterprise AI architecture.**

---

# 🏷️ Recommended SEO Metadata

### SEO Title

**Spring AI Agentic AI Employee Assistant with Ollama & Qwen**

### Meta Description

**Build an AI Employee Assistant with Spring Boot, Spring AI, Ollama and Qwen. Learn AI tool calling, Agentic AI, employee data retrieval, leave tools, RAG, embeddings and enterprise AI.**

### Suggested URL Slug

```text
spring-ai-agentic-ai-employee-assistant-ollama-qwen
```

### Primary Keyword

```text
Spring AI Agentic AI Employee Assistant
```

### Secondary Keywords

```text
Spring AI
Spring Boot AI
Spring AI Ollama
Qwen 2.5
Ollama
Agentic AI
AI tool calling
Spring AI tool calling
Java Generative AI
RAG with Spring AI
AI Employee Assistant
AI HR Assistant
Enterprise AI
Java AI application
Spring Boot AI tutorial
```

---

# 📚 Recommended Eduarn SEO Content Cluster

To strengthen the project's visibility on Eduarn.com, this project can become the center of a related content cluster:

```text
Spring AI Tutorial
        │
        ├── Spring AI + Ollama
        │
        ├── Qwen 2.5 with Spring Boot
        │
        ├── Spring AI Tool Calling
        │
        ├── Agentic AI with Java
        │
        ├── RAG with Spring AI
        │
        ├── Embeddings with Ollama
        │
        ├── Vector Database + Spring AI
        │
        └── AI Employee Assistant Project
```

This creates a stronger topical relationship between individual educational articles and the main project rather than relying on a single page to rank for every AI-related keyword.

---

# ⭐ Why This Project Matters

The key learning objective is not simply connecting a chatbot to a Java application.

The important progression is:

```text
LLM
 ↓
LLM + Application
 ↓
LLM + Tools
 ↓
LLM + Multiple Tools
 ↓
Agentic Workflow
 ↓
Agentic Workflow + RAG
 ↓
Enterprise AI Application
```

That progression provides a practical foundation for understanding how modern AI applications can be built using the Java and Spring ecosystem.

---

# 📄 License

Add your preferred project license here, for example:

```text
MIT License
```

If this repository is intended for educational use, clearly state the applicable license and any restrictions on commercial use or redistribution.

---

# 👨‍💻 Author / Learning Resource

**Eduarn**

Practical learning resources for modern technology skills including:

* Java
* Spring Boot
* Spring AI
* Generative AI
* Agentic AI
* Cloud Computing
* AWS
* Data Engineering
* DevOps
* Python
* Data Science
* Software Development

**Learn. Build. Experiment. Grow.**
