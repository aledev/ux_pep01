package salvamemaster.ux.usach.cl.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alejandro on 12-06-2017.
 */

public class RegionDTO {
    @SerializedName("Id")
    private short id;

    @SerializedName("Descripcion")
    private String descripcion;

    public RegionDTO(short id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
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
