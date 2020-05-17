package Service;

import Classes.Produto;
import DAO.ProdutoDAO;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class ProdutoService {
    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        try {
            produtoDAO = new ProdutoDAO("produtos.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object add(Request request, Response response) {
        response.type("application/json");
        Produto produto = new Gson().fromJson(request.body(), Produto.class);

        produtoDAO.add(produto);
        response.header("Content-Type","application/json");
        return new Gson().toJson(produto);
    }

    public Object get(Request request, Response response) {
        response.header("Content-Type","application/json");
        int id = Integer.parseInt(request.params(":id"));
        return produtoDAO.get(id);
    }

    public Object getAll(Request request, Response response) {
        response.header("Content-Type","application/json");
        return produtoDAO.getAll() ;
    }

    public Object update(Request request, Response response) {
        response.type("application/json");
        Produto produto = new Gson().fromJson(request.body(), Produto.class);

        produtoDAO.update(produto);
        response.header("Content-Type","application/json");
        return new Gson().toJson(produto);
    }

    public Object delete(Request request, Response response) {
        response.type("application/json");
        Produto produto = produtoDAO.get(Integer.parseInt(request.params(":id")));

        produtoDAO.remove(produto);
        response.header("Content-Type","application/json");
        return new Gson().toJson(produto);
    }


}
