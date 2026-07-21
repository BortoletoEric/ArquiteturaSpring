package io.github.bortoletoeric.arquiteturaspring.montadora.api;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação customizada para qualificar a injeção de um motor elétrico.
 *
 * <p>Esta anotação é um @Qualifier que referencia o Bean "motorEletrico".
 * Pode ser usada em fields ou método-parâmetros para indicar
 * qual implementação de Motor deve ser injetada.</p>
 *
 * <p>Uso:</p>
 * <pre>
 *   {@code
 *   @Autowired
 *   @Eletrico
 *   private Motor motor;
 *   }
 * </pre>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see Turbo
 * @see Aspirado
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Qualifier("motorEletrico")
public @interface Eletrico {
}
