# LimaSeguraKotlin

_Proyecto enfocado en mostrar noticias actualziadas por día, ademas de contar con dos segmentos personalizados en los que puedas ver las estaciones de polícias,
bomberos y serenazgos, además con un filtro por distrito_

## Comenzando 🚀
_Un video de como funciona la APP [LimaSegura-Video](https://user-images.githubusercontent.com/43099030/164521101-2f478148-5bf4-4fae-a95f-de9f18063a34.mp4)_

_Mira **Usuario test** para conocer como son las vistas del proyecto._

### Pre-requisitos 📋

_[Ver APIS de DePeru.com](https://www.deperu.com/api/)_

_Tener instalado Android Studio, conocer sobre kotlin, google maps, mercado pago, paypal, consultas a servidor Retrofit2_

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

<p align="center">
 <img src="hhttps://i.postimg.cc/W3f7bTK2/Usuario.png"/>
</p>

_Ademas el usuario que has creado desde android studio solo tendra 1 rol por defecto el cual es usuario, tienes que modifcarlo en PGADMIN en la "tabla user_has_roles", deberia quedar así_
  
 <p align="center">
 <img src="https://i.postimg.cc/65CjQ6gp/roles.png"/>
</p>

## Ejecutando los diseños ⚙️
_* Vista Login y Registro_

_Bueno en este caso mostraré capturas de pantalla de las tres perspectivas de cada uno de los roles ( rol admin , rol cliente , rol repartidor)_


<p align="center">
 <img src="https://i.postimg.cc/pdjdCFrk/1.png"/>
 <img src="https://i.postimg.cc/t4ZRRx3y/2.png"/>
</p>

_* En este paso procederé a mostrar las vistas del rol cliente (Cliente)_

<p align="center">

</p>




## Autor ✒️

* **Paul Guillen Acuña** - *Mi Repositorio* - [PaulGuillen](https://github.com/PaulGuillen?tab=repositories)

## Licencia 📄

Este proyecto está bajo la Licencia (MIT License) - mira el archivo [LICENSE.md](LICENSE.md) para detalles
