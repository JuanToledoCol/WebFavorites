package com.misiontic.webfavorites.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misiontic.webfavorites.entity.Usuario;
import com.misiontic.webfavorites.exceptions.GeneralServiceException;
import com.misiontic.webfavorites.exceptions.NoDataFoundException;
import com.misiontic.webfavorites.exceptions.ValidateServiceException;
import com.misiontic.webfavorites.repository.UsuarioRepository;
import com.misiontic.webfavorites.validators.UsuarioValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuRepo;

	public List<Usuario> findAll(){
		try {
			List<Usuario> usuarios = usuRepo.findAll();
			return usuarios;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public Usuario findById(Long idUsuario) {
		try {
			Usuario usuario = usuRepo.findById(idUsuario).orElseThrow(() -> new NoDataFoundException("El usuario no existe"));
			return usuario;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void delete(Long idUsuario) {
		try {
			Usuario usuario = usuRepo.findById(idUsuario).orElseThrow(() -> new NoDataFoundException("El usuario no existe"));
			usuRepo.delete(usuario);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public Usuario save(Usuario usuario) {
		try {
			UsuarioValidator.save(usuario);

			if(usuario.getIdUsuario() == null) {
				Usuario usuarioN = usuRepo.save(usuario);
				return usuarioN;
			}

			Usuario usuarioUp = usuRepo.findById(usuario.getIdUsuario()).orElseThrow(() -> new NoDataFoundException("El usuario no existe"));

			usuarioUp.setIdRol(usuario.getIdRol());
			usuarioUp.setNombre(usuario.getNombre());
			usuarioUp.setApellido(usuario.getApellido());
			usuarioUp.setCorreo(usuario.getCorreo());
			usuarioUp.setUsuario(usuario.getUsuario());
			usuarioUp.setPass(usuario.getPass());

			usuRepo.save(usuarioUp);

			return usuarioUp;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
