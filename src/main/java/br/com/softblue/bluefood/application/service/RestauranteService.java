package br.com.softblue.bluefood.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter.SearchType;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ImageService imageService;

	@Transactional
	public void saveRestaurante(Restaurante restaurante) throws ValidationException {
		if (!validadeEmail(restaurante.getEmail(), restaurante.getId())) {
			throw new ValidationException("O e-mail esta duplicado!");
		}

		if (restaurante.getId() != null) {
			Restaurante clienteDB = restauranteRepository.findById(restaurante.getId()).orElseThrow();
			restaurante.setSenha(clienteDB.getSenha());

		} else {
			restaurante.encryptPassword();
			restaurante = restauranteRepository.save(restaurante);
			restaurante.setLogotipoFileName();
			imageService.uploadLogotipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());
		}

	}

	private boolean validadeEmail(String email, Integer id) {
		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente != null) {
			return false;
		}

		Restaurante restaurante = restauranteRepository.findByEmail(email);

		if (restaurante != null) {
			if (id == null) {
				return false;
			}

			if (!restaurante.getId().equals(id)) {
				return false;
			}
		}

		return true;
	}
	
	public List<Restaurante> search(SearchFilter filter){
		List<Restaurante> restaurantes = new ArrayList<>();

		if(filter.getSearchType() == SearchType.Texto) {
			restaurantes = restauranteRepository.findByNomeIgnoreCaseContaining(filter.getTexto());
			
		} else if(filter.getSearchType() == SearchType.Categoria) {
			restaurantes = restauranteRepository.findByCategorias_Id(filter.getCategoriaId());
			
		} else {
			throw new IllegalStateException("O tipo de busca " + filter.getSearchType() + " não é suportado");
		}
		
		return restaurantes;
	}
}
