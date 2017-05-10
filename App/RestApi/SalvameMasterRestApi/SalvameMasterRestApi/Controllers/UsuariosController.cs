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
    public class UsuariosController : ApiController
    {
        // GET: api/Usuarios
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET: api/Usuarios/5
        public string Get(int id)
        {
            return "value";
        }

        public string Get(string user, string pass)
        {
            string jsonResult = string.Empty;

            try
            {
                UsuarioDTO userObj = null;

                using (var dbContext = new salvameMasterEntities())
                {
                    userObj = (from u in dbContext.usuario
                               from t in dbContext.tipo_usuario
                               where u.usuario1 == user &&
                                     u.password == pass &&
                                     u.idtipousuario == t.id
                               select new UsuarioDTO
                               {
                                   Email = u.email,
                                   FchCreate = u.fchcreate,
                                   FchUpdate = u.fchupdate,
                                   Id = u.id,
                                   IdTipoUsuario = u.idtipousuario,
                                   Nombre = u.nombre,
                                   TipoUsuario = t.descripcion,
                                   Usuario = u.usuario1
                               }).FirstOrDefault();

                    if (userObj != null)
                        jsonResult = JsonConvert.SerializeObject(userObj);
                }
            }
            catch (Exception ex)
            {
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
    }
}
