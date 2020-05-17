package DAO;

import Classes.Produto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProdutoDAOTest {
    public ProdutoDAO prodDao;
    public Produto produto;
    public  Produto produtoDois;
    public Produto produtoTres;

    @BeforeAll
    public void init() throws IOException {
         this.prodDao = new ProdutoDAO("produtos_testes.bin");
         this.produto = new Produto(1, "Iphone X", "21/04/2019","Iphone Novo em otimas condiçoes de uso" ,"Eletronico", "Usado", "Belo Horizonte", "31-99203-1067", 1299);
         this.produtoDois = new Produto(2, "SamsungGalaxy", "21/04/2022","SmartFone Novo em otimas condiçoes de uso" ,"Eletronico", "Novo", "Juiz de Fora", "31-90000-1067", 1400);
         this.produtoTres = new Produto(3, "Notebook Accer", "21/04/2022","Notebook Gamer I5 Setima geraçao" ,"Eletronico", "Novo", "Belo Horizonte", "31-99203-1067", 3400);

         this.prodDao.add(produto);
         this.prodDao.add(produtoDois);
         this.prodDao.add(produtoTres);
    }

    @org.junit.jupiter.api.Test
    void add() {
        Produto produtoNovo = new Produto(5, "Impressora 3D", "21/04/2019","Impressora 3D com 6 meses de Uso" ,"Eletronico", "Usado", "Belo Horizonte", "31-99203-1067", 1299);
        prodDao.add(produtoNovo);
        assertEquals("Impressora 3D", prodDao.get(5).getNomeProduto());

    }

    @org.junit.jupiter.api.Test
    void getAll() {

        List<Produto> Lista = new ArrayList<Produto>();

        Lista.add(this.produto);
        Lista.add(this.produtoDois);
        Lista.add(this.produtoTres);

        for (Produto prod : prodDao.getAll()) {
            assertTrue(Lista.contains(prod));
        }

    }

    @org.junit.jupiter.api.Test
    void update() {

        this.produto.setPrecoProduto(2000);

        prodDao.update(this.produto);

        System.out.println( prodDao.get(1).getPrecoProduto());
        assertEquals(2000,  prodDao.get(1).getPrecoProduto());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        prodDao.remove(produtoTres);

        List<Produto> Lista = new ArrayList<Produto>();
        Lista = prodDao.getAll();

        assertFalse(Lista.contains(produtoTres));

    }
}