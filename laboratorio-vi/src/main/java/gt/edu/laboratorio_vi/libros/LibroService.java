package gt.edu.laboratorio_vi.libros;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final List<Libro> libros = new ArrayList<>();
    private Long siguienteId = 1L;

    public List<Libro> obtenerTodos() {
        return libros;
    }

    public Libro obtenerPorTitulo(String titulo) {
        return libros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public Libro crear(Libro libro) {
        libro.setId(siguienteId++);
        libros.add(libro);
        return libro;
    }

    public Libro actualizar(Long id, Libro datos) {
        Libro libro = buscarPorId(id);

        if (libro == null) {
            return null;
        }

        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setIsbn(datos.getIsbn());
        libro.setAnioPublicacion(datos.getAnioPublicacion());
        libro.setEstado(datos.getEstado());

        return libro;
    }

    public boolean eliminar(Long id) {
        return libros.removeIf(libro -> libro.getId().equals(id));
    }

    private Libro buscarPorId(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}