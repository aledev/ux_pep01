package salvamemaster.ux.usach.cl.entities;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by admin on 25-05-2017.
 */

public class PersonaDTO {
    @SerializedName("Id")
    private long id;

    @SerializedName("Nombre")
    private String nombre;

    @SerializedName("ApellidoPaterno")
    private String paterno;

    @SerializedName("ApellidoMaterno")
    private String materno;

    @SerializedName("Email")
    private String mail;

    @SerializedName("NombreCompleto")
    private String nombreCompleto;

    @SerializedName("FchNacimiento")
    private Date fechaNacimiento;

    @SerializedName("FchRegistro")
    private Date fechaRegistro;

    @SerializedName("Sexo")
    private String tipoSexo;

    @SerializedName("IdTipoPersona")
    private int idTipoPersona;

    @SerializedName("Foto")
    private  String foto;

    public PersonaDTO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(String tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
