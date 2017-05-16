using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class InscripcionDTO
    {
        [JsonProperty("Id")]
        public long Id
        {
            get;
            set;
        }

        [JsonProperty("IdUsuario")]
        public long IdUsuario
        {
            get;
            set;
        }

        [JsonProperty("Usuario")]
        public UsuarioDTO Usuario
        {
            get;
            set;
        }

        [JsonProperty("FchInscripcion")]
        public DateTime FchInscripcion
        {
            get;
            set;
        }

        [JsonProperty("Token")]
        public string Token
        {
            get;
            set;
        }

        [JsonProperty("FchExpiracion")]
        public DateTime FchExpiracion
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

        [JsonProperty("Estadoñ")]
        public EstadoDTO Estado
        {
            get;
            set;
        }
    }
}