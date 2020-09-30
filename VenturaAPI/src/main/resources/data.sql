SET @TIME_IN_15_DAYS = DATEADD(DAY, +15, CURRENT_TIMESTAMP());
SET @TIME_YESTERDAY = DATEADD(DAY, -1, CURRENT_TIMESTAMP());

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(250)                                   NOT NULL,
    address       VARCHAR(250)                                   NOT NULL,
    phone_number  VARCHAR(250)                                   NOT NULL,
    document_id   VARCHAR(250)                                   NOT NULL,
    razao_social  VARCHAR(250)                                   NULL,
    email         VARCHAR(250)                                   NOT NULL,
    password      VARCHAR(250)                                   NOT NULL,
    type          ENUM ('candidato', 'empresa', 'administrador') NOT NULL,
    date_created  TIMESTAMP                                               DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO users (id, name, address, phone_number, document_id, razao_social, email, password,
                   type)
VALUES (1, 'Eliodoro Gonçalves Fonseca', 'Rua do Carmo, 305/256 - Botafogo - Rio de Janeiro',
        '11911111111', '09889009', '', 'eliodoro@fonseca.com', '123456', 'candidato'),
       (2, 'Francisa Alves', 'Av. Belarmindo Gomes, 400 sala B - Caxias', '11911111111', '3654464',
        '', 'franciscas@alves.com', '123456', 'candidato'),
       (3, 'Rômulo da Silva', 'Travessa Roque Santos, 286 casa 1 - São Pedro da Aldeia',
        '11911111111', '786989769', '', 'romulo@silva.com', '123456', 'candidato'),
       (4, 'Carolina Braga', 'Rua Raquel Matos, 444/304 - Centro - Rio de Janeiro', '11911111111',
        '123421234', '', 'carolina@braga.com', '123456', 'candidato'),
       (5, 'Paulo Barbosa', 'Alameda Santos, 288 casa 10 - Pinheiros - São Paulo', '11911111111',
        '8970808', '', 'paulo@barbosa.com', '123456', 'candidato'),
       (6, 'Santana Óleo e Gás', 'Av. Passos , 2222 - Centro - Rio de Janeiro', '11911111111',
        '3425555', 'Santana LTDA', 'rh@santanaoleoeas.com', '123456', 'empresa'),
       (7, 'SmartTrends', 'Av. Galega Silva, 88 - Leblon - Rio de Janeiro', '11911111111',
        '56867865878', 'SmartTrends LTDA', 'rh@smarttrends.com', '123456', 'empresa'),
       (8, 'B2Y Retail', 'Alameda Garrido, 77 - Belo Horizonte', '11911111111', '45635643564',
        'B2Y Retail LTDA', 'rh@2yretail.com', '123456', 'empresa'),
       (9, 'Hans Monen', 'Avenida Atlantica, 22/301 - Copacabana - Rio de Janeiro', '11911111111',
        '789679879', '', 'hans.monen@venturarh.com', '123456', 'administrador'),
       (10, 'Leandro Medina', 'Rua Andrade Rocha, 678/301 - Jardins - São Paulo', '11911111111',
        '456745675467', '', 'leandro.medina@venturarh.com', '123456',
        'administrador');

DROP TABLE IF EXISTS vagas CASCADE;
CREATE TABLE vagas
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    owner_id          INT                NOT NULL,
    short_description VARCHAR(250)       NOT NULL,
    long_description  VARCHAR(250)       NOT NULL,
    city              VARCHAR(250)       NOT NULL,
    state             VARCHAR(250)       NOT NULL,
    type              ENUM ('CLT', 'PJ') NOT NULL,
    time_span         VARCHAR(250)       NOT NULL,
    date_expiration   TIMESTAMP          NOT NULL,
    date_created      TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified     TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO vagas (id, owner_id, short_description, long_description, city, state, type, time_span,
                   date_expiration)
VALUES (1, 6, 'Gerente de Projetos Sênior', 'MockLongDescription', 'Rio de Janeiro', 'RJ', 'CLT',
        '6 meses',
        @TIME_IN_15_DAYS),
       (2, 6, 'Engenheiro de Óleo e Gás', 'MockLongDescription', 'Rio de Janeiro', 'RJ', 'PJ',
        'Permanente',
        @TIME_IN_15_DAYS),
       (3, 6, 'Recepcionista', 'MockLongDescription', 'Vitória', 'ES', 'CLT', '1 ano',
        @TIME_YESTERDAY),
       (4, 7, 'Telemarketing', 'MockLongDescription', 'São Paulo', 'SP', 'PJ', '2 meses',
        @TIME_IN_15_DAYS),
       (5, 7, 'Desenvolvedor Front-End', 'MockLongDescription', 'São Paulo', 'SP', 'CLT',
        'Permanente', @TIME_IN_15_DAYS),
       (6, 7, 'Analista DevOps', 'MockLongDescription', 'Belo Horizonte', 'MG', 'PJ', 'Permanente',
        @TIME_YESTERDAY),
       (7, 8, 'Atendimento ao cliente', 'MockLongDescription', 'Recife', 'PE', 'CLT', '6 meses',
        @TIME_IN_15_DAYS),
       (8, 8, 'Executivo financeiro', 'MockLongDescription', 'Fortaleza', 'CE', 'PJ', '6 meses',
        @TIME_YESTERDAY),
       (9, 9, 'Motorista', 'MockLongDescription', 'Belo Horizonte', 'MG', 'CLT', 'Permanente',
        @TIME_IN_15_DAYS);

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
       (3, 3, 1, 'https://www.mockdomain.com/MockCurriculumUrl3', 'MockTextContent3'),
       (4, 4, 4, 'https://www.mockdomain.com/MockCurriculumUrl4', 'MockTextContent4'),
       (5, 5, 5, 'https://www.mockdomain.com/MockCurriculumUrl5', 'MockTextContent5'),
       (6, 6, 1, 'https://www.mockdomain.com/MockCurriculumUrl1', 'MockTextContent6'),
       (7, 7, 2, 'https://www.mockdomain.com/MockCurriculumUrl2', 'MockTextContent7'),
       (8, 8, 1, 'https://www.mockdomain.com/MockCurriculumUrl3', 'MockTextContent8'),
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

DROP TABLE IF EXISTS vaga_criterios;
CREATE TABLE vaga_criterios
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id       INT                                                          NOT NULL,
    name          VARCHAR(250)                                                 NOT NULL,
    description   VARCHAR(250)                                                 NOT NULL,
    pmd           ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    weight        ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    position      INT                                                          NOT NULL,
    date_created  TIMESTAMP                                                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO vaga_criterios (id, vaga_id, name, description, pmd, weight, position)
VALUES (1, 1, 'MockCriteriaName01', 'MockDescription01', 'baixo', 'medio', 0),
       (2, 1, 'MockCriteriaName02', 'MockDescription02', 'medio', 'muito alto', 1),
       (3, 1, 'MockCriteriaName03', 'MockDescription03', 'muito baixo', 'muito baixo', 2);



