using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class AgendamientoDTO
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

        [JsonProperty("FchVisita")]
        public DateTime FchVisita
        {
            get;
            set;
        }

        [JsonProperty("FchAgendamiento")]
        public DateTime FchAgendamiento
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

        [JsonProperty("Telefono")]
        public string Telefono
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