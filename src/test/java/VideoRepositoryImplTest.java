
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.service.CultureMediaService;
import culturemedia.service.impl.CultureMediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;

import java.util.List;
import java.util.function.BooleanSupplier;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class VideoRepositoryImplTest {

    // Declaraciones de mocks y el objeto bajo prueba (SUT)

    @BeforeEach
    void setup() {
        // Inicializar mocks antes de cada prueba
    }

    @Test
    void testEjemplo() {
        // Ejemplo de prueba
    }

    @Mock
    private VideoRepository mockRepository;

    @InjectMocks
    private CultureMediaServiceImpl videoService; // Clase que usa VideoRepository

    @Test
    void testGuardarVideo() {
        // Configurar comportamiento del mock
        Video video = new Video("Título", "URL", "Descripción");
        when(mockRepository.guardar(video)).thenReturn(true);

        // Llamar al método real
        List<Video> resultado = videoService.save(video);

        // Verificar el resultado
        assertTrue((BooleanSupplier) resultado);

        // Verificar interacción con el mock
        verify(mockRepository).guardar(video);
    }

}