# README: Proyecto de Pruebas de Resiliencia con Circuit Breaker

## 1. Descripción de la prueba
El proyecto tiene como objetivo probar el patrón Circuit Breaker implementado mediante Resilience4J en una aplicación desarrollada con Spring Boot. Se simula la comunicación con dos servicios externos: uno principal y otro de contingencia. La configuración del Circuit Breaker incluye un TimeLimiter y un fallback para garantizar la resiliencia en caso de fallos del servicio principal.

## 2. Objetivo(s) de la prueba
- Validar la capacidad del Circuit Breaker para:
  - Detectar fallos en el servicio principal y activar el fallback.
  - Transicionar entre los estados CLOSED, OPEN y HALF_OPEN según la configuración.
  - Responder correctamente al servicio de contingencia cuando el principal falla.
- Verificar que el TimeLimiter interrumpe solicitudes que exceden el tiempo configurado.
- Comprobar el manejo de errores y tiempos de respuesta en escenarios simulados.

## 3. Pasos implementados para llevar a cabo la prueba
1. **Configuración del proyecto:**
   - Configuración de Resilience4J en Spring Boot con las siguientes propiedades:
     - `failureRateThreshold`: 50%
     - `slidingWindowSize`: 4
     - `waitDurationInOpenState`: 20 segundos
     - `TimeLimiter`: 10 segundos
   - Creación de servicios simulados (mocks) en SOAP UI para el servicio principal y el de contingencia.
2. **Implementación del Circuit Breaker:**
   - Clase `ResilienceCircuitBreakerConfig` para definir la configuración global.
   - Clase `CreditProductService` que utiliza el Circuit Breaker y el fallback.
3. **Simulación de escenarios:**
   - Prueba con el servicio principal funcionando correctamente.
   - Simulación de fallos en el servicio principal (HTTP 500, timeout, etc.).
   - Verificación de transiciones de estados del Circuit Breaker:
     - CLOSED → OPEN: Cuando el 50% de las solicitudes fallan.
     - OPEN → HALF_OPEN: Después de 20 segundos.
     - HALF_OPEN → CLOSED: Si las solicitudes de prueba son exitosas.
4. **Ejecución de pruebas manuales:**
   - Uso de SOAP UI para pruebas manuales.


## 4. Tecnologías usadas en la prueba
- **Lenguajes:**
  - Java 17
- **Frameworks y librerías:**
  - Spring Boot 3.1.3
  - Resilience4J 3.0.2
  - Jackson (ObjectMapper)
  - RestTemplate
- **Herramientas de simulación:**
  - SOAP UI para servicios simulados (mocks).
  - WireMock para pruebas automatizadas.
- **Pruebas:**
  - JUnit 5
  - Mockito

## 5. Resultados
- El Circuit Breaker respondió correctamente a los fallos del servicio principal, activando el fallback al servicio de contingencia.
- La transición de estados CLOSED → OPEN, OPEN → HALF_OPEN y HALF_OPEN → CLOSED se comportó según lo esperado:
  - Se activó el estado OPEN al superar el 50% de fallos en una ventana de 4 llamadas.
  - El estado HALF_OPEN permitió realizar solicitudes de prueba después de 20 segundos.
  - El estado CLOSED se reestableció tras recibir respuestas exitosas en las pruebas.
- El TimeLimiter interrumpió correctamente las solicitudes que excedieron los 10 segundos.

## 6. Conclusiones
- El uso de Resilience4J permite implementar un patrón Circuit Breaker robusto que mejora la resiliencia de la aplicación frente a fallos en servicios externos.
- La configuración utilizada (failureRateThreshold, slidingWindowSize, waitDurationInOpenState, TimeLimiter) se comportó de manera efectiva en los escenarios probados.
- SOAP UI y WireMock son herramientas complementarias útiles para simular servicios externos y automatizar pruebas de resiliencia.
- Se recomienda incorporar monitoreo adicional (por ejemplo, con Micrometer) para rastrear métricas del Circuit Breaker en entornos productivos.

