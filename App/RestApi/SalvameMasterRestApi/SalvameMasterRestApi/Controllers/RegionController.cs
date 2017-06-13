using log4net;
using Newtonsoft.Json;
using SalvameMasterRestApi.Models.DBModel;
using SalvameMasterRestApi.Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Results;

namespace SalvameMasterRestApi.Controllers
{
    public class RegionController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(RegionController).Name);
        #endregion

        // GET: api/Region
        public JsonResult<List<RegionDTO>> Get()
        {
            List<RegionDTO> regionList = null;

            try
            {
                using (var dbContext = new salvameMasterEntities())
                {
                    regionList = (from t in dbContext.Region
                                  select new RegionDTO
                                  {
                                      Id = t.Id,
                                      Descripcion = t.Descripcion
                                  }).ToList();
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), ex);
            }

            return Json(regionList);
        }
    }
}
