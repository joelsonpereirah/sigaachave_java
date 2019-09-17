# SigaAChave

## Urls Suportadas

#### Usuário

GET - Retorna todos usuários  
`ip:port/sigaachave/usuarios`

GET - Retorna usuario {id}  
`ip:port/sigaachave/usuarios/{id}`

GET - Retornar reservas do Usuario {id}  
`ip:port/sigaachave/usuarios/{id}/reservas`

POST - Adiciona usuario  
`ip:port/sigaachave/usuarios/adicionar/{nome}+{senha}+{papel}`

PUT - Atualiza usuario {id}  
`ip:port/sigaachave/usuarios/{id}/atualizar/{nome}+{senha}+{papel}`

DELETE - Exclui usuario {id}  
`ip:port/sigaachave/usuarios/{id}/excluir`

#### Reservas

GET - Retorna todas reservas  
`ip:port/sigaachave/reservas`

GET - Retorna reserva {id}  
`ip:port/sigaachave/reservas/{id}`

POST - Adiciona reserva  
`ip:port/sigaachave/reservas/adicionar/{sala}+{data}+{isFixo}`

PUT - Atualiza reserva {id}  
`ip:port/sigaachave/reservas/{id}/atualizar/{sala}+{data}+{status}`

PUT - Confirmar reserva pendente {id}  
`ip:port/sigaachave/reservas/status/{id}`

#### Salas

GET - Retorna todas as salas  
`ip:port/sigaachave/salas`

GET - Retorna sala por {id}  
`ip:port/sigaachave/salas/{id}`

POST - Adiciona sala  
`ip:port/sigaachave/salas/adicionar/{nome}+{localizacao}+{descricao}+{permiteFixar}`

PUT - Atualiza sala {id}  
`ip:port/sigaachave/salas/{id}/atualizar/{nome}+{descricao}+{localizacao}+{permiteFixo}`

DELETE - Exclui sala {id}  
`ip:port/sigaachave/salas/{id}/excluir`
