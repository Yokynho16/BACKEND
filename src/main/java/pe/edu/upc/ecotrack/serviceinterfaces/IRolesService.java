package pe.edu.upc.ecotrack.serviceinterfaces;

import pe.edu.upc.ecotrack.entities.Roles;

import java.util.List;

public interface IRolesService {
    public List<Roles> list();

    public void insert(Roles roles);

    public Roles listId(int id);

    public void update(Roles roles);

    public void delete(int id);
    public List<String[]>UsuariosPorRolDTO();

}
