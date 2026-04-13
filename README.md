# 📦 Reto 3 - Inventory API Resiliente

Proyecto backend desarrollado con **Spring Boot 3**, **Java 21** y arquitectura basada en **microservicios**, implementando un **Inventory Service** y un **API Gateway** con resiliencia mediante **Circuit Breaker y fallback**.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.2+
- Spring Data JPA
- H2 Database
- Spring Cloud Gateway
- Resilience4j Circuit Breaker
- SpringDoc OpenAPI (Swagger)
- Jakarta Validation
- JUnit 5
- Mockito
- Maven

---

## 🏗️ Arquitectura de la solución

La solución está compuesta por dos microservicios:

### 1. Inventory Service

Gestiona los productos del inventario.

Puerto:

```text
8081
```

Funciones principales:

- Crear productos
- Consultar productos
- Buscar por ID
- Filtrar por categoría
- Actualizar stock
- Eliminar productos

---

### 2. API Gateway

Punto único de entrada para enrutar las peticiones al Inventory Service.

Puerto:

```text
8080
```

Incluye:

- Routing centralizado
- Circuit Breaker
- Fallback de contingencia

---

## 📁 Estructura del proyecto

```text
inventory-service/
│
├── src/main/java/com/inventory/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── dto/
│   └── exception/
│
└── src/test/java/

api-gateway/
│
├── src/main/java/com/gateway/
│   └── controller/
│
└── src/main/resources/
```

---

## 📦 Modelo Product

Campos implementados:

- id
- name
- description
- category
- price
- stock
- createdAt
- updatedAt

---

## 🌐 Endpoints REST

### Crear producto

```http
POST /api/products
```

### Obtener todos

```http
GET /api/products
```

### Obtener por ID

```http
GET /api/products/{id}
```

### Filtrar por categoría

```http
GET /api/products?category=Computo
```

### Actualizar stock

```http
PUT /api/products/{id}/stock?stock=20
```

### Eliminar producto

```http
DELETE /api/products/{id}
```

---

## 🔄 API Gateway

Ruta principal:

```http
http://localhost:8080/api/products
```

Redirige al microservicio:

```http
http://localhost:8081
```

---

## 🛡️ Resiliencia

Se implementó **Circuit Breaker con Resilience4j**.

Si el servicio de inventario no está disponible, responde:

```json
{
  "message": "Inventory service temporarily unavailable"
}
```

Ruta fallback:

```http
/fallback/inventory
```

---

## 🧪 Evidencia de pruebas

Pruebas ejecutadas correctamente:

```text
Runs: 4/4
Errors: 0
Failures: 0
```

Incluye:

### Unitarias

- Validación de stock negativo
- Consulta sin errores
- Validación básica del servicio

### Integración

- Context load
- Flujo principal

---

## 📘 Swagger / OpenAPI

Disponible en:

```text
http://localhost:8081/swagger-ui.html
```

---

## 💻 Ejemplos curl

### Crear producto

```bash
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d "{\"name\":\"Laptop\",\"description\":\"Ultrabook\",\"category\":\"Computo\",\"price\":3200,\"stock\":8}"
```

---

### Obtener productos

```bash
curl http://localhost:8080/api/products
```

---

### Obtener por ID

```bash
curl http://localhost:8080/api/products/1
```

---

### Actualizar stock

```bash
curl -X PUT "http://localhost:8080/api/products/1/stock?stock=20"
```

---

### Eliminar producto

```bash
curl -X DELETE http://localhost:8080/api/products/1
```

---

## ▶️ Cómo ejecutar

### Inventory Service

```bash
cd inventory-service
mvn spring-boot:run
```

---

### API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

---

## ✅ Estado del reto

✔️ Completado al 100%

---

## 👨‍💻 Autor

Proyecto desarrollado como solución del **Reto 3 - Inventory API Resiliente**