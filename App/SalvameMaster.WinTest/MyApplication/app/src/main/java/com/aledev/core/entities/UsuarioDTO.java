package com.aledev.core.entities;

import com.google.gson.annotations.*;
import java.util.Date;

/**
 * Created by Alejandro on 19-05-2017.
 */

public class UsuarioDTO {
    //region propiedades privadas

    @SerializedName("Id")
    private long id;

    @SerializedName("IdPersona")
    private long idPersona;

    //@SerializedName("IdPersona")
    //private PersonaDTO _persona;

    //[JsonProperty("Trabajador")]
    //private TrabajadorDTO _trabajador;

    @SerializedName("Password")
    private String password;

    @SerializedName("FchCreate")
    private Date fchCreate;

    @SerializedName("IdEstado")
    private short idEstado;

    //[JsonProperty("Estado")]
    //private EstadoDTO _estado;
    //endregion

    //region constructores
    public UsuarioDTO(long id, long idPersona, String password, Date fchCreate, short idEstado){
        this.id = id;
        this.idPersona = idPersona;
        this.password = password;
        this.fchCreate = fchCreate;
        this.idEstado = idEstado;
    }
    //endregion
}
