package br.unb.deolhonoenade.Teste;

import android.test.AndroidTestCase;
import br.unb.deolhonoenade.model.Curso;
import br.unb.deolhonoenade.model.Instituicao;
import junit.framework.Assert;
import java.util.ArrayList;

public class TestInstituicao extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInstituicao() {
		Instituicao instituicao = new Instituicao("Universidade de Brasilia", "Universidades", "DF", "Publica", 2);
		Assert.assertNotSame("Quimica", instituicao.getNome());
	}
		
	public void testGetCursos() {
		ArrayList<Curso> Cursos = new ArrayList<Curso>(50);
		Instituicao instituicao = new Instituicao("Universidade Federal de Ouro Preto", "Universidades", "MG", "Publica", 6);
		
		instituicao.setCursos(Cursos);
		
		Assert.assertEquals(new ArrayList<Curso>(10), instituicao.getCursos());
	}

	public void testSetCursos() {
        Curso curso = new Curso(3, 6, "Direito", 25, 15, "Porto Alegre", (float)2.45, null);
 		curso.setNome("Adiministracao");
 		assertSame("Adiministracao", curso.getNome());
	}

	public void testGetNome() {
		Instituicao instituicao = new Instituicao("Universidade Federal do Amazonas", "Universidades", "AM", "Publica", 4);
		assertEquals("Universidade Federal do Amazonas",instituicao.getNome());
	}

	public void testSetNome() {
		Instituicao instituicao = new Instituicao("Universidade Catolica de Petropolis", "Universidades", "Distrito Federal", "Privada", 15);
 		
 		instituicao.setNome("Universidade Federal de Uberlandia");
 		assertSame("Universidade Federal de Uberlandia", instituicao.getNome());
	}


	public void testGetOrganizacaoAcademica() {
		Instituicao instituicao = new Instituicao("Universidade Estadual do Ceara", "Universidades", "CE", "Publica", 29);
		assertEquals("Universidades",instituicao.getOrganizacaoAcademica());
		
	}

	public void testSetOrganizacaoAcademica() {
		Instituicao instituicao = new Instituicao("Universidade Gama Filho", "Universidades", "RJ", "Privada", 16);
		instituicao.setOrganizacaoAcademica("Faculdades");
		assertSame("Faculdades", instituicao.getOrganizacaoAcademica());
	}

	public void testGetUf() {
		Instituicao instituicao = new Instituicao("Faculdade de Tecnologia Americana", "Faculdades", "SP", "Publica", 35);
		assertEquals("SP",instituicao.getUf());
		
	}

	public void testSetUf() {
		Instituicao instituicao = new Instituicao("Universidade do Estado do Para", "Universidades", "PA", "Publica", 38);
		instituicao.setUf("PA");
		assertSame("PA", instituicao.getUf());
	}

	public void testGetTipo() {
		Instituicao instituicao = new Instituicao("Universidade do Estado da Bahia", "Universidades", "BA", "Publica", 40);
		assertEquals("Publica",instituicao.getTipo());
	}

	public void testSetTipo() {
		Instituicao instituicao = new Instituicao("Universidade Estadual de Goias", "Universidades", "GO", "Publica", 47);
		instituicao.setTipo("Publica");
		assertNotSame("Privada", instituicao.getTipo());
	}

	public void testGetCodIES() {
		Instituicao instituicao = new Instituicao("Faculdade de Direito de Franca", "Faculdades", "SP", "Publica", 59);
		assertEquals(59, instituicao.getCodIES());
	}

	public void testSetCodIES() {
		Instituicao instituicao = new Instituicao("Universidade Cruzeiro do Sul", "Universidades", "SP", "Privada", 221);
		instituicao.setCodIES(221);
		assertEquals(221, instituicao.getCodIES());
	}

}
