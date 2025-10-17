# exam-app-backend
## 🚀 auth-service
- POST
(http://localhost:8080/api/auth/send-otp?email=akash.unknow@gmail.com)
- OutPut
  **OTP sent successfully to akash.unknow@gmail.com**

- POST
(http://localhost:8080/api/auth/verify-otp?email=akash.unknow@gmail.com&otp=634202)


- OUtPUt
**{
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NjA3MDk5NTgsImV4cCI6MTc2MDc5NjM1OH0.7-ki60w_MYWRLBGkUryzLOFrIbiD8Gd8ay2w59vAGA9Nep2GZfNPeBF9r9FGNW4G6WxLp9pdRwNI62Zn8gu0zw",
"userId": "1",
"name": "User",
"email": "",
"role": "USER"
}**


- POST
  (http://localhost:8080/api/auth/register)
-input
**{
  "name": "akash",
  "phoneNumber": "6398017566",
  "email": "akashK@gmail.com",
  "password": "MySecurePassword123"
  }**

- OUtPUt
**{
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiZW1haWwiOiJha2FzaEtAZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NjA3MDk2MDIsImV4cCI6MTc2MDc5NjAwMn0.wCYnLM-1vLB40hM1PIaHcFxnltbUCL9aZoNXvEVznHx0vRZ5N069GiFdRx1U7dVaV50Ak_cZOnPvZafpJv_E8A",
"userId": "4",
"name": "akash",
"email": "akashK@gmail.com",
"role": "USER"
}**



- POST
  (http://localhost:8080/api/auth/login)
- input
  **{
  "email": "akashK@gmail.com",
  "password": "MySecurePassword123"
  }**

- OUtPUt
**{
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiZW1haWwiOiJha2FzaEtAZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NjA3MDk2NzAsImV4cCI6MTc2MDc5NjA3MH0.tJl8HwHVvwAIUgIYSIDBtRY_UOebXMerc7LfXNqbrTqA0js59xD_FwKZEOGtJdZ_GEOFxQQRqeu61SLg9HJBfw",
"userId": "4",
"name": "akash",
"email": "akashK@gmail.com",
"role": "USER"
}**

