# Gerenciador-tarefas-desafio-API-REST
Criando uma API de gerenciamento de tarefas

- Pessoa (id, nome, departamento e  lista de tarefas)
- Tarefa (id, título, descrição, prazo, departamento, duração, id_pessoa e se já foi finalizado)
- Framework: SprintBoot
- Bancos de dados: PostgreSQL

## Funcionalidades:

- Adicionar pessoa (post/pessoas)

- Alterar pessoa (put/pessoas/{id})

- Remover pessoa (delete/pessoas/{id})

- Adicionar tarefa (post/tarefas)

- Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})

- Finalizar tarefa (put/tarefas/finalizar/{id})

- Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)

- Buscar pessoas por nome, retorna média de horas gastas por tarefa. (get/pessoas/gastos)

- Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)

- Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
