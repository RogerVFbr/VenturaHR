SET @TIME_NOW = DATEADD(MINUTE, -2, CURRENT_TIMESTAMP());

DROP TABLE IF EXISTS scenes;
CREATE TABLE scenes
(
    scene_id          INT AUTO_INCREMENT PRIMARY KEY,
    title             VARCHAR(250)                                           NOT NULL,
    scene_update_time TIMESTAMP                                              NOT NULL,
    date_modified     TIMESTAMP                                              NOT NULL,
    current_status    ENUM ('pendente', 'preparada', 'gravada', 'pendurada') NOT NULL
);
INSERT INTO scenes (scene_id, title, scene_update_time, date_modified, current_status)
VALUES (1, 'Fatima encontra Otto', @TIME_NOW, @TIME_NOW, 'pendente'),
       (2, 'Festa no bar de Zica', @TIME_NOW, @TIME_NOW, 'pendente'),
       (3, 'Fernando e Leila', @TIME_NOW, @TIME_NOW, 'pendente'),
       (4, 'Barraca de cachorro quente', @TIME_NOW, @TIME_NOW, 'pendente'),
       (5, 'Aniversario no feriado', @TIME_NOW, @TIME_NOW, 'pendente'),
       (6, 'Briga Iris e Marcia', @TIME_NOW, @TIME_NOW, 'pendente'),
       (7, 'Encontro em Madureira', @TIME_NOW, @TIME_NOW, 'pendente'),
       (8, 'Familia Pereira se reencontra', @TIME_NOW, @TIME_NOW, 'pendente'),
       (9, 'Troca de postos', @TIME_NOW, @TIME_NOW, 'pendente'),
       (10, 'Democracia aos frangalhos', @TIME_NOW, @TIME_NOW, 'pendente');

DROP TABLE IF EXISTS scenes_log;
CREATE TABLE scenes_log
(
    log_id            INT AUTO_INCREMENT PRIMARY KEY,
    scene_id          INT                                                    NOT NULL,
    title             VARCHAR(250)                                           NOT NULL,
    scene_update_time TIMESTAMP                                              NOT NULL,
    date_modified     TIMESTAMP                                              NOT NULL,
    current_status    ENUM ('pendente', 'preparada', 'gravada', 'pendurada') NOT NULL
);
INSERT INTO scenes_log (log_id, scene_id, title, scene_update_time, date_modified, current_status)
VALUES (1, 1, 'Fátima encontra Otto', @TIME_NOW, @TIME_NOW, 'pendente'),
       (2, 2, 'Festa no bar de Zica', @TIME_NOW, @TIME_NOW, 'pendente'),
       (3, 3, 'Acidente em Cachoeiro', @TIME_NOW, @TIME_NOW, 'pendente'),
       (4, 4, 'Barraca de cachorro quente', @TIME_NOW, @TIME_NOW, 'pendente'),
       (5, 5, 'Aniversario no feriado', @TIME_NOW, @TIME_NOW, 'pendente'),
       (6, 6, 'Briga Iris e Marcia', @TIME_NOW, @TIME_NOW, 'pendente'),
       (7, 7, 'Encontro em Madureira', @TIME_NOW, @TIME_NOW, 'pendente'),
       (8, 8, 'Familia Pereira se reencontra', @TIME_NOW, @TIME_NOW, 'pendente'),
       (9, 9, 'Troca de postos', @TIME_NOW, @TIME_NOW, 'pendente'),
       (10, 10, 'Democracia aos frangalhos', @TIME_NOW, @TIME_NOW, 'pendente');

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(250)                                  NOT NULL,
    document_id   VARCHAR(250)                                  NOT NULL,
    type          ENUM ('candidato', 'empresa', 'admnistrador') NOT NULL,
    date_created  TIMESTAMP                                              DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO users (id, name, document_id, type)
VALUES (1, 'Eliodoro Gonçalves Fonseca', '09889009', 'candidato'),
       (2, 'Francisa Alves', '3654464', 'candidato'),
       (3, 'Rômulo da Silva', '786989769', 'candidato'),
       (4, 'Carolina Braga', '123421234', 'candidato'),
       (5, 'Paulo Barbosa', '8970808', 'candidato'),
       (6, 'Santana Óleo e Gás', '3425555', 'empresa'),
       (7, 'SmartTrends', '56867865878', 'empresa'),
       (8, 'B2Y Retail', '45635643564', 'empresa'),
       (9, 'Hans Monen', '789679879', 'admnistrador'),
       (10, 'Leandro Medina', '456745675467', 'admnistrador');

DROP TABLE IF EXISTS vagas;
CREATE TABLE vagas
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    owner_id         INT                          NOT NULL,
    shortDescription VARCHAR(250)                 NOT NULL,
    longDescription  VARCHAR(250)                 NOT NULL,
    status           ENUM ('aberto', 'encerrado') NOT NULL,
    date_created     TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified    TIMESTAMP                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO vagas (id, owner_id, shortDescription, longDescription, status)
VALUES (1, 6, 'Gerente de Projetos Sênior', 'MockLongDescription', 'aberto'),
       (2, 6, 'Engenheiro de Óleo e Gás', 'MockLongDescription', 'aberto'),
       (3, 6, 'Recepcionista', 'MockLongDescription', 'encerrado'),
       (4, 7, 'Telemarketing', 'MockLongDescription', 'aberto'),
       (5, 7, 'Desenvolvedor Front-End', 'MockLongDescription', 'encerrado'),
       (6, 7, 'Analista DevOps', 'MockLongDescription', 'aberto'),
       (7, 8, 'Atendimento ao cliente', 'MockLongDescription', 'aberto'),
       (8, 8, 'Executivo financeiro', 'MockLongDescription', 'aberto'),
       (9, 9, 'Motorista', 'MockLongDescription', 'encerrado');

DROP TABLE IF EXISTS resposta;
CREATE TABLE resposta
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id          INT                          NOT NULL,
    candidato_id     INT                          NOT NULL,
    curriculo_url    VARCHAR(250)                 NOT NULL,
    text_content     VARCHAR(250)                 NOT NULL,
    date_created     TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified    TIMESTAMP                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO resposta (id, vaga_id, candidato_id, curriculo_url, text_content)
VALUES (1, 1, 1, 'MockCurriculumUrl', 'MockTextContent'),
       (2, 2, 2, 'MockCurriculumUrl', 'MockTextContent'),
       (3, 3, 4, 'MockCurriculumUrl', 'MockTextContent'),
       (4, 4, 4, 'MockCurriculumUrl', 'MockTextContent'),
       (5, 5, 5, 'MockCurriculumUrl', 'MockTextContent'),
       (6, 6, 1, 'MockCurriculumUrl', 'MockTextContent'),
       (7, 7, 2, 'MockCurriculumUrl', 'MockTextContent'),
       (8, 8, 3, 'MockCurriculumUrl', 'MockTextContent'),
       (9, 9, 4, 'MockCurriculumUrl', 'MockTextContent');



