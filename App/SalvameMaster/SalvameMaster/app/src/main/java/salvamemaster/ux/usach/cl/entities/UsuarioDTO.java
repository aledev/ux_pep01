package salvamemaster.ux.usach.cl.entities;
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

    public UsuarioDTO(){

    }

    public UsuarioDTO(long id, long idPersona, String password, Date fchCreate, short idEstado){
        this.id = id;
        this.idPersona = idPersona;
        this.password = password;
        this.fchCreate = fchCreate;
        this.idEstado = idEstado;
    }
    //endregion

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFchCreate() {
        return fchCreate;
    }

    public void setFchCreate(Date fchCreate) {
        this.fchCreate = fchCreate;
    }

    public short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(short idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString(){
        return "Id Persona "+idPersona;
    }

}