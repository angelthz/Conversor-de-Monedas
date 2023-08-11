# Conversor-de-Monedas
Conversor de monedas hecho para el Challenge de Alura.

![Con-im3](https://i.postimg.cc/0QZxpdPG/ConMon2.png)

## Contenido

1. [Descripcion](#funcionamiento)
1. [Configuraciones](#2-configuraciones)
1. [Uso](#uso)

## 1. Descripcion

Un conversor de monedas hecho en Java como parte del Challenge del programa Alura One. Este conversor toma los datos de la API de Fixer para realizar la conversion de monedas.

Por el momento convierte la moneda por defecto (MXN Pesos Mexicanos) a diferentes monedas nacionales.

## 2. Configuraciones

Esta aplicacion hace uso de la API de Conversion de Monedas Fixer, por lo tanto para poder hacer uso de esta es necesario crear una cuenta gratis y colocar llave o token de autenticacion que se nos provee Fixer para su uso.

Puedes registrarte [aqui](https://apilayer.com/marketplace/fixer-api#endpoints), una vez hecho el registro gratis simplemente basta con copiar la llave de autenticacion en el archivo de configuraciones de la aplicacion y la misma actualizara los datos de conversion a los mas actuales.

Dado que el uso gratuito de la API de Fixer esta limitado a cierto numero de "requests" _Mensuales_, la apliacion actualiza los datos solo una vez por semana.

### 2.1 Pasos para la configuracion

Una vez hecho el registro en [Fixer](https://apilayer.com/marketplace/fixer-api#endpoints) podras ver una pantalla como las siguiente, copia la API Key y pegala en el archivo de configuracion de la aplicacion.

![Fixer.example](https://i.postimg.cc/wvnRdpRb/Captura-de-pantalla-2023-08-11-151211.png)


Ve al directorio resources/conf/ y abre el fichero **config.json**.

![Dir-image](https://i.postimg.cc/kXsMQCX3/Captura-de-pantalla-2023-08-11-151500.png)

Remplaza el texto de la propiedad **"api.key"** por la llave proporcionada por Fixer, una vez hecho esto guarda el archivo y podras ejecutar el programa.

![Conf-json](https://i.postimg.cc/ZRx2GTQJ/Captura-de-pantalla-2023-08-11-151651.png)

Al ejecutar el programa podras ver una pantalla como la siguiente:

![Con-m1](https://i.postimg.cc/mkwc1F6K/ConMon1.png)

## 3. Uso

Una vez abierta la aplicacion simplemente coloca la cantidad a convertir entre la moneda por defecto (MXN) y selecciona otra moneda del menu desplegable, una vez hecho esto puedes realizar la conversion entre la moneda base y la moneda extranjera.

![Con-im3](https://i.postimg.cc/0QZxpdPG/ConMon2.png)
