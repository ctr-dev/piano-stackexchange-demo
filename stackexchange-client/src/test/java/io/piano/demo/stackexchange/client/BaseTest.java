package io.piano.demo.stackexchange.client;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    @BeforeAll
    final void defaultInit() {
        MockitoAnnotations.openMocks(this);
        init();
    }

    protected void init() {
        // Overridable
    }

    @SneakyThrows
    @SuppressWarnings("SameParameterValue")
    protected static byte[] readResource(String resourcePath) {
        try (InputStream stream = BaseTest.class.getClassLoader().getResourceAsStream(resourcePath)) {
            return Objects.requireNonNull(stream).readAllBytes();
        }
    }

}
