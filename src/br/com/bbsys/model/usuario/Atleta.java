package br.com.bbsys.model.usuario;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Atleta{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idatleta;
	private Date dataCadastroAtleta;	
	
}
