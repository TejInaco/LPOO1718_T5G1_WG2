A segunda parte do projecto incide sobre um jogo onde o user controlará um carro numa estrada, onde evita colisões com outros
carros ou outros objectos numa estrada com 3 vias. Quanto mais rápido percorrer a via maior sem colisões maior é o seu score. 
O jogo será em 2D com top view.

## Architecture Design

  #### Package and UML
    
  O projecto utilizará a framework libGDX com foco no desenvolvimento em AndroidSDK e em Desktop.Utilizirá o package Physics
  Utilizou-se o diagram UML proposto pelo Prof André Restivo, onde se pretende acrescentar as classes especificas do jogo.
  
  ![UML](UMLvelesc.png)
 
  
  
  #### Design of behavioural aspects (Maquina de Estados)
//Maquinas de estados

»os desenhos das maquinas de estado

  #### Expected Design Patterns 
Esperamos implentar o MVC com a seguinte estruturação:

 [MVC](mvc.png)

## GUI Design

   ### Main functionalities
   
   Haverá um interface com o menu inicial onde se pode iniciar o jogo, ver scores e escolher os níveis de dificuldade.
   O controlo do carro é definido com as teclas, UP, DOWN, LEFT e RIGHT para o controlo de velocidade e de direcções     respectivamente.

  ### GUI mock-ups
  
  Foi utlizado o sofware Tiled para gerar o mapa do jogo e os objectos estáticos dentro do mapa.
  
  ## Test Design
  
  ### Test cases
  No android
  1)inclinação do ecrã para a direita, o carro vira para a direita
  2)inclinação do ecrã para a esquerda, o carro vira para a esquerda
  3) Teste dos inputs das teclas para modificação da velocidade e direcção do carro
  4) Se colisao com outro veiculo tira pontos
  5) Se colisao entre player 1 e player 2 nao tira pontos mas tem efeitos fisicos
  6) Se frente/trás altera velocidade como deve
  7) Se atropelar diminui vidas.
  8) Se atingir caes ou gator diminui pontos
  9) Se bonus de vidas/pontos produz o efeito desejado
  10) Se colisao acusa
  11) Se escolha no menu altera para menu certo
Se escolho modo muda o modo
Se lombas com excesso diminyi velocidade e pontos e vibra no Android
Se multiplayer preenche ambas estruturas e cada im no certo
Se nao vira com pisca mas vira com pisca e so para esse lado
Se passadeiras, peoes e animais aleatorios na aparicao
Se pisca .muda sprite
Se pontos maiores que guardados sao guardados e so esses
Se por um morrer o outro nao acaba
Se v nao desce abaido de zero e s quando zero => parado e nao vira
Se v nao ultrapassa limite na macro
Se veiculos aleatorios
Se vidas = 0 => gameover
Se vira direita e esqurrda mas sem ultrapassar lites nem passar obstaculoa
Se vira so com pisca
Se muda estado para o estado correcto com eventos de mudança de estado
Se inputs espoletam os eventos desejados
