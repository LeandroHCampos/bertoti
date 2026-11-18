# Contribuições Individuais

Minhas contribuições ao projeto [**devops-agent**](https://github.com/AtlazDB/devops-agent), um agente que analisa falhas de workflows do GitHub Actions com um LLM local.

| Commit | Data | O que foi feito | Padrões |
| :--- | :--- | :--- | :--- |
| `1817464` | 17/08/2026 | Criação do projeto Spring Boot e do pipeline de CI no GitHub Actions. | — |
| `e2852d4` | 17/08/2026 | Permissão de execução no Maven Wrapper (`mvnw`) para o CI rodar. | — |
| `19c0c47` | 17/08/2026 | Integração com a API do GitHub para listar os workflow runs. | Builder, Singleton/DI |
| `a776eb9` | 15/09/2026 | Revisão e merge do PR #6 (State e Strategy do agente). | — |

---

## Estrutura inicial e CI — `1817464`, `e2852d4`

Criei o projeto base (Spring Boot, Java 17, Maven) e o workflow `.github/workflows/ci.yml`, que compila o projeto a cada `push` e `pull_request` na `main`:

```yaml
steps:
  - uses: actions/checkout@v4
  - uses: actions/setup-java@v4
    with:
      java-version: '17'
      distribution: 'temurin'
      cache: maven
  - run: ./mvnw clean compile
```

Em seguida dei permissão de execução ao `mvnw`, sem a qual o passo de compilação falhava no runner Linux.

## Integração com o GitHub — `19c0c47`

**Classes:** `GitHubConfig`, `GitHubService`, `WorkflowRun`, `WorkflowRunsResponse`

- `GitHubConfig`: record com `@ConfigurationProperties(prefix = "github")` que lê token, organização e repositório de variáveis de ambiente (`GITHUB_TOKEN`, etc.), sem credenciais no código.
- `GitHubService`: monta um `RestClient` autenticado e consulta `GET /repos/{org}/{repo}/actions/runs`.
- `WorkflowRun` / `WorkflowRunsResponse`: records que mapeiam a resposta JSON da API.
- `CommandLineRunner` na aplicação que imprime os runs ao subir, para validar a integração.

```java
this.restClient = RestClient.builder()
        .baseUrl("https://api.github.com")
        .defaultHeader("Authorization", "Bearer " + config.token())
        .defaultHeader("Accept", "application/vnd.github+json")
        .build();
```

- **Builder:** o `RestClient` é configurado passo a passo e criado com `build()`.
- **Singleton / Injeção de Dependência:** `GitHubService` é um bean único do Spring (`@Service`) que recebe o `GitHubConfig` pelo construtor.

Essa base foi depois evoluída pelos colegas (renomeada para `GitHubClient`, consulta de jobs e logs, integração com o LLM).
