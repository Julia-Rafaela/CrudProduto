package br.edu.fateczl.CrudProduto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.CrudProduto.model.Produto;
import br.edu.fateczl.CrudProduto.persistence.GenericDao;
import br.edu.fateczl.CrudProduto.persistence.ProdutoDao;

@Controller
public class ProdutoController {

	@Autowired
	GenericDao gdao;

	@Autowired
	ProdutoDao pdao;

	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
	public ModelAndView produtoGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		  String cmd = allRequestParam.get("cmd");
		    String codigo = allRequestParam.get("codigo");

		    if (cmd != null) {
		        Produto p = new Produto();
		        String saida = "";
		        String erro = "";
		        List<Produto> produtos = new ArrayList<>();

		        try {
		            if (cmd.contains("alterar")) {
		                if (codigo != null) {
		                    p.setCodigo(Integer.parseInt(codigo));
		                    p = buscarProduto(p);
		                } else {
		                    erro = "Código de Produto não fornecido.";
		                }
		            } else if (cmd.contains("excluir")) {
		                if (codigo != null) {
		                    p.setCodigo(Integer.parseInt(codigo));
		                    excluirProduto(p);
		                    saida = "Produto excluído com sucesso!";
		                    p = null;
		                } else {
		                    erro = "Código de Produto não fornecido.";
		                }
		            }
		            produtos = listarProdutos();
		        } catch (SQLException | ClassNotFoundException e) {
		            erro = e.getMessage();
		        } finally {
		            model.addAttribute("saida", saida);
		            model.addAttribute("erro", erro);
		            model.addAttribute("produto", p);
		            model.addAttribute("produtos", produtos);
		        }
		}
		return new ModelAndView("produto");
	}

	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.POST)
	public ModelAndView produtoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		// entrada
		String cmd = allRequestParam.get("botao");
		String codigo = allRequestParam.get("codigo");
		String nome = allRequestParam.get("nome");
		String valorUnitario = allRequestParam.get("valorUnitario");
		String quantidade = allRequestParam.get("quantidade");

		// saida
		String saida = "";
		String erro = "";
		Produto p = new Produto();
		List<Produto> produtos = new ArrayList<>();

		if (!cmd.contains("Listar")) {
			if (codigo != null) {
				p.setCodigo(Integer.parseInt(codigo));
			} else {
				erro = "Código de Produto não fornecido.";
			}
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				p.setNome(nome);
				p.setValorUnitario(Float.parseFloat(valorUnitario));
			
			// Verificação de nulidade antes da conversão
			if (quantidade != null) {
				p.setQuantidade(Integer.parseInt(quantidade));
			}
		}
		try {
			if (cmd.contains("Cadastrar")) {
				saida = cadastrarProduto(p);
				p = null;
			}
			if (cmd.contains("Alterar")) {
				saida = alterarProduto(p);
				p = null;
			}
			if (cmd.contains("Excluir")) {
				saida = excluirProduto(p);
				p = null;
			}
			if (cmd.contains("Buscar")) {
				if (codigo != null) {
					p.setCodigo(Integer.parseInt(codigo));
					p = buscarProduto(p);
				} else {
					erro = "Código de funcionário não fornecido.";
				}
			}
			if (cmd.contains("Listar")) {
				produtos = listarProdutos();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("produto", p);
			model.addAttribute("produtos", produtos);
		}

		return new ModelAndView("produto");
	}

	private String cadastrarProduto(Produto p) throws SQLException, ClassNotFoundException {
		String saida = pdao.iudProduto("I", p);
		return saida;

	}

	private String alterarProduto(Produto p) throws SQLException, ClassNotFoundException {
		String saida = pdao.iudProduto("U", p);
		return saida;

	}

	private String excluirProduto(Produto p) throws SQLException, ClassNotFoundException {
		Float valorUnitario = (p.getValorUnitario() != null) ? p.getValorUnitario().floatValue() : 0.0f;
		p.setValorUnitario(valorUnitario);
		String saida = pdao.iudProduto("D", p);
		return saida;
	}

	private Produto buscarProduto(Produto p) throws SQLException, ClassNotFoundException {
		p = pdao.consultar(p);
		return p;

	}

	private List<Produto> listarProdutos() throws SQLException, ClassNotFoundException {
		List<Produto> professores = pdao.listar();
		return professores;
	}

}