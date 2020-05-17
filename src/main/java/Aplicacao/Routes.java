package Aplicacao;

import Service.ProdutoService;
import Service.UsuarioService;

import static spark.Spark.*;
public class Routes {
    public static void main(String[] args) {
        CorsFilter.apply();
        UsuarioService usuarioService = new UsuarioService();
        ProdutoService produtoService = new ProdutoService();

        get("/users", "application/json", (request, response) ->
                usuarioService.getAll(request, response), new JSONTransformer());

        get("/users/:id", "application/json", (request, response) ->
                usuarioService.get(request, response), new JSONTransformer());


        post("/users", (request, response) -> usuarioService.add(request, response));


        put("/users", (request, response) -> usuarioService.update(request, response));

        delete("/users/:id", (request, response) -> usuarioService.delete(request, response));


        get("/products", "application/json", (request, response) ->
                produtoService.getAll(request, response), new JSONTransformer());

        get("/products/:id", "application/json", (request, response) ->
                produtoService.get(request, response), new JSONTransformer());


        post("/products", (request, response) -> produtoService.add(request, response));

        put("/products", (request, response) -> produtoService.update(request, response));

        delete("/products/:id", (request, response) -> produtoService.delete(request, response));


    }
}
