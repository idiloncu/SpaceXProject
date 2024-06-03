import com.example.spacexproject.model.SpaceModel
import retrofit2.Call
import retrofit2.http.GET

interface SpaceApiService {

    //GET https://api.spacexdata.com/v3/capsules
    //Base URL https://api.spacexdata.com/v3/

    @GET("/capsules")
    fun getData(): Call<List<SpaceModel>>

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v3/"
    }
}
