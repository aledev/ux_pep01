package salvamemaster.ux.usach.cl.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 25-05-2017.
 */

public class TipoPersonaDTO {
    @SerializedName("Id")
    private int id;

    @SerializedName("Descripcion")
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
