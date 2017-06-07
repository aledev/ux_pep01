package salvamemaster.ux.usach.cl.entities;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
/**
 * Created by admin on 06-06-2017.
 */

public class RecursoDTO {

    private File foto;
    private ByteArrayOutputStream fotoMemoria;

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public ByteArrayOutputStream getFotoMemoria() {
        return fotoMemoria;
    }

    public void setFotoMemoria(ByteArrayOutputStream fotoMemoria) {
        this.fotoMemoria = fotoMemoria;
    }
}
