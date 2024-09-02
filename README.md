## Project

Este projeto Java tem como objetivo distribuir protocolos de atendimento de clientes para três diferentes times, 
cada um contendo um ou mais atendentes. Cada atendente pode atender até três protocolos simultaneamente. 
A lógica do projeto permite que cada time possua quantos atendentes forem necessários.

O projeto foi construído de tal modo que cada instância de atendente (representado pela classe Atendente) 
instancie como dependência um ThreadPoolExecutor com, no máximo, 3 threads paralelas. Isso permite que cada
Atendente processe até 3 solicitações simultâneas. Em caso de chegada alguma solicitação e todos os atendentes do time estiverem ocupados,
essa solicitação irá para uma fila (queue, FIFO) do time. Quando algum dos atendentes ficar com menos 3 atendimentos simultâneos a aplicação
automaticamente puxa da fila uma solicitação para ser processada pelo atendente que está com menos de 3 atendimentos.

Foi usado ExecutorService para que o processo se dê de modo assíncrono e o ciclo de vida das threads seja gerenciado pela 
própria API de concorrência do Java. 

Foi feita uma simulação de processamento da solicitação com `TimeUnit.SECONDS.sleep(10)` e também o processamento da fila (Queue do Java).


## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/releases/)

