# Hiring Challenge Ricoh

### Documentacion

Aplicación desarrollada con Spring Boot y PostgreSQL.

## Prerequisitos

Para ejecutar Ejecutar la aplicacion es necesario tener los siguientes requerimientos:

* Docker 19.x.x
* Docker-compose V1.25.x
* ## Descarga y Configuración del proyecto.

Descargar este proyecto como zip o clonar el proyecto. En caso de descargar como Zip extraer los archivos dentro de una
carpeta.

Dentro de los archivos extraídos;
En el archivo `application.yml` y `application-dev.yml` se encuentran las configuraciones del proyecto por defecto.

## Ejecución del proyecto

Para desplegar el proyecto, se deben ejecutar desde la terminal o consola de windows los siguientes comandos

    $ cd proyecto
    $ docker-compose up -d

Se debe ejecutar el siguiente comando, ubicado en la raiz del proyecto.

    $ docker-compose up -d
    $ mvn package -Dmaven.test.skip=true

## Trabajando

El proyecto expone API's REST, que permiten ejecutar las funcionalidades de los requerimientos. Las api se pueden
consumir mediante CURL o utilizando Swagger UI.

Al Swagger se accede desde la siguiente url: http://localhost:8080/swagger-ui/index.html.

### Usuarios por defecto:

    usuario: admin
    clave: admin
    rol: ADMIN

***

    usuario: user
    clave: user
    rol: USER

### Privacidad

Accesos publicos

    /auth/login
    /nobel/{categoria}/{anio}
    /health/

Accesos USER

    /vacantes/

Accesos ADMIN

    **/**