package service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.CarroDAO;
import model.Carro;
import spark.Request;
import spark.Response;


public class CarroService {

	private CarroDAO carroDAO;

	public CarroService() {
		carroDAO = new CarroDAO();
	}

	public Object add(Request request, Response response) throws ParseException {
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		//int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		java.sql.Date dataFabricacao =(java.sql.Date) new SimpleDateFormat("dd/MM/yyyy").parse(request.queryParams("dataFabricacao")); 
		//LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));

		//int id = carroDAO.getMaxId() + 1;

		Carro carro = new Carro(1, descricao, preco, dataFabricacao);

		carroDAO.inserirCarro(carro);

		response.status(201); // 201 Created
		return 1;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Carro carro = (Carro) carroDAO.getCarro(id);
		
		if (carro != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<carro>\n" + 
            		"\t<id>" + carro.getId() + "</id>\n" +
            		"\t<descricao>" + carro.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + carro.getPreco() + "</preco>\n" +
            		//"\t<quantidade>" + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + carro.getDataFabricacao() + "</fabricacao>\n" +
            		//"\t<validade>" + produto.getDataValidade() + "</validade>\n" +
            		"</carro>\n";
        } else {
            response.status(404); // 404 Not found
            return "Carro " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) throws ParseException {
        int id = Integer.parseInt(request.params(":id"));
        
		Carro carro = (Carro) carroDAO.getCarro(id);

        if (carro != null) {
        	carro.setDescricao(request.queryParams("descricao"));
        	carro.setPreco(Float.parseFloat(request.queryParams("preco")));
        	//carro.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	carro.setDataFabricacao((java.sql.Date) new SimpleDateFormat("dd/MM/yyyy").parse(request.queryParams("dataFabricacao")));
        	//carro.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	carroDAO.atualizarCarro(carro);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Produto não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

  
        if (id>0 ) {

           carroDAO.excluirCarro(id);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Carro não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<carros type=\"array\">");
		for (Carro carro : carroDAO.getCarros()) {
			returnValue.append("\n<carro>\n" + 
            		"\t<id>" + carro.getId() + "</id>\n" +
            		"\t<descricao>" + carro.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + carro.getPreco() + "</preco>\n" +
            		//"\t<quantidade>" + carro.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + carro.getDataFabricacao() + "</fabricacao>\n" +
            		//"\t<validade>" + carro.getDataValidade() + "</validade>\n" +
            		"</produto>\n");
		}
		returnValue.append("</carros>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}