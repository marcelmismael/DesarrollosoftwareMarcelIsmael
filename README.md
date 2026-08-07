# Alex_api

Frontend Angular del **Sistema de Gestión de Incidentes TI - MISY S.A.**
Actividad 9 - Universidad Técnica de Manabí, Facultad de Ciencias Informáticas.
Consume la API REST desarrollada en la Actividad 8 (`mayte_api`).

## Estructura de componentes

| Componente | Ruta | Función |
|---|---|---|
| `navegacion` | (barra superior, en todas las páginas) | Menú de navegación responsive |
| `dashboard` | `/dashboard` | Resumen general e indicadores de tickets |
| `registro-incidentes` | `/registro` | Formulario para crear un nuevo ticket |
| `listado-tickets` | `/tickets` | Tabla con filtro, cambio de estado y eliminación |

## Requisitos previos

- Node.js 18 o superior
- Angular CLI: `npm install -g @angular/cli`
- El backend `mayte_api` corriendo (ver carpeta hermana en este mismo proyecto)

## Instalación y ejecución local

```bash
cd Alex_api
npm install
npm start
```

Esto levanta la app en `http://localhost:4200`, apuntando por defecto a
`http://localhost:8081/tickets` (ver `src/environments/environment.ts`).

Asegúrate de tener el backend corriendo en paralelo:
```bash
cd ../mayte_api
mvn spring-boot:run
```

## Seguridad (XSS)

- Angular escapa automáticamente cualquier interpolación `{{ }}` en las
  plantillas, por lo que nunca se usa `[innerHTML]` con datos del usuario.
- Adicionalmente, `TicketService` limpia (sanitiza) los campos de texto
  libre (`titulo`, `descripcion`) antes de enviarlos al backend, quitando
  etiquetas HTML/script.
- El backend valida nuevamente los datos con `@NotBlank` / `@NotNull`
  (defensa en profundidad: nunca confiar solo en el frontend).

## Despliegue en la nube (camino simple, sin Docker)

Esta es la forma **más simple** de cumplir el requisito de la Actividad 9
(persistencia remota + backend con URL pública + frontend en hosting
estático), sin tocar Dockerfiles ni configurar servicios a mano.

### 1. Backend + Base de datos → Railway (todo en un solo lugar)

1. Ve a [railway.app](https://railway.app) e inicia sesión con GitHub.
2. **New Project → Deploy from GitHub repo** → selecciona tu repositorio.
   - Si el repo contiene varias carpetas (`mayte_api`, `Alex_api`), en
     "Settings" del servicio configura **Root Directory: `mayte_api`**.
3. Railway detecta automáticamente que es un proyecto Maven (por el
   `pom.xml`) y lo compila y ejecuta solo — **no necesita Dockerfile**, y
   usa la misma versión de Java que indica `<java.version>` en el
   `pom.xml`, así que no hay conflicto de versiones posible.
4. En el mismo proyecto de Railway: **New → Database → PostgreSQL**
   (un clic). Railway crea la base y las variables `PGHOST`, `PGPORT`,
   `PGDATABASE`, `PGUSER`, `PGPASSWORD` automáticamente.
5. En el servicio del backend, pestaña **Variables**, agrega:
   ```
   SPRING_PROFILES_ACTIVE=prod
   ```
   Con esto la app usa `application-prod.properties` (PostgreSQL) en vez
   de H2. En tu máquina local, sin esa variable, sigue usando H2 igual
   que siempre — no cambia nada de tu flujo local.
6. En **Settings → Networking**, genera un dominio público (botón
   "Generate Domain"). Copia esa URL — es tu backend en producción, por
   ejemplo `https://mayte-api-production.up.railway.app`.

### 2. Frontend → Netlify Drop (sin cuenta, sin CLI, sin config)

1. Edita `src/environments/environment.prod.ts` con la URL real del
   backend que te dio Railway:
   ```ts
   export const environment = {
     production: true,
     apiUrl: 'https://mayte-api-production.up.railway.app/tickets'
   };
   ```
2. En tu máquina, dentro de `Alex_api`:
   ```bash
   npm run build:prod
   ```
   Esto genera la carpeta `dist/alex-api/browser`.
3. Ve a **[app.netlify.com/drop](https://app.netlify.com/drop)** y
   **arrastra esa carpeta** (`dist/alex-api/browser`) directamente al
   navegador. En segundos te da una URL pública funcionando
   (ej. `https://tu-proyecto.netlify.app`). No requiere cuenta para
   probarlo, aunque crear una gratis te permite volver a subir
   actualizaciones y le da un nombre fijo.

### 3. Ajustar CORS (un paso final, 1 línea)

El backend ya acepta peticiones desde cualquier origen
(`@CrossOrigin(origins = "*")` en `TicketController`), así que el paso 2
funcionará sin configuración adicional. Si luego quieres restringirlo al
dominio final de Netlify por seguridad, cambia el `"*"` por tu URL de
Netlify.

### Checklist final de la actividad
- [ ] Backend con URL pública (Railway) ✅ no depende de localhost
- [ ] Base de datos PostgreSQL remota (no H2 en memoria) ✅
- [ ] Frontend publicado en hosting estático (Netlify) ✅
- [ ] `environment.prod.ts` apunta a la URL real del backend
- [ ] Probaste la URL de Netlify en el navegador y carga datos reales

---

## Alternativa avanzada: Render + Docker

Si prefieres usar Render en vez de Railway, en la raíz de `PROYECTO III`
hay un `render.yaml` (Blueprint) y un `Dockerfile` ya corregidos y
funcionales para ese camino. Es más manual (recuerda que Docker exige que
la versión de Java del `Dockerfile` coincida siempre con la del
`pom.xml`), pero sirve si tu docente pide específicamente Render.
