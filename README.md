# Problema
La responsable de gestión de datos, Clara, se ha ausentado por una baja médica prolongada dejando incompleto un proyecto de análisis de datos y gestión de consumo y sostenibilidad en el centro de datos. Nosotros tenemos el trabajo de continuar y terminar el proyecto que Clara empezo. El proyecto piloto se implementaria inicialmente en Donosti y luego la idea seria ampliarlo a nivel de España.

## Tecnologías utilizadas

### Lenguajes de programación
- **Python 3.12**
- **Java 24.0.2**

### Base de datos
- **MySQL 8.0**

### Librerías / Dependencias
- Python: `pandas`, `matplotlib`, `seaborn`
- Java: `mysql-connector-j`, `dotenv-java`

### Herramientas y Entorno
- IDE: **VSCode**

## Pandas y CSV
Dentro de este Github te vas a encontrar un programa de `.ipynb` en el que se van a analizar algunos datos del Centro de datos. Van a aparecer algunos gráficos representativos y datos. Lo encontraras en la carpeta `lenguaje_de_marcas` y el archivo se llama `analisis_de_datos.ipynb` y el archivo que analizamos es el archivo llamado `datos_data_center.csv`.

## Base de datos
Los archivos de la base de datos están en la carpeta `base_de_datos`:
```
    └── base_de_datos/
        ├── centro_datos.sql # Archivo SQL para montar la BBDD y contiene las consultas SQL
        └── datos_de_centro_datos.sql # Archivo SQL que se monta los datos de las tablas de la BBDD
```

> Nota: Primero ejecutar `centro_datos.sql` y luego `datos_de_centro_datos.sql`.

## Java
Encontraras el programa de java que se conecta con la base de datos que hemos creado en MySQL teniendo en cuenta las entidades que va a hacer nuestra empresa más sostenible y digitalizada y su ubicación es en `programación/scr/Java_Challenge_4` y el archivo a ejecutar es `VentanaPrincipal.java`.

Encontraras toda la documentación generada en la carpeta `programacion/doc`: [link](https://github.com/martarivascebanc-dev/Grupo-5-Michael_Jackson---Reto-4/tree/main/programacion/doc)

```
    └── programacion/
        ├── .vscode/
        |   └── settings.json
        ├── doc/ # carpeta donde esta toda la documentación JavaDoc documentado y generado por Ibai
        ├── lib/
        |   ├── dotenv-java-3.0.0.jar # Archivo .jar para obtener el contenido del .env
        |   └── mysql-connector-j-9.6.0.jar # Archivo .jar para la conexión a la BBDD de MySQL
        ├── src/Java_Challenge_4/
        |   ├── AES256.class
        |   ├── AES256.java # Programa para cifrar el password de MySQL
        |   ├── VentanaPrincipal.java # Programa principal con la ventana swing
        |   ├── dbConnection.class
        |   └── dbConnection.java # Programa que realiza la conexión a la BBDD de MySQL
        ├── .env # Archivo de configuración de entorno
        └── .gitignore
```
Link de la ubicación del archivo principal: [link](https://github.com/martarivascebanc-dev/Grupo-5-Michael_Jackson---Reto-4/tree/main/programacion/src/Java_Challenge_4)

### Pasos a seguir
Una vez que tienes todo el contenido de la carpeta `programacion` y sus archivos sigue los siguientes pasos:
1. En la carpeta `programacion` debes crear un archivo llamado `.env` con el siguiente contenido:
      ```
      DB_URL=jdbc:mysql://localhost:3306/ejemplo
      DB_USER=root
      DB_PASSWORD_CIFRADA=
      AES_KEY=
      ```
      DB_URL &nbsp; &rarr; &nbsp; deberas reemplazar `ejemplo` con el nombre de la BBDD que te quieres conectar  
      DB_USER &nbsp; &rarr; &nbsp; deberas poner el usuario del que te conectas a la BBDD
2. Abre el archivo AES256 y en `String password = "password"`; &nbsp; &nbsp; &nbsp; &larr; &nbsp; reemplaza `password` por tu contraseña de MySQL
      * Obtendras lo siguiente de manera cifrada:  
            - &nbsp; AES_KEY= xxxxxxxx  
            - &nbsp; DB_PASSWORD_CIFRADA= xxxxxxxx    

3. Con los datos obtenidos guardalos en su sitio en el `.env` previamente creado.
      * Tendras que tener de esta manera el `.env`:  
      &nbsp;
      ```
      DB_URL=jdbc:mysql://localhost:3306/ejemplo
      DB_USER=root
      DB_PASSWORD_CIFRADA=xxxxxxxxxxxxxxxxxxxxxxxx
      AES_KEY=xxxxxxxxxxxxxxxxxxxxxxxx
      ```
4. Si has colocado todo correctamente ejecuta el archivo `VentanaPrincipal.java`

## Autores

**Marta Rivas**  
**Ibon YE**  
**Ibai López**  

Repositorio mantenido por el grupo `Michael Jackson`.
