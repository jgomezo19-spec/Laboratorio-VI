package gt.edu.laboratorio_vi.cursos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final List<Curso> cursos = new ArrayList<>();
    private Long siguienteId = 1L;

    public List<Curso> obtenerTodos() {
        return cursos;
    }

    public Curso obtenerPorCodigo(String codigo) {
        return cursos.stream()
                .filter(curso -> curso.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public Curso crear(Curso curso) {
        curso.setId(siguienteId++);
        cursos.add(curso);
        return curso;
    }

    public Curso actualizar(Long id, Curso datos) {
        Curso curso = buscarPorId(id);

        if (curso == null) {
            return null;
        }

        curso.setNombre(datos.getNombre());
        curso.setCodigo(datos.getCodigo());
        curso.setCreditos(datos.getCreditos());
        curso.setEstado(datos.getEstado());

        return curso;
    }

    public boolean eliminar(Long id) {
        return cursos.removeIf(curso -> curso.getId().equals(id));
    }

    private Curso buscarPorId(Long id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}