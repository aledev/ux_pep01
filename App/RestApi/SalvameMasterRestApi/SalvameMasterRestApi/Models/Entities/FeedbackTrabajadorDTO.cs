using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class FeedbackTrabajadorDTO
    {
        [JsonProperty("Id")]
        public long Id
        {
            get;
            set;
        }

        [JsonProperty("IdTrabajador")]
        public long IdTrabajador
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

        [JsonProperty("FchFeedback")]
        public DateTime FchFeedback
        {
            get;
            set;
        }

        [JsonProperty("Valoracion")]
        public decimal Valoracion
        {
            get;
            set;
        }

        [JsonProperty("Observacion")]
        public string Observacion
        {
            get;
            set;
        }
    }
}