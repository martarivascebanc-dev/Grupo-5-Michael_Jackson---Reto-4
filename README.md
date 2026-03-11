# Problema
Clara se ha ido de la empresa y hay que hacer su trabajo dentro del Centro de datos. Dentro del centro de datos vamos a expandirnos, primero vamos a emplezar por Donosti y luego ampliaremos a nivel de España.

## Pandas
Dentro de este Github te vas a encontrar un programa de python en el que se van a analizar algunos datos del Centro de datos. Van a aparecer algunos gráficos representativos y datos. Lo encontraras en la rama de "Ibon" y el archivo se llama "Pandas".

## Java
Vas a encontrar un programa de java que va a conectarse con la base de datos que hemos creado en MySQL teniendo en cuenta las entidades que va a hacer nuestra empresa más sostenible y digitalizada. Lo encontraras las clases en la rama "Marta" y el archivo se llama "Centrodedatos.java". El que conecta a la base de datos en "programación" y en la carpeta scr/Java_Challenge_4 y el archivo a ejecutar es VentanaPrincipal.java.

Rama &nbsp; &rarr; &nbsp; Ibai
```
    ├── .vscode/
    |   └── settings.json
    ├── src/
    |   ├── Java_Challenge_4/
    |   |   ├── AES256.class
    |   |   ├── AES256.java # Programa para cifrar el password de MySQL
    |   |   ├── VentanaPrincipal.java # Programa principal con la ventana swing
    |   |   ├── dbConnection.class
    |   |   └── dbConnection.java # Programa que realiza la conexión a la BBDD de MySQL
    |   └── lib/
    |       ├── dotenv-java-3.0.0.jar
    |       └── mysql-connector-j-9.6.0.jar
    └── .gitignore
```
Link de la ubicación del programa principal: [link](https://github.com/martarivascebanc-dev/Grupo-5-Michael_Jackson---Reto-4/tree/ibai/src/Java_Challenge_4)

### Pasos a seguir
Una vez que tienes toda la rama y los archivos sigue los siguientes pasos:
1. En la misma altura de src debes crear un archivo llamado .env con el siguiente contenido:
      ```
      DB_URL=jdbc:mysql://localhost:3306/ejemplo
      DB_USER=root
      DB_PASSWORD_CIFRADA=
      AES_KEY=
      ```
      DB_URL &nbsp; &rarr; &nbsp; deberas colocar localhost: y el puesto y luego / y el nombre de tu BBDD  
      DB_USER &nbsp; &rarr; &nbsp; deberas poner el usuario del que te conectas a la BBDD
2. Abre el archivo AES256 y en String password = "password"; &nbsp; &nbsp; &nbsp; &larr; &nbsp; reemplaza "password" por tu contraseña de MySQL
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
5. Si has colocado todo correctamente ejecuta el archivo VentanaPrincipal.java
