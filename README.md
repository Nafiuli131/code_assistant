# 🧠 Code Assistant application

A Spring Boot-based backend service that integrates with Together.ai's `meta-llama/Llama-Vision-Free` model to simulate a chat assistant that remembers conversations per user and chat session.

---

## 🚀 Features

- Create users
- Start and continue chat sessions
- Retrieve full chat history per user and chat name
- Send text + (optional) image prompt to Together.ai
- Memory-aware responses based on chat history

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- JPA (Hibernate)
- Together.ai API
- H2 / MySQL (as needed)

---

## 📦 API Endpoints

### ✅ Create a New User

POST /user

bash
Copy
Edit

#### Request:
```json
{
  "userName": "Nafiul Islam",
  "password": "1234"
}
✅ Get User by UserName
sql
Copy
Edit
GET /user?userName=Nafiul Islam
Response:
json
Copy
Edit
{
  "id": 102,
  "userName": "Nafiul Islam",
  "password": "1234"
}
💬 Chat with Assistant
bash
Copy
Edit
POST /chat
Request:
json
Copy
Edit
{
  "userId": 102,
  "chatName": "firstChat",
  "prompt": "add previous sum with 100 ",
  "imageUrl": null
}
Response:
json
Copy
Edit
{
  "id": 163,
  "prompt": "add previous sum with 100 ",
  "response": "We previously calculated that 2 + 2 = 4.\n\n Adding 4 to 100:\n\n 100 + 4 = 104",
  "imageUrl": null,
  "ts": "2025-06-19T14:12:22.803281919"
}
📚 Get Chat History
bash
Copy
Edit
POST /chat/history
Request:
json
Copy
Edit
{
  "userId": 102,
  "chatName": "firstChat"
}
Response:
json
Copy
Edit
[
  {
    "chatName": "firstChat",
    "role": "user",
    "chatText": "2+2=?"
  },
  {
    "chatName": "firstChat",
    "role": "assistant",
    "chatText": "2 + 2 = 4"
  },
  {
    "chatName": "firstChat",
    "role": "user",
    "chatText": "remember the previous sum "
  },
  {
    "chatName": "firstChat",
    "role": "assistant",
    "chatText": "The previous sum was 2 + 2 = 4.\n\nWould you like to do another one?"
  },
  {
    "chatName": "firstChat",
    "role": "user",
    "chatText": "add previous sum with 100 "
  },
  {
    "chatName": "firstChat",
    "role": "assistant",
    "chatText": "We previously calculated that 2 + 2 = 4.\n\n Adding 4 to 100:\n\n 100 + 4 = 104"
  }
]
📌 Setup
Clone the repository:

bash
Copy
Edit
git clone https://github.com/your-username/code-assistant.git
cd code-assistant
Configure your application.properties with your Together.ai API key:

properties
Copy
Edit
together.api.key=your_api_key_here
Run the application:

bash
Copy
Edit
./mvnw spring-boot:run
🧠 Together.ai Model Used
meta-llama/Llama-Vision-Free

Context length: ~8192 tokens

Supports multi-turn conversation + image input (optional)

🗂 Directory Structure
bash
Copy
Edit
src/
├── controller/        # REST endpoints
├── dto/               # Request/response payloads
├── model/             # JPA entities
├── repository/        # JPA repositories
├── service/           # Business logic & Together.ai integration
🧑‍💻 Author
Nafiul Islam

Developer @ PowerLedger (Code Assistant Project)
