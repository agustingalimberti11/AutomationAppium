# language: es
@android @login
Característica: Inicio de sesión
  Como usuario de la tienda
  Quiero ingresar con mis credenciales
  Para acceder al catálogo con mi sesión iniciada

  Antecedentes:
    Dado que estoy en la pantalla de login

  @smoke
  Escenario: Ingresar con credenciales válidas
    Cuando ingreso el usuario "bob@example.com"
    Y ingreso la contraseña "10203040"
    Y envío el formulario de login
    Entonces veo el catálogo de productos

  Escenario: Validar que la contraseña es obligatoria
    Cuando ingreso el usuario "bob@example.com"
    Y envío el formulario de login
    Entonces permanezco en la pantalla de login
    Y veo el error de contraseña "Enter Password"

  Escenario: Validar que el usuario es obligatorio
    Cuando ingreso la contraseña "10203040"
    Y envío el formulario de login
    Entonces permanezco en la pantalla de login
    Y veo el error de usuario "Username is required"