//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace SalvameMasterRestApi.Models.DBModel
{
    using System;
    using System.Collections.Generic;
    
    public partial class Trabajador
    {
        public long Id { get; set; }
        public long IdPersona { get; set; }
        public short IdTipoTrabajador { get; set; }
        public System.DateTime FchInicioTrabajador { get; set; }
        public decimal PrecioHora { get; set; }
        public decimal PrecioVisita { get; set; }
        public string Telefono { get; set; }
        public string Direccion { get; set; }
        public short IdRegion { get; set; }
        public string Latitud { get; set; }
        public string Longitud { get; set; }
        public string Descripcion { get; set; }
    }
}
