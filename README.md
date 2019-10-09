# SigaAChave

## Urls Suportadas

#### Usuário

GET - Retorna todos usuários  
`ip:port/sigaachave/usuarios`

GET - Retorna usuario {id}  
`ip:port/sigaachave/usuario?id={value}`

POST - Adiciona usuario  
`ip:port/sigaachave/usuario/adicionar?nome={value}&cpf={value}&senha={value}&papel={value}`

PUT - Atualiza usuario {id}  
`ip:port/sigaachave/usuario/atualizar?id={value}&nome={value}&cpf={value}&senha={value}&papel={value}`

DELETE - Exclui usuario {id}  
`ip:port/sigaachave/usuario/excluir?id={value}`

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

#### Sala

GET - Retorna todas as salas  
`ip:port/sigaachave/salas`

GET - Retorna sala por {id}  
`ip:port/sigaachave/sala?id={value}`

POST - Adiciona sala  
`ip:port/sigaachave/sala/adicionar?nome={value}&localizacao={value}&descricao={value}&permiteFixo={value}`

PUT - Atualiza sala {id}  
`ip:port/sigaachave/sala/atualizar?id={value}&nome={value}&localizacao={value}&descricao={value}&permiteFixo={value}`

DELETE - Exclui sala {id}  
`ip:port/sigaachave/sala/excluir?id={value}`
