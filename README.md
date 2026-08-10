# Character List — Rick and Morty

Aplicativo Android que consome a [Rick and Morty API](https://rickandmortyapi.com/) e exibe uma lista de personagens (imagem, nome e última localização conhecida)
em uma `RecyclerView`. O consumo da API é assíncrono e mantém a interface responsiva durante a requisição.

## Descrição

Ao abrir, o aplicativo faz uma requisição a `GET /api/character` e apresenta os personagens retornados em uma lista rolável. Cada item mostra:

- A imagem do personagem (que é carregada a partir da URL retornada pela API);
- O nome;
- A última localização conhecida (`location.name`).

## Requisitos

| Ferramenta | Versão |
|---|---|
| Android Studio | Ladybug (2024.2) ou superior |
| JDK | 11 |
| SDK mínimo (`minSdk`) | 24 (Android 7.0) |
| SDK alvo (`targetSdk`) | 36 |
| Linguagem | Java |

### Dependências externas

- **Retrofit** (`com.squareup.retrofit2:retrofit`) — cliente HTTP declarativo para consumir a API REST.
- **Converter Gson** (`com.squareup.retrofit2:converter-gson`) — converte o JSON da resposta em objetos Java.
- **Glide** (`com.github.bumptech.glide:glide`) — carrega e exibe as imagens dos personagens a partir das suas respectivas URLs. Foi escolhioda após pesquisas,
  onde foi concluída ser a solução mais comum (em sistemas Androids) e rápida para o caso, cuidando de download em segundo plano, cache e reaproveitamento durante o scroll.
- **AndroidX Lifecycle (ViewModel + LiveData)** — gerenciamento de estado da tela com segurança de ciclo de vida.

Todas são gerenciadas via Gradle e baixadas automaticamente no Gradle Sync.

## Instruções de execução

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/Gustavoss150/CharacterList.git
   cd CharacterList
   ```

2. **Abrir o projeto**
   Abra o Android Studio em **Open** e selecione a pasta do projeto. Aguarde o Gradle Sync concluir (baixa as dependências na primeira vez).

3. **Executar**
   Escolha um dispositivo no seletor do topo e clique em **Run**.
   - **Emulador:** crie um dispositivo virtual em **Tools → Device Manager** (ex.: Pixel 7, API 34+) e execute.
   - **Dispositivo físico:** ative as Opções do desenvolvedor e a Depuração USB, onecte por cabo e selecione o aparelho.

   É necessário conexão com a internet para carregar a lista.

## Organização do projeto

O projeto segue o padrão **MVVM**, com separação clara entre interface e lógica.

```
Camada de modelo (model). Reúne os DTOs que espelham o JSON da API:
- Characters (o personagem);
- CharacterResponse (o envelope da listagem);
- Location;
- Episode. São preenchidos automaticamente pelo Gson na conversão da resposta.

Camada de rede (network). Responsável pelo acesso HTTP.
- CharacterAPI é a interface Retrofit que declara os endpoints;
- RetrofitClient configura o cliente (URL base e conversor Gson) e o disponibiliza para o restante da aplicação.

Camada de dados (repository).
- CharacterRepository executa as chamadas de rede de forma assíncrona e trata os resultados (sucesso e erro), repassando-os por meio de um contrato próprio;
- RepositoryCallback. Isso mantém as camadas superiores independentes do Retrofit.

Camada de estado (viewmodel).
- CharacterViewModel solicita os dados ao repositório e mantém o estado da tela em objetos LiveData (lista de personagens e mensagem de erro), sobrevivendo à recriação da Activity.

Camada de apresentação (ui e MainActivity).
- A MainActivity observa o LiveData e atualiza a interface quando os dados chegam;
- CharacterAdapter faz a ponte entre a lista de personagens e a RecyclerView, reaproveitando as linhas visíveis durante o scroll.

Fluxo resumido: a MainActivity dispara a busca no CharacterViewModel, que chama o CharacterRepository, que consome a API via Retrofit.
A resposta, convertida pelo Gson, retorna pelo RepositoryCallback ao ViewModel, que a publica no LiveData. A MainActivity, observando, recebe a lista e a entrega ao CharacterAdapter, que a exibe na RecyclerView.
```

### Separação entre interface e lógica

- **Interface:** `MainActivity`, `CharacterAdapter` e os layouts XML. Apenas exibem os dados e observam o estado; não decidem nada.
- **Lógica:** `CharacterViewModel` e `CharacterRepository`. O ViewModel guarda o estado (via `LiveData`) e sobrevive à recriação da tela (ex.: rotação); o Repository acessa a API e trata as respostas.

A `MainActivity` **observa** o `LiveData` do ViewModel e nunca busca dados diretamente — quando a lista chega, o observador é notificado e atualiza `RecyclerView`.

### Consumo da API (assincronia)

O consumo é feito com o método assíncrono do Retrofit (`enqueue`), que executa a requisição em uma thread de segundo plano. Isso mantém a *main thread* (a única
responsável por desenhar a interface) livre, evitando travamentos durante a requisição. Quando a resposta chega, o resultado é publicado no `LiveData` e a
tela é atualizada.

### Exibição na lista

A `RecyclerView` usa um `LinearLayoutManager` (lista vertical) e um
`CharacterAdapter`. O Adapter, por meio de um `ViewHolder`, reaproveita as
linhas visíveis durante o scroll (em vez de criar uma view por item), e para
cada linha preenche nome, localização e dispara o Glide para carregar a imagem.

## Decisões técnicas

- **MVVM com ViewModel + LiveData**, mantendo o estado fora da Activity e seguro quanto ao ciclo de vida.
- **Escopo focado nos dados já presentes em `character`.** A API expõe episódios e localizações como recursos separados (por URL), mas o nome da localização já vem embutido em cada personagem.
- **Modelos enxutos:** os DTOs declaram apenas os campos utilizados; o Gson ignora os demais campos do JSON automaticamente.

## Limitações conhecidas e melhorias futuras

Os itens abaixo foram deixados de fora do escopo da entrega por priorização de tempo, focando nos requisitos centrais do desafio (consumo de API + lista + UI responsiva). A base para eles já está preparada no código.

- **Tela de detalhe do personagem.** A `CharacterAPI` e o `CharacterRepository` já incluem o método `getEpisode(url)`, que busca o nome do primeiro episódio
  ("First seen in") a partir da URL contida no personagem. A tela de detalhe que consumiria esse método (ao tocar em um item) não foi construída nesta entrega.
  - A ideia era a seguinte: criar um DTO de detalhe, montado a partir dos dados que a API já traz no personagem mais a busca do episódio. (há um método getEpisode() no repository, mas que não cheguei a implementar no final)

- **Exibição de status e gênero com indicador colorido.** O plano era exibir o`status` do personagem (Alive / Dead / Unknown) com um indicador de cor, como
  no site oficial, modelando o status como `enum`. A modelagem em Java é direta. O que faltou foi tempo para estudar a implementação na camada visual. Ficou como
  melhoria futura para não comprometer a conclusão dos requisitos principais.

- **Estados de carregamento e erro na UI.** O `CharacterViewModel` já expõe um `LiveData` de erro (`getErro()`), mas a tela ainda não apresenta um indicador
  de carregamento (spinner) nem uma mensagem visual de falha de rede. O tratamento de erro existe na lógica; falta a apresentação.

- **Tratamento de `location` nula.** Em casos raros, um personagem pode retornar `location` sem nome; a exibição assume que o campo está presente.
