
# Sistema de Mensajería en Java

Una aplicación de escritorio desarrollada en Java utilizando Swing para la interfaz gráfica de usuario, con funcionalidad de autenticación, registro de usuarios y mensajería en tiempo (semi) real. Utiliza conexión a una base de datos MySQL.

---
## Características

- **Login y Registro** de usuarios.
- **Mensajería con múltiples usuarios** (tipo chat global).
- **Actualización periódica** de mensajes sin necesidad de recargar.
- **Interfaz moderna y con estilos CSS embebidos**.
- **Uso de UUIDs y timestamps** para el control y orden de los mensajes.

## Tecnologias
- **Java**
- **Swing (GUI)**
- **MySQL**
- **HTML/CSS** embebido en `JTextPane` para el formato de los mensajes

---

## 🧑‍💻 Forma de Uso

Una vez dentro de la aplicación:

### 1. **Inicio de sesión**

* Ingresa tu nombre de usuario y contraseña.
* Si no tienes cuenta, haz clic en **Registrar** para crear una nueva.

### 2. **Registro**

* Llena todos los campos con la información solicitada.
* Al registrarte correctamente, te devolvera a la pantalla de login.

### 3. **Pantalla de Chat**

Después de iniciar sesión:

* Verás una interfaz con un historial de mensajes y un área de texto para escribir.
* El historial de mensajes se actualiza automáticamente cada 2 segundos.
* Los mensajes se distinguen por color:

  * **Tus mensajes** aparecen con fondo azul oscuro.
  * **Mensajes de otros usuarios** aparecen con fondo gris.

#### 4. **Enviar un mensaje**

* Escribe tu mensaje en el cuadro inferior.
* Presiona el botón **Enviar**.
* Tu mensaje se almacenará en la base de datos y se mostrará inmediatamente en el chat.
