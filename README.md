# Blueprint de Render para el Sistema de Gestion de Incidentes TI - MISY S.A.
# Define los 2 servicios (backend + frontend) para que Render los configure
# automaticamente y correctamente, evitando errores de configuracion manual
# (como usar el comando de Java para desplegar el frontend Angular).
#
# Uso: en Render -> New -> Blueprint -> selecciona este repositorio.
# Render detecta este archivo y crea ambos servicios con la configuracion
# correcta ya aplicada.

services:
  # ---------------- BACKEND: mayte_api (Spring Boot, Docker) ----------------
  - type: web
    name: mayte-api
    runtime: docker
    dockerfilePath: ./mayte_api/Dockerfile
    dockerContext: ./mayte_api
    plan: free
    envVars:
      - key: PORT
        value: 8081
    # Render inyecta automaticamente la variable PORT; application.properties
    # ya usa server.port=${PORT:8081}, por lo que no requiere mas cambios.
