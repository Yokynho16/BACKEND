package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Roles;
import pe.edu.upc.ecotrack.repositories.IRolesRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.IRolesService;

import java.util.List;

@Service
public class RolesServiceImplement implements IRolesService {
    @Autowired
    private IRolesRepository rR;

    @Override
    public List<Roles> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Roles roles) {
        rR.save(roles);
    }

    @Override
    public Roles listId(int id) {
        return rR.findById(id).orElse(new Roles());
    }

    @Override
    public void update(Roles roles) {
        rR.save(roles);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public List<String[]> UsuariosPorRolDTO() {
        return rR.UsuariosPorRolDTO();
    }
}
