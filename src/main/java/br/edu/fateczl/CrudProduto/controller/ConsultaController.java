package br.edu.fateczl.CrudProduto.controller;

import java.sql.SQLException;
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
public class ConsultaController {

    @Autowired
    GenericDao gDao;

    @Autowired
    ProdutoDao pDao;


    @RequestMapping(name = "consulta", value = "/consulta", method = RequestMethod.GET)
	public ModelAndView indexGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		return new ModelAndView("consulta");
	}

    @RequestMapping(name = "consulta", value = "/consulta", method = RequestMethod.POST)
    public ModelAndView dependentePost(@RequestParam("valorEntrada") int valorEntrada, ModelMap model) {
        return realizarConsulta(valorEntrada, model);
    }

    private ModelAndView realizarConsulta(int valorEntrada, ModelMap model) {
        String erro = "";
        List<Produto> produtos = null;
        
        int quantidade = 0;
        
        try {
            produtos = listarProdutos(valorEntrada);
            quantidade = calcularQtd(valorEntrada);
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage();
        }
        
        model.addAttribute("erro", erro);
        model.addAttribute("produtos", produtos);
        model.addAttribute("qtdEstoque",quantidade); 
        
        return new ModelAndView("consulta", model);
    }

    private List<Produto> listarProdutos(int valor) throws SQLException, ClassNotFoundException {
        return pDao.listarProduto(valor);
    }
    
    private int calcularQtd(int QtdProdutos) throws SQLException, ClassNotFoundException {
        return pDao.calcularQtd(QtdProdutos);
    }
}

