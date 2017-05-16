using log4net;
using Newtonsoft.Json;
using SalvameMasterRestApi.Models.DBModel;
using SalvameMasterRestApi.Models.Entities;
using SalvameMasterRestApi.src.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace SalvameMasterRestApi.Controllers
{
    public class UsuariosController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(UsuariosController).Name);
        #endregion

        #region metodos publicos
        public string Get(string user, string pass)
        {
            string jsonResult = string.Empty;

            try
            {
                UsuarioDTO userObj = null;

                using (var dbContext = new salvameMasterEntities())
                {
                    userObj = (from u in dbContext.Usuario
                               from t in dbContext.TipoPersona
                               from p in dbContext.Persona
                               from e in dbContext.Estado
                               where p.Email == user &&
                                     u.Password == pass &&
                                     u.IdPersona == p.Id &&
                                     t.Id == p.IdTipoPersona &&
                                     u.IdEstado == e.Id
                               select new UsuarioDTO
                               {
                                   FchCreate = u.FchCreate,
                                   Id = u.Id,
                                   IdEstado = u.IdEstado,
                                   IdPersona = p.Id,
                                   Estado = new EstadoDTO
                                   {
                                       Id = e.Id,
                                       Descripcion = e.Descripcion
                                   },
                                   Persona = new PersonaDTO
                                   {
                                       ApellidoMaterno = p.ApellidoMaterno,
                                       ApellidoPaterno = p.ApellidoPaterno,
                                       Email = p.Email,
                                       FchNacimiento = p.FchNacimiento,
                                       FchRegistro = p.FchRegistro,
                                       Id = p.Id,
                                       IdEstado = p.IdEstado,
                                       IdTipoPersona = p.IdTipoPersona,
                                       Nombre = p.Nombre,
                                       NombreCompleto  = p.Nombre + " " + p.ApellidoPaterno + " " + p.ApellidoMaterno,
                                       Sexo = p.Sexo,
                                       TipoPersona = new TipoPersonaDTO
                                       {
                                           Id = t.Id,
                                           Descripcion = t.Descripcion
                                       }
                                   }
                               }).FirstOrDefault();

                    // Chequeamos si el usuario es de tipo "trabajador"
                    if (userObj != null)
                    {
                        if(userObj.Persona.IdTipoPersona == (short)EnumUtils.TipoPersona.Trabajador)
                        {
                            userObj.Trabajador = (from t in dbContext.Trabajador
                                                  from tt in dbContext.TipoTrabajador
                                                  from r in dbContext.Region
                                                  where t.IdTipoTrabajador == tt.Id &&
                                                        t.IdRegion == r.Id &&
                                                        t.IdPersona == userObj.IdPersona
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
                        }

                        jsonResult = JsonConvert.SerializeObject(userObj);
                    }
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {2} => {3}", this.GetType().Name, methodName, "Error"), ex);
            }

            return jsonResult;
        }

        // POST: api/Usuarios
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Usuarios/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Usuarios/5
        public void Delete(int id)
        {
        }
        #endregion
    }
}
