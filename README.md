🧠 Code Assistant Application
This is a Spring Boot-based backend service that integrates with Together.ai's meta-llama/Llama-Vision-Free model. It simulates a chat assistant capable of remembering conversations on a per-user and per-chat session basis.

🚀 Features
User Management: Create new users for the application.

Chat Sessions: Start and continue chat sessions, maintaining conversational context.

Chat History Retrieval: Easily retrieve full chat history for any user and specific chat name.

Flexible Prompting: Send text prompts with optional image inputs to the Together.ai model.

Contextual Responses: The assistant provides memory-aware responses based on the ongoing chat history.

🛠️ Tech Stack
Java 17

Spring Boot

JPA (Hibernate)

Together.ai API

H2 / MySQL (for database persistence)

📦 API Endpoints
✅ Create a New User
POST /user

Request:

{
  "userName": "Nafiul Islam",
  "password": "1234"
}

✅ Get User by UserName
GET /user?userName=Nafiul Islam

Response:

{
  "id": 102,
  "userName": "Nafiul Islam",
  "password": "1234"
}

💬 Chat with Assistant
POST /chat

Request:

{
  "userId": 102,
  "chatName": "firstChat",
  "prompt": "add previous sum with 100 ",
  "imageUrl": null
}

Response:

{
  "id": 163,
  "prompt": "add previous sum with 100 ",
  "response": "We previously calculated that 2 + 2 = 4.\n\n Adding 4 to 100:\n\n 100 + 4 = 104",
  "imageUrl": null,
  "ts": "2025-06-19T14:12:22.803281919"
}

📚 Get Chat History
POST /chat/history

Request:

{
  "userId": 102,
  "chatName": "firstChat"
}

Response:

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

git clone https://github.com/your-username/code-assistant.git
cd code-assistant

Configure your application.properties with your Together.ai API key:

together.api.key=your_api_key_here

Run the application:

./mvnw spring-boot:run

🧠 Together.ai Model Used
The application utilizes the meta-llama/Llama-Vision-Free model from Together.ai.

Context length: Approximately 8192 tokens.

Capabilities: Supports multi-turn conversations and optional image inputs.

🗂 Directory Structure
src/
├── controller/        # REST endpoints
├── dto/               # Request/response payloads
├── model/             # JPA entities
├── repository/        # JPA repositories
└── service/           # Business logic & Together.ai integration

🧑‍💻 Author
Nafiul Islam

Developer @ PowerLedger (Code Assistant Project)
