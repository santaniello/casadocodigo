package casadocodigo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.conf.JPATestConfiguration;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.repositories.ProdutoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPATestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository repo;

	@Test
	public void testSaveProduto() {
		Produto p = new Produto();
		p.setTitulo("teste");
		p.setDescricao("teste");
		p.setPaginas(333);
		Produto save = repo.save(p);
		System.out.println(save.getId());
	}

}
