\# Domain-Constrained Chatbot (RAG System)



A locally hosted chatbot that answers questions strictly from a CSV-based knowledge base using semantic search and LLMs.



\## 🚀 Features



\* Domain-restricted responses (no hallucination outside knowledge base)

\* Semantic search using embeddings (vector similarity)

\* Local LLM inference using Ollama (Qwen / LLaMA)

\* CSV-based knowledge ingestion pipeline

\* Modular architecture (ingestion, retrieval, generation)



\## 🧠 Architecture



CSV → Structured Q\&A → Embeddings → Vector Search → LLM → Response



\## ⚙️ Tech Stack



\* Java 17+

\* Langchain4j

\* Ollama (local LLM)

\* Apache Commons CSV



\## 🧪 Example



User: What AI powers this system?

Bot: The chatbot uses a locally hosted large language model via Ollama.



User: What is machine learning?

Bot: I don't know based on the given data.



\## 🔒 Key Highlight



This project implements a Retrieval-Augmented Generation (RAG) pipeline to ensure accurate and domain-restricted responses.



