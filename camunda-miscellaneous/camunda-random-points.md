# Miscellaneous topics

- In a BPMN process what is the best component for an external HTTP call?
  - A: REST API Outbound connector

- How to filter a second table with the value of the first table in DMN?
  - A: Have to create Decision Requirements Diagram (DRD) where the output of the first table is used on the second table
the two tables must be linked with the arrow flow of the decision

- What is the API for client creation?
  - A: Administration API, it provides a programmatic interface for managing camunda clusters and API clients. Offers endpoints
for cluster backup, creation and deletion as well as client and member management

- Optimize can receive data from external databases?
  - A: Optimize do not make direct external queries, but external data can be imported into optimize

- In a worker scenario, how to send two different errors, one should throw exception and the other should let a retry for 3x?
  - A: For the error that will retry 3x, a ``failJob`` command is needed, and for an immediate error to be thrown a ``throwError`` command is needed

- In a screen with a dynamic list that can be changed, items can be added or removed, how to show the consolidated result at the end of the form?
  - A: Create a text field with a FEEL expression that summarize the items quantity multiplied by the value. ex: ``{{sum(dynamicList[quantity*value])}}``  

- Given the following input: ``JSON { contact:{[name,phone][name, phone]}}`` what is the best way to show it on screen? Considering it can be changed.
  - A: Using a dynamic list component of camunda forms

- After creating a connector template, how to use it in a BPMN?
  - A: The connector has to be published to organization

- After some job worker lifetime passed the frequency of timeout errors increased, how to solve?
  - A: Best option is to increase the job worker threads, because the job worker accumulate a big backlog of pending jobs.
Other solutions can be, increase the timeout duration (maybe this solution will mask the real problem) 
and adjust the poll interval (if the worker is spending too much time polling jobs)

- When a job worker returns an error and still has retries, what happens?
  - A: As the worker still has a retry, it will retry the failed job, zeebe will decrease the numbers of retries and 
return the job the to queue. In the queue, the job will be picked by any worker that is available of the same type of the job
not necessarily by the same worker that picked it before.





# Translate and correct after...


- caiu uma quetão muito parecida com a do simulado sobre a comunicação entre zeebe e worker... zeebe joga na fila, worker consome a fila

- tenho 3 processos, como "solicitação", "aprovação" e "entrega". Apesar de o fluxo padrão ser sempre a maioria dos casos, o processo do meio pode ter um caminho mais complexo baseado nas regras de negocio e nas variaveis, qual a melhor forma de desenvolver esse caminho "extra"? paralel gateway, call activity, adhoc subprocess

- você ensinou novos colaboradores sobre exclusive gateway e inclusive gateway, qual a anotação abaixo que está correta?
  - o fluxo default sempre vai ser executado em um inclusive gateway?
  - o fluxo default vai ser executado somente quando nenhum outro for verdadeiro?
  - não lembro as outras opções

- uma lista de dados retirados de um documento vai passar por varias Decision Tables, cada uma vai validar os dados e tendo palavras chave, vai setar um score, esse score vai ser usado pra definir qual area deve atuar na tarefa. Por exemplo, se tiver a palavra "invoice" um score 0.5 é adicionado para a area XPTO, se tiver a palavra "blabla", um score 1 é adicionado para a área XYZ... qual a hit policy deve ser definida para saber o score de cada area?
  - collect(sum) (selecionei essa)
  - collect (max)
    não lembro mais outras

- boas praticas de uso de terminate. Um terminate cancela o processo pai de call activity? de event subprocess?

- tendo um processo com gateway paralelo, onde um caminho sempre fica em looping e o outro chega a um fim, qual a melhor forma de encerrar o processo?
  - selecionei "terminate event"

- preciso de variaveis especificas dentro de um event subprocess
  - preciso mapear no input?
  - elas vem automatico, não preciso fazer nada?
  - devo enviar por message?
  - devo criar um service task dentro do sub-processes para buscar os dados da tarefa pai?

- tenho processo "red", "yellow" e "gree", todos parados em um ecento intermediario de signal. Um processo externo envia um signal, depois envia novamente, depois um processo "blue" chega com o token em um evento signal também, o que acontece?
  - o primeiro sinal dispara red, yellow e green, o segundo sinal não faz nada, o token em blue vai ficar esperando um terceiro sinal?
  - o primeiro sinal dispara somente red, o segundo somente yellow, green e blue vao ficar esperando
  - o primeiro sinal dispara red, yellow e green, o segundo sinal não faz nada, quando o blue chegar no evento intermediario vai disparar por ter um sinal sem ser consumido

- qual melhor forma de garantir uma tarefa que o cliente visualise dados especificos, manual task, send message task, user task?

- 4 ou 5 questões de feel, gastei tempo demais nelas

- se vc nao quer que os service task tenham retry, qual propriedade nos out-of-the-box connectors a camunda te fornece para ajustar isso? (sinceramente aqui ja to confuso se nao to misturndo duas perguntas)

- voce precisa mapear o fluxo do negocio baseado no status do retorno do connector . em qual campo se faz isso? Respose, result, error handling (selecionei essa pq lembrei disso https://docs.camunda.io/docs/components/connectors/use-connectors/#error-expression)

- no DRD você desenhou os inputs da sua Decision table para ficar mais facil a vizualização, qual a relação dos inputs do DRD com os inputs do Decision Table?
  - você precisa fazer um input na tabela pra cada input desenhado no DRD?
  - elas não tem relação na pratica, então você pode fazer o que quiser no DRD?
  - coisas assim