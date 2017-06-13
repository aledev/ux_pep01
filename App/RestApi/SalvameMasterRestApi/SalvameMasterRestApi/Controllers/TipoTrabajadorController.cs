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
    public class TipoTrabajadorController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(TipoTrabajadorController).Name);
        #endregion

        // GET: api/TipoTrabajador
        public JsonResult<List<TipoTrabajadorDTO>> Get()
        {
            List <TipoTrabajadorDTO> objResult = null;

            try
            {
                using (var dbContext = new salvameMasterEntities())
                {
                    objResult = (from t in dbContext.TipoTrabajador
                                 select new TipoTrabajadorDTO
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

            return Json(objResult);
        }
    }
}
