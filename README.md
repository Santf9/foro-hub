# 🗨️ Foro Hub API

**Foro Hub** es una API RESTful desarrollada con **Spring Boot** que simula un foro de discusión, permitiendo la gestión completa de tópicos educativos. Este proyecto está centrado en la funcionalidad de **Tópicos**, que representan las preguntas y discusiones principales del foro.

## 📋 Índice

- [Características Principales](#-características-principales)
- [Tecnologías Utilizadas](#️-tecnologías-utilizadas)
- [Arquitectura del Proyecto](#️-arquitectura-del-proyecto)
- [Funcionalidades de Tópicos](#-funcionalidades-de-tópicos)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Uso de la API](#-uso-de-la-api)
- [Estructura de Datos](#-estructura-de-datos)
- [Validaciones y Reglas de Negocio](#-validaciones-y-reglas-de-negocio)
- [Seguridad](#️-seguridad)

## 🎯 Características Principales

### 🎪 **Gestión Completa de Tópicos**
- **Creación de tópicos**: Los usuarios pueden crear nuevas discusiones
- **Consulta de tópicos**: Listado paginado y consulta individual
- **Actualización de tópicos**: Modificación de contenido existente  
- **Eliminación de tópicos**: Borrado completo del sistema
- **Prevención de duplicados**: Validación automática de tópicos únicos

### 🏷️ **Estados de Tópicos**
- **ABIERTO**: Tópico activo y disponible para discusión
- **CERRADO**: Tópico cerrado, no acepta más respuestas
- **SOLUCIONADO**: Tópico resuelto con una respuesta satisfactoria

### 📚 **Integración con Cursos**
- Cada tópico está asociado a un curso específico
- Categorización por áreas de conocimiento
- Relación Many-to-One con la entidad Curso

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 21 | Lenguaje de programación principal |
| **Spring Boot** | 3.5.4 | Framework principal del proyecto |
| **Spring Data JPA** | - | Persistencia y acceso a datos |
| **Spring Security** | - | Autenticación y autorización |
| **Spring Validation** | - | Validación de datos |
| **MySQL** | 8.x | Base de datos relacional |
| **Lombok** | - | Reducción de código boilerplate |
| **JWT (Auth0)** | 4.4.0 | Tokens de autenticación |
| **Maven** | - | Gestión de dependencias |

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una **arquitectura por capas** bien definida:

```
src/main/java/com/foro_hub/foro_hub/
├── 📁 controller/          # Controladores REST
│   └── TopicoController.java
├── 📁 domain/              # Lógica de negocio
│   ├── 📁 topico/          # Dominio de Tópicos
│   │   ├── Topico.java                    # Entidad principal
│   │   ├── EstadoTopico.java              # Enum de estados
│   │   ├── TopicoService.java             # Servicios de negocio
│   │   ├── ITopicoRepository.java         # Repositorio de datos
│   │   ├── DatosRegistroTopicoDTO.java    # DTO de creación
│   │   ├── DatosRespuestaTopicoDTO.java   # DTO de respuesta
│   │   └── ActualizarTopicoDTO.java       # DTO de actualización
│   ├── 📁 curso/           # Dominio de Cursos
│   └── 📁 usuario/         # Dominio de Usuarios
└── 📁 infra/               # Infraestructura
    ├── 📁 security/        # Configuración de seguridad
    └── 📁 errores/         # Manejo de excepciones
```

## 🎪 Funcionalidades de Tópicos

### ➕ **Crear Tópico**
- Valida unicidad por título y mensaje
- Asigna automáticamente fecha de creación
- Estado inicial: **ABIERTO**
- Asociación obligatoria a un curso existente

### 📋 **Listar Tópicos**
- **Paginación**: 10 elementos por página por defecto
- **Ordenamiento**: Por fecha de creación (ascendente)
- **Personalizable**: Tamaño de página y criterios de ordenamiento

### 🔍 **Consultar Tópico por ID**
- Búsqueda específica de un tópico
- Validación de existencia
- Respuesta completa con todos los detalles

### ✏️ **Actualizar Tópico**
- Modificación de título, mensaje, autor y curso
- Validación de unicidad en cambios
- Preservación de fecha de creación original
- Actualización transaccional

### 🗑️ **Eliminar Tópico**
- Eliminación completa del sistema
- Validación previa de existencia
- Respuesta HTTP 204 (No Content) exitosa

## 🔗 Endpoints de la API

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| `POST` | `/topicos` | Crear nuevo tópico | ✅ Requerida |
| `GET` | `/topicos` | Listar tópicos (paginado) | ✅ Requerida |
| `GET` | `/topicos/{id}` | Obtener tópico por ID | ✅ Requerida |
| `PUT` | `/topicos/{id}` | Actualizar tópico | ✅ Requerida |
| `DELETE` | `/topicos/{id}` | Eliminar tópico | ✅ Requerida |

### 🔐 **Autenticación**
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/login` | Autenticar usuario y obtener JWT |

## 🚀 Instalación y Configuración

### 📋 **Prerrequisitos**
- Java 21 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE de preferencia (IntelliJ IDEA, Eclipse, VS Code)

### ⚙️ **Configuración de Variables de Entorno**

Crea las siguientes variables de entorno:

```bash
# Base de datos
export MYSQL_HOST=localhost:3306
export NAME_DB=foro_hub_db
export MYSQL_USER=tu_usuario
export DB_PASSWORD=tu_contraseña

# JWT Secret
export JWT_SECRET=tu_clave_secreta_jwt_muy_larga_y_segura
```

### 🗄️ **Configuración de Base de Datos**

1. **Crear base de datos MySQL:**
```sql
CREATE DATABASE foro_hub_db;
USE foro_hub_db;
```

2. **Las tablas se crean automáticamente** gracias a `hibernate.ddl-auto=update`

### 💻 **Ejecución del Proyecto**

```bash
# 1. Clonar el repositorio
git clone <url-del-repositorio>
cd foro-hub

# 2. Compilar el proyecto
mvn clean compile

# 3. Ejecutar la aplicación
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## 📖 Uso de la API

### 🔑 **1. Autenticación**

```bash
POST http://localhost:8080/login
Content-Type: application/json

{
    "usuario": "admin",
    "password": "123456"
}
```

**Respuesta:**
```json
{
    "jwTtoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### ➕ **2. Crear Tópico**

```bash
POST http://localhost:8080/topicos
Authorization: Bearer <tu_jwt_token>
Content-Type: application/json

{
    "titulo": "¿Cómo implementar paginación en Spring Boot?",
    "mensaje": "Necesito ayuda para implementar paginación eficiente en mis endpoints REST",
    "autor": "Juan Pérez",
    "curso": "Spring Boot Avanzado"
}
```

### 📋 **3. Listar Tópicos**

```bash
GET http://localhost:8080/topicos?page=0&size=5&sort=fechaCreacion,desc
Authorization: Bearer <tu_jwt_token>
```

### 🔍 **4. Obtener Tópico por ID**

```bash
GET http://localhost:8080/topicos/1
Authorization: Bearer <tu_jwt_token>
```

### ✏️ **5. Actualizar Tópico**

```bash
PUT http://localhost:8080/topicos/1
Authorization: Bearer <tu_jwt_token>
Content-Type: application/json

{
    "titulo": "¿Cómo implementar paginación y ordenamiento en Spring Boot?",
    "mensaje": "Necesito ayuda detallada para implementar paginación y ordenamiento en mis endpoints REST con Spring Data JPA",
    "autor": "Juan Pérez",
    "curso": "Spring Boot Avanzado"
}
```

### 🗑️ **6. Eliminar Tópico**

```bash
DELETE http://localhost:8080/topicos/1
Authorization: Bearer <tu_jwt_token>
```

## 📊 Estructura de Datos

### 📝 **Entidad Tópico**

```java
{
    "id": 1,
    "titulo": "¿Cómo implementar paginación en Spring Boot?",
    "mensaje": "Necesito ayuda para implementar paginación eficiente...",
    "fechaCreacion": "2025-08-19T19:03:53",
    "estado": "ABIERTO",
    "autor": "Juan Pérez",
    "curso": "Spring Boot Avanzado"
}
```

### 📚 **Entidad Curso**

```java
{
    "id": 1,
    "nombre": "Spring Boot Avanzado",
    "categoria": "PROGRAMACION"
}
```

### 🏷️ **Estados de Tópico**
- `ABIERTO` - Tópico activo
- `CERRADO` - Tópico cerrado  
- `SOLUCIONADO` - Tópico resuelto

### 📂 **Categorías de Curso**
- `PROGRAMACION`
- `DESARROLLO_WEB`
- `MOBILE`
- `DEVOPS`
- `DATA_SCIENCE`
- `DESIGN`
- `BUSINESS`

## ✅ Validaciones y Reglas de Negocio

### 🎯 **Validaciones de Tópicos**

| Campo | Validación | Mensaje de Error |
|-------|------------|------------------|
| `titulo` | No vacío/nulo | "El título es obligatorio" |
| `mensaje` | No vacío/nulo | "El mensaje es obligatorio" |
| `autor` | No vacío/nulo | "El autor es obligatorio" |
| `curso` | Debe existir en BD | "El curso especificado no existe: {nombre}" |

### 🔒 **Reglas de Negocio**

1. **Unicidad**: No pueden existir dos tópicos con el mismo título y mensaje
2. **Estado inicial**: Los tópicos nuevos se crean con estado `ABIERTO`
3. **Fecha automática**: La fecha de creación se asigna automáticamente
4. **Curso obligatorio**: Todo tópico debe estar asociado a un curso existente
5. **Validación en actualización**: Al actualizar, se verifica que no se cree un duplicado

### 🚨 **Manejo de Errores**

```java
// Tópico duplicado
{
    "error": "Ya existe un tópico con el mismo título y mensaje"
}

// Curso no encontrado
{
    "error": "El curso especificado no existe: Curso Inexistente"
}

// Tópico no encontrado
{
    "error": "No se encontró el tópico con ID: 999"
}
```

## 🛡️ Seguridad

### 🔐 **Autenticación JWT**
- **Algoritmo**: HS256
- **Expiración**: 24 horas (86400000 ms)
- **Secret**: Configurable vía variable de entorno
- **Header**: `Authorization: Bearer <token>`

### 🔒 **Endpoints Protegidos**
Todos los endpoints de tópicos requieren autenticación JWT válida:
- ✅ `/topicos/**` - Requiere autenticación
- 🔓 `/login` - Público
- 🔓 `/h2-console/**` - Solo para desarrollo

### 🛡️ **Configuración de Seguridad**
- CORS habilitado para desarrollo
- Headers de seguridad configurados
- Sesiones stateless (JWT)
- Protección CSRF deshabilitada (API REST)

---

## 🚀 **Próximas Mejoras**

- [ ] Sistema de respuestas a tópicos
- [ ] Votos y puntuaciones
- [ ] Notificaciones en tiempo real
- [ ] API de búsqueda avanzada
- [ ] Documentación con Swagger/OpenAPI
- [ ] Tests unitarios y de integración
- [ ] Métricas y monitoreo

---

**Desarrollado con mucha predisposición usando Spring Boot**
