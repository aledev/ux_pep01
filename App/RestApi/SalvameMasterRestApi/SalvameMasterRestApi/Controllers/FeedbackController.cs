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

namespace SalvameMasterRestApi.Controllers
{
    public class FeedbackController : ApiController
    {
        #region propiedades privadas
        private static readonly ILog log = LogManager.GetLogger(typeof(FeedbackController).Name);
        #endregion

        #region metodos publicos
        // GET: api/Feedback
        #region Feedback Get
        public System.Web.Http.Results.JsonResult<List<FeedbackTrabajadorDTO>> Get(long idTrabajador)
        {
            List<FeedbackTrabajadorDTO> feedbackList = null;

            try
            {
                using (var dbContext = new salvameMasterEntities())
                {
                    feedbackList = (from f in dbContext.FeedbackTrabajador
                                    from t in dbContext.Trabajador
                                    from u in dbContext.Usuario
                                    where f.IdTrabajador == t.Id &&
                                          f.IdUsuario == u.Id &&
                                          f.IdTrabajador == idTrabajador
                                    select new FeedbackTrabajadorDTO
                                    {
                                        FchFeedback = f.FchFeedback,
                                        Id = f.Id,
                                        IdTrabajador = f.IdTrabajador,
                                        IdUsuario = f.IdUsuario,
                                        Observacion = f.Observacion,
                                        Trabajador = new TrabajadorDTO
                                        {
                                            Descripcion = t.Descripcion,
                                            Direccion = t.Direccion,
                                            Id = t.Id,
                                            FchInicioTrabajador = t.FchInicioTrabajador,
                                            IdPersona = t.IdPersona,
                                            IdRegion = t.IdRegion,
                                            IdTipoTrabajador = t.IdTipoTrabajador,
                                            Latitud = t.Latitud,
                                            Longitud = t.Longitud,
                                            PrecioHora = t.PrecioHora,
                                            PrecioVisita = t.PrecioVisita,
                                            Telefono = t.Telefono
                                        },
                                        Usuario = new UsuarioDTO
                                        {
                                            FchCreate = u.FchCreate,
                                            Id = u.Id,
                                            IdEstado = u.IdEstado,
                                            IdPersona = u.IdPersona
                                        },
                                        Valoracion = f.Valoracion
                                    }).ToList();
                }
            }
            catch (Exception ex)
            {
                var methodName = System.Reflection.MethodBase.GetCurrentMethod().Name;
                log.Error(string.Format("{0} {1} => {2}", this.GetType().Name, methodName, "Error"), ex);
            }

            return Json(feedbackList, new JsonSerializerSettings() { DateFormatString = "yyyy-MM-ddTHH:mm:ssZ" });
        }
        #endregion

        #region Feedback Post
        // POST: api/Feedback
        public string Post(HttpRequestMessage request)
        {
            string jsonResult = string.Empty;
            MessageReturnDTO messageObj = new MessageReturnDTO();

            try
            {
                string value = request.Content.ReadAsStringAsync().Result;
                FeedbackTrabajadorDTO feedbackObj = JsonConvert.DeserializeObject<FeedbackTrabajadorDTO>(value);

                using (var dbContext = new salvameMasterEntities())
                {
                    using (var trx = new TransactionScope())
                    {
                        // Creamos el objeto persona en DB
                        var objFeedback = dbContext.FeedbackTrabajador.Add(new FeedbackTrabajador
                        {
                            FchFeedback = DateTime.Now,
                            IdTrabajador = feedbackObj.IdTrabajador,
                            IdUsuario = feedbackObj.IdUsuario,
                            Observacion = feedbackObj.Observacion,
                            Valoracion = feedbackObj.Valoracion
                        });
                        // Guardamos los cambios
                        dbContext.SaveChanges();
                        // Completamos la transacción
                        trx.Complete();

                        messageObj = new MessageReturnDTO
                        {
                            Resultado = true,
                            Mensaje = "OK"
                        };

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
                    Mensaje = "Error al intentar ingresar el feedback."
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
                    Mensaje = "Error al intentar ingresar el feedback."
                };

                jsonResult = JsonConvert.SerializeObject(messageObj);
            }

            return jsonResult;
        }
        #endregion

        // PUT: api/Feedback/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Feedback/5
        public void Delete(int id)
        {
        }
        #endregion
    }
}
