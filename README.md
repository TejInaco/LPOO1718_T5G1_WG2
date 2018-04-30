A segunda parte do projecto incide sobre um jogo onde o user controlará um carro numa estrada, onde evita colisões com outros
carros ou outros objectos numa estrada com 3 vias. Quanto mais rápido percorrer a via maior sem colisões maior é o seu score. 
O jogo será em 2D com top view.

## Architecture Design

  #### Package and UML
  
  O projecto utilizará a framework libGDX com foco no desenvolvimento em AndroidSDK e em Desktop.Utilizirá o package Physics
  Utilizou-se o diagram UML proposto pelo Prof André Restivo, onde se pretende acrescentar as classes especificas do jogo.
  
  // ![converageJunit](converageJunit.png)
 »diagram do uml ... Inserir as imagens no repositorio 
  
  
  #### Design of behavioural aspects (Maquina de Estados)
//Maquinas de estados

»os desenhos das maquinas de estado

  #### Expected Design Patterns 
Esperamos implentar o MVC com a seguinte estruturação:

»por aqui a imagem do controller, mdel, view

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
  
