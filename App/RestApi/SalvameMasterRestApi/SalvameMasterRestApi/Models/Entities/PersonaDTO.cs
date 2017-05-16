using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class PersonaDTO
    {
        [JsonProperty("Id")]
        public long Id
        {
            get;
            set;
        }

        [JsonProperty("Nombre")]
        public string Nombre
        {
            get;
            set;
        }

        [JsonProperty("ApellidoPaterno")]
        public string ApellidoPaterno
        {
            get;
            set;
        }

        [JsonProperty("ApellidoMaterno")]
        public string ApellidoMaterno
        {
            get;
            set;
        }

        [JsonProperty("NombreCompleto")]
        public string NombreCompleto
        {
            get;
            set;
        }

        [JsonProperty("Email")]
        public string Email
        {
            get;
            set;
        }

        [JsonProperty("FchNacimiento")]
        public DateTime FchNacimiento
        {
            get;
            set;
        }

        [JsonProperty("FchRegistro")]
        public DateTime FchRegistro
        {
            get;
            set;
        }

        [JsonProperty("Sexo")]
        public string Sexo
        {
            get;
            set;
        }

        [JsonProperty("IdTipoPersona")]
        public short IdTipoPersona
        {
            get;
            set;
        }

        [JsonProperty("TipoPersona")]
        public TipoPersonaDTO TipoPersona
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