package salvamemaster.ux.usach.cl.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alejandro on 12-06-2017.
 */

public class TipoTrabajadorDTO {
    @SerializedName("Id")
    private short id;

    @SerializedName("Descripcion")
    private String descripcion;

    public TipoTrabajadorDTO(short id, String descripcion){
        this.setId(id);
        this.setDescripcion(descripcion);
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
