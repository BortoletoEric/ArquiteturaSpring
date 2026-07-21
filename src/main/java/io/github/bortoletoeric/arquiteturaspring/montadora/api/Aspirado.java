package io.github.bortoletoeric.arquiteturaspring.montadora.api;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação customizada para qualificar a injeção de um motor aspirado.
 * 
 * <p>Esta anotação é um @Qualifier que referencia o Bean "motorAspirado".
 * Pode ser usada em fields ou método-parâmetros para indicar
 * qual implementação de Motor deve ser injetada.</p>
 * 
 * <p>Uso:</p>
 * <pre>
 *   {@code
 *   @Autowired
 *   @Aspirado
 *   private Motor motor;
 *   }
 * </pre>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Turbo
 * @see Eletrico
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Qualifier("motorAspirado")
public @interface Aspirado {
}
