SET @TIME_IN_15_DAYS = DATEADD(DAY, +15, CURRENT_TIMESTAMP());
SET @TIME_YESTERDAY = DATEADD(DAY, -1, CURRENT_TIMESTAMP());

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(250)                                   NOT NULL,
    document_id   VARCHAR(250)                                   NOT NULL,
    email         VARCHAR(250)                                   NOT NULL,
    password      VARCHAR(250)                                   NOT NULL,
    type          ENUM ('candidato', 'empresa', 'administrador') NOT NULL,
    date_created  TIMESTAMP                                               DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO users (id, name, document_id, email, password, type)
VALUES (1, 'Eliodoro Gonçalves Fonseca', '09889009', 'eliodoro@fonseca.com', '123456', 'candidato'),
       (2, 'Francisa Alves', '3654464', 'franciscas@alves.com', '123456', 'candidato'),
       (3, 'Rômulo da Silva', '786989769', 'romulo@silva.com', '123456', 'candidato'),
       (4, 'Carolina Braga', '123421234', 'carolina@braga.com', '123456', 'candidato'),
       (5, 'Paulo Barbosa', '8970808', 'paulo@barbosa.com', '123456', 'candidato'),
       (6, 'Santana Óleo e Gás', '3425555', 'rh@santanaoleoeas.com', '123456', 'empresa'),
       (7, 'SmartTrends', '56867865878', 'rh@smarttrends.com', '123456', 'empresa'),
       (8, 'B2Y Retail', '45635643564', 'rh@2yretail.com', '123456', 'empresa'),
       (9, 'Hans Monen', '789679879', 'hans.monen@venturarh.com', '123456', 'administrador'),
       (10, 'Leandro Medina', '456745675467', 'leandro.medina@venturarh.com', '123456',
        'administrador');

DROP TABLE IF EXISTS vagas CASCADE;
CREATE TABLE vagas
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    owner_id          INT          NOT NULL,
    short_description VARCHAR(250) NOT NULL,
    long_description  VARCHAR(250) NOT NULL,
    location          VARCHAR(250) NOT NULL,
    date_expiration   TIMESTAMP    NOT NULL,
    date_created      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO vagas (id, owner_id, short_description, long_description, location, date_expiration)
VALUES (1, 6, 'Gerente de Projetos Sênior', 'MockLongDescription', 'Rio de Janeiro', @TIME_IN_15_DAYS),
       (2, 6, 'Engenheiro de Óleo e Gás', 'MockLongDescription', 'Rio de Janeiro', @TIME_IN_15_DAYS),
       (3, 6, 'Recepcionista', 'MockLongDescription', 'Vitória', @TIME_YESTERDAY),
       (4, 7, 'Telemarketing', 'MockLongDescription', 'São Paulo', @TIME_IN_15_DAYS),
       (5, 7, 'Desenvolvedor Front-End', 'MockLongDescription', 'São Paulo', @TIME_IN_15_DAYS),
       (6, 7, 'Analista DevOps', 'MockLongDescription', 'Belo Horizonte', @TIME_YESTERDAY),
       (7, 8, 'Atendimento ao cliente', 'MockLongDescription', 'Recife', @TIME_IN_15_DAYS),
       (8, 8, 'Executivo financeiro', 'MockLongDescription', 'Fortaleza', @TIME_YESTERDAY),
       (9, 9, 'Motorista', 'MockLongDescription', 'Belo Horizonte', @TIME_IN_15_DAYS);

DROP TABLE IF EXISTS respostas CASCADE;
CREATE TABLE respostas
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id       INT          NOT NULL,
    candidato_id  INT          NOT NULL,
    curriculo_url VARCHAR(250) NOT NULL,
    text_content  VARCHAR(250) NOT NULL,
    date_created  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO respostas (id, vaga_id, candidato_id, curriculo_url, text_content)
VALUES (1, 1, 1, 'https://www.mockdomain.com/MockCurriculumUrl1', 'MockTextContent1'),
       (2, 2, 2, 'https://www.mockdomain.com/MockCurriculumUrl2', 'MockTextContent2'),
       (3, 3, 4, 'https://www.mockdomain.com/MockCurriculumUrl3', 'MockTextContent3'),
       (4, 4, 4, 'https://www.mockdomain.com/MockCurriculumUrl4', 'MockTextContent4'),
       (5, 5, 5, 'https://www.mockdomain.com/MockCurriculumUrl5', 'MockTextContent5'),
       (6, 6, 1, 'https://www.mockdomain.com/MockCurriculumUrl1', 'MockTextContent6'),
       (7, 7, 2, 'https://www.mockdomain.com/MockCurriculumUrl2', 'MockTextContent7'),
       (8, 8, 3, 'https://www.mockdomain.com/MockCurriculumUrl3', 'MockTextContent8'),
       (9, 9, 4, 'https://www.mockdomain.com/MockCurriculumUrl4', 'MockTextContent9');

DROP TABLE IF EXISTS resposta_criterio;
CREATE TABLE resposta_criterio
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    criterio_id   INT                                                          NOT NULL,
    resposta_id   INT                                                          NOT NULL,
    level         ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    date_created  TIMESTAMP                                                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO resposta_criterio (id, criterio_id, resposta_id, level)
VALUES (1, 1, 1, 'baixo'),
       (2, 2, 1, 'medio'),
       (3, 3, 1, 'alto');

DROP TABLE IF EXISTS criterio;
CREATE TABLE criterio
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id       INT                                                          NOT NULL,
    name          VARCHAR(250)                                                 NOT NULL,
    description   VARCHAR(250)                                                 NOT NULL,
    pmd           ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    weight        ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    date_created  TIMESTAMP                                                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO criterio (id, vaga_id, name, description, pmd, weight)
VALUES (1, 1, 'MockCriteriaName01', 'MockDescription01', 'baixo', 'medio'),
       (2, 1, 'MockCriteriaName02', 'MockDescription02', 'medio', 'muito alto'),
       (3, 1, 'MockCriteriaName03', 'MockDescription03', 'muito baixo', 'muito baixo');



