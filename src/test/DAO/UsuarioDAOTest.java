package DAO;

import Classes.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioDAOTest {

    public UsuarioDAO userDAO;
    public Usuario usuarioUm;
    public Usuario usuarioDois;
    public Usuario usuarioTres;

    @BeforeAll
    public void init() throws IOException {
        this.userDAO = new UsuarioDAO("usuarios_teste.bin");
        this.usuarioUm = new Usuario(1, "Gabriel Moura", "11606499602", "gabrielmoura.music@gmail.com", "31-99203-1067");
        this.usuarioDois = new Usuario(2, "Clayson", "9998291381", "clayson.music@gmail.com", "31-99999-1067");
        this.usuarioTres = new Usuario(3, "Robertson", "12323123213", "Robertson.music@gmail.com", "31-123233-1067");

        userDAO.add(usuarioUm);
        userDAO.add(usuarioDois);
        userDAO.add(usuarioTres);
    }

    @Test
    void add() {
        Usuario usuarioNovo = new Usuario(4, "Gabriel Henrique", "11602499602", "danilogentily.music@gmail.com", "31-9923203-1067");
        userDAO.add(usuarioNovo);
        try {
            assertEquals("Gabriel Henrique", userDAO.get(1).getNome());
        } catch (AssertionError e) {
            System.out.println("Usuario Incorreto");
        } finally {
            assertEquals("Gabriel Henrique", userDAO.get(4).getNome());
            System.out.println("Usuario Correto!");
        }
    }

    @Test
    void getAll() {
        List<Usuario> Lista = new ArrayList<Usuario>();

        Lista.add(this.usuarioUm);
        Lista.add(this.usuarioDois);
        Lista.add(this.usuarioTres);

        for (Usuario user : userDAO.getAll()) {
            assertTrue(Lista.contains(user));
        }

        System.out.println("Todos os Usuarios confirmados");
    }

    @Test
    void update() {
        this.usuarioUm.setEmail("gabrielkira33@gmail.com");

        this.userDAO.update(this.usuarioUm);
        assertEquals("gabrielkira33@gmail.com", userDAO.get(1).getEmail());
        System.out.println("Email Alterado!");
    }

    @Test
    void remove() {
        userDAO.remove(usuarioTres);

        List<Usuario> Lista = new ArrayList<Usuario>();
        Lista = userDAO.getAll();

        assertFalse(Lista.contains(usuarioTres));
        System.out.println("usuario removido");
    }
}