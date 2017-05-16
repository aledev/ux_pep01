using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.src.Utils
{
    public class EnumUtils
    {
        public enum Estado
        {
            Habilitado = 1,
            No_Habilitado = 2
        }

        public enum TipoPersona
        {
            Cliente = 1,
            Trabajador = 2
        }

        public enum TipoTrabajador
        {
            Gasfiter = 1,
            Cerrajero = 2,
            Mecanico = 3,
            Pintor = 4,
            Carpintero = 5,
            Electrico = 6,
            Topografo = 7,
            Albanil = 8
        }
    }
}