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
`ip:port/sigaachave/reserva?id={value}`

POST - Adiciona reserva  
`ip:port/sigaachave/reserva/adicionar?sala={value}&dataConsulta={value}&horaConsulta={value}&isFixa={value}`

PUT - Atualiza reserva {id}  
`ip:port/sigaachave/reserva/atualizar?id={value}&sala={value}&dataConsulta={value}&horaConsulta={value}&isFixa={value}`

DELETE - Exclui sala {id}  
`ip:port/sigaachave/reserva/excluir?id={value}`

GET - Retorna todas reservas {status}  
`ip:port/sigaachave/reservas/status?status={value}`

GET - Retorna todas reservas {idUsuario}  
`ip:port/sigaachave/reservas/usuario?idUsuario={idUsuario}`

GET - Retorna todas reservas {data}  
`ip:port/sigaachave/reservas/status?data={value}`

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


#### Chamado

GET - Retorna todos os chamados
`ip:port/sigaachave/chamados`

GET - Retorna chamado por {id}
`ip:port/sigaachave/chamado?id={value}`

POST - Adiciona chamado
`ip:port/sigaachave/chamado/adicionar?idUsuario={value}&sala={value}&descricao={value}`

PUT - Atualiza chamado {id}
`ip:port/sigaachave/chamado?id={value}&sala={value}&descricao={value}`

DELETE - Exclui chamado {id}
`ip:port/sigaachave/chamado/excluir?id={value}`

GET - Retorna chamados por {status}
`ip:port/sigaachave/chamados/status?status={value}`
