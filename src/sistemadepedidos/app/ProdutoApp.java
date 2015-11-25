package sistemadepedidos.app;

import java.util.List;
import sistemadepedidos.dominio.Produto;
import sistemadepedidos.exceptions.CampoObrigatorioNaoPreenchidoException;
import sistemadepedidos.exceptions.PrecoZeradoOuNegativoException;
import sistemadepedidos.exceptions.RegistroJaExisteException;
import sistemadepedidos.repositorio.ProdutoDao;

/**
 * Classe responsável pelas lógicas de negócio dos Produtos
 *
 * @author LuizRicardo
 */
public class ProdutoApp {

    /**
     * Método que cria um Produto
     *
     * @param produto Produto
     */
    public void Criar(Produto produto) {
        try {
            //Validar o cliente
            Validar(produto);

            //Verificar se o produto já existe pela descrição
            if (RegistroJaExiste(produto.getDescricao())) {
                throw new RegistroJaExisteException("Já existe um Produto com esta Descrição.");
            }

            //Instanciar a camada Dao
            ProdutoDao produtoDao = new ProdutoDao();

            //Cria o Produto
            produtoDao.Criar(produto);
        } catch (CampoObrigatorioNaoPreenchidoException conpe) {
            throw conpe;
        }
    }

    /**
     * Método que edita um Produto
     *
     * @param produto Produto
     */
    public void Editar(Produto produto) {
        try {
            //Validar o Produto
            Validar(produto);

            //Instanciar a camada Dao
            ProdutoDao produtoDao = new ProdutoDao();

            //Edita o Produto
            produtoDao.Editar(produto);
        } catch (CampoObrigatorioNaoPreenchidoException conpe) {
            throw conpe;
        }
    }

    /**
     * Método que encontra um Produto
     *
     * @param codigo Código
     * @return Produto
     */
    public Produto Encontrar(int codigo) {
        try {
            //Instanciar a camada Dao
            ProdutoDao produtoDao = new ProdutoDao();

            //Encontrar o Produto e retornar
            return produtoDao.Encontrar(codigo);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que lista os Produtos
     *
     * @return Lista de Produtos
     */
    public List<Produto> Listar() {
        try {
            //Instanciar a camada Dao
            ProdutoDao produtoDao = new ProdutoDao();

            //Listar os Produtos e retornar
            return produtoDao.Listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que remove o Produto pelo Código
     *
     * @param codigo Código
     */
    public void Remover(int codigo) {
        try {
            //Instanciar a camada Dao
            ProdutoDao produtoDao = new ProdutoDao();

            //Remover o Produto e retornar
            produtoDao.Remover(codigo);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que valida um Produto
     *
     * @param produto produto
     */
    public void Validar(Produto produto) {
        //Valdiar Descrição
        if (produto.getDescricao().equals("")) {
            throw new CampoObrigatorioNaoPreenchidoException("O campo Descrição é obrigatório.");
        }

        //Validar o Valor do Produto
        if (produto.getPreco() <= 0) {
            throw new PrecoZeradoOuNegativoException("O Preço deve ser maior que zero.");
        }
    }

    /**
     * Método que verifica se um regitro já existe ou não pela descrição
     *
     * @param descricao Descrição
     * @return True or False
     */
    private boolean RegistroJaExiste(String descricao) {
        try {
            //Instanciar a camada Dao
            ProdutoDao produtoDao = new ProdutoDao();

            //Retornar se o registro existe ou não
            return produtoDao.RegistroJaExiste(descricao);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
