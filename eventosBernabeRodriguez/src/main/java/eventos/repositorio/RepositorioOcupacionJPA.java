package eventos.repositorio;

import eventos.modelo.Ocupacion;

public class RepositorioOcupacionJPA extends RepositorioJPA<Ocupacion>{

	@Override
	public Class<Ocupacion> getClase() {
		return Ocupacion.class;
	}

}
