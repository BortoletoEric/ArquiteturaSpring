package io.github.bortoletoeric.arquiteturaspring.montadora.api;

import io.github.bortoletoeric.arquiteturaspring.montadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller para testar a fábrica de carros.
 * 
 * <p>Fornece endpoints para criar e ligar veículos. Demonstra:</p>
 * <ul>
 *   <li>Injeção de dependência com @Autowired</li>
 *   <li>Uso de qualificadores customizados (@Turbo)</li>
 *   <li>REST endpoints com @PostMapping</li>
 *   <li>Manipulação de objetos injetados pelo Spring</li>
 * </ul>
 * 
 * <p>Endpoints:</p>
 * <ul>
 *   <li>POST /carros - Cria um HondaHRV e tenta ligar com a chave fornecida</li>
 * </ul>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Motor
 * @see HondaHRV
 * @see Turbo
 */
@RestController
@RequestMapping("/carros")
public class TesteFabricaController {

    /**
     * Motor injetado com qualificador @Turbo.
     * 
     * <p>Será uma instância do motor turbo definido em MontadoraConfiguration.</p>
     */
    @Autowired
    @Turbo
    private Motor motor;

    /**
     * Cria um HondaHRV com o motor injetado e tenta ligá-lo.
     * 
     * <p>Recebe uma chave no corpo da requisição e tenta ligar o veículo.
     * A ignição terá sucesso apenas se a chave for da montadora Honda.</p>
     * 
     * @param chave a chave do carro enviada no corpo da requisição JSON
     * @return um CarroStatus indicando o resultado da operação de ignição
     */
    @PostMapping
    public CarroStatus ligarCarro(@RequestBody Chave chave) {
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }

}
