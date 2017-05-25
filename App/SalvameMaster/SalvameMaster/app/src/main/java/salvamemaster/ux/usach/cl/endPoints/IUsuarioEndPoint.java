package salvamemaster.ux.usach.cl.endPoints;

import salvamemaster.ux.usach.cl.entities.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Alejandro on 17-05-2017.
 */

public interface IUsuarioEndPoint {
    @GET("/SalvameMaster/api/Usuarios")
    Call<UsuarioDTO> getUserByLogon(@Query("user") String username,
                                    @Query("pass") String password);

    @POST("/SalvameMaster/api/Usuarios")
    Call<String> createUser(@Body UsuarioDTO user);
}
