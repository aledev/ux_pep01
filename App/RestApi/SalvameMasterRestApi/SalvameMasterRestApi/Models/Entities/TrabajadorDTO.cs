using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class TrabajadorDTO
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

        [JsonProperty("Persona")]
        public PersonaDTO Persona
        {
            get;
            set;
        }

        [JsonProperty("IdTipoTrabajador")]
        public short IdTipoTrabajador
        {
            get;
            set;
        }

        [JsonProperty("TipoTrabajador")]
        public TipoTrabajadorDTO TipoTrabajador
        {
            get;
            set;
        }

        [JsonProperty("FchInicioTrabajador")]
        public DateTime FchInicioTrabajador
        {
            get;
            set;
        }

        [JsonProperty("PrecioHora")]
        public decimal PrecioHora
        {
            get;
            set;
        }

        [JsonProperty("PrecioVisita")]
        public decimal PrecioVisita
        {
            get;
            set;
        }

        [JsonProperty("Telefono")]
        public string Telefono
        {
            get;
            set;
        }

        [JsonProperty("Direccion")]
        public string Direccion
        {
            get;
            set;
        }

        [JsonProperty("IdRegion")]
        public short IdRegion
        {
            get;
            set;
        }

        [JsonProperty("Region")]
        public RegionDTO Region
        {
            get;
            set;
        }

        [JsonProperty("Latitud")]
        public string Latitud
        {
            get;
            set;
        }

        [JsonProperty("Longitud")]
        public string Longitud
        {
            get;
            set;
        }

        [JsonProperty("Descripcion")]
        public string Descripcion
        {
            get;
            set;
        }
    }
}