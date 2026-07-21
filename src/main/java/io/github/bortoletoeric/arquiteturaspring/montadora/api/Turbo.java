package io.github.bortoletoeric.arquiteturaspring.montadora.api;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação customizada para qualificar a injeção de um motor turbo.
 *
 * <p>Esta anotação é um @Qualifier que referencia o Bean "motorTurbo".
 * Pode ser usada em fields ou método-parâmetros para indicar
 * qual implementação de Motor deve ser injetada.</p>
 *
 * <p>Uso:</p>
 * <pre>
 *   {@code
 *   @Autowired
 *   @Turbo
 *   private Motor motor;
 *   }
 * </pre>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see Aspirado
 * @see Eletrico
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Qualifier("motorTurbo")
public @interface Turbo {
}
