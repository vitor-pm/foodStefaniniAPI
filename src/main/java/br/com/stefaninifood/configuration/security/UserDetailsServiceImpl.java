package br.com.stefaninifood.configuration.security;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente = repository.findByEmail(username);
        cliente.orElseThrow(() -> new UsernameNotFoundException("Email: " + username + " não encontrado."));

        return cliente.map(UserDetailsImpl::new).get();
    }
}
