# RetrofitLiveDataCallAdapter
retrofit simple LiveData CallAdapter and related LiveData wrapper for handling responses

## sample usage :

retrofit init
```
Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
```

service interface
```
interface CloudApiContract {
    @GET("/")
    fun getIP(): NetworkLiveData<String>
}
```

in view model
```
var ipLiveData = MutableLiveData<String>()
ipCloudRepository.getCurrentIP().observeForever {

            onSuccess {
                //body
                ipLiveData.value = it
            }
            onHttpException { code, message ->
                //code
                //message
            }
            onConnectionFailed { it.message.log() }

               //finished
        }
```
