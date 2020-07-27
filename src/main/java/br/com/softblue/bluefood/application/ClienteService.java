package br.com.softblue.bluefood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void saveCliente(Cliente cliente) throws ValidationException {

		if (!validadeEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidationException("O e-mail esta duplicado!");
		}

		clienteRepository.save(cliente);
	}

	private boolean validadeEmail(String email, Integer id) {

		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente != null) {
			if (id == null) {
				return false;
			}

			if (!cliente.getId().equals(id)) {
				return false;
			}
		}

		return true;
	}
}
