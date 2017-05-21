package com.aledev.core.endpoints;

import com.aledev.core.entities.UsuarioDTO;
import retrofit2.*;
import retrofit2.http.*;

/**
 * Created by Alejandro on 19-05-2017.
 */

public interface IUsuarioEndpoint {
    @GET("api/Usuarios")
    Call<UsuarioDTO> getUserByLogon(@Query("user") String username,
                                    @Query("pass") String password);

    @POST("api/Usuarios")
    Call<String> createUser(@Body UsuarioDTO user);
}
