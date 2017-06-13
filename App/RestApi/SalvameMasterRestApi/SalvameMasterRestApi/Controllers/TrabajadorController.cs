using GeoCoordinatePortable;
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
    public class TrabajadorController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(TrabajadorController).Name);
        #endregion

        [HttpGet]
        // GET: api/Trabajador/5
        public JsonResult<TrabajadorDTO> Get(long id)
        {
            TrabajadorDTO objDb = null;

            try
            {
                using (var dbContext = new salvameMasterEntities())
                {
                    objDb = (from t in dbContext.Trabajador
                             from tt in dbContext.TipoTrabajador
                             from r in dbContext.Region
                             from p in dbContext.Persona
                             where t.IdTipoTrabajador == tt.Id &&
                                 t.IdRegion == r.Id &&
                                 t.Id == id &&
                                 t.IdPersona == p.Id
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
                                 },
                                 Persona = new PersonaDTO
                                 {
                                     ApellidoMaterno = p.ApellidoMaterno,
                                     ApellidoPaterno = p.ApellidoPaterno,
                                     Email = p.Email,
                                     Foto = p.Foto,
                                     FchNacimiento = p.FchNacimiento,
                                     FchRegistro = p.FchRegistro,
                                     Id = p.Id,
                                     IdEstado = p.IdEstado,
                                     IdTipoPersona = p.IdTipoPersona,
                                     Nombre = p.Nombre,
                                     NombreCompleto = p.Nombre + " " + p.ApellidoPaterno + " " + p.ApellidoMaterno,
                                     Sexo = p.Sexo
                                 }
                             }).FirstOrDefault();
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), ex);
            }

            return Json(objDb);
        }

        [HttpGet]
        // GET: api/Trabajador/5
        public JsonResult<List<TrabajadorDTO>> Get(string latitud, string longitud)
        {
            List<TrabajadorDTO> objDb = null;

            try
            {
                using (var dbContext = new salvameMasterEntities())
                {
                    objDb = (from t in dbContext.Trabajador
                             from tt in dbContext.TipoTrabajador
                             from p in dbContext.Persona
                             from r in dbContext.Region
                             let geo = new GeoCoordinate { Latitude = double.Parse(t.Latitud), Longitude = double.Parse(t.Longitud) }
                             where t.IdTipoTrabajador == tt.Id &&
                                 t.IdRegion == r.Id &&
                                 t.IdPersona == p.Id
                             orderby geo.GetDistanceTo(new GeoCoordinate { Latitude = double.Parse(latitud), Longitude = double.Parse(longitud) })
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
                                 Persona = new PersonaDTO
                                 {
                                     ApellidoMaterno = p.ApellidoMaterno,
                                     ApellidoPaterno = p.ApellidoPaterno,
                                     Email = p.Email,
                                     FchNacimiento = p.FchNacimiento,
                                     FchRegistro = p.FchRegistro,
                                     Id = p.Id,
                                     Foto = p.Foto,
                                     IdEstado = p.IdEstado,
                                     IdTipoPersona = p.IdTipoPersona,
                                     Nombre = p.Nombre,
                                     NombreCompleto = p.Nombre + " " + p.ApellidoPaterno + " " + p.ApellidoMaterno,
                                     Sexo = p.Sexo
                                 },
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
                             }).Take(100).ToList();
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), ex);
            }

            return Json(objDb);
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
