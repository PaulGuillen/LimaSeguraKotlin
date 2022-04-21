# LimaSeguraKotlin

Proyecto enfocado en mostrar noticias actualizadas por día, ademas de contar con segmentos personalizados, podrás apreciar las estaciones de bomberos, serenazgo y policía por distrito y poder llamar a cada uno de estos.

## Comenzando 🚀

Mira **Usuario test** para conocer el usuario asignado para esta prueba de diseños.

### Pre-requisitos 📋

[Ver APIS](https://www.deperu.com/api/) -> Fuente : DePeru.com

_Tener instalado Android Studio, conocer sobre kotlin, consultas con retrofit2_

```
. MinSdk =  23
. CompileSdk = 31
. TargetSdk = 31
. Gradle JDFK = corretto - 15 Amanzon Corretto version 15.0.2
. API = 23 Oreo 6.0
```

```
  //Ejemplo -> Interface routes, consultar al endpoint de "DePeru.com"
    @GET("noticias.json")
    fun  getNewsList (): Call<MutableList<News>>

    @GET("cotizaciondolar.json")
    fun getDolarPeru () : Call<Dolar>
    
    @GET("uitperu.json")
    fun getUIT () : Call<UIT>

```

## Usuario test 🖇️

_En este caso usaremos el usuario TEST_

```
. correo = test@gmail.com
. contraseña = 123456
```

<p align="center">
 <img src="https://i.postimg.cc/W3f7bTK2/Usuario.png"/>
</p>


## Modelo de datos en Postman 📖

Mostraré un ejemplo del estructura de la información que utilizaremos, en este caso es cotizaciondolar.json

```
 {
    "servicio": "Tipo de cambio referencial dolares",
    "sitio": "DePeru.com",
    "enlace": "https://www.deperu.com/tipo_cambio/",
    "Cotizacion": [
        {
            "Compra": 3.702,
            "Venta": 3.709
        }
    ]
}

```

## Ejecutando los diseños ⚙️
_* Vista Login y Registro_

_Procederemos a mostrar las vistas del registro e inicio de sesión_

<p align="center">
 <img src="https://i.postimg.cc/7hX63Z3z/Screenshot-1650580536.png"/>
 <img src="https://i.postimg.cc/NffLgRtk/Screenshot-1650580309.png"/>
 <img src="https://i.postimg.cc/zXbZSfF1/Screenshot-1650580540.png"/>
 <img src="https://i.postimg.cc/HsqGSC68/Screenshot-1650580321.png"/>
</p>

_* En este paso mostraré las demas vistas_

<p align="center">
    <img src="https://i.postimg.cc/0QQHkttq/Screenshot-1649191655.png"/>
   <img src="https://i.postimg.cc/Cdyv4wwF/Screenshot-1649191659.png"/>
   <img src="https://i.postimg.cc/BZdszD6v/Screenshot-1649191662.png"/>
   <img src="https://i.postimg.cc/MKVxJTMm/Screenshot-1649191664.png"/>
</p>

Video de como funciona la APP 
[LimaSegura-Video](https://user-images.githubusercontent.com/43099030/164521101-2f478148-5bf4-4fae-a95f-de9f18063a34.mp4)

## Autor ✒️

* **Paul Guillen Acuña** - *Mi Repositorio* - [PaulGuillen](https://github.com/PaulGuillen?tab=repositories)

## Licencia 📄

Este proyecto está bajo la Licencia (MIT License) - mira el archivo [LICENSE.md](LICENSE.md) para detalles
