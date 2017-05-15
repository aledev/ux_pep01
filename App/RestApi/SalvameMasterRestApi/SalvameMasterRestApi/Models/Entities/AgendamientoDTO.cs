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

        [JsonProperty("IdUsuario")]
        public long IdUsuario
        {
            get;
            set;
        }


    }
}