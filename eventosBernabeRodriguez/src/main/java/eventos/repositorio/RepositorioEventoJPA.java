package eventos.repositorio;

import eventos.modelo.Evento;

public class RepositorioEventoJPA extends RepositorioJPA<Evento> {

	@Override
	public Class<Evento> getClase() {
		return Evento.class;
	}
}
