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
    
    public partial class usuario
    {
        public long id { get; set; }
        public string usuario1 { get; set; }
        public string nombre { get; set; }
        public string email { get; set; }
        public short idtipousuario { get; set; }
        public System.DateTime fchcreate { get; set; }
        public Nullable<System.DateTime> fchupdate { get; set; }
        public bool habilitado { get; set; }
        public string password { get; set; }
    
        public virtual tipo_usuario tipo_usuario { get; set; }
    }
}
