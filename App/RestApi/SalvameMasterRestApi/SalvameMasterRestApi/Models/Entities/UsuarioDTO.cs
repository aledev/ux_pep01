using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class UsuarioDTO
    {
        [JsonProperty("Id")]
        public long Id
        {
            get;
            set;
        }

        [JsonProperty("IdPersona")]
        public long IdPersona
        {
            get;
            set;
        }



        [JsonProperty("Trabajador")]
        public TrabajadorDTO Trabajador
        {
            get;
            set;
        }

        [JsonProperty("Password")]
        public string Password
        {
            get;
            set;
        }

        [JsonProperty("FchCreate")]
        public DateTime FchCreate
        {
            get;
            set;
        }

        [JsonProperty("IdEstado")]
        public short IdEstado
        {
            get;
            set;
        }
    }
}