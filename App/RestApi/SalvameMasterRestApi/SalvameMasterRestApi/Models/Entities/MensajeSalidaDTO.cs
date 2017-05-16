using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class MensajeSalidaDTO
    {
        [JsonProperty("Id")]
        public long Id
        {
            get;
            set;
        }

        [JsonProperty("EmailFrom")]
        public string EmailFrom
        {
            get;
            set;
        }

        [JsonProperty("EmailTo")]
        public string EmailTo
        {
            get;
            set;
        }

        [JsonProperty("Html")]
        public string Html
        {
            get;
            set;
        }

        [JsonProperty("Asunto")]
        public string Asunto
        {
            get;
            set;
        }

        [JsonProperty("FchInsert")]
        public DateTime FchInsert
        {
            get;
            set;
        }

        [JsonProperty("NroIntentos")]
        public short NroIntentos
        {
            get;
            set;
        }

        [JsonProperty("FchUpdate")]
        public DateTime FchUpdate
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

        [JsonProperty("Estado")]
        public EstadoDTO Estado
        {
            get;
            set;
        }
    }
}