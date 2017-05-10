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
        [JsonProperty("id")]
        public long Id
        {
            get;
            set;
        }

        [JsonProperty("usuario")]
        public string Usuario
        {
            get;
            set;
        }

        [JsonProperty("password")]
        public string Password
        {
            get;
            set;
        }

        [JsonProperty("nombre")]
        public string Nombre
        {
            get;
            set;
        }

        [JsonProperty("email")]
        public string Email
        {
            get;
            set;
        }

        [JsonProperty("idtipousuario")]
        public short IdTipoUsuario
        {
            get;
            set;
        }

        [JsonProperty("tipousuario")]
        public string TipoUsuario
        {
            get;
            set;
        }

        [JsonIgnore()]
        public DateTime FchCreate
        {
            get;
            set;
        }

        [JsonIgnore()]
        public DateTime? FchUpdate
        {
            get;
            set;
        }
    }
}