package salvamemaster.ux.usach.cl.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Alejandro on 12-06-2017.
 */

public class TrabajadorDTO {
    //region propiedades privadas
    @SerializedName("Id")
    private long id;

    @SerializedName("IdPersona")
    private long idPersona;

    @SerializedName("Persona")
    private PersonaDTO persona;

    @SerializedName("FchCreate")
    private Date fchCreate;

    @SerializedName("IdEstado")
    private short idEstado;

    @SerializedName("IdTipoTrabajador")
    private short idTipoTrabajador;

    @SerializedName("TipoTrabajador")
    private TipoTrabajadorDTO tipoTrabajador;

    @SerializedName("FchInicioTrabajador")
    private Date fchInicioTrabajador;

    @SerializedName("PrecioHora")
    private double precioHora;

    @SerializedName("PrecioVisita")
    private double precioVisita;

    @SerializedName("Telefono")
    private String telefono;

    @SerializedName("Direccion")
    private String direccion;

    @SerializedName("IdRegion")
    private short idRegion;

    @SerializedName("Latitud")
    private String latitud;

    @SerializedName("Longitud")
    private String longitud;

    @SerializedName("Descripcion")
    private String descripcion;
    //endregion

    //region constructores
    public TrabajadorDTO(long id, long idPersona, PersonaDTO persona, short idTipoTrabajador,
                         TipoTrabajadorDTO tipoTrabajador, Date fchInicioTrabajador,
                         double precioHora, double precioVisita, String telefono,
                         String direccion, short idRegion, String latitud, String longitud,
                         String descripcion){
        this.setId(id);
        this.setIdPersona(idPersona);
        this.setPersona(persona);
        this.setIdTipoTrabajador(idTipoTrabajador);
        this.setTipoTrabajador(tipoTrabajador);
        this.setFchInicioTrabajador(fchInicioTrabajador);
        this.setPrecioHora(precioHora);
        this.setPrecioVisita(precioVisita);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setIdRegion(idRegion);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setDescripcion(descripcion);
    }

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

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
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

    public short getIdTipoTrabajador() {
        return idTipoTrabajador;
    }

    public void setIdTipoTrabajador(short idTipoTrabajador) {
        this.idTipoTrabajador = idTipoTrabajador;
    }

    public TipoTrabajadorDTO getTipoTrabajador() {
        return tipoTrabajador;
    }

    public void setTipoTrabajador(TipoTrabajadorDTO tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    public Date getFchInicioTrabajador() {
        return fchInicioTrabajador;
    }

    public void setFchInicioTrabajador(Date fchInicioTrabajador) {
        this.fchInicioTrabajador = fchInicioTrabajador;
    }

    public double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    public double getPrecioVisita() {
        return precioVisita;
    }

    public void setPrecioVisita(double precioVisita) {
        this.precioVisita = precioVisita;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public short getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(short idRegion) {
        this.idRegion = idRegion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //endregion

    //region metodos publicos

    //endregion
}
