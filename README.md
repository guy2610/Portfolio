# Portfolio of Engineering Projects

Welcome to my portfolio repository showcasing a range of software engineering projects, including low-level programming, backend development, security, and algorithms.

This collection reflects practical experience in programming, systems, and software design. Projects are organized by programming language, and each folder includes source code and documentation.


---

## Technologies & Topics Covered

- **Programming Languages:** C, C++, Java, Python, SQL
- **Domains:** Operating systems, systems programming, defensive programming, object-oriented design, databases, cryptography, and cybersecurity

---
## Highlighted Engineering Projects

This repository includes two larger, end-to-end engineering projects that demonstrate system-level design, robustness, and real-world constraints.

### Secure Encrypted File Transfer Protocol (C++ / Python)

A full client-server system implementing a custom binary protocol for secure file transfer over TCP.

**Location:** `Secure-Encrypted-File-Transfer-Protocol/`

**Key aspects:**
- C++17 client using Boost.Asio and Crypto++
- Async Python server using asyncio with multi-client concurrency
- Custom binary protocol with explicit request/response codes
- RSA-2048 key exchange, AES-256-CBC file encryption, CRC32 integrity checks
- Defensive protocol validation, timeouts, and crash-safe persistence
- Clear separation between networking, protocol, crypto, and application logic

Includes protocol specification, architecture notes, and a prebuilt Windows client.

---

### Marker Coverage Estimator (C++ / OpenCV)

A computer vision project implementing robust detection and validation of a 3×3 colored marker grid.

**Repository:** https://github.com/guy2610/marker-coverage-estimator

**Key aspects:**
- Image segmentation in HSV color space
- Geometric normalization using PCA
- Grid reconstruction via clustering and assignment
- Convex hull-based coverage estimation
- Robustness to rotation, scale, and perspective distortion
- Performance-oriented C++ implementation with OpenCV

Includes a detailed algorithm description and performance metrics.


---
## Repository Structure

- **C/**  
  Projects written in C language, primarily focusing on low-level programming. Includes:
  - System call implementations
  - Linux namespaces (PID, mount)
  - Preprocessor simulations
  - Simple assembler
  - Bit-level operations  
  These projects demonstrate a deep understanding of memory, processes, and OS internals.

- **C++/**  
  C++ mini-projects related to secure coding. Includes:
  - VTable hijacking simulation
  - Object-oriented programming principles
  - Defensive techniques against memory corruption and code injection  

- **Java/**  
  Applications built with Java, including:
  - GUI-based games (Hangman, Mastermind, Game of Life)
  - Multithreaded banking system
  - Calendar app using priority queues and data structures  
  These projects reflect experience with Java OOP, GUI (Swing), and concurrent programming.

- **Python/**  
  Advanced scripting and metaprogramming using Python. Covers:
  - Custom class creation at runtime
  - Decorators and introspection
  - Dynamic code manipulation

- **SQL/**  
  Work from the database systems course, including:
  - Complex SQL queries
  - Handling XML and JSON in SQL contexts
  - Relational schema design and optimization

- **Security/**  
  Projects and seminar papers related to cybersecurity:
  - Code injection detection using machine learning
  - Cryptography exercises (encryption, secure protocols)
  - Workshop deliverables involving secure file sharing and vulnerability analysis

---

## How to Navigate

Each folder under the main categories (`C/`, `Java/`, etc.) contains project folders.  
Project folder names include a brief description and relevant context.

---

## About

These projects reflect both academic foundations and self-driven engineering efforts, highlighting my progression as a software engineer.

Feel free to explore and reach out via GitHub if you have any questions or would like to collaborate.


---

Thank you for visiting!
