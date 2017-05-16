using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SalvameMasterRestApi.Models.Entities
{
    [Serializable]
    public class RegionDTO
    {
        [JsonProperty("Id")]
        public short Id
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