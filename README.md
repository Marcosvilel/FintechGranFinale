
---

# 💰 Sistema Fintech - Projeto Acadêmico FIAP  
*Um sistema financeiro completo desenvolvido em Java Web para o curso FIAP*  

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white)
![Servlets](https://img.shields.io/badge/Jakarta_Servlets-6DB33F?style=for-the-badge&logo=java&logoColor=white)

## 📌 Visão Geral  
Este projeto é um sistema **Fintech** desenvolvido como parte do curso da **FIAP**, implementando:  
✔ **Backend** em Java (Servlets, JDBC)  
✔ **Frontend** com JSP e CSS  
✔ **Banco de dados** Oracle (instância FIAP)  
✔ **Autenticação** de usuários  
✔ **CRUD** completo para operações financeiras  

---

## 🛠️ Tecnologias Utilizadas  
| **Backend**       | **Frontend**      | **Banco de Dados** | **Padrões**       |
|-------------------|-------------------|--------------------|-------------------|
| Java EE           | JSP               | Oracle Database    | DAO Pattern       |
| Servlets          | HTML5             | SQL                | MVC               |
| JDBC              | CSS3              |                    | Singleton (Connection) |

---

## ⚙️ Funcionalidades  
✅ **Autenticação segura** (login/logout)  
✅ **Cadastro de usuários**  
✅ **Operações financeiras**:  
   - 💸 Transferências  
   - 💳 Pagamentos  
   - 📈 Investimentos  
✅ **Consultas**:  
   - 📊 Saldo  
   - 📜 Extrato  
✅ **Tratamento de erros** com páginas customizadas  

---

## 🗂️ Estrutura do Projeto  
```
📁 fintech-fiap/  
├── 📁 src/  
│   ├── 📁 main/  
│   │   ├── 📁 java/            # Código Java (DAOs, Models, Servlets)  
│   │   └── 📁 webapp/          # JSP, CSS, recursos estáticos  
│   └── 📁 test/                # Testes (se houver)  
├── 📁 sql/                     # Scripts SQL (DDL, DML)  
├── 📄 README.md                # Este arquivo  
└── 📄 pom.xml                  # Dependências Maven (se aplicável)  
```

---

## 🚀 Como Executar  
1. **Pré-requisitos**:  
   - Java 8+  
   - Oracle Database (ou acesso à instância FIAP)  
   - Servidor Tomcat 9+  

2. **Configuração**:  
   - Importe o projeto no **IntelliJ/Eclipse** como *Maven Project*.  
   - Execute os scripts SQL em `sql/` no Oracle.  
   - Configure `ConnectionManager.java` com suas credenciais.  

3. **Execução**:  
   - Deploy no Tomcat.  
   - Acesse: `http://localhost:8080/fintech-fiap/login.jsp`  

---

## 📝 Licença  
Este projeto é acadêmico, desenvolvido para fins educacionais.  

---

### ✨ **Dúvidas? Contribuições?**  
Sinta-se à vontade para abrir uma **issue** ou um **pull request**!  

---

### 🔗 **Links Úteis**  
- [Documentação Oracle JDBC](https://docs.oracle.com/en/database/)  
- [FIAP - Fintech](https://www.fiap.com.br)  

---
