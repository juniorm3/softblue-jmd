package br.com.softblue.bluefood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

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
			//TODO: Upload!
		}
		
	}

	private boolean validadeEmail(String email, Integer id) {

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
}
