# PRO413_20262_Hito2_Quarkus_ApellidoNombre

## 1. Descripción del proyecto
API REST desarrollada en Quarkus para la gestión de un recurso **Productos**. Expone
dos operaciones (GET y POST) y almacena la información temporalmente en memoria
(no requiere base de datos). Este proyecto es consumido por la aplicación Laravel
del Hito 2.

## 2. Framework y versión utilizada
- Quarkus 3.15.1
- Java 17
- Maven 3.9+

## 3. Requisitos previos
- JDK 17 instalado (`java -version`)
- Maven 3.9 o superior (`mvn -version`)
- Puerto 8080 libre

## 4. Instalación de dependencias
Las dependencias se descargan automáticamente al compilar con Maven:
```bash
mvn clean install
```

## 5. Configuración necesaria
No se requiere configuración adicional. El archivo
`src/main/resources/application.properties` ya define el puerto y CORS habilitado
para que Laravel pueda consumir la API sin problemas.

## 6. Comando para ejecutar el proyecto
Modo desarrollo (recomendado, con hot-reload):
```bash
mvn quarkus:dev
```
O bien, generar el jar y ejecutarlo:
```bash
mvn clean package
java -jar target/quarkus-app/quarkus-run.jar
```

## 7. Puerto utilizado
`8080` (http://localhost:8080)

## 8. Endpoints disponibles
| Método | Endpoint            | Descripción                       |
|--------|---------------------|------------------------------------|
| GET    | `/api/productos`    | Devuelve la lista de productos    |
| POST   | `/api/productos`    | Registra un nuevo producto        |

## 9. Ejemplo de petición
**GET**
```bash
curl -X GET http://localhost:8080/api/productos
```

**POST**
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Monitor 24 pulgadas","precio":150.00}'
```

## 10. Ejemplo de respuesta
**GET**
```json
[
  { "id": 1, "nombre": "Teclado mecánico", "precio": 45.99 },
  { "id": 2, "nombre": "Mouse inalámbrico", "precio": 19.50 }
]
```

**POST**
```json
{ "id": 3, "nombre": "Monitor 24 pulgadas", "precio": 150.00 }
```
Código HTTP: `201 Created`

## 11. Forma de comprobar la integración
1. Ejecutar este proyecto Quarkus (`mvn quarkus:dev`).
2. Ejecutar el proyecto Laravel (ver su propio README).
3. Abrir la interfaz de Laravel en el navegador y usar el formulario/listado:
   - Al cargar la página, Laravel hace un GET a Quarkus y muestra la lista de productos.
   - Al enviar el formulario, Laravel hace un POST a Quarkus y el nuevo producto
     aparece reflejado en la lista.
4. También puede verificarse por consola: cada petición GET/POST muestra un log en
   la terminal donde corre Quarkus (`quarkus.log.level=INFO`).
