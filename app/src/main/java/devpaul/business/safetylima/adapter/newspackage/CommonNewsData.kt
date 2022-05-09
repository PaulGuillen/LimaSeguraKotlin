package devpaul.business.safetylima.adapter.newspackage

object CommonNewsData {

    private  val BASE_URL = "https://newsapi.org/"

    val retrofitService : RetrofitServiceNewsApart
    get () = RetrofitClient.getClient(BASE_URL).create(RetrofitServiceNewsApart::class.java)

}