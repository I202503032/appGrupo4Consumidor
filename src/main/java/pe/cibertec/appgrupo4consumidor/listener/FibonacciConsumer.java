package pe.cibertec.appgrupo4consumidor.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.cibertec.appgrupo4consumidor.service.FibonacciService;

import java.util.Arrays;
import java.util.List;

@Component
public class FibonacciConsumer {

    private final FibonacciService fibonacciService;

    public FibonacciConsumer(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @RabbitListener(queues = "Grupo4Queue")
    public void recibirMensaje(String mensaje) {

        System.out.println("Mensaje recibido: " + mensaje);

        List<Integer> posiciones = Arrays.stream(mensaje.split(";"))
                .map(Integer::parseInt)
                .toList();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<Long> resultado = fibonacciService.calculateSequence(posiciones);

        System.out.println("Resultado Fibonacci: " + resultado);
    }
}