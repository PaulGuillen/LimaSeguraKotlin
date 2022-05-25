# LimaSeguraKotlin

_Proyecto enfocado en mostrar noticias actualizadas por día, además de contar con segmentos personalizados, podrás apreciar las estaciones de bomberos, serenazgo y policía por distrito y poder llamar a cada uno de estos, también podrás visualizar las noticias por país de NewsAPIS y de DePeru.com_

## Comenzando 🚀

_Mira **Usuario test** para conocer el usuario asignado para esta prueba de diseños._

_Puedes visualziar la app en Google Play [Lima Segura](https://play.google.com/store/apps/details?id=devpaul.business.safetylima)._

### Pre-requisitos 📋

[API_DePeru.com](https://www.deperu.com/api/) -> Fuente : DePeru.com.

[NewsApi.org](https://newsapi.org/) -> Fuente : NewsApi.org.

_Tener instalado Android Studio, conocer sobre kotlin, consultas con retrofit2, conocimiento de firebase firestore._

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

_En este caso usaremos el usuario TEST._

```
. correo = test@gmail.com
. contraseña = 123456
```

<p align="center">
 <img src="https://i.postimg.cc/W3f7bTK2/Usuario.png"/>
</p>


## Modelo de datos en Postman 📖

_Mostraré un ejemplo del estructura de la información que utilizaremos, en este caso es cotizaciondolar.json_

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

_Vista Login y Registro_

_Procederemos a mostrar las vistas del registro e inicio de sesión_

<p align="center">
 <img src="https://i.postimg.cc/7hX63Z3z/Screenshot-1650580536.png"/>
 <img src="https://i.postimg.cc/NffLgRtk/Screenshot-1650580309.png"/>
 <img src="https://i.postimg.cc/zXbZSfF1/Screenshot-1650580540.png"/>
 <img src="https://i.postimg.cc/HsqGSC68/Screenshot-1650580321.png"/>
</p>

_En este paso mostraré las demas vistas_

<p align="center">
   <img src="https://i.postimg.cc/0QQHkttq/Screenshot-1649191655.png"/>
   <img src="https://i.postimg.cc/GtMLxxQZ/Screenshot-1653170291.png"/>
   <img src="https://i.postimg.cc/15t55bRP/Screenshot-1653170295.png"/>
   <img src="https://i.postimg.cc/HLGpzxws/Screenshot-1653170299.png"/>
   <img src="https://i.postimg.cc/BZdszD6v/Screenshot-1649191662.png"/>
   <img src="https://i.postimg.cc/MKVxJTMm/Screenshot-1649191664.png"/>
</p>

## Video 📄

_En este video se mostrará las demas funcionalidades de la app._

_Nota : La ultima actualización del video esta en mi perfil de linkedin, Github no permite archivos pesados._

[LimaSegura-Video](https://user-images.githubusercontent.com/43099030/164521101-2f478148-5bf4-4fae-a95f-de9f18063a34.mp4)

## Contribuyendo 🖇️

_Agradecer a la gente de DePeru.com por seguir subiendo informacion a sus servidores, un ❤️ por [DePeru.com](https://github.com/deperucom) 😊._

_Tambien agradecer ❤️ a la página [NewsAPi.org](https://newsapi.org/) 😊_

_No olviden apoyar a los creadores de las APIS_

## Autor ✒️

_**Paul Guillen Acuña** - *Mi Repositorio* -[PaulGuillen](https://github.com/PaulGuillen?tab=repositories)_
