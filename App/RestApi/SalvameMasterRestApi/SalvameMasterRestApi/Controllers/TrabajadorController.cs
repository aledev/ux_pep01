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

namespace SalvameMasterRestApi.Controllers
{
    public class TrabajadorController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(TrabajadorController).Name);
        #endregion

        // GET: api/Trabajador/5
        public string Get(long id)
        {
            string jsonObj = string.Empty;

            try
            {
                TrabajadorDTO objDb = null;

                using (var dbContext = new salvameMasterEntities())
                {

                    objDb = (from t in dbContext.Trabajador
                             from tt in dbContext.TipoTrabajador
                             from r in dbContext.Region
                             where t.IdTipoTrabajador == tt.Id &&
                                 t.IdRegion == r.Id &&
                                 t.Id == id
                             select new TrabajadorDTO
                             {
                                 Descripcion = t.Descripcion,
                                 Direccion = t.Direccion,
                                 FchInicioTrabajador = t.FchInicioTrabajador,
                                 Id = t.Id,
                                 IdPersona = t.IdPersona,
                                 IdRegion = t.IdRegion,
                                 IdTipoTrabajador = t.IdTipoTrabajador,
                                 Latitud = t.Latitud,
                                 Longitud = t.Longitud,
                                 PrecioHora = t.PrecioHora,
                                 PrecioVisita = t.PrecioVisita,
                                 Region = new RegionDTO
                                 {
                                     Id = r.Id,
                                     Descripcion = r.Descripcion
                                 },
                                 Telefono = t.Telefono,
                                 TipoTrabajador = new TipoTrabajadorDTO
                                 {
                                     Id = tt.Id,
                                     Descripcion = tt.Descripcion
                                 }
                             }).FirstOrDefault();

                    jsonObj = JsonConvert.SerializeObject(objDb);
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {2} => {3}", this.GetType().Name, methodName, "Error"), ex);
            }

            return jsonObj;
        }

        // POST: api/Trabajador
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Trabajador/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Trabajador/5
        public void Delete(int id)
        {
        }
    }
}
