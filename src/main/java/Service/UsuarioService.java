package Service;

import Classes.Usuario;
import DAO.UsuarioDAO;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        try {
            usuarioDAO = new UsuarioDAO("usuarios.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object add(Request request, Response response) {
        response.type("application/json");
        Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);

        usuarioDAO.add(usuario);
        response.header("Content-Type","application/json");
        return new Gson().toJson(usuario);
    }

    public Object update(Request request, Response response) {
        response.type("application/json");
        Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
        usuarioDAO.update(usuario);
        response.header("Content-Type","application/json");
        return new Gson().toJson(usuario);
    }

    public Object get(Request request, Response response) {
        response.header("Content-Type","application/json");
        int id = Integer.parseInt(request.params(":id"));
        return usuarioDAO.get(id);
    }

    public Object getAll(Request request, Response response) {
        response.header("Content-Type","application/json");
        return usuarioDAO.getAll() ;
    }

    public Object delete(Request request, Response response) {
        response.type("application/json");
        Usuario usuario = usuarioDAO.get(Integer.parseInt(request.params(":id")));
        usuarioDAO.remove(usuario);
        response.header("Content-Type","application/json");
        return new Gson().toJson(usuario);
    }

}
