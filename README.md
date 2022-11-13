# HearthStone

Escalabilidade como principal desafio

Problemas:
1. API sem paginação pode ser um gargalo para o usuário, uma vez que os dados são volumosos e a experiência possa vir a ser desagradável.
2. Os dados esperados na tela de detalhe são dados que não possuem em todas as cartas esperadas, será necessário então fazer um filtro das mesmas.
2.1. Tela de detalhes - Dados esperados 
  A imagem principal do cartão
  Nome
  Descrição "flavor"
  Descrição curta
  Set pertencente
  Tipo
  Facção
  Raridade
  Ataque
  Custo
  Health

A não paginação e a recorrência de informações influenciam em alguns problemas grandes arquiteturais
