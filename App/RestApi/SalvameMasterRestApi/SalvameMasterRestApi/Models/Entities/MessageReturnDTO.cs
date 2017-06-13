using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class MessageReturnDTO
    {
        [JsonProperty("Resultado")]
        public bool Resultado
        {
            get;
            set;
        }

        [JsonProperty("Mensaje")]
        public string Mensaje
        {
            get;
            set;
        }

        [JsonProperty("Errores")]
        public List<string> Errores
        {
            get;
            set;
        }
    }
}