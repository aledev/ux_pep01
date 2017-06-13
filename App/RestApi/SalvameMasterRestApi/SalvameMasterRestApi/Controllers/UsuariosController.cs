using log4net;
using Newtonsoft.Json;
using SalvameMasterRestApi.Models.DBModel;
using SalvameMasterRestApi.Models.Entities;
using SalvameMasterRestApi.src.Utils;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Transactions;
using System.Web.Http;
using System.Web.Http.Results;

namespace SalvameMasterRestApi.Controllers
{
    public class UsuariosController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(UsuariosController).Name);
        #endregion

        #region metodos publicos
        #region Usuario Get
        public System.Web.Http.Results.JsonResult<UsuarioDTO> Get(string user, string pass)
        {
            UsuarioDTO userObj = null;

            try
            {
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
                                       },
                                       Foto = p.Foto
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
                    }
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), ex);
            }

            return Json(userObj, new JsonSerializerSettings() { DateFormatString = "yyyy-MM-ddTHH:mm:ssZ" });
        }
        #endregion

        #region Usuario Post
        [HttpPost]
        // POST: api/Usuarios
        public string Post(HttpRequestMessage request)
        {
            string jsonResult = string.Empty;
            MessageReturnDTO messageObj = new MessageReturnDTO();

            try
            {
                string value = request.Content.ReadAsStringAsync().Result;
                UsuarioDTO userObj = JsonConvert.DeserializeObject<UsuarioDTO>(value);

                using (var dbContext = new salvameMasterEntities())
                {
                    using (var trx = new TransactionScope())
                    {
                        var objExists = dbContext.Persona.Where(p => p.Email == userObj.Persona.Email &&
                                                                p.IdEstado == (short)EnumUtils.Estado.Habilitado).FirstOrDefault();

                        // Validamos que el correo no exista en DB
                        if (objExists == null)
                        {
                            // Creamos el objeto persona en DB
                            var objPersona = dbContext.Persona.Add(new Persona
                            {
                                ApellidoMaterno = userObj.Persona.ApellidoMaterno,
                                ApellidoPaterno = userObj.Persona.ApellidoPaterno,
                                Email = userObj.Persona.Email,
                                FchNacimiento = userObj.Persona.FchNacimiento,
                                FchRegistro = userObj.Persona.FchRegistro,
                                Foto = userObj.Persona.Foto,
                                IdEstado = userObj.Persona.IdEstado,
                                IdTipoPersona = userObj.Persona.IdTipoPersona,
                                Nombre = userObj.Persona.Nombre,
                                Sexo = userObj.Persona.Sexo
                            });
                            // Guardamos los cambios
                            dbContext.SaveChanges();
                            // Obtenemos el id de la persona
                            userObj.Persona.Id = objPersona.Id;
                            userObj.IdPersona = objPersona.Id;

                            // Creamos el objeto usuario en DB
                            var objUsuario = dbContext.Usuario.Add(new Usuario
                            {
                                IdEstado = (short)EnumUtils.Estado.Habilitado,
                                IdPersona = userObj.IdPersona,
                                Password = userObj.Password
                            });
                            // Guardamos los cambios
                            dbContext.SaveChanges();
                            // Obtenemos el id del usuario
                            userObj.Id = objUsuario.Id;

                            // Verificamos si el usuario de tipo Trabajador
                            if (objPersona.IdTipoPersona == (short)EnumUtils.TipoPersona.Trabajador)
                            {
                                // Creamos el objeto trabajador en DB
                                var objTrabajador = dbContext.Trabajador.Add(new Trabajador
                                {
                                    Descripcion = userObj.Trabajador.Descripcion,
                                    Direccion = userObj.Trabajador.Direccion,
                                    FchInicioTrabajador = userObj.Trabajador.FchInicioTrabajador,
                                    IdPersona = objPersona.Id,
                                    IdRegion = userObj.Trabajador.IdRegion,
                                    IdTipoTrabajador = userObj.Trabajador.IdTipoTrabajador,
                                    Latitud = userObj.Trabajador.Latitud,
                                    Longitud = userObj.Trabajador.Longitud,
                                    PrecioHora = userObj.Trabajador.PrecioHora,
                                    PrecioVisita = userObj.Trabajador.PrecioVisita,
                                    Telefono = userObj.Trabajador.Telefono
                                });
                                // Guardamos los cambios
                                dbContext.SaveChanges();
                                // Obtenemos el id del trabajador
                                userObj.Trabajador.Id = objTrabajador.Id;
                                userObj.Trabajador.IdPersona = objPersona.Id;
                            }
                            // Completamos la transacción
                            trx.Complete();

                            messageObj = new MessageReturnDTO
                            {
                                Resultado = true,
                                Mensaje = "OK"
                            };
                        }
                        else
                        {
                            messageObj = new MessageReturnDTO
                            {
                                Resultado = false,
                                Mensaje = "El email ya esta registrado"
                            };
                        }

                        jsonResult = JsonConvert.SerializeObject(messageObj);
                    }
                }
            }
            catch (SqlException sqlEx)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), sqlEx);

                messageObj = new MessageReturnDTO
                {
                    Resultado = false,
                    Mensaje = "Error al intentar crear el usuario."
                };

                if (sqlEx.Errors != null)
                {
                    List<string> erroresList = new List<string>();

                    for (int x = 0; x < sqlEx.Errors.Count; x++)
                        erroresList.Add(sqlEx.Errors[x].Message);

                    messageObj.Errores = erroresList;
                }

                jsonResult = JsonConvert.SerializeObject(messageObj);
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), ex);

                messageObj = new MessageReturnDTO
                {
                    Resultado = false,
                    Mensaje = "Error al intentar crear el usuario."
                };

                jsonResult = JsonConvert.SerializeObject(messageObj);
            }

            return jsonResult;
        }
        #endregion

        #region Usuario Put
        // PUT: api/Usuarios/5
        public void Put(int id, [FromBody]string value)
        {

        }
        #endregion

        #region Usuario Delete
        // DELETE: api/Usuarios/5
        public void Delete(int id)
        {
        }
        #endregion

        #endregion
    }
}
