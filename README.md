@finalRelease

Platform -specific executable files
-Java for multiple platforms
-Apk for Android

javadoc documentation files
Setup installation procedure
-> how to install de developemeetn enviroment
-> how to install/run the game/app
Updated UML diagrams
Diagrama de classes
(rascunho feito)
Diagrama de Estados
(rascunho feito)
Diagrama de Sequências

## Architecture Design

  #### Package and UML
    
  O projecto utilizará a framework libGDX com foco no desenvolvimento em AndroidSDK e em Desktop.Utilizirá o package Physics
  Utilizou-se o diagram UML proposto pelo Prof André Restivo, onde se pretende acrescentar as classes especificas do jogo.
  
  ![UML](UMLvelesc.png)
 
  
  
  #### Design of behavioural aspects (Maquina de Estados)

  ![State Machine](stateMachine.png)


 ##  Design Patterns usados
 
 ### Flyweight
 Tendo em conta o uso de texturas no jogo, e sendo que muitas das  texturas são reusadas em diferentes locais, o Design Pattern FLyweight é implementado através da nossa classe Assets, que tem como resposabilidade o load das texturas usadas no jogo e o seu dispose(). 
 Na implementação da classe Física para o comportamento dos objectos também recorremosao flyweight, pois a maior parte das variáveis, como por exemplo, fricção, restituição, densidade, velocidade máxima e forças são partilhadas por diversos objectos.
 
### Update Method
Na maior parte dos objectos implementa-se o método update(delta) para simular um frame do comportamento de objectos independentes. A ideia consiste no jogo fazer o update() em toda a rede de objectos.

### Component
Em continuação da ideia anterior do Update Method, o Design Pattern foi utilizado na class CarroControlado em que foi definida uma classe InputHandler, para controlo do CarroControlado quer para Desktop quer para Android.

 
## GUI Design

   ### Main functionalities
   
   Haverá um interface com o menu inicial onde se pode iniciar o jogo, ver scores, e escolher o modo de jogo.
   O controlo do carro é definido com as teclas, UP, DOWN, LEFT e RIGHT para o controlo de velocidade e de direcções     respectivamente. No Android este controlo é efectuado através do giroscópio que detectará as inclinações do dispositivo.
 
  ### GUI mock-ups
  
  Foi utilizado o sofware Tiled para gerar o mapa do jogo e os objectos estáticos dentro do mapa.
  
  ## User Manual
  
  Ao iniciar o jogo é visualizado o ecrã de boas-vindas:
  
  ![Ecra de Boas Vindas](firstScreen.png)
  
  Pressionar "enter" ou "clickar" no rato, e será visualizado o Menu de opções:
  
  ![Menu](SecondScreen.png)
  
  Existem três opções: Play, Exit e Scores.No play iniciaremos o jogo, e no exit encerrá a aplicação. Os scores mostram as últimas pontuações activas.
  No caso de user seleccionar Play, iniciará o jogo mostrando a seguinte imagem:
  
  ![Menu](gameScreen.png)
  
  O user terá controlo do carro apresentado no canto inferior esquerdo. O controlo será feito exclusivamente com as setas UP, para aumentar a velocidade até a um limite máximo de velocidade, o LEFT para o carro se deslocar para a esquerda, o RIGHT para o carro se deslocar para a direita, e o DOWN para travar e parar o carro completamente.
  O objectivo será completar o troço da estrada o mais rápido possível evitando colisões com outros carros que aparecerão na estrada. Caso haja colisão o jogo acabará em Game Over. Em cada nível, a velocidade máxima aumentará tornando todo o jogo mais rápido.
  
  ## Junit Test Cases
  
  Apesar de termos recorrido  a diversos links fomos infrutíferos na implementação dos testes unitários.
  Na nossa tentativa, seguimos as recomendações deste reposítório:
  https://github.com/TomGrill/gdx-testing
  
  como também tentamos mudar as configurações sugeridas nestas webpages:
  http://manabreak.eu/java/2016/10/21/unittesting-libgdx.html
  https://staticdot.com/howto/junit-libgdx
 
## Work distribution

Numa fase inicial, do desenvolvimento da ideia e e da implementação das primeiras linhas de código, a distribuição de trabalho foi feita do modo pair programming. Na fase intermédia e final, ambos fomos decidindo e resolvendo os bugs de forma prioritária.
Portanto, consideramos a distribuição do trabalho igualitária entre os membros. 

## Major Difficulties

## Lessons Learned

## Overrall time spent developing 

## Design decisions
